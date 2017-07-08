package com.zhuang.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil {
	public static String readHtmlInOneLine(String filePath, String charsetName) {

		StringBuilder stringBuilder = new StringBuilder();

		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		try {

			fileInputStream = new FileInputStream(new File(filePath));
			inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String tempString;
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuilder.append(tempString);
			}

		} catch (Exception e) {

			throw new RuntimeException("��ȡ�ļ�:��" + filePath + "������", e);

		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				inputStreamReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return stringBuilder.toString();
	}
}
