package com.team3.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team3.dao.UserDAO;
import com.team3.vo.UserVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginProc")
public class LoginProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginProc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("login dao");
		
		// userid와 userpw를 전달받아 UserVO를 전달해도 되고 userid, userpw 만 전달해도 되고 UserDAO에 InsertUser 메소드를 만든 것 처럼
		// UserDAO 클래스 안에 InsertUser 메소드 밑에 GerUser 라는 메소드를 만들어서 그 메소드에서 DB에서 slelect 쿼리 문으로 이메일과 패스워드를 검색
		// ID가 있는 사용자 정보를 받아와서 보내서  검색 결과를 비교해서 ID, PW 체크 후 맞으면 맞다고 하고 페이지 이동. 틀리면 틀리다 하고 다시 로그인
		
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		// 확인용
		System.out.println("이메일 : " + email);
		System.out.println("패스워드 : " + pw);
		
		UserVO uvo = new UserVO();
		uvo.setEmail(email);
		uvo.setPw(pw);
		String path = request.getContextPath();
		PrintWriter out = response.getWriter();
		
		try {
//			if (UserDAO.getUser(uvo)) { // uvo에 딤아서 전달 / 로그인 성공 시 유지시켜주기 위해 세션값 설정
			UserVO vo = UserDAO.getUser(email, pw);
			if (vo != null) { // 파라미터를 String 으로 넘김(email, pw)
				HttpSession session = request.getSession();
				session.setAttribute("email", vo.getEmail()); // 값을 저장 (변수와 값 저장)
				session.setAttribute("name", vo.getName());
//				response.sendRedirect(path + "/main.tm3");
				out.print("YES");
			}
			else {
//				response.sendRedirect(path + "/login.tm3");
				out.print("NO");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		/*try {
				if (UserDAO.getUser(email, pw)) { // uvo에 딤아서 전달
				}
		
				else {
					
				}
		}*/
		 // String 값 전달
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
