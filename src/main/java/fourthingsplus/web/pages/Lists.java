package fourthingsplus.web.pages;

import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
import fourthingsplus.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/lists", "/lists/*"})
public class Lists extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setup(req, resp);
        if (req.getPathInfo() == null) {
            render("FourThings+: Create a new list", "/WEB-INF/pages/createlist.jsp", req, resp);
        } else {
            int shoppinglistid = Integer.parseInt(req.getPathInfo().substring(1));
            log(req, "Accessing Shopping List " + shoppinglistid);
            try {
                ShoppingList shoppingList = api.findShoppingList(shoppinglistid);
                req.setAttribute("list", shoppingList);
                render("FourThings+: " + shoppingList.getName(), "/WEB-INF/pages/displaylist.jsp", req, resp);
            } catch (NoShoppingListExist noShoppingListExist) {
                resp.sendError(404, "Shopping list does not exist");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setup(req, resp);
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        if (name == null || name.equals("")) {
            resp.sendError(400, "Expected a name of the shopping list");
        } else {
            ShoppingList list = api.createShoppingList(name, description);
            resp.sendRedirect(req.getContextPath() + "/lists/" + list.getId());
        }
    }
}
