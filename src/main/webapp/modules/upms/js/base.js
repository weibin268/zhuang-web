
var g_settings={
    apiUrl:"/zhuang-web/upmsapi"
}

function f_getApiUrl() {
    return g_settings.apiUrl;
}

function f_post(action,data,successHandler) {

    $.ajax(f_getApiUrl()+"?action="+action,
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

function f_initDatagrid()
{
    var $datagrid=jQuery(".easyui-datagrid");

    var url=f_getApiUrl()+"?action=Base-pageList&sql="+$datagrid.attr("sql");

    //$datagrid.attr("url",url);
    $datagrid.attr("url_",url);
}

jQuery(function () {
    f_initDatagrid();
});


