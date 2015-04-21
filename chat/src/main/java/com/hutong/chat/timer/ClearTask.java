package com.hutong.chat.timer;

import java.util.TimerTask;

import com.hutong.chat.common.ChatStatic;

public class ClearTask extends TimerTask {

	@Override
	public void run() {
		ChatStatic.clearChat();
	}
}
