<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="submit-dialog" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h5>选择一下步处理人</h5>
	</div>
	<div class="modal-body">


		<input id="nextUserIds" name="nextUserIds" type="hidden"
			value="user1,user2"></input>

	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		<button class="btn btn-primary">确定</button>
	</div>
</div>