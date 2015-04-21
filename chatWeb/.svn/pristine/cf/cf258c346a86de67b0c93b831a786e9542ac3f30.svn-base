package com.hutong.chat.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hutong.chat.model.ChatItem;
import com.hutong.chat.service.ChatService;
import com.hutong.chat.web.util.WebUtil;

@Controller
public class ChatController {

	private static final Log logger = LogFactory.getLog(ChatController.class);
	
	@Autowired
	private ChatService chatService;
	
	@RequestMapping("getChat")
	@ResponseBody
	public Object getChat(final HttpServletRequest request) {
		Map<String, String> paramMap = WebUtil.getParamMapByRequest(request);
		String serverId = paramMap.get("serverId");
		String playerId = paramMap.get("playerId");
		int chatType = Integer.parseInt(paramMap.get("chatType"));
		String unionId = paramMap.get("unionId");
		String first = paramMap.get("first");
		boolean isFirst = "1".equals(first);
		List<ChatItem> list = chatService.get(serverId, playerId, chatType, unionId, isFirst);
		return list;
	}
	
	@RequestMapping("sendChat")
	@ResponseBody
	public Object sendChat(final HttpServletRequest request) {
		Map<String, String> paramMap = WebUtil.getParamMapByRequest(request);
		String serverId = paramMap.get("serverId");
		String playerId = paramMap.get("playerId");
		int chatType = Integer.parseInt(paramMap.get("chatType"));
		String unionId = paramMap.get("unionId");
		String playerName = paramMap.get("playerName");
		String toPlayerId = paramMap.get("toPlayerId");
		String toPlayerName = paramMap.get("toPlayerName");
		String chatString = paramMap.get("chatString");
		List<ChatItem> list = chatService.send(serverId, playerId, chatType, playerName, unionId, toPlayerId, toPlayerName, chatString);
		return list;
	}
}
