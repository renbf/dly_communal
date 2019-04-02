package com.project.appinterface.websocket.domain;

import java.io.Serializable;
import java.util.Map;

import javax.websocket.Session;

public class WebSocketSelectGoodsBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2099061789641198965L;
	// sessionId
	private String sessionId;
	// 接收giftId
	private String giftId;
	// 接收userId
	private String userId;
	/** 原始状态 */
	private Map<String, String> originalStateMap;
	/** 当前状态 */
	private Map<String, String> currentStateMap;
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getGiftId() {
		return giftId;
	}
	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Map<String, String> getOriginalStateMap() {
		return originalStateMap;
	}
	public void setOriginalStateMap(Map<String, String> originalStateMap) {
		this.originalStateMap = originalStateMap;
	}
	public Map<String, String> getCurrentStateMap() {
		return currentStateMap;
	}
	public void setCurrentStateMap(Map<String, String> currentStateMap) {
		this.currentStateMap = currentStateMap;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	
}
