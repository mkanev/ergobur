/* ==============================================
 Sticky Header
 =============================================== */

jQuery(".navbar").sticky({
                           topSpacing: 0
                         });

/* ==============================================
 Smooth scroll navigation
 =============================================== */

$('.page-scroll a').bind('click', function (event) {
  var $anchor = $(this);
  $('html, body').stop().animate({
                                   scrollTop: $($anchor.attr('href')).offset().top
                                 }, 1000, 'easeInOutExpo');
  event.preventDefault();
});

$('.navbar-collapse ul li a').click(function () {
  $('.navbar-toggle:visible').click();
});

/* ==============================================
 Page loader
 =============================================== */

$(window).load(function () {
  $(".loader-item").delay(500).fadeOut();
  $("#pageloader").delay(1000).fadeOut("slow");
});

/* ==============================================
 Scroll to top
 =============================================== */

$(window).scroll(function () {
  if ($(this).scrollTop() > 100) {
    $('.scrollup').fadeIn();
  } else {
    $('.scrollup').fadeOut();
  }
});

$('.scrollup').click(function () {
  $("html, body").animate({ scrollTop: 0 }, 2000);
  return false;
});
   
