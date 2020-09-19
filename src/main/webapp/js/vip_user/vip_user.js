$(function() {
	
/**
 * 页面加载时，初始化分页
 */
	var totalCount = $("#totalCount").val();
	var totalPage = totalCount%10>0?(totalCount-totalCount%10)/10+1:(totalCount-totalCount%10)/10;
	var type = $("#type").val();
	var keyword = $(".nav-search-input").val();
	var pageNumber = $("#pageNumber").val();
	var before=parseInt(pageNumber)-parseInt(1);
	var next=parseInt(pageNumber)+parseInt(1);
	var str ="";
	if(pageNumber!="" && pageNumber>1)
		str = "<a  href='/vipUser/list?type="+type+"&keyword="+keyword+"&pageNumber="+before+"'><button class='btn btn-light'>上一页</button></a> ";
	for(var i=1;i<=totalPage;i++){
		str+="<a  href='/vipUser/list?type="+type+"&keyword="+keyword+"&pageNumber="+i+"'><button class='btn btn-light'>"+i+"</button></a> ";
	}
	if(pageNumber<totalPage)
		str+="<a  href='/vipUser/list?type="+type+"&keyword="+keyword+"&pageNumber="+next+"'><button class='btn btn-light'>下一页</button></a> ";
	$("#pages").html(str);
	
	$('.nav-search-input').bind('keypress',function(event){  
	      if(event.keyCode == "13"){
	    	  type = $("#type").val();
	    	  keyword = $(".nav-search-input").val();
	    	  top.location.href="/vipUser/list?type="+type+"&keyword="+keyword;
	    	  window.event.returnValue=false;
	       }  

	   });  
	$("#update").click(function() {
		var money = $("#money").val();
		var id = $("#hiddenId").val();
		$("#myModal").modal("hide");
		$.ajax({
			url : "/vipUser/invest",
			type : 'POST',
			dataType : 'json',
			data : {
				id : id,
				money :money
			},
		}).done(function(e) {
			if (e.code == 1) {
				message("充值成功");
				var type = $("#type").val();
		    	 var keyword = $(".nav-search-input").val();
		    	 var pageNumber = $("#pageNumber").val();
		          location.href="/vipUser/list?type="+type+"&keyword="+keyword+"&pageNumber="+pageNumber;

			} else {
				message(e.message);
			}
		}).fail(function(e) {
		});
	});
	
	$(".tag").click(function(e) {
		document.getElementById('context').value+=e.target.innerText;
		
	});
	$(".tag2").click(function(e) {
		document.getElementById('context2').value+=e.target.innerText;
		
	});
	$("#consumption").click(function(){
		var money = $("#money1").val();
		var id = $("#hiddenId1").val();
		var context = $("#context").val();
		$.ajax({
			url : "/consumption/insert",
			type : 'POST',
			dataType : 'json',
			data : {
				id : id,
				money :money,
				context :context
			},
		}).done(function(e) {
			if (e.code == 1) {
				$("#myModal1").modal("hide");
				message("已消费");
				var type = $("#type").val();
		    	 var keyword = $(".nav-search-input").val();
		    	 var pageNumber = $("#pageNumber").val();
				location.href="/vipUser/list?type="+type+"&keyword="+keyword+"&pageNumber="+pageNumber;
			} else {
				message(e.message);
			}
		}).fail(function(e) {
		});
	});
	$("#appointment").click(function(){
		var vipId = $("#hiddenId2").val();
		var context = $("#context2").val();
		var money = $("#money2").val();
		var dateStr = $("#appointDate").val();
		$.ajax({
			url : "/appointment/insert",
			type : 'POST',
			dataType : 'json',
			data : {
				vipId : vipId,
				money :money,
				context :context,
				dateStr :dateStr
			},
		}).done(function(e) {
			if (e.code == 1) {
				$("#myModal2").modal("hide");
				message("已创建预约");
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