package com.team3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("*.tm3")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String url = request.getRequestURL().toString();
		String url2 = request.getRequestURI();
		
		System.out.println(url);
		System.out.println(url2);
		
		String [] sub = url.split("/");
		String [] sub2 = url2.split("/");
		
/*		int idx = 0;
		for(String s : sub) {
			System.out.println(idx++ + s);
		}*/
		
		String subUrl = sub[4];
		String subUrl2 = sub2[0];
/*		
		System.out.println(subUrl);
		System.out.println(subUrl2);*/
		
		String site = null;
		
		switch (subUrl) {
		case "login.tm3":
//			response.getWriter().append("login page");
			site = "Lion/login.jsp"; // View
			break;
		case "login_proc.tm3":
			site = "LoginProc"; // Model
			break;
		case "joinus.tm3":
			site = "SignUp/joinus.jsp"; // View
			break;
		case "joinus_proc.tm3":
			site = "JoinUsProc"; // Model
			break;
		case "main.tm3":
			/*response.getWriter().append("login success");
			if (site == null) {
				return;
			}*/
			site = "MainServlet"; 
			break;
		case "admin.tm3":
//			response.getWriter().append("only admin page");
			site = "AdminServlet"; // Model
			break;
		case "logout.tm3":
			site = "LogoutServlet"; // Model
			break;
		case "idcheck.tm3":
			site = "IdCheckServlet"; // Model
			break;
		case "deleteinfo.tm3":
			site = "DeleteInfoServlet"; // Model
			break;
		case "getuserinfo.tm3":
			site = "GetInfoServlet"; // Model
			break;
		case "editinfo.tm3":
			site = "EditInfoServlet"; // Model
			break;
		default:
			response.getWriter().append("error page");
			break;
		}
		
		

		// 서버 내에서 페이지 이동
		RequestDispatcher dis = request.getRequestDispatcher(site);
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
