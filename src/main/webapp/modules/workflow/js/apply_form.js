

var $actionType;

var $validateForm;

$(function() {

	if (!(isFirstTask == true && isRunningTask == true)&&!hasTask) {
		setFormReadonly();
	}
	
	$validateForm = $("#applyForm").validate();

});

$(function() {

	$actionType = $("#actionType");

	$("#toolbar_submit").click(function() {
		
		doSubmit("提交");
	
	});

	$("#toolbar_back").click(function() {
		
		doSubmit("退回");

	});

	$("#toolbar_delete").click(function() {
		
		if(!confirm("确定删除？"))
		{
			return false;
		}
		
		doPost("delete",function(data){
			
			var objData = eval("(" + data + ")");
			if (objData.success) {
				
				var objResult = eval("(" + objData.data + ")");
				location.replace(location.href);
				alert("删除成功！");
				
			} else {
				debugger;
				alert(objData.message);
			}
			
		});
		
	});

	
	$("#toolbar_save").click(
			function() {

				if(!validateForm()) return false;
				
				doPost("save",function(data) {
					var objData = eval("(" + data + ")");
					if (objData.success) {
						var objResult = eval("(" + objData.data + ")");
						alert("保存成功！");
						if (objResult.isNew) {
							var newUrl = window.location.href + "&taskId="
									+ objResult.taskId;
							location.replace(newUrl);
						}
					} else {
						debugger;
						alert(objData.message);
					}

				});
			});
	
	$("#toolbar_close").click(function(){
		
		window.close();
		
	});

});

function doPost(actionType,success) {
	
	$actionType.val(actionType);
	
	var url = contextPath + "/wf/engine";

	$.ajax(url,
			{
				type : "POST",
				data : $(document.forms["applyForm"]).serialize(),
				beforeSend : function() {
					if (false)
						return false;

				},
				complete : function() {

				},
				success : function(data, textStatus, jqXHR) {
					if (success) {
						success(data);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("XMLHttpRequest.status:" + XMLHttpRequest.status
							+ "\nXMLHttpRequest.readyState:"
							+ XMLHttpRequest.readyState + "\ntextStatus:"
							+ textStatus);
				}
			});
}

function setFormReadonly() {
	$("#applyForm input").attr("readonly", "readonly")
}

function validateForm()
{
	
	var result=false;
	
	result = $validateForm.form();

	if(typeof( myValidateForm)=="function")
	{
		result=myValidateForm();
	}
	
	return result;
}


function doSubmit(choice)
{
	if($("#taskId").val()=="")
	{
		alert("请先保存！");
		return false;
	}

	$("#env_choice").val(choice);
	
	init_apply_form_submit();
	
	doPost("retrieveNextTaskUsers",function(data){
		
		var objData = eval("(" + data + ")");
		if (objData.success) {

			var userList=objData.data.users ;
			var nextTaskKey=objData.data.taskKey;
			var nextTaskName=objData.data.taskName;

			$selAllUsers=$("#selAllUsers");
			$selSelectedUsers=$("#selSelectedUsers");
			
			$selAllUsers.html("");
			
			if(userList.length>1)
			{
				for(var i=0;i<userList.length;i++)
				{
					$opt=$("<option>").html(userList[i].userName).val(userList[i].userId);
					$selAllUsers.append($opt);
				}
			} else if (userList.length == 1) {
				$opt=$("<option>").html(userList[0].userName).val(userList[0].userId);	
				$selSelectedUsers.append($opt);
			}
			
			if(nextTaskKey=="_endTask_")
			{
				$opt=$("<option>").html("系统").val("system");
				$selSelectedUsers.append($opt);
			}
			
			$("#spNextTaskName").html(nextTaskName);
			
		} else {
			debugger;
			alert(objData.message);
		}
		
	});
	
	$("#submit-dialog").modal("show");
	
}