package com.project.common.result;

/**
 * 仅包含状态与消息
 * @author Administrator
 *
 */
public class Result {
	public static final String SUCCESS = "0";
	public static final String FAILED = "1";
	public static final String USERNO = "10086";
	/**没绑定账号*/
	public static final String NOBING = "10010";
	protected String message;
	protected String status;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
