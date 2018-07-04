package com.team3.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team3.dao.UserDAO;
import com.team3.vo.UserVO;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String site = "Admin/admin.jsp";
		
		// 로그인이 되었는지 확인 (관리자 페이지로 넘기기 위해) > session
		HttpSession session = request.getSession();
		String idCheck = (String)session.getAttribute("email"); // session은 Object
		
		// idCheck 값이 있으면 admin.jsp로 보내고 없으면 login.jsp로 보냄(login.tm3)
		if (idCheck == null) {
			site = "login.tm3";
			response.sendRedirect(site); // login page로 리다이렉트
			return; // if문 안의 코드 진행을 막고 login page로 이동
		}
		
		// DB 정보를 불러와 사용자 리스트 보여주기
		// UserDAO에서 회원정보 리스트를 받아오는 거 만들기
		try {
			ArrayList<UserVO> getList = UserDAO.getUser();
			// getList를 브라우저(jsp)에 전달해야 하는데 전달할 수 있는 방법이
			request.setAttribute("userlist", getList); // (식별자, 객체) userlist 이름으로 저장
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(site); // Admin/admin.jsp 로 전달
		RequestDispatcher dis = request.getRequestDispatcher(site);
		dis.forward(request, response); // request에 저장되어 있는 정보를 같이 전달함
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
