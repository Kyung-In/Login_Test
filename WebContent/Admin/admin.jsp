<%@page import="com.team3.vo.UserVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath(); // 사용자 리스트를 담은 리스트를 불러옴
	ArrayList<UserVO> getList = (ArrayList<UserVO>)request.getAttribute("userlist"); // AdminServlet에 저장시킨 getList를 받아옴
	
	/* for (UserVO vo : getList) {
		System.out.println("idx >> " + vo.getU_idx());
		System.out.println("name >> " + vo.getName());
		System.out.println("email >> " + vo.getEmail());
		System.out.println("phone >> " + vo.getPhone());
		System.out.println("pw >> " + vo.getPw());
		System.out.println("======================");
	} */
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Admin Page</title>
</head>
<body>
 <div class="container">
 <h1>Admin Page</h1><br>
관리자만 보여져야 함 <br><br>
로그인 한 사용자를 관리자라고 하면 <br>
로그인 한 사람만 이 페이지가 보여지게끔 처리 <br><br>
<a href="<%=path %>/logout.tm3"><button type="button" class="btn btn-success">logout</button></a><br><br><br>
  <h2>Hello</h2>
  <p>User Information Page</p>     
  <button type="button" class="btn btn-default" onclick="getUserInfo()">Edit</button>       
  <button type="button" class="btn btn-primary active" onclick="deleteInfo()">Delete</button>    
  <!--<button type="button" class="btn btn-danger" onclick="getUserInfo()">Test</button>-->
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Option</th>
        <th>Number</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Password</th>
      </tr>
    </thead>
    <tbody>
    <% for (UserVO vo : getList) { %>
      <tr>
        <td>
  			<input type="radio" name="opt"  value=<%=vo.getU_idx() %>>
        </td>
        <td><%=vo.getU_idx() %></td>
        <td><%=vo.getName() %></td>
        <td><%=vo.getEmail() %></td>
        <td><%=vo.getPhone() %></td>
        <td><%=vo.getPw() %></td>
      </tr>
       <% } %>
     </tbody>
  </table>
  
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">User Information Edit Page</h4>
        </div>
        <div class="modal-body">
  		<div class="form-group">
   		 <label for="name">Name </label>
   		 <input type="text" class="form-control" id="name">
  		</div>
  		<div class="form-group">
  		  <label for="email">Email</label>
   		 <input type="email" class="form-control" id="email">
  		</div>
  		<div class="form-group">
  		  <label for="phone">Phone</label>
   		 <input type="text" class="form-control" id="phone">
  		</div>
  		<div class="form-group">
  		  <label for="pwd">Password</label>
   		 <input type="password" class="form-control" id="pwd">
  		</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" onclick="editInfo()" data-dimiss="modal">Save</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
<!--  모달 끝 -->  

</div>
</body>
<script>
	function getUserInfo() {
		var u_idx = $('input[name=opt]:checked').val();
		alert($('input[name=opt]:checked').val());
		
		// ajax 사용!!!!
		var path = getContextPath();
		 $.ajax({
	         type: 'POST',
	         url: path + "/getuserinfo.tm3",
	         data: {
	             "u_idx" : u_idx
	         },
	         success: function(data){
	        	 console.log(data);
	        	 var test = data.split('/');
	        	 console.log(test);
	           if($.trim(test[0]) == 'OK'){
	            	 console.log('정보 불러오기 성공');
	            //  $("#u_idx").val(data);
	            	 $("#name").val(test[2]);
	            	 $("#email").val(test[3]);
	            	 $("#phone").val(test[4]);
	            	 $("#pwd").val(test[5]);
	            	 
	            	$("#myModal").modal('show');
	            	// alert("성공적으로 불러왔습니다.");
	             }
	             else{
	            	 console.log('서버 에러');
	             } 
	         },
	     });
	}
	
	function editInfo() {
		var u_idx = $('input[name=opt]:checked').val();
		var name = $("#name").val();
    	var email = $("#email").val();
    	var phone = $("#phone").val();
    	var pw = $("#pwd").val();
	//	alert(u_idx + " 정보를 수정하시겠습니까?");
	
		// ajax 사용!!!!
		var path = getContextPath();
		 $.ajax({
	         type: 'POST',
	         url: path + "/editinfo.tm3",
	         data: {
	             "u_idx" : u_idx,
	             "name" : name,
	             "email" : email,
	             "phone" : phone,
	             "pw" : pw
	         },
	         success: function(data){
	             if($.trim(data) == "OK"){
	            	 console.log('회원정보 수정 완료');
	            	 alert("회원정보가 성공적으로 수정 되었습니다")
	            	 location.reload();
	             }
	             else{
	            	 console.log('회원정보 수정 서버 에러');
	             }
	         },
	        //async: false
	     });
	}
	
	function deleteInfo() {
		var u_idx = $('input[name=opt]:checked').val();
		alert(u_idx + " 정보를 삭제하시겠습니까?" );
		
		// ajax 사용!!!!
		var path = getContextPath();
		 $.ajax({
	         type: 'POST',
	         url: path + '/deleteinfo.tm3', 
	         data: {
	             "u_idx" : u_idx
	         },
	         success: function(data){
	             if($.trim(data) == "OK"){
	            	 console.log('삭제 완료');
	            	 alert("성공적으로 삭제 되었습니다.");
	            	 location.reload(); // 페이지 새로고침
	             }
	             else{
	            	 console.log('서버 에러');
	             }
	         },
	        //async: false
	     });
	}
	
	// function(함수) 이름 변경 > id 부분에 수정이나 삭제할 idx 번호 삽입 > ajax 부분 url에 수정이나 삭제할 주소를 넣어 
	// 해당하는 주소로 가면 컨트롤러 쪽으로 보냄 > UserDAO로 연결해 수정이나 삭제 쿼리 날려서 처리
	function getContextPath() {
        var hostIndex = location.href.indexOf( location.host ) + location.host.length;
        return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1));
	}
	
	// 전달 매개변수는 idx(사용자 번호) / url 접속 정보(Servlet으로 보냄)

</script>
</html>