/**
 * 
 */
package net.wyun.rest.wlsp.repository;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Xuecheng
 *
 */
@Entity
@Table(name = "wlsta")
public class WlSta {
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSrcid() {
		return srcid;
	}

	public void setSrcid(int srcid) {
		this.srcid = srcid;
	}

	public int getTcount() {
		return tcount;
	}

	public void setTcount(int tcount) {
		this.tcount = tcount;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public float getRssi() {
		return rssi;
	}

	public void setRssi(float rssi) {
		this.rssi = rssi;
	}

	public short getStat() {
		return stat;
	}

	public void setStat(short stat) {
		this.stat = stat;
	}

	public String getSetby() {
		return setby;
	}

	public void setSetby(String setby) {
		this.setby = setby;
	}

	public byte getKeepalive() {
		return keepalive;
	}

	public void setKeepalive(byte keepalive) {
		this.keepalive = keepalive;
	}

	public Date getFirstseen() {
		return firstseen;
	}

	public void setFirstseen(Date firstseen) {
		this.firstseen = firstseen;
	}

	public Date getLastseen() {
		return lastseen;
	}

	public void setLastseen(Date lastseen) {
		this.lastseen = lastseen;
	}

	public short getRtrend() {
		return rtrend;
	}

	public void setRtrend(short rtrend) {
		this.rtrend = rtrend;
	}

	public int getNpacket() {
		return npacket;
	}

	public void setNpacket(int npacket) {
		this.npacket = npacket;
	}

	public short getPtrend() {
		return ptrend;
	}

	public void setPtrend(short ptrend) {
		this.ptrend = ptrend;
	}

	public short getAction() {
		return action;
	}

	public void setAction(short action) {
		this.action = action;
	}

	public String getOstype() {
		return ostype;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public int getAlivetime() {
		return alivetime;
	}

	public void setAlivetime(int alivetime) {
		this.alivetime = alivetime;
	}

	public String getSrcip() {
		return srcip;
	}

	public void setSrcip(String srcip) {
		this.srcip = srcip;
	}

	public Date getRectime() {
		return rectime;
	}

	public void setRectime(Date rectime) {
		this.rectime = rectime;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}

	public String getProgid() {
		return progid;
	}

	public void setProgid(String progid) {
		this.progid = progid;
	}

	public Date getOptime() {
		return optime;
	}

	public void setOptime(Date optime) {
		this.optime = optime;
	}

	public WlSta(){
		this.rectime = new Date();
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int srcid; 
	
	private int tcount;
	
	private String mac;
	
	private String ssid;
	
	private float rssi;
	
	private short stat;
	
	private String setby;
	
    private byte keepalive;
    
    private Date firstseen;
    
    private Date lastseen;
    
    private short rtrend;
    
    private int npacket;
    
    private short ptrend;
    
	private short action;
	
	private String ostype;
	
	private int alivetime;
	
	private String srcip;
	
	private Date rectime;
	
	private String sender;
	
	private String netid;
	
	private String progid;
	
	private Date optime;
	
	public String toString(){
		return "prefix: ";
	}
	

}
