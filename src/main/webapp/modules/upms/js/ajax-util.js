
function doPost(action,data,successHandler) {

    var url= "/zhuang-web/upmsapi?action=";

    $.ajax(url+action,
        {
            type : "POST",
            data : data,
            beforeSend : function() {
            },
            complete : function() {
            },
            success : function(data, textStatus, jqXHR) {

                var objData=eval("("+data+")");
                if(objData.success)
                {
                    if (successHandler) {
                        successHandler(objData.data,objData.message);
                    }
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                debugger;
                alert("XMLHttpRequest.status:" + XMLHttpRequest.status
                    + "\nXMLHttpRequest.readyState:"
                    + XMLHttpRequest.readyState + "\ntextStatus:"
                    + textStatus);
            }
        });

}
