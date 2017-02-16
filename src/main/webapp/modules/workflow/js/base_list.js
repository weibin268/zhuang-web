
$(function() {


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
