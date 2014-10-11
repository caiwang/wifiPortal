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
@Table(name = "authmacip")
public class Authmacip {
	
	public Authmacip(){
		this.rectime = new Date();
	}
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public String getCalled() {
		return called;
	}


	public void setCalled(String called) {
		this.called = called;
	}


	public String getSrcip() {
		return srcip;
	}


	public void setSrcip(String srcip) {
		this.srcip = srcip;
	}


	public String getProcid() {
		return procid;
	}


	public void setProcid(String procid) {
		this.procid = procid;
	}


	public String getUserurl() {
		return userurl;
	}


	public void setUserurl(String userurl) {
		this.userurl = userurl;
	}


	public String getOrgurl() {
		return orgurl;
	}


	public void setOrgurl(String orgurl) {
		this.orgurl = orgurl;
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


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String mac;
	
	private String ip;
	
	private String called;
	
	private String srcip;
	
	private String procid;
	
	private String userurl;
	
	private String orgurl;
	
	private int token;
	
	private Date rectime;
	
	private String sender;
	
	private String netid;
	
	private String progid;
	
	private Date optime;
	
	
	public String toString(){
		return "prefix: ";
	}
	

}
