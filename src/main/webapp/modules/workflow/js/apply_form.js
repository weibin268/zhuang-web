var hasTask = "${isFirstTask}" == "" ? true : false;
var isFirstTask = "${isFirstTask}" == "true" ? true : false;
var isRunningTask = "${isRunningTask}" == "true" ? true : false;
var currentTaskKey = "${currentTaskKey}";
var currentTaskName = "${currentTaskName}";

$(function() {

	if ((isFirstTask == true && isRunningTask == true)) {
		setFormReadonly();
	}

});

$(function() {

	var $actionType = $("#actionType");

	$("#toolbar_submit").click(function() {

		
		if($("#taskId").val()=="")
		{
			alert("请先保存！");
			return false;
		}
		
		$actionType.val("retrieveNextTaskUsers");
		doPost(function(data){
			
			var objData = eval("(" + data + ")");
			if (objData.success) {

				var userInfoList=objData.data ;
				alert(JSON.stringify(userInfoList));
				
			} else {
				debugger;
				alert(objData.message);
			}
			
		});
		
		$("#submit-dialog").modal("show");
		// doPost();
	});

	$("#toolbar_back").click(function() {
		$actionType.val("back");
		doPost();
	});

	$("#toolbar_save").click(
			function() {
				$actionType.val("save");
				doPost(function(data) {
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

});

function doPost(success) {
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
	$("#applyForm input").attr("disabled", "true")
}
