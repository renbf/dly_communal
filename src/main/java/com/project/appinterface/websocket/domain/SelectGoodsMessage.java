package com.project.appinterface.websocket.domain;

import java.io.Serializable;
import java.util.Map;
/**
 * 消息指令对象
 * @author rbf
 *
 */
public class SelectGoodsMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2162947275832125045L;
	/**礼物机id*/
	private String giftId;
	/**用户id*/
	private String userId;
	/**key:index;value:state
	 * 5：代表选中，4代表被选中
	 * */
	private Map<String,String> indexStateMap;

	private String exceptionStatus;
	
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

	public Map<String, String> getIndexStateMap() {
		return indexStateMap;
	}

	public void setIndexStateMap(Map<String, String> indexStateMap) {
		this.indexStateMap = indexStateMap;
	}

	public String getExceptionStatus() {
		return exceptionStatus;
	}

	public void setExceptionStatus(String exceptionStatus) {
		this.exceptionStatus = exceptionStatus;
	}
	
}
