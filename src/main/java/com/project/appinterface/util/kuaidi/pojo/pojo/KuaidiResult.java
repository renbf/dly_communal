package com.project.appinterface.util.kuaidi.pojo.pojo;

import java.util.ArrayList;

import com.project.appinterface.util.kuaidi.JacksonHelper;

public class KuaidiResult {

	private String message = "";
	private String nu = "";
	private String nuName;
	private String ischeck = "0";
	private String com = "";
	private String status = "0";
	private ArrayList<ResultItem> data = new ArrayList<ResultItem>();
	private String state = "0";
	private String condition = "";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public ArrayList<ResultItem> getData() {
		return data;
	}

	public void setData(ArrayList<ResultItem> data) {
		this.data = data;
	}

	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getNuName() {
		return nuName;
	}

	public void setNuName(String nuName) {
		this.nuName = nuName;
	}

	@Override
	public String toString() {
		return JacksonHelper.toJSON(this);
	}
}
