package com.bm.model;

public class BmVO implements java.io.Serializable{
	
	private String bm_no;
	private String bm_name;
	private String bm_number;
	private String bm_mail;
	private String bm_banknum;
	private Byte[] bm_pic;
	private String bm_num;
	private String bm_pwd;
	private String bm_jstatus;
	
	public String getBm_no() {
		return bm_no;
	}
	public void setBm_no(String bm_no) {
		this.bm_no = bm_no;
	}
	public String getBm_name() {
		return bm_name;
	}
	public void setBm_name(String bm_name) {
		this.bm_name = bm_name;
	}
	public String getBm_number() {
		return bm_number;
	}
	public void setBm_number(String bm_number) {
		this.bm_number = bm_number;
	}
	public String getBm_mail() {
		return bm_mail;
	}
	public void setBm_mail(String bm_mail) {
		this.bm_mail = bm_mail;
	}
	public String getBm_banknum() {
		return bm_banknum;
	}
	public void setBm_banknum(String bm_banknum) {
		this.bm_banknum = bm_banknum;
	}
	public Byte[] getBm_pic() {
		return bm_pic;
	}
	public void setBm_pic(Byte[] bm_pic) {
		this.bm_pic = bm_pic;
	}
	public String getBm_num() {
		return bm_num;
	}
	public void setBm_num(String bm_num) {
		this.bm_num = bm_num;
	}
	public String getBm_pwd() {
		return bm_pwd;
	}
	public void setBm_pwd(String bm_pwd) {
		this.bm_pwd = bm_pwd;
	}
	public String getBm_jstatus() {
		return bm_jstatus;
	}
	public void setBm_jstatus(String bm_jstatus) {
		this.bm_jstatus = bm_jstatus;
	}
}