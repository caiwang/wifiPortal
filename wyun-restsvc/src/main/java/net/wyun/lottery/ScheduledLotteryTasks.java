/**
 * 
 */
package net.wyun.lottery;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;

import net.wyun.rest.wlsp.Application;
import net.wyun.rest.wlsp.client.impl.AuthSmsClient;
import net.wyun.rest.wlsp.repository.AuthSms;
import net.wyun.rest.wlsp.repository.ProdOrder;
import net.wyun.rest.wlsp.repository.ProdOrderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Xuecheng
 * The database operations reference: http://info.michael-simons.eu/2014/02/25/boot-your-application-with-spring-boot/
 * 
 */
@Component
@EnableScheduling
public class ScheduledLotteryTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledLotteryTasks.class);
	private final static int QSIZE = 40;
	private BlockingQueue<ProdOrder> orderQ = new ArrayBlockingQueue<ProdOrder>(QSIZE);
	private Set<String> usedBiaoShiMa = new HashSet<String>(); // buffer for
																// those
																// BiaoShiMa
																// already saved
																// in database

	@Autowired
	private ProdOrderRepository orders;
	
	@Autowired
	CaiPiaoHandler handler;
	
	@Autowired
	private AuthSmsClient smsClient;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm:ss");

	@Value("${wlsp.lottery.name}")
	private String myServer;

	/*
	 * add a new ProdOrder to the Q
	 */
	public void addProdOrder(ProdOrder v) {
		orderQ.offer(v);
	}

	@Scheduled(fixedDelayString = "${wlsp.lottery.delay}")
	public void matchLotteryToOrder() {
		
		logger.info("\n### [Lottery Scheduler] on "
				+ dateFormat.format(new Date()));
		long time = System.currentTimeMillis();
		
		// process lottery here
		try {
			
			List<CaiPiao> cps = handler.getActiveCaiPiaos();
			for(CaiPiao cp:cps){
				//new PrintStream(System.out, true, "UTF-8").println(cp.toString());
				String bzm = cp.getBiaoZhiMa();
				if(!this.usedBiaoShiMa.contains(bzm)){
					//new addition of the caipiao
					ProdOrder po = orderQ.poll();
					if(po != null){
						po.setProdspec(cp.getNumber());
						po.setDelicode(bzm);
						po.setDelidesp(cp.getPublishTime());
						po.setDelimemo(cp.getPeriods());
						po.setUpdtime(new Date());
						ProdOrder updatedOrder = orders.save(po);
						logger.info("match caipiao: " + bzm + " with order " + cp.getOrderId());
						usedBiaoShiMa.add(bzm);
						// send sms
						// sms to customer or user
						AuthSms as2 = this.generateUserAuthSms(updatedOrder);
						smsClient.addAuthSms(as2);
						
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("lottery scheduler", e);
		}

		logger.info("order Queue size: " + orderQ.size());

		System.out.println(" ...ok [" + (System.currentTimeMillis() - time)
				+ " ms]");
	}
	
	private static String O_Prefix = "双色球";
	private static String U_Prefix = "福彩双色球,";
	private static String U_Midfix = " 标志码: ";
	private static String U_Postfix = "已收到订单，下单成功后发送标志码，请注意查收";
	private AuthSms generateUserAuthSms(ProdOrder v) {
		AuthSms as = new AuthSms();
		
		as.setPrefix(v.getProdspec());
		as.setSms(U_Prefix + v.getDelimemo() + ", " + v.getDelidesp() + ",REF#");
		as.setPostfix("" + v.getId());
		as.setMsgtype(O_Prefix);
		as.setPhone(v.getRecipphone1());
		as.setSender(v.getSender());
		as.setNetid(v.getNetid());
		as.setProgid(v.getProgid());
		as.setOptime(new Date());
		as.setSendtime(new Date());
		
		return as;
	}
	
	@PostConstruct
    public void init() {
		logger.info(myServer + " Lottery scheduler: initialising ...");
        Collection<ProdOrder> os = orders.findByDelicodeIsNull(new Sort(Sort.Direction.ASC, "rectime"));
        for(ProdOrder po:os){
        	orderQ.offer(po);
        }
        logger.info("Orders from database: " + orderQ.size());
        
        //last 24 hours
        Date oneDayAgo = new Date(System.currentTimeMillis() - 1L * 24 * 3600 * 1000);
        Collection<ProdOrder> pos = orders.findByRectimeGreaterThan(oneDayAgo);
        
        for(ProdOrder po:pos){
        	if(null != po.getDelicode()) 
        		usedBiaoShiMa.add(po.getDelicode());
        }
        logger.info("Orders from database with BiaoZhiMa in last 24 hours: " + usedBiaoShiMa.size());
        logger.info(myServer + " Lottery scheduler: initialising done");
        
    }

}
