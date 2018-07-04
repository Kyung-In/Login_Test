<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
		String path = request.getContextPath();
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path %>/SignUp/style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<title>Join Us</title>
</head>
<body>
<script>
	console.log("<%=path %>/SignUp/script.js");
</script>
<form class="grop-from" id="signup">
  <div class="form-head"><span class="text"> </span><i class="icon-placeholder"></i></div>
  <div class="form-body"><span class="error-text">Please Fill Out This Field</span>
    <div class="form-controls">
      <input id="control-name" placeholder="Name"/>
      <input id="control-phone" placeholder="Phone No"/>
      <input id="control-email" placeholder="Email"/>
      <button type="submit" id="checkbtn" class="btn tbn-default">중복확인</button>
      <input id="control-password" placeholder="Password" type="password"/>
      <input id="control-password-repeat" placeholder="Confirm Password" type="password"/>
    </div>
  </div><a class="form-action"><i class="icon-action"></i></a>
  <span id="err-text"></span>
</form>
</body>
<script src="<%=path %>/SignUp/script.js"></script>
<script>

$('.form-head').click(function(){
	  
    if($(this).closest('.grop-from').attr('id')=='signup'){
        $('.grop-from').attr('id' , 'name');
        $('.icon-action').addClass('back');
    }  
    else if($(this).closest('.grop-from').attr('id')=='success'){
          $('.grop-from').attr('id' , 'signup');
          sendData("<%=path %>");         
          
          //location.href="<%=path %>/login.tm3"
    }
});


function sendData(path) {
	var name = $('#control-name').val();
    var phone = $('#control-phone').val();
    var email = $('#control-email').val();
    var pw = $('#control-password').val();
    var pw_chk = $('#control-password-repeat').val();
    
   /*  if (name.length = 0 && phone.length = 0 && email.length = 0 && pw.length = 0) {
    	alert('입력 항목을 채워주세요.');
    	return;
    } */
    
   // console.log("입력한 이름 : " + name);
   
   if (pw != pw_chk) {
	   alert("패스워드를 확인하세요.");
	   return;
   }
    
    var email_chk = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; 
    var phone_chk = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
    
   if (!phone_chk.test(phone)) {
	   alert("전화번호를 확인하세요.");
	   return;
   }
   
   if (!email_chk.test(email)) {
		alert("이메일을 확인하세요.");
		return;
   }
   
   var postUrl = path + "/joinus_proc.tm3" 
	$.post(postUrl,
		    {
		        name: name,
		        phone: phone,
		        email: email,
		        pw: pw
		    },
		    function(data, status){
		    	//console.log("status : " + status);
		    	//console.log("data : " + data);
		        if(status.trim() == "success" && data.trim() == "OK") {
		        	console.log("데이터 저장 성공");
		        	location.href="<%=path %>/login.tm3";
		        } else {
		        	console.log("데이터 저장 실패");
		        	alert("가입에 실패하였습니다. \n시스템 관리자에게 문의하세요.");
		        }
		    });
}
</script>
</html>