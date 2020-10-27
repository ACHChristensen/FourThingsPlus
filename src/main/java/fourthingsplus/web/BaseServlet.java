package fourthingsplus.web;

import fourthingsplus.api.FourThingsPlus;
import fourthingsplus.infrastructure.DBShoppingListRepository;
import fourthingsplus.infrastructure.Database;
import fourthingsplus.web.widget.Navbar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

public class BaseServlet extends HttpServlet {
    protected static final FourThingsPlus api;

    static {
        api = createFourThingsPlus();
    }

    private static FourThingsPlus createFourThingsPlus() {
        Database db = new Database();
        return new FourThingsPlus(new DBShoppingListRepository(db));
    }

    protected void setup(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
    }

    protected void render(String title, String content, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("navbar", new Navbar(req));
        req.setAttribute("version", api.getVersion());
        req.setAttribute("title", title);
        req.setAttribute("content", content);
        req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
    }

    protected void log(HttpServletRequest req, String message) {
        System.err.println("(" + LocalDateTime.now() + ") " + this.getClass().getCanonicalName() + " \"" + req.getRequestURI() + "\": " + message);
    }

}
