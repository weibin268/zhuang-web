package com.zhuang.web.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

public class ParamConvert {

	public static String toString(Map<String, String> param) {

		String result = "";

		List<String> lsParam = new ArrayList<String>();

		for (Entry<String, String> entry : param.entrySet()) {
			lsParam.add(entry.getKey() + "=" + entry.getValue());
		}

		result = StringUtils.join("&", lsParam);

		return result;

	}

}
