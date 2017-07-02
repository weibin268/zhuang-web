
var settings={
    apiUrl:"/zhuang-web/upmsapi"
}

function getApiUrl() {
    return settings.apiUrl;
}

function doPost(action,data,successHandler) {

    $.ajax(getApiUrl()+"?action="+action,
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

jQuery(function () {
    jQuery(".easyui-datagrid").attr("url",getApiUrl()+"?action=Base-pageList&sql="+jQuery(".easyui-datagrid").attr("sql"));
})