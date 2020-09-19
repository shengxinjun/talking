$(function() {
	
/**
 * 页面加载时，初始化分页
 */
	var totalCount = $("#totalCount").val();
	var totalPage = totalCount%10>0?(totalCount-totalCount%10)/10+1:(totalCount-totalCount%10)/10;
	var keyword = $(".nav-search-input").val();
	var pageNumber = $("#pageNumber").val();
	var before=parseInt(pageNumber)-parseInt(1);
	var next=parseInt(pageNumber)+parseInt(1);
	var str ="";
	if(pageNumber!="" && pageNumber>1)
		str = "<a  href='/appointment/list?keyword="+keyword+"&pageNumber="+before+"'><button class='btn btn-light'>上一页</button></a> ";
	for(var i=1;i<=totalPage;i++){
		str+="<a  href='/appointment/list?keyword="+keyword+"&pageNumber="+i+"'><button class='btn btn-light'>"+i+"</button></a> ";
	}
	if(pageNumber<totalPage)
		str+="<a  href='/appointment/list?keyword="+keyword+"&pageNumber="+next+"'><button class='btn btn-light'>下一页</button></a> ";
	$("#pages").html(str);
	
	$('.nav-search-input').bind('keypress',function(event){  
	      if(event.keyCode == "13"){
	    	  keyword = $(".nav-search-input").val();
	    	  top.location.href="/appointment/list?keyword="+keyword;
	    	  window.event.returnValue=false;
	       }  

	   });  
	$("#update").click(function() {
		var id = $("#hiddenId").val();
		var keyword  = $(".nav-search-input").val();
		$.ajax({
			url : "/appointment/ensureConsumption",
			type : 'POST',
			dataType : 'json',
			data : {
				id : id,
			},
		}).done(function(e) {
			$("#myModal").modal("hide");
			if (e.code == 1) {
				location.href="/appointment/list?keyword="+keyword;
				message("已确认");
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