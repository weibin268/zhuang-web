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

});

function init_apply_form_submit()
{
	$("#selAllUsers").html("");
	$("#selSelectedUsers").html("");
	
}