package fourthingsplus.web.pages;

import fourthingsplus.web.BaseServlet;
import fourthingsplus.web.svg.Rect;
import fourthingsplus.web.svg.Svg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@WebServlet("/sliders")
public class Sliders extends BaseServlet {

    public static class Carport implements Serializable {
        private int width;
        private int height;

        public Carport(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public String getDrawing() {
            Svg svg = new Svg(400, 400, "-100 -100 600 600");
            svg.add(new Rect(0, 0, getWidth(), getHeight()).withStyle("fill:none; stroke-width: 2; stroke: black"));
            svg.add(new Rect(-50, -50, 500, 500).withStyle("fill:none; stroke-width: 5; stroke: gray"));
            System.out.println(svg);
            return svg.toString();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Carport carport = (Carport) session.getAttribute("carport");
        if (carport == null) {
            carport = new Carport(200, 200);
            session.setAttribute("carport", carport);
        }


        render("sliders", "/WEB-INF/pages/sliders.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Carport carport = (Carport) session.getAttribute("carport");
        if (carport == null) {
            carport = new Carport(200, 200);
            session.setAttribute("carport", carport);
        }

        carport.width = Integer.parseInt((String) req.getParameter("carport_width"));
        carport.height = Integer.parseInt((String) req.getParameter("carport_height"));

        // todo draw stuff


        resp.sendRedirect(req.getContextPath() + "/sliders");
    }
}
