package fourthingsplus.web.pages;

import fourthingsplus.web.BaseServlet;
import fourthingsplus.web.svg.Svg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class Index extends BaseServlet {
    public static final Logger log = LoggerFactory.getLogger(Index.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        log.info("Received access on {}", req.getPathInfo());

        req.setAttribute("chessboard", Svg.chessboard().toString());
        render("FourThings+", "/WEB-INF/pages/index.jsp", req, resp);
    }
}
