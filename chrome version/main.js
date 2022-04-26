$(function() {
	
	$("input").focus(function(){
		document.getElementById("tip").style.display="none";
	}) 
	$("#submit").click(function(e){
		var tkl = $("#talking").val();
		 $.ajax({
            url: "http://xjun.site:8091/tkl",
			type : 'GET',
			dataType : 'json',
			data : {
				tkl:tkl
			},
		}).done(function(e) {
				var data =  JSON.parse(e.message);
				if(data.code==200){
					window.open(data.data.click_url);
				}else{
					document.getElementById("tip").style.display="inline";
				}
		}).fail(function(e) {
		});
		
	});  
	 
});