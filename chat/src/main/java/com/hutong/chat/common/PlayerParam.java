package com.hutong.chat.common;


public class PlayerParam {
	private String playerId;
	private long lastChatTime;
	private int sendWorldTimes;
	private long lastSendWorldChatTime;
	
	
	private long lastRequestWorldsTime;
	private long lastRequestUnionsTime;
	private long lastRequestPrivatesTime;
	
	public long getLastChatTime() {
		return lastChatTime;
	}
	public void setLastChatTime(long lastChatTime) {
		this.lastChatTime = lastChatTime;
	}
	public int getSendWorldTimes() {
		return sendWorldTimes;
	}
	public void setSendWorldTimes(int sendWorldTimes) {
		this.sendWorldTimes = sendWorldTimes;
	}
	public long getLastRequestWorldsTime() {
		return lastRequestWorldsTime;
	}
	public void setLastRequestWorldsTime(long lastRequestWorldsTime) {
		this.lastRequestWorldsTime = lastRequestWorldsTime;
	}
	public long getLastRequestUnionsTime() {
		return lastRequestUnionsTime;
	}
	public void setLastRequestUnionsTime(long lastRequestUnionsTime) {
		this.lastRequestUnionsTime = lastRequestUnionsTime;
	}
	public long getLastRequestPrivatesTime() {
		return lastRequestPrivatesTime;
	}
	public void setLastRequestPrivatesTime(long lastRequestPrivatesTime) {
		this.lastRequestPrivatesTime = lastRequestPrivatesTime;
	}
	
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public long getLastSendWorldChatTime() {
		return lastSendWorldChatTime;
	}
	public void setLastSendWorldChatTime(long lastSendWorldChatTime) {
		this.lastSendWorldChatTime = lastSendWorldChatTime;
	}
	
}
