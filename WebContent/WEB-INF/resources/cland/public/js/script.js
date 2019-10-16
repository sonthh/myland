$(document).ready(function(){
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
  
});