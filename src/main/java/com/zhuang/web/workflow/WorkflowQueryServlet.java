package com.zhuang.web.workflow;

import java.io.IOException;
import java.util.HashMap;
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
import com.zhuang.web.workflow.WorkflowUtil;
import com.zhuang.workflow.WorkflowBeansFactory;
import com.zhuang.workflow.WorkflowQueryManager;
import com.zhuang.workflow.enums.ProcessMainVariableNames;
import com.zhuang.workflow.commons.PageModel;
import com.zhuang.workflow.models.FlowInfoModel;
import com.zhuang.workflow.models.ProcDefModel;

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
			
				int pageNo=Integer.valueOf(request.getParameter("pageNo"));
				Map<String, Object> condition=new HashMap<String, Object>();
				condition.put(ProcessMainVariableNames.PROC_DEF_KEY, request.getParameter(ProcessMainVariableNames.PROC_DEF_KEY));
				condition.put(ProcessMainVariableNames.PROC_TITLE, request.getParameter(ProcessMainVariableNames.PROC_TITLE));
				condition.put(ProcessMainVariableNames.PROC_TYPE, request.getParameter(ProcessMainVariableNames.PROC_TYPE));
				condition.put(ProcessMainVariableNames.PROC_CREATE_TIME+"_START", request.getParameter(ProcessMainVariableNames.PROC_CREATE_TIME+"_START"));
				condition.put(ProcessMainVariableNames.PROC_CREATE_TIME+"_END", request.getParameter(ProcessMainVariableNames.PROC_CREATE_TIME+"_END"));
				
				PageModel<FlowInfoModel> pageModel = workflowQueryManager.getMyTodoListPage(currentUserId, pageNo, 10, condition);
				myJsonResult.setSuccess(true);
				myJsonResult.setData(pageModel);
			    
			}else if(actionType.equals("mydone"))
			{
			
				int pageNo=Integer.valueOf(request.getParameter("pageNo"));
				Map<String, Object> condition=new HashMap<String, Object>();
				condition.put(ProcessMainVariableNames.PROC_DEF_KEY, request.getParameter(ProcessMainVariableNames.PROC_DEF_KEY));
				condition.put(ProcessMainVariableNames.PROC_TITLE, request.getParameter(ProcessMainVariableNames.PROC_TITLE));
				condition.put(ProcessMainVariableNames.PROC_TYPE, request.getParameter(ProcessMainVariableNames.PROC_TYPE));
				condition.put(ProcessMainVariableNames.PROC_CREATE_TIME+"_START", request.getParameter(ProcessMainVariableNames.PROC_CREATE_TIME+"_START"));
				condition.put(ProcessMainVariableNames.PROC_CREATE_TIME+"_END", request.getParameter(ProcessMainVariableNames.PROC_CREATE_TIME+"_END"));
				
				PageModel<FlowInfoModel> pageModel = workflowQueryManager.getMyDoneListPage(currentUserId, pageNo, 10, condition);
				myJsonResult.setSuccess(true);
				myJsonResult.setData(pageModel);
			    
			}else if (actionType.equals("myprocdef")) {
				
				List<ProcDefModel> procDefModels = workflowQueryManager.getProcDefList();
				
				myJsonResult.setSuccess(true);
				myJsonResult.setData(procDefModels);
			}
			
			
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
