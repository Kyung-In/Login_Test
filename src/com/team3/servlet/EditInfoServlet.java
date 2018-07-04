package com.team3.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.UserDAO;
import com.team3.vo.UserVO;

/**
 * Servlet implementation class EditInfoServlet
 */
@WebServlet("/EditInfoServlet")
public class EditInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		// admin.jsp 에 있는 modal의 저장 버튼을 누르면 ajax 가 있는 함수(editInfo())가 실행되어 이 서블릿으로 들어옴.  전달되는 값은 u_idx, name, email, phone, pw. 
		//  전달되는 값을 받아 각각의 정보를 업데이트 쿼리를 날려서 수정. 업데이트 쿼리가 실행하는 UserDAO를 만들어 호출. 
		// 성공하면 다시 admin.jsp 에서 ajax > success 쪽으로 성공 메시지를 보내 성공했다고 하고 다시 페이지 로드
		
		System.out.println("admin.jsp 의 modal 에서 Save 버튼으로 넘어옴~!");
		String u_idx = request.getParameter("u_idx");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String pw = request.getParameter("pw");
		System.out.println(u_idx + " /  " + name + " / " + email + " / " + phone + " / " + pw); // 잘 넘어왔는지 확인용
		
		UserVO vo = new UserVO();
		vo.setU_idx(Integer.parseInt(u_idx));
		vo.setName(name);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setPw(pw);
		
		try {
			UserDAO.editInfo(vo);
			response.getWriter().println("OK"); // 성공하면 OK , 다시 페이지 로드(admin.jsp)
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
