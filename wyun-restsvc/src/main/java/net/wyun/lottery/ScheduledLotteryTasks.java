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
import net.wyun.service.SmsService;
import net.wyun.util.DateUtils;
import net.wyun.util.SysInfoUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Xuecheng
 * The database operations reference: http://info.michael-simons.eu/2014/02/25/boot-your-application-with-spring-boot/
 * 
 */
@Service
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
	
	
	CaiPiaoHandler handler;
	
	@Autowired
	private SmsService smsSvc;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm:ss");

	@Value("${wlsp.lottery.name}")
	private String myServer;
	
	@Value("${caipiao.blocked.hours}")
	private String blockedHoursStr;

	/*
	 * add a new ProdOrder to the Q
	 */
	public boolean addProdOrder(ProdOrder v) {
		return orderQ.offer(v);
	}

	private static Set<String> BLOCKED_HOURS;
	@Scheduled(fixedDelayString = "${wlsp.lottery.delay}")
	public void matchLotteryToOrder() {
		
		logger.info("### [Lottery Scheduler] on " + dateFormat.format(new Date()));
		logger.info("sys info: " + SysInfoUtil.getSysInfo());
		
		//find out blocking time for example: from midnight till 5am
		String hour = DateUtils.getHour();
		
		if(BLOCKED_HOURS.contains(hour)){
			logger.info("in the blocking hours: " + hour);
			return;
		}
		
		
		long time = System.currentTimeMillis();
		
		// process lottery here
		try {
			if(null == handler){
				handler = new CaiPiaoHandler();
			}
			
			if(handler.isExpired()){
				handler = new CaiPiaoHandler();
			}
			
			List<CaiPiao> cps = handler.getActiveCaiPiaos();
			for(CaiPiao cp:cps){
				//new PrintStream(System.out, true, "UTF-8").println(cp.toString());
				String bzm = cp.getBiaoZhiMa();
				if(null != bzm && !this.usedBiaoShiMa.contains(bzm)){ // a not null bzm means we have complete info. of the caipiao
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
						smsSvc.addAuthSms(as2);
						
					}else{
						logger.warn("unclaimed caipiao: " + cp.getNumber());
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
	private static String U_Prefix = "[蓝球]福彩双色球,";
	private static String U_Midfix = " 标志码: REF#";
	private static String U_Postfix = "已收到订单，下单成功后发送标志码，请注意查收";
	private AuthSms generateUserAuthSms(ProdOrder v) {
		AuthSms as = new AuthSms();
		
		as.setPrefix(v.getProdspec());
		as.setSms(U_Prefix + v.getDelimemo() + ", " + v.getDelidesp() + U_Midfix);
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
        	//remove those belongs to hope
        	if(po.getProdtype().equals("hope")) continue;
        	orderQ.offer(po);
        }
        logger.info("Orders from database: " + orderQ.size());
        
        //last 24*3 hours
        Date oneDayAgo = new Date(System.currentTimeMillis() - 3L * 24 * 3600 * 1000);
        Collection<ProdOrder> pos = orders.findByRectimeGreaterThan(oneDayAgo);
        
        for(ProdOrder po:pos){
        	if(null != po.getDelicode()) 
        		usedBiaoShiMa.add(po.getDelicode());
        }
        logger.info("Orders from database with BiaoZhiMa in last 72 hours: " + usedBiaoShiMa.size());
        logger.info(myServer + " Lottery scheduler: initialising done");
        
        BLOCKED_HOURS = generateBlockingHours();
        
    }

	public Set<String> generateBlockingHours() {
		Set<String> blockedHours = new HashSet<String>();
		
		if(null == blockedHoursStr){
			blockedHoursStr = "00,01,02,03,04,05";
		}
		
		String[] hours = blockedHoursStr.split(",");
		for(String hour:hours){
			blockedHours.add(hour);
		}
		
		return blockedHours;
	}

}
