package org.gears.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gears.domain.BoardVO;
import org.gears.persistence.ReplyDAO;


/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("register get");
		
		
		
		request.getRequestDispatcher("/WEB-INF/board/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("register post");
		
		BoardVO vo = new BoardVO();
		ReplyDAO dao= new ReplyDAO() ;
		
		
		
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setWriter(request.getParameter("writer"));
		
		vo.setGno(request.getParameter("gno"));
		vo.setGord(Integer.parseInt(request.getParameter("gord")));
		vo.setParent(Integer.parseInt(request.getParameter("parent")));

		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("content"));
		System.out.println(request.getParameter("writer"));
		
		dao.register(vo);  
				
		response.sendRedirect("/board");
	}

}
