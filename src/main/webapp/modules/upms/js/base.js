
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

function f_searchDatagrid(dgId,dgFormName) {

    dgId=dgId||"dg";
    dgFormName=dgFormName||"dg-form";
    $dg=$("#"+dgId);
    $dg.datagrid("options").url=$dg.attr("url_");
    var dgFormData = $(document.forms[dgFormName]).serialize();
    $dg.datagrid("reload",{param:dgFormData});

}

function f_openDialog(url,option,id) {

    id=id||1;
    option=option||{};

    jQuery("#dlg"+id).dialog($.extend({
        title: ' ',
        width: 700,
        height: 400,
        closed: false,
        cache: false,
        modal: true,
        maximizable: true,
        maximized: true,
        resizable: true,
        loadingMessage: "正在加载……",
        href: url,
        //content: content,
        buttons: "#dlgButtonSaveCancel"
    }, option));

}

function f_closeDialog(id) {
    id=id||1;
    jQuery("#dlg" + id).dialog('close');
}

jQuery(function () {
    f_initDatagrid();
});


