package com.hutong.chat.web.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WebUtil {

	private static final Log logger = LogFactory.getLog(WebUtil.class);

	/**
	 * push request params key-value to map, the values are all url decoded
	 */
	public static final Map<String, String> getParamMapByRequest(HttpServletRequest request) {
		Map<String, String> requestParamsMap = new HashMap<String, String>();
		try {
			Enumeration<String> e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String param = e.nextElement();
				String value = request.getParameter(param);
				// logger.debug(String.format(
				// "getRequestParamMap(HttpServletRequest) - [%s=>%s]",
				// param, value));

				if (value != null) {
					requestParamsMap.put(param, value);
				}
			}
			/* 导致签名计算错误 */
			// requestParamsMap.put(CommandCons.PARAM_SERVER_REMOTE_IP_ADDRESS,getIpAddr(request));

		} catch (Exception e) {
			logger.error("Mapping request parameter exception " + e.getMessage());
		}
		return requestParamsMap;
	}

}
