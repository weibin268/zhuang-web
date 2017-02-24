package com.zhuang.web.workflow;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zhuang.web.util.FileUploadUtil;
import com.zhuang.workflow.WorkflowBeansFactory;

/**
 * Servlet implementation class WorkflowDeploymentServlet
 */
public class WorkflowDeploymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkflowDeploymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			FileItem fileItem = FileUploadUtil.getFileItem(request);
			InputStream inputStream=  fileItem.getInputStream();

			WorkflowBeansFactory.getWorkflowDeployment().deployByInputStream(fileItem.getName(), inputStream);
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("<script>alert('≤ø Ó≥…π¶!');window.close();</script>");
			
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
