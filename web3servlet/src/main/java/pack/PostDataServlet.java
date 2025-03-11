package pack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PostDataServlet")
public class PostDataServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 요청 처리 전용 메소드
		request.setCharacterEncoding("utf-8");
		String irum = request.getParameter("name");
		String addr[] = request.getParameterValues("addr"); // name이 같으면 배열 처리
		
		// 클라이언트로 전송할 코드 작성
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h3>서버가 전송한 자료 확인(post)</h3>");
		out.println("이름은 " + irum);
		out.println("<br>주소는 " + addr[0] + " : " +addr[1]);
		
		// checkbox
        String[] lan = request.getParameterValues("lan");
        if (lan != null && lan.length > 0) {
            out.println("<br>선택된 언어는 ");
            for (String ss : lan) {
                out.println(ss + " ");
            }
        } else {
            out.println("<br>선택된 언어는 없습니다.");
        }
		
		// radio
		out.println("<br>운영체제는 " + request.getParameter("os"));
		
		out.println("<br><br><a href='postdata.html'>자료 다시 입력</a>");
		out.println("</body></html>");
		out.close();
	}

}
