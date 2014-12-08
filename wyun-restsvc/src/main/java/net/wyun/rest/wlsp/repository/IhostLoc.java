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
@Table(name = "ihostloc")
public class IhostLoc {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public IhostLoc(){
		this.rectime = new Date();
	}
	
	private String mac; //`mac` varchar(36) DEFAULT NULL, #mac address of eth0 on the ihost
	
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
	public String getPriip() {
		return priip;
	}
	public void setPriip(String priip) {
		this.priip = priip;
	}
	public String getPubip() {
		return pubip;
	}
	public void setPubip(String pubip) {
		this.pubip = pubip;
	}
	public String getRpttype() {
		return rpttype;
	}
	public void setRpttype(String rpttype) {
		this.rpttype = rpttype;
	}
	public String getApptype() {
		return apptype;
	}
	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
	public String getDbinput() {
		return dbinput;
	}
	public void setDbinput(String dbinput) {
		this.dbinput = dbinput;
	}
	public String getDboutput() {
		return dboutput;
	}
	public void setDboutput(String dboutput) {
		this.dboutput = dboutput;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWechatid() {
		return wechatid;
	}
	public void setWechatid(String wechatid) {
		this.wechatid = wechatid;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getProgid() {
		return progid;
	}
	public void setProgid(String progid) {
		this.progid = progid;
	}
	public Date getRectime() {
		return rectime;
	}
	public void setRectime(Date rectime) {
		this.rectime = new Date();
	}
	private String priip; //  `priip` varchar(64) DEFAULT NULL, #private ip addr of eth0
	private String pubip; //  `pubip` varchar(64) DEFAULT NULL, #public ip addr retrived form request url
	
	@Column(name="rpttype", columnDefinition="enum('start','end','mid')")
	private String rpttype; // `rpttype` set('start', 'end', 'mid') not NULL default 'start', #status of this report:start;end;mid; of work
	
	@Column(name="apptype", columnDefinition="enum('ma','hr','ms', 'other')")
	private String apptype; //  `apptype` set('ma', 'hr', 'ms', 'other') not NULL default 'other', #ma-meeting affair;hr-human resource;ms-mobile station
	
	private String dbinput; //  `dbinput` varchar(128) DEFAULT NULL, #db file imported into ihost at the begining (github url)
	private String dboutput; //  `dboutput` varchar(128) DEFAULT NULL, #db file dumped from ihost in the end (github url)
	private String city; // `city` varchar(64) DEFAULT NULL, #city name
	private String location; //  `location` varchar(64) DEFAULT NULL, #street name or building name
	private String company; //  `company` varchar(64) DEFAULT NULL, #company name
	private String owner; //  `owner` varchar(64) DEFAULT NULL, #the owner of the ihost
	private String latitude; //  `latitude` varchar(36) DEFAULT NULL,
	private String longitude; //  `longitude` varchar(36) DEFAULT NULL,
	private String admin; //  `admin` varchar(36) DEFAULT NULL, #administrator
	private String phone; // `phone` varchar(64) DEFAULT NULL, #phone number of the administrator
	private String wechatid; //  `wechatid` varchar(64) DEFAULT NULL,
	private String memo; //  `memo` varchar(128) DEFAULT NULL,
	private String progid; //  `progid` varchar(36) DEFAULT NULL,
	private Date rectime; //  `rectime` datetime DEFAULT NULL,

}
