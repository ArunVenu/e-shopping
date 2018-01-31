$(function(){
	
	//solving active menu problem
	
	switch(menu){
	
	case 'About' :
		$('#about').addClass('active');
		break;
	
	case 'All Product' :
		$('#allproduct').addClass('active');
		break;
		
	case 'Contact Us' :
		$('#contact').addClass('active');
		break;
	
	default :
		if(menu == "Home") break;
		$('#allproduct').addClass('active');
		$('#a_'+menu).addClass('active');
	
		break;
	
	
	}
	
	
	
	
	
	
});