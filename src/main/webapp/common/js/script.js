$(function(){
	$("header .menu-btn").mouseenter(function(){
		if(!$(this).hasClass("active")){
			gsap.to($(this).find("span").eq(0),0.4, {width:0, yoyo:true, repeat:1, ease:Power3.easeOut});	
			gsap.to($(this).find("span").eq(1),0.4, {delay:0.1, width:0, yoyo:true, repeat:1, ease:Power3.easeOut});	
			gsap.to($(this).find("span").eq(2),0.4, {delay:0.2, width:0, yoyo:true, repeat:1, ease:Power3.easeOut});
		}	
	});
	
	$("header .menu-btn").mouseleave(function(){
		if(!$(this).hasClass("active")){
			gsap.to($(this).find("span").eq(0),0.4, {width:"100%", ease:Power3.easeOut});	
			gsap.to($(this).find("span").eq(1),0.4, {delay:0, width:"100%", ease:Power3.easeOut});
			gsap.to($(this).find("span").eq(2),0.4, {delay:0, width:"100%", ease:Power3.easeOut});
		}
	});
	
	$("header .menu-btn").click(function(){
		if(!$(this).hasClass("active")){
			gsap.to($(this).find("span").eq(0), 0.4,{top:14, rotate:"45deg", ease:Power3.easeOut});
			gsap.to($(this).find("span").eq(1), 0.4,{opacity:0, ease:Power3.easeOut});
			gsap.to($(this).find("span").eq(2), 0.4,{top:14, rotate:"-45deg", ease:Power3.easeOut, onComplete:function(){
				$("header .menu-btn").addClass("active");
			}});
			$("header").addClass("active");
			$("header .menu").fadeIn(500);	
		}else{
			$(this).removeClass("active");
			gsap.to($(this).find("span").eq(0), 0.4,{top:"", rotate:0, ease:Power3.easeOut});
			gsap.to($(this).find("span").eq(1), 0.4,{opacity:1, ease:Power3.easeOut});
			gsap.to($(this).find("span").eq(2), 0.4,{top:"", rotate:0, ease:Power3.easeOut});
			$("header").removeClass("active");
			$("header .menu").fadeOut(500);	
		}
	});
	
	$(".detail-icon").mouseover(function(){
		$(this).css("color", "#659EFF");
	});
	
	$(".detail-icon").mouseleave(function(){
		if(!$(this).parent().parent().next().is(":visible")){
			$(this).css("color", "gray");
		}
	});
	
	$(".accor-title").mouseover(function(){
		$(this).css("cursor", "pointer");
	});

	$(".accor-content").slideUp(0);
	$(".accor-title").click(function(){
		if(!$(this).parents(".accor-div").find(".accor-content").is(":visible")){
			$(this).parents(".accor-div").siblings().find(".accor-content").slideUp();
			$(this).parents(".accor-div").find(".accor-content").slideDown();
			$(this).find('.glyphicon-menu-down').css('color','#659EFF').removeClass("glyphicon-menu-down").addClass("glyphicon-menu-up");
			$(this).parents(".accor-div").siblings().find('.glyphicon-menu-up').css('color','gray').removeClass("glyphicon-menu-up").addClass("glyphicon-menu-down");
		} else{
			$(this).parents(".accor-div").find(".accor-content").slideUp();
			$(this).find(".glyphicon-menu-up").css('color','gray').removeClass("glyphicon-menu-up").addClass("glyphicon-menu-down");
		}
	});
});

function popup(url, w, h){
   var width = $(window).innerWidth();
   var height = $(window).innerHeight();
   
   var left = ((width / 2) - (w / 2));
   var top = ((height / 2) - (h / 2));
   var options = 'top=' + top + ', left=' + left + ', width=' + w + ', height=' + h + ', status=no, menubar=no, toolbar=no, resizable=no';
   window.open(url, "",  options);
}