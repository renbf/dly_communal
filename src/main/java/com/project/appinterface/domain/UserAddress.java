package com.project.appinterface.domain;

public class UserAddress {
	private String id;
	private String receiver;
	private String address;
	private String telephone;
	private String user_id;
	private String address_detailed;
	private String default_address;
	
	public String getDefault_address() {
		return default_address;
	}
	public void setDefault_address(String default_address) {
		this.default_address = default_address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAddress_detailed() {
		return address_detailed;
	}
	public void setAddress_detailed(String address_detailed) {
		this.address_detailed = address_detailed;
	}
	
}
