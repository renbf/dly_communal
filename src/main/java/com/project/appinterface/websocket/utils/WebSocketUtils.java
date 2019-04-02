package com.project.appinterface.websocket.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.appinterface.websocket.domain.SelectGoodsMessage;
import com.project.appinterface.websocket.domain.WebSocketSelectGoodsBean;
/**
 * 
 * @author rbf
 *
 */
public class WebSocketUtils {
	private static Logger log = LoggerFactory.getLogger(WebSocketUtils.class);
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static Map<String, Map<String,WebSocketSelectGoodsBean>> webSocketMap = new ConcurrentHashMap<>();
    /*
    Add Session
     */
    public static void add(String giftId,String userId, Session session) throws Exception{
    	log.info("giftId="+giftId+",userId="+userId+",sessionId="+session.getId());
        Map<String, List<String>> paramMap = session.getRequestParameterMap();
		List<String> contentList = paramMap.get("content");
		WebSocketSelectGoodsBean bean = new WebSocketSelectGoodsBean();
		if (CollectionUtils.isNotEmpty(contentList)) {
			Map<String, String> originalStateMap = new ConcurrentHashMap<String, String>();
			Map<String, String> currentStateMap = new ConcurrentHashMap<String, String>();
			if(contentList.size() == 1){
				String[] contents = contentList.get(0).split(",");
				for(int i=0;i<contents.length;i++){
					originalStateMap.put(String.valueOf(i), contents[i]);
					currentStateMap.put(String.valueOf(i), contents[i]);
				}
				bean.setOriginalStateMap(originalStateMap);
				bean.setCurrentStateMap(currentStateMap);
				bean.setGiftId(giftId);
				bean.setUserId(userId);
				bean.setSessionId(session.getId());
				bean.setSession(session);
			}
		}
		if (webSocketMap.containsKey(giftId)) {
			Map<String,WebSocketSelectGoodsBean> webSocketSet = webSocketMap.get(giftId);
			Map<String, String> currentStateMap = bean.getCurrentStateMap();
			//消息指令
			Map<String, String> checkedStateMap = new ConcurrentHashMap<String, String>();
			for(String sessionid : webSocketSet.keySet()){
				WebSocketSelectGoodsBean item = webSocketSet.get(sessionid);
				Map<String, String> currentStateMapItem = item.getCurrentStateMap();
				for (String index : currentStateMapItem.keySet()) {
					//遍历所有选中的下标
					if("5".equals(currentStateMapItem.get(index))){
						//其他人选中的下标改成4
						currentStateMap.put(index, "4");
						checkedStateMap.put(index, "4");
					}
				}
			}
			webSocketSet.put(session.getId(), bean);
			//发消息
			SelectGoodsMessage selectGoodsMessage = new SelectGoodsMessage();
			selectGoodsMessage.setGiftId(giftId);
			selectGoodsMessage.setUserId(userId);
			selectGoodsMessage.setIndexStateMap(checkedStateMap);
			String messages = JSON.toJSONString(selectGoodsMessage);
			sendMessage(messages,bean.getSession());
		} else {
			Map<String,WebSocketSelectGoodsBean> webSocketSet = new ConcurrentHashMap<String,WebSocketSelectGoodsBean>();
			webSocketSet.put(session.getId(), bean);
			webSocketMap.put(giftId, webSocketSet);
		}
    }
    public static void close(String giftId,String userId, Session session) throws Exception{
    	log.info("giftId="+giftId+",userId="+userId+",sessionId="+session.getId());
    	String sessionId = session.getId();
    	Map<String,WebSocketSelectGoodsBean> webSocketSet = webSocketMap.get(giftId);
    	WebSocketSelectGoodsBean bean = webSocketSet.get(sessionId);
    	
    	Map<String, String> indexStateMap = new ConcurrentHashMap<String, String>();
		if (bean.getOriginalStateMap() != null && bean.getOriginalStateMap().size() > 0 && bean.getCurrentStateMap() != null
				&& bean.getCurrentStateMap().size() > 0 && bean.getCurrentStateMap().size() == bean.getOriginalStateMap().size()) {
			for (String index : bean.getOriginalStateMap().keySet()) {
				// 5是代表选中；把当前选中的还原
				if ("5".equals(bean.getCurrentStateMap().get(index))) {
					indexStateMap.put(index, bean.getOriginalStateMap().get(index));
				}
			}
		}
		if (indexStateMap.size() > 0) {
			SelectGoodsMessage selectGoodsMessage = new SelectGoodsMessage();
			selectGoodsMessage.setGiftId(giftId);
			selectGoodsMessage.setUserId(userId);
			selectGoodsMessage.setIndexStateMap(indexStateMap);
			
			for (String sessionid : webSocketSet.keySet()) {
				WebSocketSelectGoodsBean item = webSocketSet.get(sessionid);
				for (String index : indexStateMap.keySet()) {
					if (item.getCurrentStateMap() != null) {
						// 其他用户
						if (!item.getUserId().equals(userId)) {
							item.getCurrentStateMap().put(index, indexStateMap.get(index));
							String messages = JSON.toJSONString(selectGoodsMessage);
							sendMessage(messages,item.getSession());
						}
					}
				}
			}
		}
		webSocketSet.remove(sessionId);
		if (webSocketSet.size() < 1) {
			webSocketMap.remove(giftId);
		}
    }

    /*
    Receive Message
     */
    public static void message(String message, Session session) throws Exception{
    	String sessionId = session.getId();
    	JSONObject jsStr = JSONObject.parseObject(message); // 将字符串{“id”：1}
		SelectGoodsMessage selectGoodsMessage = (SelectGoodsMessage) JSONObject.toJavaObject(jsStr,
				SelectGoodsMessage.class);
		// 消息指令
		Map<String, String> indexStateMap = selectGoodsMessage.getIndexStateMap();
		// 群发消息
		Map<String,WebSocketSelectGoodsBean> webSocketSet = webSocketMap.get(selectGoodsMessage.getGiftId());
		for (String sessionid : webSocketSet.keySet()) {
			WebSocketSelectGoodsBean item = webSocketSet.get(sessionid);
			for (String index : indexStateMap.keySet()) {
				if (item.getCurrentStateMap() != null) {
					// 其他用户
					if (!item.getUserId().equals(selectGoodsMessage.getUserId())) {
						item.getCurrentStateMap().put(index, indexStateMap.get(index));
						String messages = JSON.toJSONString(selectGoodsMessage);
						sendMessage(messages,item.getSession());
					} else {
						// 当前发消息的用户
						// 4是被选中
						if ("4".equals(indexStateMap.get(index))) {
							item.getCurrentStateMap().put(index, "5");
						} else {
							item.getCurrentStateMap().put(index, indexStateMap.get(index));
						}
					}
				}
			}
		}
    }

    
    public static void sendMessage(String messages, Session session) throws IOException{
    	log.info("messages="+messages+",sessionId="+session.getId());
    	session.getBasicRemote().sendText(messages);
    }
}
