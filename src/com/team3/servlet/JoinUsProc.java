package com.team3.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.UserDAO;
import com.team3.db.DBConn;
import com.team3.vo.UserVO;

/**
 * Servlet implementation class JoinUsProc
 */
@WebServlet("/JoinUsProc")
public class JoinUsProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinUsProc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		// name, phone, email, pw 데이터 받기
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		// 확인용
		System.out.println("이름 : " + name);
		
		UserVO uvo = new UserVO();
		uvo.setName(name);
		uvo.setPhone(phone);
		uvo.setEmail(email);
		uvo.setPw(pw);
		
		// static 으로 만들었기 때문에 바로 사용 가능 / 원래는 객체 생성 후 만듦
		
		PrintWriter out = response.getWriter();
		
		try {
			UserDAO.InsertUser(uvo);
			out.println("OK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// DB 연결해서 데이터 삽입 / DB 커넥터(jar 파일 라이브러리) / 우리 DB는 MySQL / DAO(클래스)를 만들어 데이터 삽입 쿼리를 넣는 실행 메소드 생성
		// DAO 안에는 테이블 정보(컬럼값)를 담는 클래스가 필요(VO 또는 DTO)
		
		
		/*response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("이름 : " + name + "<br>");
		out.println("폰 번호 : " + phone + "<br>");
		out.println("이메일 : " + email + "<br>");
		out.println("비밀번호 : " + pw + "<br>");*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
