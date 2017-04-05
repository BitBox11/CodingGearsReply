package org.gears.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gears.domain.BoardVO;
import org.gears.domain.Pager;
import org.gears.persistence.ReplyDAO;


/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardListController() {
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
		System.out.println("board get");

		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("pageNum"));
		} catch (Exception e) {
		}

		try {
			System.out.println("page: " + page);
			ReplyDAO dao = new ReplyDAO();
			int total = dao.getListCount();

			request.setAttribute("list", dao.readList(page));
			request.setAttribute("pager", new Pager(page, total));
			BoardVO vo = new BoardVO();
			
//			if(vo.getGord() != null){
//			List<String> aaa = new ArrayList<>();
//			
//			for (int i = 0; i < 6; i++) {
//				aaa.add("re:");
//			}
//			request.setAttribute("re", aaa);
//			}
			
			
			} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/views/readlist.jsp").forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.sendRedirect("register");
		
	}

}
