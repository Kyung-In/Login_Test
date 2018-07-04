package com.team3.dao;

import java.sql.Connection;

/*
 *  수정쿼리
 *  update user set name = '일' where u_idx = 7;
 * 
 *  삭제쿼리
 *  delete from user where u_idx = 21;
 *  
 *  추가쿼리
 *  insert into user(name, phone, email, pw)
	 values ('빅데이터' , '010-2112-0592', 'bigdata@aa.com', '1111')
	 
	 update user set state=0 where u_idx=14
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.team3.db.DBConn;
import com.team3.vo.UserVO;

public class UserDAO {
	public static void InsertUser(UserVO vo) throws Exception {
		// DB 접속
		Connection db = DBConn.getConnection();
		
		// 쿼리 날려서 유저 정보를 삽입
		// insert into user (name, phone, email, pw) values ('이름', '010-1234-1234', 'a@a.com', '1234')
		String sql = "insert into user (name, phone, email, pw) values (?, ?, ?, ?)";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPhone());
		pstmt.setString(3, vo.getEmail());
		pstmt.setString(4, vo.getPw());
		
		// 쿼리 실행
		pstmt.executeUpdate();
		db.close();
	}
	
	// 사용자 정보 수정
	public static void editInfo(UserVO vo) throws Exception {
		Connection db = DBConn.getConnection();
		
		String sql = "update user set name = ?, phone = ?, email = ?, pw = ? where u_idx = ?";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPhone());
		pstmt.setString(3, vo.getEmail());
		pstmt.setString(4, vo.getPw());
		pstmt.setInt(5, vo.getU_idx());
		
		pstmt.executeUpdate();
		db.close();
	}
	
	// 사용자 정보 삭제 메소드
	public static void deleteInfo(String u_idx) throws Exception {
		// DB 접속
		Connection db = DBConn.getConnection();

		String sql = "delete from user where u_idx = ?"; // 전체가 문자 그래서 51라인 setString으로 받아도 괜찮
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, u_idx);
		
		// 쿼리 실행
		pstmt.executeUpdate();
		db.close();
	}
	
	
	// VO 객체를 넣어서 email, ps 정보 확인
	public static Boolean getUser(UserVO vo) throws Exception {
		
		Connection db = DBConn.getConnection();
		
		// 쿼리 날려서 유저 정보를 검색
		String sql = "select * from user where email = ?";
//		String sql = "select * from user where email = ? and pw = ?";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, vo.getEmail());
		// DB에서 email과 함께 검색해서 넣어도 됨. 검색된 데이터가 있으면 로그인. 없으면 로그인 실패
//		pstmt.setString(2, vo.getPw()); 
		
		// email 만으로 검색해서 데이터가 있으면 pw DB 데이터와 vo.getPw() 데이터와 비교
		
		// 쿼리 실행해서 결과값 반환
		Boolean isLogin = false;
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) { // 검색된 데이터가 있으면 로그인 / 패스워드 체크 후 로그인 성공 여부
			String dbPw = rs.getString("pw");
			if (dbPw.equals(vo.getPw())) { // 입력한 패스워드가 데이터베이스 패스워드와 일치할 때
				isLogin = true;
			}
			else { // 불일치할 때
				// isLogin 이 false가 기본값이라서 else  문은 사실상 불필요~!~!!!!~!!~~~~~~~~!!!!
			}
		}
		else { // email 정보가 없음
			
		}
		
		db.close();
		return isLogin;
	}
	
	public static UserVO getUserInfo(String u_idx) throws Exception {
		
		Connection db = DBConn.getConnection();
		
		// 쿼리 날려서 유저 정보를 검색
		String sql = "select * from user where u_idx = ?";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, u_idx);
		
		UserVO vo = null; // 사용자 정보를 담는 객체
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) { 
			vo = new UserVO();
			vo.setU_idx(rs.getInt("u_idx")); // 담아온 값 세팅
			vo.setName(rs.getString("name")); // 담아온 값 세팅
			vo.setEmail(rs.getString("email")); // 담아온 값 세팅
			vo.setPhone(rs.getString("Phone")); // 담아온 값 세팅
			vo.setPw(rs.getString("pw")); // 담아온 값 세팅
		}
		db.close();
		return vo;
	}
	
	// String email, pw 를 매개변수(파라미터)로 넣어서  UserVO 값을 반환
	// 들어가는 매개변수는 String email, String pw / 리턴받는 형은 UserVO
		public static UserVO getUser(String email, String pw) throws Exception {
			
			Connection db = DBConn.getConnection();
			
			// 쿼리 날려서 유저 정보를 검색
//			String sql = "select * from user where email = ?. ?";
			String sql = "select * from user where email = ? and pw = ?";
			PreparedStatement pstmt = db.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pw);
			UserVO vo = null;
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 검색된 데이터가 있으면 로그인 / 패스워드 체크 후 로그인 성공 여부
				vo = new UserVO();
				vo.setName(rs.getString("name")); // 담아온 값 세팅
				vo.setEmail(rs.getString("email")); // 담아온 값 세팅
				vo.setPhone(rs.getString("Phone")); // 담아온 값 세팅
			}
			db.close();
			return vo;
		}
		
		
		public static int getMember(String id) throws Exception {
			
			Connection db = DBConn.getConnection();
			
			String sql = "select * from user where email = ? ";
			PreparedStatement pstmt = db.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int ret = 0; // return / id가 없으면 0 
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { 
				ret = 1; // id가 있으면1
			}
			db.close();
			return ret;
		}
		
		// 사용자 정보 리스트를 가져오는 메소드
		// UserVO는 사용자 하나의 정보 / 리스트에 UserVO를 담으면 여러 개의 사용자 정보를 받아 올 수 있을듯
		/*
		 * getUser를 호출하면 리스트를 받을 수 있다.
		 * 사용법: ArrayList<UserVO> getLIst = UserDAO.getUser();
		 */
		public static void testMethod() {
			System.out.println("여기는 반환값(return 값)이 없는");
			System.out.println("메소드 이름 앞에 void를 쓰고");
		}
		public static int testMethod2() {
			System.out.println("숫자를 외부로 전달하기 위해서는 메소드 이름 앞에 int를 쓰고");
			// 아래에 return int 변수나 숫자를 써서 전달
			return 1;
		}
		public static String testMethod3() {
			System.out.println("숫자를 외부로 전달하기 위해서는 메소드 이름 앞에 int를 쓰고");
			return "문자열 전달";
		}
		public static ArrayList<String> testMethod4() {
			// 문자열 리스트를 전달하기 위해 메소드 이름 앞에 문자열 리스트를 담는 ArrayList를 씀
			ArrayList<String> strList = new ArrayList<>();
			strList.add("문자열1");
			strList.add("문자열2");
			strList.add("문자열3");
			
			// ArrayLIst의 참조 변수를 리턴(전달)
			return strList;
		}
		
		public static ArrayList<UserVO> getUser() throws Exception { // 파라미터 없는 오버로딩 메소드
			
			Connection db = DBConn.getConnection();
			
			String sql = "select * from user"; // 사용자 정보 전체 검색 쿼리
			PreparedStatement pstmt = db.prepareStatement(sql); // sql 관리 객체
			ResultSet rs = pstmt.executeQuery(); // 쿼리를 DB에 날려 rs에 갑을 받음

			// 사용자 정보를 받을 리스트 생성
			ArrayList<UserVO> userList = new ArrayList<>();
			
			// 사용자 정보를 한 줄씩 읽어서 user 테이블 정보의 마지막까지 읽어서 더 이상 읽을 정보가 없으면 while 문 종료
			while (rs.next()) { 
				UserVO vo = new UserVO(); // 사용자 정보를 담는 객체
				vo.setU_idx(rs.getInt("u_idx")); // 사용자의 key 값 (Primary Key)
				vo.setName(rs.getString("name")); // 사용자의 이름
				vo.setEmail(rs.getString("email"));  // 사용자의 이메일
				vo.setPhone(rs.getString("Phone")); // 사용자의 전화번호
				vo.setPw(rs.getString("pw")); // 사용자의 비밀번호
				userList.add(vo); // 사용자 정보 객체를 리스트에 담기
			}
			db.close(); // DB 연결 정보 닫기
			return userList; // 사용자 정보 리스트를 메소드 외부로 보내기
		}
		
		
		/*
		 * 리턴타입 boolean, ID가 있으면 true, 없으면 false
		 * boolean isID = idCheck(id);
		 * isID 값을 비교
		 */
		public static boolean idCheck(String id) throws Exception {
			
			Connection db = DBConn.getConnection();
			
			String sql = "select * from user where email = ?";
			PreparedStatement pstmt = db.prepareStatement(sql);
			pstmt.setString(1, id); // id가 ? 에 mapping(바인딩)

//			boolean isID = false; // 검색된 값이 없으면 false 디폴트
			ResultSet rs = pstmt.executeQuery();
			/*if (rs.next()) {
				isID = true;
				}*/
			boolean isID = rs.next(); // rs.next()는 true, false 반환하므로 if 문 없이도 무방
			db.close();
			return isID;
		}
}
