var path= location.pathname;
$(document).ready(function(){	
	$('#side a').each(function(i){
		var currentLink= $(this).attr('href'); 
		if(currentLink == path){
			$(this).addClass('active');
			if($(this).parents('li').hasClass('panel')){
				var panel = $(this).parents('.panel')
				$(this).parents('ul').addClass('in')
			}
		}
	})
});


