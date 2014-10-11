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
@Table(name = "authclient")
public class AuthClient {
	
	public AuthClient(){
		this.rectime = new Date();
	}
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public String getStat() {
		return stat;
	}


	public void setStat(String stat) {
		this.stat = stat;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getSphone() {
		return sphone;
	}


	public void setSphone(String sphone) {
		this.sphone = sphone;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getPlan() {
		return plan;
	}


	public void setPlan(String plan) {
		this.plan = plan;
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


	public int getToken() {
		return token;
	}


	public void setToken(int token) {
		this.token = token;
	}


	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public int getImgchk1() {
		return imgchk1;
	}


	public void setImgchk1(short imgchk1) {
		this.imgchk1 = imgchk1;
	}


	public int getImgchk2() {
		return imgchk2;
	}


	public void setImgchk2(short imgchk2) {
		this.imgchk2 = imgchk2;
	}


	public int getImgchk3() {
		return imgchk3;
	}


	public void setImgchk3(short imgchk3) {
		this.imgchk3 = imgchk3;
	}


	public short getManstat() {
		return manstat;
	}


	public void setManstat(short manstat) {
		this.manstat = manstat;
	}


	public String getManchker() {
		return manchker;
	}


	public void setManchker(String manchker) {
		this.manchker = manchker;
	}


	public String getManid() {
		return manid;
	}


	public void setManid(String manid) {
		this.manid = manid;
	}


	public String getMantype() {
		return mantype;
	}


	public void setMantype(String mantype) {
		this.mantype = mantype;
	}


	public Date getMantime() {
		return mantime;
	}


	public void setMantime(Date mantime) {
		this.mantime = mantime;
	}


	public byte getOptflag() {
		return optflag;
	}


	public void setOptflag(byte optflag) {
		this.optflag = optflag;
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


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;  //用户id
	
	private String cid;    //用户id
	private String ctype;    //用户类型
	private String stat;   //账号状态
		
	private String phone;   //注册时所用的手机号码
	private String sphone; //发送短信时所用的手机号码
	
	private String msg;  //短信
	
	private String plan;   //vlan
	
	private String question; //复位密码的问题
	private String answer;  //复位密码的答案
	
	private int token;   //注册时的session token
	private String mac;   //mac地址
	private String img; //证照文件名
	private short imgchk1; //证照人工审核
	private short imgchk2;
	private short imgchk3;
	
	private short manstat;
	private String manchker;
	
	private String manid;
	private String mantype;
	private Date mantime;
	
	private byte optflag;
	private String srcip; //注册时所连接的ihost ip
	private String srcname; //注册时所连接的ihost name
	
	private Date rectime; //数据库操作时间
	
	private String sender;  //发送到iserver时，节点名称
	
	private String netid;  //节点网络id
	
	private String progid;  //节点程序id
	
	private Date optime;  //在iserver上的记录时间
	
	
	public String toString(){
		return "prefix: ";
	}

}
