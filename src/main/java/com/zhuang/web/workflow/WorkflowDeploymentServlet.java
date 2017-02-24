package com.zhuang.web.workflow;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuang.web.models.MyJsonResult;
import com.zhuang.web.util.FileUploadUtil;
import com.zhuang.workflow.WorkflowBeansFactory;
import com.zhuang.workflow.WorkflowDeployment;
import com.zhuang.workflow.commons.PageModel;
import com.zhuang.workflow.models.DeploymentInfoModel;
import com.zhuang.workflow.models.FlowInfoModel;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String actionType = request.getParameter("actionType");

		if (actionType.equals("upload")) {

			try {

				FileItem fileItem = FileUploadUtil.getFileItem(request);
				InputStream inputStream = fileItem.getInputStream();

				WorkflowBeansFactory.getWorkflowDeployment().deployByInputStream(fileItem.getName(), inputStream);

				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("<script>alert('≤ø Ó≥…π¶!');window.close();</script>");

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (actionType.equals("list")) {
			MyJsonResult myJsonResult = new MyJsonResult();

			try {

				int pageNo = Integer.valueOf(request.getParameter("pageNo"));
				Map<String, Object> condition = new HashMap<String, Object>();

				WorkflowDeployment workflowDeployment = WorkflowBeansFactory.getWorkflowDeployment();
				PageModel<DeploymentInfoModel> pageModel = workflowDeployment.getDeploymentInfoPage(pageNo, 10,
						condition);
				myJsonResult.setSuccess(true);
				myJsonResult.setData(pageModel);
			} catch (Exception e) {

				myJsonResult.setSuccess(false);
				myJsonResult.setMessage(e.getMessage());
				myJsonResult.setData(ExceptionUtils.getStackTrace(e));

			} finally {
				response.setCharacterEncoding("UTF-8");
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				gson.toJson(myJsonResult, response.getWriter());
			}
		}
	}

}
