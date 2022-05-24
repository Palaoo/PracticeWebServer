
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class empServlet
 */
@WebServlet("/empList")
public class empServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public empServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); // 출력 인코딩 타입 utf-8
		
		PrintWriter out = response.getWriter();
		empDAO dao = new empDAO();
		ArrayList<empVO> list = dao.listEmployees();

		out.print("<html><head><title>Result from employees</title></head><body>");
		out.print("<table border=1><tr><th>사원ID</th><th>사원명</th><th>매니저ID</th><th>부서ID</th></tr>"); // head
																									// line
		empVO eVO;
		
		for (int i = 0; i < list.size(); i++) {
			eVO = list.get(i);
			out.print("<tr><td>" + eVO.getEmp_id() + "</td><td>" + eVO.getEmp_name() + "</td><td>" + eVO.getManager_name()
					+ "</td><td>" + eVO.getDepartment_name() + "</td></tr>");
		}
		
		out.print("</table></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
