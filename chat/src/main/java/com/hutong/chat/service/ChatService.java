package com.hutong.chat.service;

import java.util.List;
import java.util.Timer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hutong.chat.common.ChatServer;
import com.hutong.chat.common.ChatStatic;
import com.hutong.chat.model.ChatItem;
import com.hutong.chat.timer.ClearTask;

@Service
public class ChatService {
	private static final Logger log = LogManager.getLogger(ChatService.class);
	
	
	private static Timer clearTimer = new Timer();
	
	public ChatService(){
		clearTimer.schedule(new ClearTask(), 1000, 1000*60*30);
//		clearTimer.schedule(new ClearTask(), 1000, 1000*60*1);
		log.info("ChatService init..");
	}
	
	/**
	 * 获得聊天记录
	 * @param serverId
	 * @param playerId
	 * @param chatType
	 * @param unionId
	 * @param isFirst 
	 * @return
	 */
	public List<ChatItem> get(String serverId, String playerId, int chatType,String unionId, boolean isFirst){
		int getMaxSeq = ChatStatic.MAX_CHAT_COUNT;
		
		ChatServer serverChat = ChatServer.getServerChat(serverId);
		List<ChatItem> chatListItems =  null;
		
		switch (chatType) {
		case ChatStatic.WORLD_CHAT:
			chatListItems = serverChat.getWorldChats(playerId,getMaxSeq,isFirst);
			break;
		case ChatStatic.UNION_CHAT:	
			chatListItems = serverChat.getUnionChats(unionId,playerId,getMaxSeq,isFirst);
			break;
		case ChatStatic.PRIVATE_CHAT:
			chatListItems = serverChat.getPrivateChats(playerId,getMaxSeq,isFirst);
			break;
		default:
			
		}
		return chatListItems;		
	}

	/**
	 * 发送聊天记录
	 * @param serverId
	 * @param playerId
	 * @param chatType
	 * @param chatString
	 */
	public List<ChatItem> send(String serverId, String playerId, int chatType, String playerName,String unionId,
			String toPlayerId,String toPlayerName, String chatString){
		ChatServer serverChat = ChatServer.getServerChat(serverId);
		int getMaxSeq = ChatStatic.MAX_CHAT_COUNT;
		
		StringBuffer sb = new StringBuffer();
		List<ChatItem> chatListItems =  null;
		
		switch (chatType) {
		case ChatStatic.WORLD_CHAT:
			sb.append("[WORLD] \t");
			chatListItems = serverChat.addWorldChats(playerId,playerName,chatString,getMaxSeq);
			break;
		case ChatStatic.UNION_CHAT:	
			sb.append("[UNION ").append(unionId).append("] \t");
			chatListItems = serverChat.addUnionChats(unionId,playerId,playerName,chatString,getMaxSeq);
			break;
		case ChatStatic.PRIVATE_CHAT:
			sb.append("[PRIVATE to ").append(toPlayerId).append("(").append(toPlayerName).append(")] \t");
			chatListItems = serverChat.addPrivateChats(playerId,playerName,toPlayerId,toPlayerName,chatString,getMaxSeq);
			break;
		default:
			
		}
		sb.append(playerId).append("(").append(playerName).append("): ");
		sb.append(chatString);
		log.info(sb.toString());
		return chatListItems;
	}
		
	public static void main(String[] args) {
		final ChatService s = new ChatService();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					String serverId = "35";
					String playerId = "10000001";
					int chatType = ChatStatic.WORLD_CHAT;
					String unionId = "union1";
					List<ChatItem> list = s.get(serverId, playerId, chatType, unionId,false);
					for (int i = list.size() - 1; i >= 0; i--) {
						ChatItem chatListItem = list.get(i);
						StringBuffer sb = new StringBuffer();
						sb.append(Thread.currentThread().getName())
							.append("\t")
							.append(chatListItem.getSendPlayerName())
							.append("\t(")
//							.append(new Date(chatListItem.getSendTime()).toLocaleString())
							.append(chatListItem.getSendTime())
							.append(")")
							.append(": ")
							.append(chatListItem.getContent());
						
						System.out.println(sb.toString());
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		},"playerId-10000001").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					String serverId = "35";
					String playerId = "10000002";
					int chatType = ChatStatic.WORLD_CHAT;
					String unionId = "union1";
					List<ChatItem> list = s.get(serverId, playerId, chatType, unionId,false);
					for (int i = list.size() - 1; i >= 0; i--) {
						ChatItem chatListItem = list.get(i);
						StringBuffer sb = new StringBuffer();
						sb.append(Thread.currentThread().getName())
							.append("\t\t\t\t\t\t\t\t")
							.append(chatListItem.getSendPlayerName())
							.append("\t(")
//							.append(new Date(chatListItem.getSendTime()).toLocaleString())
							.append(chatListItem.getSendTime())
							.append(")")
							.append(": ")
							.append(chatListItem.getContent());
						
						System.out.println(sb.toString());
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		},"playerId-10000002").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while(true){
					String serverId = "35";
					String playerId = "10000001";
					int chatType = ChatStatic.WORLD_CHAT;
					String unionId = "union1";
					s.send(serverId, playerId, chatType, "player", unionId, "10000002", "小王", "Hello World! 这是第" + i++ + "次");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		},"playerId-10000001").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while(true){
					String serverId = "35";
					String playerId = "10000002";
					int chatType = ChatStatic.WORLD_CHAT;
					String unionId = "union1";
					s.send(serverId, playerId, chatType, "小王", unionId, "10000001", "player", "咳咳咳! 这是第" + i++ + "次");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		},"playerId-10000002").start();
	}
}
