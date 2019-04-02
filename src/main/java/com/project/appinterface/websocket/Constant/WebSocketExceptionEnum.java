package com.project.appinterface.websocket.Constant;

public enum WebSocketExceptionEnum {
	
	ONOPEN("1","websocket打开异常"),
	ONCLOSE("2","websocket关闭异常"),
	ONMESSAGE("3","websocket收消息异常"),
	ONOTHER("4","websocket其他异常");
	private String status;
    private String msg;
    
    WebSocketExceptionEnum(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
}
