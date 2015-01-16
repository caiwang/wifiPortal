/**
 * 
 */
package net.wyun.service;

/**
 * @author Xuecheng
 *
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import net.wyun.rest.wlsp.client.impl.AuthSmsClient;
import net.wyun.rest.wlsp.repository.AuthSms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SmsService extends Thread{

	private static final Logger logger = LoggerFactory.getLogger(SmsService.class);
	private ExecutorService service;

	@Autowired
	private AuthSmsClient smsClient;

	private final static int QSIZE = 40;
	private BlockingQueue<AuthSms> smsQ = new ArrayBlockingQueue<AuthSms>(QSIZE);

	public boolean addAuthSms(AuthSms v) {
		return smsQ.offer(v);
	}

	public SmsService() {
		service = Executors.newFixedThreadPool(2);
	}

	boolean isShutDown = false;
	
	@Override
	public void run() {
		this.setName("sms_service");
		execute();
	}

	
	//This part needs to be placed in a thread. If not, Spring context got blocked.
	private void execute() {
		while (!isShutDown) {
			try {
				AuthSms v = smsQ.poll(30, TimeUnit.SECONDS);
				if(null != v) 
					service.execute(new SmsTask(v));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	private void stopService() {
		service.shutdown();
	}

	
	@PostConstruct
	public void init() {
		logger.info("start sms service to send authsms to sms server...");
		this.start();
	}
	

	
	@PreDestroy
	public void cleanUp() {
		logger.info("clean up before shut down sms service.");
		isShutDown = true;
		while (!smsQ.isEmpty()) {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		logger.info("shut down sms service now.");
		stopService();
	}


	class SmsTask implements Runnable {

		private AuthSms authSms;

		private SmsTask(AuthSms v) {
			authSms = v;
		}

		@Override
		public void run() {
			smsClient.addAuthSms(authSms);
		}

	}

}
