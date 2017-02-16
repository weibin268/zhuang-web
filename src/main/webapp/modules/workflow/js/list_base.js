
$(function() {


});

function renderList(actionType,containerId,templateId,formId)
{
	if(!formId)
	{
		formId="conditionForm";
	}
	
	doPost(actionType,formId,function(data){

		var objData = eval("(" + data + ")");
		if (objData.success) {
			
			$("#" + containerId).html(template(templateId, objData.data));
		
			
		} else {
			debugger;
			alert(objData.message);
		}


	});
    
}

function doPost(actionType,formId,success) {
	
	var url = contextPath + "/wf/query?actionType="+actionType;

	$.ajax(url,
			{
				type : "POST",
				data : $("#" + formId).serialize(),
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
