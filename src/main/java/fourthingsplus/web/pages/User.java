package fourthingsplus.web.pages;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class User extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var s = req.getSession();
        s.setAttribute("user", req.getParameter("email"));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
