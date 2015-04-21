package com.hutong.chat.common;

import java.util.ArrayList;
import java.util.List;

import com.hutong.chat.model.ChatItem;

public class ChatList {


	private static Object lock = new Object();
	
	private ArrayList<ChatItem> chatListItems = new ArrayList<ChatItem>();
	
	/**
	 * 获取聊天记录
	 * @param getMaxSize
	 * @param lastRequestTime
	 * @return
	 */
	public ArrayList<ChatItem> get(int getMaxSize, long lastRequestTime,
			boolean isFirst) {
		ArrayList<ChatItem> returnChatListItems = new ArrayList<ChatItem>(getMaxSize);
		
		if (lastRequestTime != 0 && !isFirst) {
			/**
			 * 获取最后一次请求时间之后所有聊天记录
			 */
			synchronized (lock) {
				for (int i = chatListItems.size() - 1; i >= 0; i--) {
					if (chatListItems.get(i).getSendTime() > lastRequestTime && returnChatListItems.size() < getMaxSize) {
						returnChatListItems.add(chatListItems.get(i));
					} else {
						break;
					}
				}
			}
		}else{
			/**
			 * 如果第一次请求, 返回聊天记录中最后getMaxSize条记录
			 */
			synchronized (lock) {
				int minIndex = 0;
				int itemsSize = chatListItems.size();
				int maxIndex = itemsSize - 1;

				if (itemsSize > getMaxSize) {
					minIndex = itemsSize - getMaxSize;
				}
				
				for (int i = maxIndex; i >= minIndex; i--) {
					returnChatListItems.add(chatListItems.get(i));
				}
			}
		}
//		System.out.println(" chatListItems.size: " +  chatListItems.size());
		return returnChatListItems;
	}

	public ArrayList<ChatItem> get(int getMaxSeq, long lastRequestTime) {
		return this.get(getMaxSeq, lastRequestTime, false);
	}

	public void add(String playerId,
			String playerName, String content,String toPlayerId, String toPlayerName, long now) {
		ChatItem item = new ChatItem();
		item.setSendPlayerId(playerId);
		item.setSendPlayerName(playerName);
		item.setContent(content);
		item.setToPlayerId(toPlayerId);
		item.setToPlayerName(toPlayerName);
		item.setSendTime(now);
		synchronized (lock) {
			chatListItems.add(item);
		}
	}

	public List<ChatItem> add(String playerId, String playerName,
			String content, long now, int getMaxSeq, long lastRequestTime) {
		return this.add(playerId, playerName, content, null, null, now, getMaxSeq, lastRequestTime);
	}

	public List<ChatItem> add(String playerId, String playerName, String content,
			String toPlayerId, String toPlayerName, long now, int getMaxSeq, long lastRequestTime) {
		ChatItem item = new ChatItem();
		item.setSendPlayerId(playerId);
		item.setSendPlayerName(playerName);
		item.setContent(content);
		item.setToPlayerId(toPlayerId);
		item.setToPlayerName(toPlayerName);
		item.setSendTime(now);
		ArrayList<ChatItem> returnChatListItems;
		synchronized (lock) {
			returnChatListItems = this.get(getMaxSeq, lastRequestTime);
			chatListItems.add(item);
			returnChatListItems.add(item);
		}
		return returnChatListItems;
	}


	public void clear() {
		synchronized (lock) {
			int chatSize = chatListItems.size();
			
			if(chatSize > ChatStatic.MAX_KEEP_CHAT_COUNT){
				int removeCount = chatSize - ChatStatic.MAX_KEEP_CHAT_COUNT;
				int index = removeCount;
				ArrayList<ChatItem> newList = new ArrayList<ChatItem>(ChatStatic.MAX_KEEP_CHAT_COUNT);
				for (int i = index; i < chatSize ; i++) {
					newList.add(chatListItems.get(i));
				}
				chatListItems.clear();
				chatListItems = newList;
			}
		}
		System.out.println(" clear chatListItems.size: " +  chatListItems.size());
	}



}
