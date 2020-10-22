package fourthingsplus.web;

import fourthingsplus.api.FourThingsPlus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends HttpServlet {
    protected static final FourThingsPlus api;

    static {
        api = new FourThingsPlus();
    }


    protected void render(String title, String content, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("version", api.getVersion());
        req.setAttribute("title", title);
        req.setAttribute("content", content);
        req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
    }
}
