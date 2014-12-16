/**
 * 
 */
package net.wyun.rest.wlsp.repository;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name = "useractive")
public class UserActive {

	public UserActive() {
		this.rectime = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int srcid; // int DEFAULT NULL, # iserver字段
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public boolean isOnsite() {
		return onsite;
	}

	public void setOnsite(boolean onsite) {
		this.onsite = onsite;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Date getMacfirst() {
		return macfirst;
	}

	public void setMacfirst(Date macfirst) {
		this.macfirst = macfirst;
	}

	public Date getMacmark() {
		return macmark;
	}

	public void setMacmark(Date macmark) {
		this.macmark = macmark;
	}

	public Date getMaclast() {
		return maclast;
	}

	public void setMaclast(Date maclast) {
		this.maclast = maclast;
	}

	public Date getPagefirst() {
		return pagefirst;
	}

	public void setPagefirst(Date pagefirst) {
		this.pagefirst = pagefirst;
	}

	public Date getPagemark() {
		return pagemark;
	}

	public void setPagemark(Date pagemark) {
		this.pagemark = pagemark;
	}

	public Date getPagelast() {
		return pagelast;
	}

	public void setPagelast(Date pagelast) {
		this.pagelast = pagelast;
	}

	public String getUpdby() {
		return updby;
	}

	public void setUpdby(String updby) {
		this.updby = updby;
	}

	public String getInsby() {
		return insby;
	}

	public void setInsby(String insby) {
		this.insby = insby;
	}

	public String getSrcip() {
		return srcip;
	}

	public void setSrcip(String srcip) {
		this.srcip = srcip;
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

	public Date getUpdtime() {
		return updtime;
	}

	public void setUpdtime(Date updtime) {
		this.updtime = updtime;
	}

	public Date getRectime() {
		return rectime;
	}

	public void setRectime(Date rectime) {
		this.rectime = new Date();
	}

	public short getPushflag() {
		return pushflag;
	}

	public void setPushflag(short pushflag) {
		this.pushflag = pushflag;
	}

	private String mac; // varchar(36) DEFAULT NULL, # 用户的mac地址
	private String phone; // varchar(30) DEFAULT NULL, # 用户的手机号（与用户多对多关系）
	private String userrole; // varchar(30) DEFAULT NULL, #
								// 不同角色，每个mac在每个userrole中有一个default userid
	private String userid; // varchar(36) DEFAULT NULL, # mac对应的userid，多对多的关系
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean onsite; // tinyint DEFAULT '0', # 0-not on site; 1-onsite
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean online; // tinyint DEFAULT '0', # 0-not online; 1-online
	
	
	private Date macfirst; // datetime DEFAULT '1970-1-1 00:00:00', # mac首次发现时间
	private Date macmark; // datetime DEFAULT NULL, # mac标记时间，中途更新积分时使用
	private Date maclast; // datetime DEFAULT '1970-1-1 00:00:00', # mac最后一次发现时间
	private Date pagefirst; // datetime DEFAULT '1970-1-1 00:00:00', # 首次访问站点时间
	private Date pagemark; // datetime DEFAULT NULL, # 站点标记时间，中途更新积分时使用
	private Date pagelast; // datetime DEFAULT '1970-1-1 00:00:00', # 末次访问站点时间
	private String updby; // varchar(30) DEFAULT NULL, # 更新记录的程序
	private String insby; // varchar(30) DEFAULT NULL, # 创建记录的程序

	private String srcip; // varchar(64) DEFAULT NULL, # iserver字段
	private String sender; // varchar(36) DEFAULT NULL, # iserver字段
	private String netid; // varchar(36) DEFAULT NULL, # iserver字段
	private String progid; // varchar(36) DEFAULT NULL, # iserver字段
	private Date updtime; // datetime DEFAULT NULL, # 记录更新时间
	private Date rectime; // datetime DEFAULT NULL, # 记录时间

	private short pushflag; // tinyint DEFAULT '1',

}
