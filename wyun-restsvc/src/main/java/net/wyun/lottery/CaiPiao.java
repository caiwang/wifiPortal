/**
 * 
 */
package net.wyun.lottery;

/**
 * @author Xuecheng
 * chinese meaning for lottery
 * This class is used to bind a user transaction in prodorder table
 *
 */
public class CaiPiao {
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBiaoZhiMa() {
		return biaoZhiMa;
	}
	public void setBiaoZhiMa(String biaoZhiMa) {
		this.biaoZhiMa = biaoZhiMa;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isHitLottery() {
		return hitLottery;
	}
	public void setHitLottery(boolean hitLottery) {
		this.hitLottery = hitLottery;
	}
	
	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	private String orderTime;  //1月7日 11:15:05
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getPeriods() {
		return periods;
	}
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	private String periods;   //期次：2015004
	private String number;  //for ex.  03 04 16 18 27 28   08
	private String biaoZhiMa;   //彩票标识码：3a16c32792603939f62545adb44916a
	private String status;  //  出票状态：已出票
	private String publishTime; //今天 21:30开奖
	private boolean hitLottery; //中奖状态：未中
	private String orderId; //订单编号： 2015010711CP93012077
	
	public String toString(){
		return periods + ", " + number + ", " + publishTime + ", " + biaoZhiMa;
	}
}
