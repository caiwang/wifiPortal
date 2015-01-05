/**
 * 
 */
package net.wyun.rest.wlsp.repository;

import java.math.BigDecimal;
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
@Table(name = "prodorder")
public class ProdOrder {
	
	
	public ProdOrder(){
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

	

	 private int srcid; // int DEFAULT NULL,	#	iserver字段
	 
	 private String username; // varchar(36) DEFAULT NULL,	#	username	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProdcode() {
		return prodcode;
	}

	public void setProdcode(String prodcode) {
		this.prodcode = prodcode;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public String getProdtype() {
		return prodtype;
	}

	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}

	public String getProdspec() {
		return prodspec;
	}

	public void setProdspec(String prodspec) {
		this.prodspec = prodspec;
	}

	public String getProddesp() {
		return proddesp;
	}

	public void setProddesp(String proddesp) {
		this.proddesp = proddesp;
	}

	public BigDecimal getQuan() {
		return quan;
	}

	public void setQuan(BigDecimal quan) {
		this.quan = quan;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getRecipaddr() {
		return recipaddr;
	}

	public void setRecipaddr(String recipaddr) {
		this.recipaddr = recipaddr;
	}

	public String getRecipname() {
		return recipname;
	}

	public void setRecipname(String recipname) {
		this.recipname = recipname;
	}

	public String getRecipphone1() {
		return recipphone1;
	}

	public void setRecipphone1(String recipphone1) {
		this.recipphone1 = recipphone1;
	}

	public String getRecipphone2() {
		return recipphone2;
	}

	public void setRecipphone2(String recipphone2) {
		this.recipphone2 = recipphone2;
	}

	public String getRecipemail() {
		return recipemail;
	}

	public void setRecipemail(String recipemail) {
		this.recipemail = recipemail;
	}

	public String getAssignto() {
		return assignto;
	}

	public void setAssignto(String assignto) {
		this.assignto = assignto;
	}

	public String getDelicode() {
		return delicode;
	}

	public void setDelicode(String delicode) {
		this.delicode = delicode;
	}

	public String getDelidesp() {
		return delidesp;
	}

	public void setDelidesp(String delidesp) {
		this.delidesp = delidesp;
	}

	public String getDelimemo() {
		return delimemo;
	}

	public void setDelimemo(String delimemo) {
		this.delimemo = delimemo;
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


	private String prodcode; // varchar(10) DEFAULT NULL,	#	product code
	 private String prodname; // varchar(36) DEFAULT NULL,	#	product name
	 private String prodtype; // `prodtype` varchar(36) DEFAULT NULL,	#	product type 
	 private String prodspec; //varchar(36) DEFAULT NULL,	#	product specification
	 private String proddesp; // varchar(36) DEFAULT NULL,	#	product description
	 
	 @Column(name = "quan", columnDefinition="decimal", precision=10, scale=2)  private BigDecimal	 quan; // `quan` decimal(10,2) DEFAULT NULL,	#	quantity of product
	 private String unit; // `unit` varchar(36) DEFAULT NULL,	#	unit of product
	 private String pkg; // `pkg` varchar(36) DEFAULT NULL,	#	package of product
	 private String recipaddr; // varchar(128) DEFAULT NULL,	#	recipient address
	 private String recipname; // varchar(36) DEFAULT NULL,	#	recipient name
	 private String recipphone1; // varchar(30) DEFAULT NULL,	#	recipient phone number #1
	 private String recipphone2; // varchar(30) DEFAULT NULL,	#	recipient phone number #2
	 private String recipemail; // varchar(64) DEFAULT NULL,	#	recipient phone email
	 private String assignto; // varchar(64) DEFAULT 'iserver',	#	processor of the order: iserver/local
	 private String delicode; // varchar(36) DEFAULT NULL,	#	delivery code (link to delivery table)
	 private String delidesp; // varchar(128) DEFAULT NULL,	#	delivery description
	 private String delimemo; // varchar(128) DEFAULT NULL,	#	delivery memo
	 
	 private String srcip; // varchar(64) DEFAULT NULL,	#	iserver字段
	 private String sender; // varchar(36) DEFAULT NULL, 	#	iserver字段
	 private String netid; // varchar(36) DEFAULT NULL, 	#	iserver字段
	 private String progid; // varchar(36) DEFAULT NULL, 	#	iserver字段
	 private Date updtime; // datetime DEFAULT NULL,	#	记录更新时间
	 private Date rectime; // datetime DEFAULT NULL,	#	记录时间
	 	
	 private short pushflag; // tinyint DEFAULT '1',

	
}
