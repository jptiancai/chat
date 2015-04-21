package com.hutong.chat.model;

import java.io.Serializable;

public class ChatItem implements Serializable{
	
	/**
	 * 
	 */
	private static final transient long serialVersionUID = 253104361742669589L;
	
	
	private long sendTime;
	private String sendPlayerId;
	private String sendPlayerName;

	private String toPlayerId = "";
	private String toPlayerName = "";
	private String content;
	
	
	public long getSendTime() {
		return sendTime;
	}
	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}
	public String getSendPlayerId() {
		return sendPlayerId;
	}
	public void setSendPlayerId(String sendPlayerId) {
		this.sendPlayerId = sendPlayerId;
	}
	public String getSendPlayerName() {
		return sendPlayerName;
	}
	public void setSendPlayerName(String sendPlayerName) {
		this.sendPlayerName = sendPlayerName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getToPlayerId() {
		return toPlayerId;
	}
	public void setToPlayerId(String toPlayerId) {
		this.toPlayerId = toPlayerId;
	}
	public String getToPlayerName() {
		return toPlayerName;
	}
	public void setToPlayerName(String toPlayerName) {
		this.toPlayerName = toPlayerName;
	}

}
