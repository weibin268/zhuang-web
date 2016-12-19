package com.zhuang.web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadUtil {

	public static List<FileItem> getFileItems(HttpServletRequest request) throws FileUploadException {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		return upload.parseRequest(request);
	}

	public static FileItem getFileItem(HttpServletRequest request) throws FileUploadException, IOException {

		List<FileItem> fileItems = getFileItems(request);
		if (fileItems.size() > 0) {
			return fileItems.get(0);

		} else {
			return null;
		}
	}

	public static void saveAs(HttpServletRequest request, String fileName) throws IOException, FileUploadException {

		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		FileItem fileItem=null;
		try {
			fileItem = getFileItem(request);

			inputStream=fileItem.getInputStream();
			
			fileOutputStream = new FileOutputStream(new File(fileName));
			byte[] buffer = new byte[1024];

			int count;
			while ((count = inputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, count);
			}

			fileOutputStream.flush();

		}finally {
			
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if(fileItem!=null)
			{
				fileItem.delete();
			}
		}

	}
}
