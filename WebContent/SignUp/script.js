$('.form-action').click(function(){ 
 
    var form_id = $('.grop-from').attr('id');
      $('.icon-action').addClass('back');
  
    if($('#control-' + form_id).val() != ''){
    	console.log(form_id);
      switch (form_id) {
          case 'name':
              form_id = "phone";
              break;
          case "phone":
        	  console.log("폰입력창?");
        	  form_id = "email";
              break;
          case "email":
        	  console.log("이메일 입력 후 클릭하면 여기로 들어옴.");
        	  var email = $('#control-' + form_id).val();
        	  console.log("사용자가 입력한 내용 >> " + email);
        	  var aa = id_check(email);
        	  console.log("사용여부 >> " + aa);
        	  if (aa == false) {
        		  $('#err-text').html('중복된 이메일을 입력하였습니다.');
        		  form_id = "email"; // 중복된 아이디가 있으면 다시 email 폼으로 보냄
        	  } 
        	  else {
        		  $('#err-text').html('');
        		  form_id = "password";  // 중복된 아이디 없이 사용할 수 있으면 password 폼으로 보냄
        	  }
              break;
          case "password":
              form_id = "password-repeat";
              break;
          case "password-repeat":
              form_id = "success";
              break;   
        case "success":
              form_id = "signup";
              break; 
      }
       $('.icon-action').addClass('back');
  }
  
  else{
     switch (form_id) {
          case 'name':
              form_id = "signup";
              $('.icon-action').removeClass('back');
              break;
          case "phone":
              form_id = "name";
              break;
          case "email":
              form_id = "phone";
              break;
          case "password":
              form_id = "email";
              break;
          case "password-repeat":
              form_id = "password";
              break; 
         case "success":
              form_id = "signup";
              break; 
      }
     $('.icon-action').removeClass('back');
  }
 
  $('.grop-from').attr('id' , form_id);
  
});

$('input').keyup(function(){
   $('.grop-from').removeClass('error');
    $('.error-text').fadeOut();
    
    if($(this).val()!=''){
      $('.icon-action').removeClass('back');
    }
  else{
    $('.icon-action').addClass('back');
  }
})

function getContextPath() {
        var hostIndex = location.href.indexOf( location.host ) + location.host.length;
        return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}

function id_check(id){
	var isID = false;
	var path = getContextPath();
	 $.ajax({
         type: 'POST',
         url: path + '/idcheck.tm3', 
         data: {
             "id" : id
         },
         success: function(data){
             if($.trim(data) == "OK"){
            	 console.log("사용불가능");
            	 isID = false;
//                 $('#checkMsg').html('<p style="color:blue">사용가능</p>'); 
             }
             else{
            	 console.log("사용가능");
            	 isID = true;
//                 $('#checkMsg').html('<p style="color:red">사용불가능</p>');
             }
         },
        async: false // ajacx가 비동기로 동작하기 때문에 데이터를 동기화 시키기 위해
     });    //end ajax    
	 return isID;
}
