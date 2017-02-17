
$(function() {

	initPager();

});

function renderList(formId,containerId,templateId,pageNo)
{
	var url=$("#"+formId).attr("action");
	
	url=url+"&pageNo="+pageNo;
	
	doPost(url,formId,function(data){

		var objData = eval("(" + data + ")");
		if (objData.success) {
						
			$("#" + containerId).html(template(templateId, objData.data));
			
		} else {
			debugger;
			alert(objData.message);
		}


	});
    
}

function initPager()
{
	$(document).on("click","#list-pager a",function(){
		
		$this=$(this);
		var pageNo=$this.attr("data-pager");
		var enabled=$this.attr("data-enabled");
		
		if(enabled=="true")
		{
            renderList("conditionForm", "list-container", "list-template",pageNo);
		}
		
	});	
}

function doPost(url,formId,success) {
	
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
