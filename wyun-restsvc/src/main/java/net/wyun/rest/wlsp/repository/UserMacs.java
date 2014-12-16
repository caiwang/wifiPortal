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
@Table(name = "usermacs")
public class UserMacs {

	public UserMacs() {
		this.rectime = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String userid; // varchar(36) DEFAULT NULL, # mac对应的userid，多对多的关系
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getSrcid() {
		return srcid;
	}

	public void setSrcid(int srcid) {
		this.srcid = srcid;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public String getSrcnode() {
		return srcnode;
	}

	public void setSrcnode(String srcnode) {
		this.srcnode = srcnode;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
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

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getDft() {
		return dft;
	}

	public void setDft(String dft) {
		this.dft = dft;
	}

	public String getPrio() {
		return prio;
	}

	public void setPrio(String prio) {
		this.prio = prio;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getPntmaster() {
		return pntmaster;
	}

	public void setPntmaster(String pntmaster) {
		this.pntmaster = pntmaster;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	private int srcid; // int DEFAULT NULL, # iserver字段
	private int token; // `token` int DEFAULT NULL, # 8位随机数，由ihost产生
	private String srcnode; // varchar(10) DEFAULT NULL, # （预留）
	private String usercode; // varchar(30) DEFAULT NULL, # 用户编码（预留）
	private String mac; // varchar(36) DEFAULT NULL, # mac地址

	private String phone; // varchar(30) DEFAULT NULL, # 用户提供的手机号（与用户多对多关系）
	private String stat; // varchar(3) DEFAULT '100', # 数据状态 100-有效
	private String dft; // varchar(3) DEFAULT '100', #
						// 100-此记录的mac-userid是默认值，一个mac一个userrole下同时只有一个默认userid
	private String prio; // varchar(3) DEFAULT '0', #
							// 多个mac-userrole-userid的排序优先级；大于0的最大值可用于自动签到
	private String userrole; // varchar(30) DEFAULT NULL, #
								// 不同角色，每个mac在每个userrole中有一个default userid #
								// 100-代表 200-嘉宾 300-媒体 400-会务
	private String pntmaster; // varchar(3) DEFAULT NULL, #
								// 积分主记录标识，100-此userid为主记录，一个mac只对一个userid积分

	private String memo; // varchar(128) DEFAULT NULL, # 备注
	private String srcip; // varchar(64) DEFAULT NULL, # iserver字段
	private String sender; // varchar(36) DEFAULT NULL, # iserver字段
	private String netid; // varchar(36) DEFAULT NULL, # iserver字段
	private String progid; // varchar(36) DEFAULT NULL, # iserver字段
	private Date updtime; // datetime DEFAULT NULL, # 记录更新时间
	private Date rectime; // datetime DEFAULT NULL, # 记录时间

	private short pushflag; // tinyint DEFAULT '1',
}
