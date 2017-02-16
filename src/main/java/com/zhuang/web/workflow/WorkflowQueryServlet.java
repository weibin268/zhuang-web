package com.zhuang.web.workflow;

import java.io.IOException;
import java.util.HashMap;
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
import com.zhuang.workflow.WorkflowQueryManager;
import com.zhuang.workflow.commons.PageModel;
import com.zhuang.workflow.models.FlowInfoModel;

/**
 * Servlet implementation class WorkflowQueryServlet
 */
public class WorkflowQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkflowQueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MyJsonResult myJsonResult = new MyJsonResult();
		String actionType = request.getParameter("actionType");
		
		WorkflowQueryManager workflowQueryManager = WorkflowBeansFactory.getWorkflowQueryManager();

		try {
			
			String currentUserId = WorkflowUtil.getCurrentUserId();
			
			if(actionType.equals("mytodo"))
			{
				
				Map<String, Object> condition=new HashMap<String, Object>();
			    PageModel<FlowInfoModel> pageModel = workflowQueryManager.getMyTodoListPage(currentUserId, 1, 10, condition);
				myJsonResult.setSuccess(true);
				myJsonResult.setData(pageModel);
			    
			}
			
			
		} catch (Exception e) {

			myJsonResult.setSuccess(false);
			myJsonResult.setMessage(e.getMessage());
			myJsonResult.setData(ExceptionUtils.getStackTrace(e));

		} finally {
			response.setCharacterEncoding("UTF-8");
			Gson gson = new GsonBuilder().create();
			gson.toJson(myJsonResult, response.getWriter());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
