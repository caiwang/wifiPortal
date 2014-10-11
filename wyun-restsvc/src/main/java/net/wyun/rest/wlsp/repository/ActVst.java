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
@Table(name = "actvst")
public class ActVst {
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPkttime() {
		return pkttime;
	}

	public void setPkttime(Date pkttime) {
		this.pkttime = pkttime;
	}

	public float getTimefrac() {
		return timefrac;
	}

	public void setTimefrac(float timefrac) {
		this.timefrac = timefrac;
	}

	public String getSrcmac() {
		return srcmac;
	}

	public void setSrcmac(String srcmac) {
		this.srcmac = srcmac;
	}

	public String getSrcip() {
		return srcip;
	}

	public void setSrcip(String srcip) {
		this.srcip = srcip;
	}
	
	public int getSrcid() {
		return srcid;
	}

	public void setSrcid(int srcid) {
		this.srcid = srcid;
	}

	public String getDestip() {
		return destip;
	}

	public void setDestip(String destip) {
		this.destip = destip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public ActVst(){
		this.rectime = new Date();
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Date pkttime; //数据包时间
	
	private float timefrac;  //亚秒
	
	private String srcmac; //网络访问源MAC
	
	private int srcid; 
	
	private String srcip;
	
	private String destip;
	
	private String url;  //网络访问url
	
	private Date rectime;
	
	private String sender;
	
	private String netid;
	
	private String progid;
	
	private Date optime;
	
	public String toString(){
		return "prefix: ";
	}
	

}
