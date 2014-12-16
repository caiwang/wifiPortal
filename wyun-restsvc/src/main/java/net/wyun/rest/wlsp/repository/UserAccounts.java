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

import org.hibernate.annotations.Type;

/**
 * @author Xuecheng
 *
 */
@Entity
@Table(name = "useraccounts")
public class UserAccounts {
	
	
	public UserAccounts(){
		this.rectime = new Date();
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	 private String userid;  // varchar(36) DEFAULT NULL,	#	为user分配一个uuid. It is generated in ihost by php. 
	 
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

	public String getUser_uuid() {
		return user_uuid;
	}

	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getUseremail1() {
		return useremail1;
	}

	public void setUseremail1(String useremail1) {
		this.useremail1 = useremail1;
	}

	public String getUseremail2() {
		return useremail2;
	}

	public void setUseremail2(String useremail2) {
		this.useremail2 = useremail2;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getPntfactor() {
		return pntfactor;
	}

	public void setPntfactor(int pntfactor) {
		this.pntfactor = pntfactor;
	}

	public short getByear() {
		return byear;
	}

	public void setByear(short byear) {
		this.byear = byear;
	}

	public short getBmonth() {
		return bmonth;
	}

	public void setBmonth(short bmonth) {
		this.bmonth = bmonth;
	}

	public short getBday() {
		return bday;
	}

	public void setBday(short bday) {
		this.bday = bday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOccup() {
		return occup;
	}

	public void setOccup(String occup) {
		this.occup = occup;
	}

	public String getOrgn() {
		return orgn;
	}

	public void setOrgn(String orgn) {
		this.orgn = orgn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getRegphone() {
		return regphone;
	}

	public void setRegphone(String regphone) {
		this.regphone = regphone;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getOpen1() {
		return open1;
	}

	public void setOpen1(String open1) {
		this.open1 = open1;
	}

	public String getOpen2() {
		return open2;
	}

	public void setOpen2(String open2) {
		this.open2 = open2;
	}

	public String getCheck() {
		return smscheck;
	}

	public void setCheck(String check) {
		this.smscheck = check;
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

	public String getIntid() {
		return intid;
	}

	public void setIntid(String intid) {
		this.intid = intid;
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

	public short isPushflag() {
		return pushflag;
	}

	public void setPushflag(short pushflag) {
		this.pushflag = pushflag;
	}

	 private int srcid; // int DEFAULT NULL,	#	iserver字段
	 private int token; // `token` int DEFAULT NULL,	#	8位随机数，由ihost产生
	 private String srcnode; // varchar(10) DEFAULT NULL,	#	（预留）
	 private String usercode; // varchar(30) DEFAULT NULL,	#	用户编码（预留）
	 private String user_uuid; // varchar(36) DEFAULT NULL,	#	用户uuid. It is generated on iserver. Globally effective to cover cases that the user might change his phone and/or phone number.
	 private String mac; // varchar(36) DEFAULT NULL,	#	mac地址
	 private String userpass; //`userpass` varchar(30) DEFAULT NULL,	#	用户密码
	 private String useremail1; // `useremail1` varchar(64) DEFAULT NULL,	#	用户email
	 private String useremail2; // `useremail2` varchar(64) DEFAULT NULL,	#	用户备用email
	 private String question; // `question` varchar(30) DEFAULT NULL,	#	密码提示问题
	 private String answer; // `answer` varchar(30) DEFAULT NULL,	#	密码答案，用于找回密码
	 private String fname; // varchar(20) DEFAULT NULL,	#	名字
	 private String lname; // varchar(20) DEFAULT NULL,	#	姓
	 private String userrole; // varchar(20) default NULL, #	不同角色，每个mac在每个userrole中有一个default userid #   100-代表 200-嘉宾 300-媒体 400-会务
	 private String usertype; // varchar(10) DEFAULT NULL,	#	用户类型：预注册/现场注册
	 private int integral; // int DEFAULT '0',	#	userid下的积分
	 private int pntfactor; //int DEFAULT '1000',	#	points转integral的因子，1000代表1
	 private short byear; //smallint DEFAULT NULL,	#	生日，年
	 private short bmonth; // `bmonth` smallint DEFAULT NULL,	#	生日，月
	 private short bday; //smallint DEFAULT NULL,	#	生日，日
	 private String gender; // varchar(8) DEFAULT NULL,	#	性别
	 private String occup; //varchar(30) DEFAULT NULL,	#	职业
	 private String orgn; //varchar(64) DEFAULT NULL,	#	工作单位
	 private String title; //varchar(32) DEFAULT NULL,	#	职务
	 private String cid; // varchar(30) DEFAULT NULL,	#	证件号
	 private String ctype; // varchar(10) DEFAULT NULL,	#	证件类别
	 private String regphone; //varchar(30) DEFAULT NULL,	#	（预）注册所用的电话号码
	 private String captcha; //varchar(10) DEFAULT NULL,	#	（预）注册所用的验证码
	 private String phone; // varchar(30) DEFAULT NULL,	#	常用电话号码
	 private String address; // varchar(128) DEFAULT NULL,	#	地址
	 private String location; // varchar(32) DEFAULT NULL,	#	所在区域
	 private String action; //varchar(128) DEFAULT NULL,	#	活动（预留）
	 private String stat;  //varchar(3) DEFAULT '100',	#	数据状态 100-有效 
	 private String open1; //varchar(3) DEFAULT '100',	#	数据对招聘者公开，100-公开，0-不公开
	 private String open2; // varchar(3) DEFAULT '100',	#	数据对求职者公开，100-公开，0-不公开
	 private String smscheck; //varchar(3) DEFAULT '100',	#	短信验证，100-验证，0-不验证
	 private String memo; // varchar(128) DEFAULT NULL,	#	备注
	 private String srcip; // varchar(64) DEFAULT NULL,	#	iserver字段
	 private String sender; // varchar(36) DEFAULT NULL, 	#	iserver字段
	 private String netid; // varchar(36) DEFAULT NULL, 	#	iserver字段
	 private String progid; // varchar(36) DEFAULT NULL, 	#	iserver字段
	 private String intid; // varchar(30) DEFAULT NULL,	#	与phpyun的对应关系（记录学历等其他个人信息）
	 private Date updtime; // datetime DEFAULT NULL,	#	记录更新时间
	 private Date rectime; // datetime DEFAULT NULL,	#	记录时间
	 
	
	 private short pushflag; // tinyint DEFAULT '1',

	
}
