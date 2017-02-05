$(function(){


    $("#selAllUsers").on("dblclick","option",function(){
        $this = $(this);
        $("#selSelectedUsers").append($this.clone());
        $this.remove();
    });

    
    $("#selSelectedUsers").on("dblclick","option",function(){
        $this = $(this);
        $("#selAllUsers").append($this.clone());
        $this.remove();
    });

    $("#submit-dialog-ok").click(function(){
    	
    	$options = $("#selSelectedUsers option");
    	
        if($options.length==0)
        {
            alert("请选择处理人！");
            return false;
        }

    	var arrUserIds=[];
        
    	for(var i=0;i<$options.length;i++)
    	{

    		var tempUserId=$options.get(i).value;
            arrUserIds.push(tempUserId);

    	}

    	$("#nextUserIds").val(arrUserIds.join(","));
    	

    	$actionType.val("submit");
		doPost(function(data) {
			var objData = eval("(" + data + ")");
			if (objData.success) {
				
				alert("提交成功！");
				//window.close();
			} else {
				debugger;
				alert(objData.message);
			}

		});
    	
    });
    
});

function init_apply_form_submit()
{
	$("#selAllUsers").html("");
	$("#selSelectedUsers").html("");
	
}