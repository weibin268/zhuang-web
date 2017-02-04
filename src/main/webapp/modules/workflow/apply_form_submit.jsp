<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id="submit-dialog" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	style="width: 500px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h5>下一步处理人</h5>
	</div>
	<div class="modal-body">

		<input id="nextUserIds" name="nextUserIds" type="hidden"
			value=""></input>


		<table>
			<tr>

				<td><select id="selAllUsers" multiple="true" style="height: 200px;">
						
				</select></td>

				<td></td>

				<td><select id="selSelectedUsers" multiple="true" style="height: 200px;">
				
				</select></td>

			</tr>

		</table>


	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		<button id="submit-dialog-ok" class="btn btn-primary">确定</button>
	</div>
</div>