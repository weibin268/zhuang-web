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
    	
    	var arrUserIds=[];
        arrUserIds.push("aa");
    	
    	for(var i=0;i<$options.length;i++)
    	{

    		var tempUserId=$options.get(i).value;
            arrUserIds.push(tempUserId);

    	}

    	$("#nextUserIds").val(arrUserIds.join(","));
    	
    });
    
});

function init_apply_form_submit()
{
	$("#selAllUsers").html("");
	$("#selSelectedUsers").html("");
	
}