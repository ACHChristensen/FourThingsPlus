package fourthingsplus.web.pages;

import fourthingsplus.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("")
public class Index extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ArrayList<String> list = new ArrayList();
        req.getSession().getAttributeNames().asIterator().forEachRemaining(list::add);
        System.out.println(list);
        render("FourThings+", "/WEB-INF/pages/index.jsp", req, resp);
    }
}
