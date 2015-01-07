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
@Table(name = "authsms")
public class AuthSms {
	
	public AuthSms(){
		this.rectime = new Date();
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSrcid() {
		return srcid;
	}

	public void setSrcid(long srcid) {
		this.srcid = srcid;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public short getStat() {
		return stat;
	}

	public void setStat(short stat) {
		this.stat = stat;
	}

	public short getOptflag() {
		return optflag;
	}

	public void setOptflag(short optflag) {
		this.optflag = optflag;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public Date getRectime() {
		return rectime;
	}

	public void setRectime(Date rectime) {
		this.rectime = new Date();
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

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	
	public String getMsgtype() {
		return msgtype;
	}


	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long srcid;
	
	private String msgtype;  //for ex. 双色球
	
	private String prefix;
	
	private String sms;
	
	private String postfix;
	
	private String mac;
	
	private String ip;
	
	private String phone;
	
	private short stat;
	
	private short optflag;
	
	private int token;
	
	private Date rectime;
	
	private String sender;
	
	private String netid;
	
	private String progid;
	
	private Date optime;
	
	private Date sendtime;
	
	public String toString(){
		return "prefix: " + this.getPrefix();
	}
	

}
