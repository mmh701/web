package pack2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션을 사용
		// 두 개의 값 받기
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		
		// 만약 세션이 있다면 "list" 라는 키의 값 읽기
		HttpSession httpSession = request.getSession(true);	// 각 클라이언트 고유 세션(영역)
		ArrayList<Goods> glist = (ArrayList<Goods>)httpSession.getAttribute("list");
		
		if(glist == null ) glist = new ArrayList<Goods>();	// Goods용 컬렉션 객체 생성
		
		glist.add(new Goods(name,price));	// 장바구니에 담을 DTO에 값 저장 후 컬렉션에 담기
		httpSession.setAttribute("list", glist);	// 컬렉션(장바구니 정보가 있는 DTO)을 세션에 저장
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>😊 " + name + "상품을 구입하셨습니다");
		out.println("<br>[ <a href='shopping/shop.html'>계속 쇼핑</a>]");
		out.println("&nbsp;&nbsp;[ <a href='BuyServlet'>결제 하기</a>]");
		out.println("<br><table width=80% border='1'>");
		out.println("<tr><th>상품명</th><th>가격</th></tr>");
		
		for (int i = 0; i < glist.size(); i++) {
			Goods goods = glist.get(i); // 컬렉션에 저장된 상품자료(DTO) 한 개씩 꺼내서 출력
			out.println("<tr>");
			out.println("<td>" + goods.getName() + "</td>");
			out.println("<td>" + goods.getPrice() + "</td>");
			out.println("<tr>");
		}
		out.println("</table>");
		out.println("</body></html>");
		out.close();
	}

}
