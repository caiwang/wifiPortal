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
	
	public CaiPiao(String doc){
		
		
		
	}
	
	
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
	
	private String number;  //for ex.  03 04 16 18 27 28   08
	private  String biaoZhiMa;   //彩票标识码：3a16c32792603939f62545adb44916a
	private String status;  //  出票状态：已出票
	private boolean hitLottery; //中奖状态：未中

}
