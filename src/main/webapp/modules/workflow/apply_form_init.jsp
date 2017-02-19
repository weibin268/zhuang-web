<%@page import="com.zhuang.workflow.enums.WorkflowChoiceOptions"%>
<%@page import="org.activiti.engine.repository.ProcessDefinition"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.zhuang.workflow.activiti.ProcessDefinitionManager"%>
<%@page import="com.zhuang.workflow.WorkflowEngine"%>
<%@page import="com.zhuang.workflow.WorkflowBeansFactory"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.zhuang.workflow.util.ApplicationContextUtil"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	String taskId = request.getParameter("taskId");
	String defKey = request.getParameter("defKey");
	String formUrl="";
	
	
	if (taskId == null) {
		taskId = "";
	}

	Map<String, Object> formData = new HashMap<String, Object>();

	if (taskId != "") {

		WorkflowEngine workflowEngine = WorkflowBeansFactory.getWorkflowEngine();
		formData = workflowEngine.retrieveFormData(taskId);

	} else {
		ProcessDefinitionManager processDefinitionManager = WorkflowBeansFactory.getProcessDefinitionManager();
		ProcessDefinition processDefinition = processDefinitionManager.getProcessDefinitionEntityByKey(defKey);
		formData.put("proDefKey", processDefinition.getKey());
		formData.put("proDefName", processDefinition.getName());

	}

	for (Entry<String, Object> entry : formData.entrySet()) {
		request.setAttribute(entry.getKey(), entry.getValue());
	}


	Map<String, String> workflowFormMappings = (Map<String, String>) ApplicationContextUtil
	.GetApplicationContext().getBean("workflowFormMappings");

	String formName = "";

	Object objPage = workflowFormMappings.get(defKey);
	if (objPage != null) {
		formName = objPage.toString();
	} else {
		formName = defKey + ".jsp";
	}

	formUrl = "./forms/" + formName;
	
	if(request.getAttribute(WorkflowChoiceOptions.DELETE)==null)
	{
		if(request.getAttribute("isFirstTask")==null || (request.getAttribute("isFirstTask").toString()=="true" && request.getAttribute("isRunningTask").toString()=="true"))
		{
	request.setAttribute(WorkflowChoiceOptions.DELETE,true);
		}else
		{
	request.setAttribute(WorkflowChoiceOptions.DELETE,false);
		}
	}
	
	if(request.getAttribute(WorkflowChoiceOptions.BACK)==null)
	{
		request.setAttribute(WorkflowChoiceOptions.BACK,false);
	}
	
	if(request.getAttribute(WorkflowChoiceOptions.SUBMIT)==null)
	{
		if( request.getAttribute("isRunningTask")==null || request.getAttribute("isRunningTask").toString()=="true")
		{
	request.setAttribute(WorkflowChoiceOptions.SUBMIT,true);
		}else{
	request.setAttribute(WorkflowChoiceOptions.SUBMIT,false);
		}
	}
	
	if (request.getAttribute(WorkflowChoiceOptions.SAVE) == null) {
		if (request.getAttribute("isFirstTask") == null || (request.getAttribute("isFirstTask").toString() == "true" && request.getAttribute("isRunningTask").toString() == "true" )) {
			request.setAttribute(WorkflowChoiceOptions.SAVE, true);
		} else {
			request.setAttribute(WorkflowChoiceOptions.SAVE, false);
		}
	}
	
%>
<script type="text/javascript">

var hasTask = "${isFirstTask}" == "" ? true : false;
var isFirstTask = "${isFirstTask}" == "true" ? true : false;
var isRunningTask = "${isRunningTask}" == "true" ? true : false;
var currentTaskKey = "${currentTaskKey}";
var currentTaskName = "${currentTaskName}";
</script>

