
$(function() {


});

function renderList(url,containerId,templateId,formId)
{
	if(!formId)
	{
		formId="conditionForm";
	}
	
	doPost(url,formId,function(data){

		var objData = eval("(" + data + ")");
		if (objData.success) {
			
			template.helper('dateFormat', dateFormat);
			
			$("#" + containerId).html(template(templateId, objData.data));
			
		} else {
			debugger;
			alert(objData.message);
		}


	});
    
}

var dateFormat = function (date, format) {
    if (date == "" || date == null)
        return '';
    var date = date.substr(0, 19);
    date = date.replace('T', ' ');
    var matches = date.match(/\d+/g);
    var year = matches[0];
    var month = matches[1];
    var d = matches[2];
    var hour = matches[3];
    var m = matches[4];
    var s = matches[5];
    format = format.replace("yyyy", year);
    format = format.replace("MM", month);
    format = format.replace("dd", d);
    format = format.replace("HH", hour);
    format = format.replace("mm", m);
    format = format.replace("ss", s);
    return format;
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
