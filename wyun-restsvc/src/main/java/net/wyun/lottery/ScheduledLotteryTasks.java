/**
 * 
 */
package net.wyun.lottery;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;

import net.wyun.rest.wlsp.repository.ProdOrder;
import net.wyun.rest.wlsp.repository.ProdOrderRepository;

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

	
	private final static int QSIZE = 40;
	private BlockingQueue<ProdOrder> orderQ = new ArrayBlockingQueue<ProdOrder>(QSIZE);
	private Set<String> usedBiaoShiMa = new HashSet<String>(); // buffer for
																// those
																// BiaoShiMa
																// already saved
																// in database

	@Autowired
	private ProdOrderRepository orders;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm:ss");

	@Value("${wlsp.lottery.name}")
	private String configServers;

	private String[] servers;

	/*
	 * add a new ProdOrder to the Q
	 */
	public void addProdOrder(ProdOrder v) {
		orderQ.offer(v);
	}

	@Scheduled(fixedDelayString = "${wlsp.lottery.delay}")
	public void matchLotteryToOrder() {
		
		System.out.println("\n### [Lottery Scheduler] on "
				+ dateFormat.format(new Date()));
		long time = System.currentTimeMillis();
		System.out.print("==> ");
		
		// process lottery here

		System.out.println("order Queue size: " + orderQ.size());

		System.out.println(" ...ok [" + (System.currentTimeMillis() - time)
				+ " ms]");
	}
	
	@PostConstruct
    public void init() {
        System.out.println("Lottery scheduler: initialising ...");
        Collection<ProdOrder> os = orders.findByDelicodeIsNull(new Sort(Sort.Direction.ASC, "rectime"));
        for(ProdOrder po:os){
        	orderQ.offer(po);
        }
        System.out.println("Orders from database: " + orderQ.size());
        
        //last 24 hours
        Date oneDayAgo = new Date(System.currentTimeMillis() - 1L * 24 * 3600 * 1000);
        Collection<ProdOrder> pos = orders.findByRectimeGreaterThan(oneDayAgo);
        
        for(ProdOrder po:pos){
        	if(null != po.getDelicode()) usedBiaoShiMa.add(po.getDelicode());
        }
        System.out.println("Orders from database with BiaoZhiMa in last 24 hours: " + usedBiaoShiMa.size());
        System.out.println("Lottery scheduler: initialising done");
        
    }

}
