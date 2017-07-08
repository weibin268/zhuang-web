
var g_settings={
    contextPath:"/zhuang-web",
    apiPath:"/zhuang-web/upmsapi",
    upmsPath:"/modules/upms"
}


function f_post(action,data,successHandler) {

    $.ajax(g_settings.apiPath+"?action="+action,
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

function f_includeHtmlFile(url) {

    url=g_settings.apiPath+"?action=Template-includeHtml&url="+g_settings.upmsPath+url;

    document.write("<script src='"+url+"'></script>");

}

function f_initDatagrid()
{
    var $datagrid=jQuery(".easyui-datagrid");

    var url=g_settings.apiPath+"?action=Base-pageList&sql="+$datagrid.attr("sql");

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



