package com.zhuang.web.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.zhuang.web.http.ParamConvert;

public class ParamConvertTest {

	@Test
	public void testToString()
	{
		Map<String, String> mapParam=new HashMap<String, String>();
		
		mapParam.put("a", "1");
		mapParam.put("b", "2");
		
		System.out.println(ParamConvert.toString(mapParam));;
		
	}
	
}
