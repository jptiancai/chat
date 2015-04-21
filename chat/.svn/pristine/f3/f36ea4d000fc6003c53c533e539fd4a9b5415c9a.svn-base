package com.hutong.chat.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.hutong.chat.model.ChatItem;

public class ChatServer {
	public static Object serverChatsLock=new Object();
	
	private static Object unionChatsMapLock=new Object();
	private static Object privateChatsMapLock=new Object();
	private static Object playerParamsLock=new Object();
	
	private ChatList worldChats = new ChatList();		
	public HashMap<String, ChatList> unionChatsMap = new HashMap<String, ChatList>();
	public HashMap<String, ChatList> privateChatsMap = new HashMap<String, ChatList>();
	
	public HashMap<String, PlayerParam> playerParams=new HashMap<String, PlayerParam>();
	
	private ChatServer(){
	}
	
	public static ChatServer getServerChat(String serverId){
		ChatServer serverChat = null;
		serverChat = ChatStatic.serverChats.get(serverId);
		if(null == serverChat){
			synchronized (serverChatsLock) {
				serverChat = ChatStatic.serverChats.get(serverId);
				if(null == serverChat){
					serverChat = new ChatServer();
					ChatStatic.serverChats.put(serverId, serverChat);
				}
			}
		}
		return serverChat;
	}
	
	
	
	public List<ChatItem> getWorldChats(String playerId, int getMaxSeq, boolean isFirst){
		PlayerParam playerParam = this.getPlayerParam(playerId);
		long lastRequestTime = playerParam.getLastRequestWorldsTime();
		ArrayList<ChatItem> chatListItems = worldChats.get(getMaxSeq,lastRequestTime,isFirst);
		playerParam.setLastRequestWorldsTime(System.currentTimeMillis());
		return chatListItems;
	}
	
	public List<ChatItem> getUnionChats(String unionId, String playerId, int getMaxSeq, boolean isFirst){
		PlayerParam playerParam = this.getPlayerParam(playerId);
		long lastRequestTime = playerParam.getLastRequestUnionsTime();
		ChatList unionChatList = this.getUnionChatList(unionId);
		ArrayList<ChatItem> chatListItems = unionChatList.get(getMaxSeq,lastRequestTime,isFirst);
		playerParam.setLastRequestUnionsTime(System.currentTimeMillis());
		return chatListItems;
	}

	private ChatList getUnionChatList(String unionId) {
		ChatList unionChatList;

		unionChatList = unionChatsMap.get(unionId);
		if(null == unionChatList){
			synchronized (unionChatsMapLock) {
				unionChatList = unionChatsMap.get(unionId);
				if(null == unionChatList){
					unionChatList = new ChatList();
					unionChatsMap.put(unionId, unionChatList);
				}
			}
		}
		return unionChatList;
	}
	
	public List<ChatItem> getPrivateChats(String playerId, int getMaxSeq, boolean isFirst){
		PlayerParam playerParam = this.getPlayerParam(playerId);
		long lastRequestTime = playerParam.getLastRequestPrivatesTime();
		ChatList privateChatList = this.getPrivateChatList(playerId);
		
		ArrayList<ChatItem> chatListItems = privateChatList.get(getMaxSeq,lastRequestTime,isFirst);
		playerParam.setLastRequestPrivatesTime(System.currentTimeMillis());
		return chatListItems;
	}

	private ChatList getPrivateChatList(String playerId) {
		ChatList privateChatList;
		privateChatList = privateChatsMap.get(playerId);
		if(null == privateChatList){
			synchronized (privateChatsMapLock) {
				privateChatList = privateChatsMap.get(playerId);
				if(null == privateChatList){
					privateChatList = new ChatList();
					privateChatsMap.put(playerId, privateChatList);
				}
			}
		}
		return privateChatList;
	}

	public synchronized PlayerParam getPlayerParam(String playerId) {
		PlayerParam playerParam;
		playerParam = playerParams.get(playerId);
		if (null == playerParam) {
			synchronized (playerParamsLock) {
				playerParam = playerParams.get(playerId);
				if (null == playerParam) {
					playerParam = new PlayerParam();
					playerParam.setPlayerId(playerId);
					playerParam.setLastChatTime(0);
					playerParam.setLastRequestPrivatesTime(0);
					playerParam.setLastRequestUnionsTime(0);
					playerParam.setLastRequestWorldsTime(0);
					playerParam.setSendWorldTimes(0);
					playerParam.setLastSendWorldChatTime(0);
					playerParams.put(playerId, playerParam);
				}
			}
		}
		return playerParam;
	}

	public List<ChatItem> addWorldChats(String playerId,String playerName, String content, int getMaxSeq) {
		PlayerParam playerParam = this.getPlayerParam(playerId);
//		long lastSendTime = playerParam.getLastSendWorldChatTime();
		long lastRequestTime = playerParam.getLastRequestWorldsTime();
		long now = System.currentTimeMillis();
		List<ChatItem> chatListItems = worldChats.add(playerId,playerName,content,now, getMaxSeq,lastRequestTime);
		playerParam.setLastSendWorldChatTime(now);
		playerParam.setLastRequestWorldsTime(now);
		return chatListItems;
	}

	public List<ChatItem> addUnionChats(String unionId, String playerId,String playerName,
			String content, int getMaxSeq) {
		PlayerParam playerParam = this.getPlayerParam(playerId);
		ChatList unionChatList = this.getUnionChatList(unionId);
		long lastRequestTime = playerParam.getLastRequestUnionsTime();
		long now = System.currentTimeMillis();
		List<ChatItem> chatListItems = unionChatList.add(playerId,playerName,content,now, getMaxSeq,lastRequestTime);
		playerParam.setLastRequestUnionsTime(now);
		return chatListItems;
	}

	public List<ChatItem> addPrivateChats(String playerId,String playerName,String toPlayerId,String toPlayerName, String content, int getMaxSeq) {
		PlayerParam playerParam = this.getPlayerParam(playerId);
		ChatList privateFromChatList = this.getPrivateChatList(playerId);
		long lastRequestTime = playerParam.getLastRequestPrivatesTime();
		long now = System.currentTimeMillis();
		List<ChatItem> chatListItems = privateFromChatList.add(playerId,playerName,content,toPlayerId,toPlayerName,now, getMaxSeq,lastRequestTime);
		ChatList privateToChatList = this.getPrivateChatList(toPlayerId);
		privateToChatList.add(playerId,playerName,content,toPlayerId,toPlayerName,now);
		playerParam.setLastRequestPrivatesTime(now);
		return chatListItems;
	}

	public void clearChat() {
		worldChats.clear();
		
		synchronized (unionChatsMapLock) {
			Set<String> unionIdSet = unionChatsMap.keySet();
			for (String unionId : unionIdSet) {
				ChatList unionChatList = this.getUnionChatList(unionId);
				unionChatList.clear();
			}
		}
		synchronized (playerParamsLock) {
			Set<String> userIdSet = privateChatsMap.keySet();
			for (String userId : userIdSet) {
				ChatList privateChat = this.getPrivateChatList(userId);
				privateChat.clear();
			}
		}
	}
	
}
