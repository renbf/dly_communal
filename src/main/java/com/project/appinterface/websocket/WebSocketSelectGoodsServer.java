package com.project.appinterface.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.project.appinterface.util.JedisUtil;
import com.project.appinterface.websocket.Constant.WebSocketExceptionEnum;
import com.project.appinterface.websocket.utils.WebSocketUtils;

@ServerEndpoint("/websocket/{giftId}/{userId}")
@Component
@Scope("prototype")
public class WebSocketSelectGoodsServer {

	private static Logger log = LoggerFactory.getLogger(WebSocketSelectGoodsServer.class);
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private static Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();
	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("giftId") String giftId, @PathParam("userId") String userId) {
		try {
			addOnlineCount(); // 在线数加1
			log.info("有新窗口开始监听:" + giftId + ",当前在线人数为" + getOnlineCount());
			sessionMap.put(session.getId(), session);
			StringBuilder sb = new StringBuilder();
			for(String sessionId:sessionMap.keySet()) {
				sb.append(sessionId+",");
			}
			JedisUtil.set("websocket_sessionId", sb.toString(), 0);
			Map<String, String> giftIdMapOld = JedisUtil.getMap("websocket_"+giftId);
			//校验
			if(giftIdMapOld != null) {
			for(String key:giftIdMapOld.keySet()) {
				if(!sessionMap.containsKey(key)) {
					JedisUtil.mapRemove("websocket_"+giftId, key);
				}
			}
			}
			WebSocketUtils.add(giftId, userId, session,sessionMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(WebSocketExceptionEnum.ONOPEN.getStatus());
		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(Session session,@PathParam("giftId") String giftId, @PathParam("userId") String userId) {
		try {
			
			WebSocketUtils.close(giftId, userId, session,sessionMap);
			sessionMap.remove(session.getId());
			StringBuilder sb = new StringBuilder();
			for(String sessionId:sessionMap.keySet()) {
				sb.append(sessionId+",");
			}
			JedisUtil.set("websocket_sessionId", sb.toString(), 0);
			subOnlineCount(); // 在线数减1
			log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(WebSocketExceptionEnum.ONCLOSE.getStatus());
		}
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message
	 *            客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(@PathParam("giftId") String giftId, @PathParam("userId") String userId,String message, Session session) {
		try {
			log.info("收到来自窗口" + giftId + "的信息:" + message);
			WebSocketUtils.message(message, session,sessionMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(WebSocketExceptionEnum.ONMESSAGE.getStatus());
		}
	}

	/**
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error,@PathParam("giftId") String giftId, @PathParam("userId") String userId) {
		try {
//			sessionMap.remove("sessionId_"+session.getId());
//			SelectGoodsMessage selectGoodsMessage = new SelectGoodsMessage();
//			selectGoodsMessage.setGiftId(giftId);
//			selectGoodsMessage.setUserId(userId);
			
//			if (WebSocketExceptionEnum.ONOPEN.equals(error.getMessage())) {
//				selectGoodsMessage.setExceptionStatus(WebSocketExceptionEnum.ONOPEN.getStatus());
//				String message = JSON.toJSONString(selectGoodsMessage);
//				this.sendMessage(message,session);
//			} else if (WebSocketExceptionEnum.ONCLOSE.equals(error.getMessage())) {
//				selectGoodsMessage.setExceptionStatus(WebSocketExceptionEnum.ONCLOSE.getStatus());
//				String message = JSON.toJSONString(selectGoodsMessage);
//				this.sendMessage(message,session);
//			} else if (WebSocketExceptionEnum.ONMESSAGE.equals(error.getMessage())) {
//				selectGoodsMessage.setExceptionStatus(WebSocketExceptionEnum.ONMESSAGE.getStatus());
//				String message = JSON.toJSONString(selectGoodsMessage);
//				this.sendMessage(message,session);
//			} else {
//				selectGoodsMessage.setExceptionStatus(WebSocketExceptionEnum.ONOTHER.getStatus());
//				String message = JSON.toJSONString(selectGoodsMessage);
//				this.sendMessage(message,session);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 实现服务器主动推送
	 */
	public void sendMessage(String message,Session session) throws IOException {
		log.info("message="+message);
		session.getBasicRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketSelectGoodsServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketSelectGoodsServer.onlineCount--;
	}

}
