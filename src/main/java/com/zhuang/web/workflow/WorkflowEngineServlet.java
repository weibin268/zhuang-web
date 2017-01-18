package com.zhuang.web.workflow;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String defKey = request.getParameter("defKey");
		String actionType = request.getParameter("actionType");
		String taskId = request.getParameter("taskId");
		String bizKey = request.getParameter("bizKey");
		String comment = request.getParameter("comment");
		String nextUserIds = request.getParameter("nextUserIds");

		String currentUserId = "zzwwbb";

		WorkflowEngine workflowEngine = WorkflowBeansFactory.getWorkflowEngine();

		Map<String, Object> formData = WorkflowUtil.getFormData(request);

		if (actionType.equals("save")) {

			if (taskId == null || taskId == "") {
				String[] instValues = workflowEngine.startNew(defKey, currentUserId, bizKey, formData).split("|");
				String instId = instValues[0];
				taskId = instValues[1];

			}
			workflowEngine.save(taskId, comment, formData);

		} else if (actionType.equals("submit")) {
			
			String[] arrNextUsers = nextUserIds.split(",");
			List<String> nextUsers = Arrays.asList(arrNextUsers);

			workflowEngine.submit(taskId, nextUsers, comment, formData);

		} else if (actionType.equals("back")) {
			
			workflowEngine.back(taskId, comment, formData);
		
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
