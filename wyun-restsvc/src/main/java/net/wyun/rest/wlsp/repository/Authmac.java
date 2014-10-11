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
@Table(name = "authmac")
public class Authmac {
	
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


	public short getStat() {
		return stat;
	}


	public void setStat(short stat) {
		this.stat = stat;
	}


	public Date getFromtime() {
		return fromtime;
	}


	public void setFromtime(Date fromtime) {
		this.fromtime = fromtime;
	}


	public int getLasting() {
		return lasting;
	}


	public void setLasting(int lasting) {
		this.lasting = lasting;
	}


	public short getPushflag() {
		return pushflag;
	}


	public void setPushflag(short pushflag) {
		this.pushflag = pushflag;
	}


	public String getPushurl() {
		return pushurl;
	}


	public void setPushurl(String pushurl) {
		this.pushurl = pushurl;
	}


	public int getPushtime() {
		return pushtime;
	}


	public void setPushtime(int pushtime) {
		this.pushtime = pushtime;
	}


	public String getCid() {
		return cid;
	}


	public void setCid(String cid) {
		this.cid = cid;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getToken() {
		return token;
	}


	public void setToken(int token) {
		this.token = token;
	}


	public String getBase() {
		return base;
	}


	public void setBase(String base) {
		this.base = base;
	}


	public String getSrcip() {
		return srcip;
	}


	public void setSrcip(String srcip) {
		this.srcip = srcip;
	}


	public String getSrcname() {
		return srcname;
	}


	public void setSrcname(String srcname) {
		this.srcname = srcname;
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


	public Authmac(){
		this.rectime = new Date();
	}
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int srcid;
	
	private String mac;
	
	private String ip;
	
	private short stat;
	
	private Date fromtime;
	
	private int lasting;
	
	private short pushflag;
	
	private String pushurl;
	
	private int pushtime;
	
	private String cid;
	
	private String phone;
	
	private int token;
	
	private String base;
	
	private String srcip;
	
	private String srcname;
	
	
	private Date rectime;
	
	private String sender;
	
	private String netid;
	
	private String progid;
	
	private Date optime;
	
	
	public String toString(){
		return "prefix: ";
	}
	

}
