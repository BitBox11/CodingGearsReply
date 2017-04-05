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
 * Servlet implementation class DetailCotroller
 */
@WebServlet("/detail")
public class DetailCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailCotroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		BoardVO vo = new BoardVO();
		int bno = Integer.parseInt(request.getParameter("bno"));
//		request.setAttribute("bno",Integer.parseInt(request.getParameter("bno")));
		
//		vo.setBno(Integer.parseInt(request.getParameter("bno")));
//		vo.setTitle(request.getParameter("title"));
//		vo.setContent(request.getParameter("content"));
//		vo.setWriter(request.getParameter("writer"));

		ReplyDAO dao = new ReplyDAO();

	
		request.setAttribute("list2", dao.detail(bno));

		request.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		System.out.println("detail post");
		
		BoardVO vo = new BoardVO();
		ReplyDAO dao= new ReplyDAO();
		
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setWriter(request.getParameter("writer"));
		vo.setGno(request.getParameter("gno"));
		vo.setGord(Integer.parseInt(request.getParameter("gord")));
		vo.setParent(Integer.parseInt(request.getParameter("parent")));

		
		System.out.println(request.getParameter("gno"));
		System.out.println(request.getParameter("gord"));
		System.out.println(request.getParameter("parent"));
		
		dao.register(vo);
		
		response.sendRedirect("board");
		
		
		
		
	}

}
