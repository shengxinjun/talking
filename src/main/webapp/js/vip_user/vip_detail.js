$(function() {
	
	/**
	 * 会员注册事件
	 */
	$("#update").click(function() {
		var id = $("#vipId").val();
		var username = $("#username").val();
		var telephone = $("#telephone").val();
		var sex = $("input[type='radio']:checked").val();
		var age = $("#age").val();
		var money = $("#money").val();
		
		if (!username) {
			message("请输入姓名");
			return;
		}
		if (!telephone) {
			message("请输入电话号码");
			return;
		}else if(telephone.length!=11){
			message("电话号码格式不正确");
			return;
		}
		if (age<1||age>100){
			message("年龄格式不正确");
			return;
		}
		$.ajax({
			url : "/vipUser/update",
			type : 'POST',
			dataType : 'json',
			data : {
				id : id,
				username : username,
				telephone : telephone,
				age : age,
				sex : sex,
				money :money
			},
		}).done(function(e) {
			if (e.code == 1) {
				location.href = '/vipUser/list';
			} else {
				message(e.message);
			}
		}).fail(function(e) {
		});
	});
	
	function message(text){
		if(text!=null)
		bootbox.dialog({
			message: "<span class='bigger-110'>"+text+"</span>",
			buttons:
			{
				/*"success" :
				 {
					"label" : "<i class='ace-icon fa fa-check'></i> Success!",
					"className" : "btn-sm btn-success",
					"callback": function() {
						//Example.show("great success");
					}
				},*/
				"danger" :
				{
					"label" : "ok",
					"className" : "btn-sm btn-danger",
					"callback": function() {
						//Example.show("uh oh, look out!");
					}
				}, 
				/*"click" :
				{
					"label" : "Click ME!",
					"className" : "btn-sm btn-primary",
					"callback": function() {
						//Example.show("Primary button");
					}
				}, 
				"button" :
				{
					"label" : "Just a button...",
					"className" : "btn-sm"
				}*/
			}
		});
	}
});