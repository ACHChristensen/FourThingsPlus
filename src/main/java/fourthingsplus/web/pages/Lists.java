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

@WebServlet("/lists")
public class Lists extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            ShoppingList list = api.findShoppingList(1);
            render("FourThings+ : Create a new list", "/WEB-INF/pages/lists.jsp", req, resp);
        } catch (NoShoppingListExist noShoppingListExist) {
            resp.sendError(404, "Vi kunne ikke finde din liste.");
        }

    }
}
