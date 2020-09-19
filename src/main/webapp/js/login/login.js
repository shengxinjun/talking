$(function() {
	/**
	 * 页面加载初始化
	 */
	var name=getCookie("name");
	var password=getCookie("password");
	var remember=getCookie("remember");
	if(remember && remember==1){
		$('#remember').prop('checked',true);
        $('#name').val(name);
        $('#password').val(password);
	}
	/**
	 * 登录事件
	 */
	$("#login").click(function() {
		var name = $("#name").val();
		var password = $("#password").val();
		var remember;
		if ($("#remember").is(':checked')) {
			remember = 1;
		} else {
			remember = 0;
		}
		if (!name) {
			message("请输入用户名");
			return;
		}
		if (!password) {
			message("请输入密码");
			return;
		}
		$.ajax({
			url : "/login/login",
			type : 'POST',
			dataType : 'json',
			data : {
				name : name,
				password : password,
				remember : remember
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
	/**
	 * 注册事件
	 */
	$("#register").click(function() {
		var telephone = $("#reg_telephone").val();
		var email = $("#reg_email").val();
		var username = $("#reg_username").val();
		var password = $("#reg_password").val();
		var rpassword = $("#reg_rpassword").val();
		var user={};
		if(!(/^1[34578]\d{9}$/.test(telephone))){
			message("手机号码格式有误");
			return;
		}
		if(username==""){
			message("用户名格式有误");
			return;
		}
		if(password.length<5){
			message("密码长度太短");
			return;
		}
		if(password==rpassword){
			user.telephone=telephone;
			user.email=email;
			user.name=username;
			user.password=password;
		}else{
			message("两次输入密码不同");
			return;
		}
		var str = JSON.stringify(user);
		$.ajax({
			url : "/register",
			type : 'POST',
			dataType : 'json',
			data : {
				user:str
			},
		}).done(function(e) {
			if (e.code == 1) {
				location.href = '/';
			} else {
				message(e.message);
			}
		}).fail(function(e) {
		});
	});
	/**
	 * 获取验证码
	 */
	$("#getCheckCode").click(function() {
		var email = $("#forg_email").val();
		var adip=returnCitySN["cip"];
		$.ajax({
			url : "/sendCheckCode",
			type : 'POST',
			dataType : 'json',
			data : {
				email:email,
				ip:adip
			},
		}).done(function(e) {
			if (e.code == 1) {
				message("验证码已发送，请注意查收！");
			} else {
				message(e.message);
			}
		}).fail(function(e) {
		});
	});
	/**
	 * 找回密码
	 */
	$("#getPassword").click(function() {
		var email = $("#forg_email").val();
		var checkCode = $("#forg_checkCode").val();
		
		$.ajax({
			url : "/checkCode",
			type : 'POST',
			dataType : 'json',
			data : {
				email:email,
				code:checkCode
			},
		}).done(function(e) {
			if (e.code == 1) {
				message("密码已发送到您的邮箱，请注意查收！");
			} else {
				message(e.message);
			}
		}).fail(function(e) {
		});
	});
	
	
	/**
	 * 获取cookie方法
	 */
	function getCookie(cookieName) {
		var allcookies = document.cookie;
		var cookiePos = allcookies.indexOf(cookieName); // 索引的长度
		if (cookiePos != -1) {
			// 把cookie_pos放在值的开始，只要给值加1即可。
			cookiePos += cookieName.length + 1;
			var cookieEnd = allcookies.indexOf(";", cookiePos);

			if (cookieEnd == -1) {
				cookieEnd = allcookies.length;
			}
			var value = decodeURI(allcookies.substring(cookiePos, cookieEnd));
		}
		return value;
	}
	
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