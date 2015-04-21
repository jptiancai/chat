package com.hutong.chat.common;

import java.util.HashMap;
import java.util.Set;


public class ChatStatic {
	public final static int WORLD_CHAT=0;
	public final static int UNION_CHAT=1;
	public final static int PRIVATE_CHAT=2;
	
	
	public final static int MAX_CHAT_COUNT = 50;
	public final static int MAX_KEEP_CHAT_COUNT = 200;
	

//	public final static int CustomContent=0;
//	public final static int ArenaReport=1;
//	public final static int MineralHelp=2;

	public static HashMap<String, ChatServer> serverChats = new HashMap<String,ChatServer>();


	public static void clearChat() {
		Set<String> serverIdSet = serverChats.keySet();
		for (String serverId : serverIdSet) {
			ChatServer chatServer = ChatServer.getServerChat(serverId);
			chatServer.clearChat();
		}
	}
}
