$(document).ready(function(){


  $(".submenu > a").click(function(e) {
    e.preventDefault();
    var $li = $(this).parent("li");
    var $ul = $(this).next("ul");

    if($li.hasClass("open")) {
      $ul.slideUp(350);
      $li.removeClass("open");
    } else {
      $(".nav > li > ul").slideUp(350);
      $(".nav > li").removeClass("open");
      $ul.slideDown(350);
      $li.addClass("open");
    }
  });
  
  
  /* tranhuuhongson
   * scroll to top
   * */
  $(window).scroll(function() {
		if ($(this).scrollTop() > 100) {
			$('.be-scroll-top').fadeIn();
		} else {
			$('.be-scroll-top').fadeOut();
		}
	});
	$(".be-scroll-top").click(function() {
		$("html, body").animate({ scrollTop: 0 }, "slow");
		return false;
	});
	
   $('.custom-control-label').click(function() {
       var x = $('input.custom-control-input');
       //console.log(x.is(':checked')); cũng là một cách để kiểm tra có check hay không
       x[0].checked = !x[0].checked;
   });	
  
});