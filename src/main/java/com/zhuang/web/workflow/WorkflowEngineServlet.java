package com.zhuang.web.workflow;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuang.web.models.MyJsonResult;
import com.zhuang.web.util.WorkflowUtil;
import com.zhuang.workflow.WorkflowBeansFactory;
import com.zhuang.workflow.WorkflowEngine;

/**
 * Servlet implementation class WorkflowEngineServlet
 */
public class WorkflowEngineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorkflowEngineServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MyJsonResult myJsonResult = new MyJsonResult();

		String defKey = request.getParameter("defKey");
		String actionType = request.getParameter("actionType");
		String taskId = request.getParameter("taskId");
		String bizKey = request.getParameter("bizKey");
		String comment = request.getParameter("comment");
		String nextUserIds = request.getParameter("nextUserIds");

		String currentUserId = "zzwwbb";

		try {

			WorkflowEngine workflowEngine = WorkflowBeansFactory.getWorkflowEngine();

			Map<String, Object> formData = WorkflowUtil.getFormData(request);

			if (actionType.equals("save")) {

				boolean isNew=false;
				
				if (taskId == null || taskId == "") {

					String instValues = workflowEngine.startNew(defKey, currentUserId, bizKey, formData);
					String[] arrInstValues = instValues.split("\\|");
					String instId = arrInstValues[0];
					taskId = arrInstValues[1];
					
					isNew=true;
				}
				
				workflowEngine.save(taskId, comment, formData);

				myJsonResult.setSuccess(true);
				myJsonResult.setData("{taskId:" + taskId + ",isNew:" + isNew + "}");

			} else if (actionType.equals("submit")) {

				String[] arrNextUsers = nextUserIds.split(",");
				List<String> nextUsers = Arrays.asList(arrNextUsers);

				workflowEngine.submit(taskId, nextUsers, comment, formData);

			} else if (actionType.equals("back")) {

				workflowEngine.back(taskId, comment, formData);

			}
		} catch (Exception e) {
			
			myJsonResult.setSuccess(false);
			myJsonResult.setMessage(e.getMessage());
			myJsonResult.setData(ExceptionUtils.getStackTrace(e));
		
		}finally {
			Gson gson=new GsonBuilder().create();
			gson.toJson(myJsonResult,response.getWriter());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
