package fourthingsplus.web.pages;

import fourthingsplus.domain.shoppinglist.InvalidShoppingListId;
import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
import fourthingsplus.domain.shoppinglist.ShoppingListFactory;
import fourthingsplus.domain.validation.ValidationException;
import fourthingsplus.web.BaseServlet;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;


@WebServlet({"/lists", "/lists/*"})
public class Lists extends BaseServlet {
    private static final Logger log = getLogger(Lists.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setup(req, resp);
        log.info("Serving /lists {}", req.getPathInfo());
        if (req.getPathInfo() == null) {
            render("FourThings+: Create a new list", "/WEB-INF/pages/createlist.jsp", req, resp);
        } else {
            ShoppingList.Id id;
            try {
                id = ShoppingList.idFromString(req.getPathInfo().substring(1));
            } catch (InvalidShoppingListId e) {
                log.warn("Could not parse {}", req.getPathInfo().substring(1));
                resp.sendError(400, "Invalid request");
                return;
            }
            try {
                ShoppingList shoppingList = api.find(id);
                req.setAttribute("list", shoppingList);
                render("FourThings+: " + shoppingList.getName(), "/WEB-INF/pages/displaylist.jsp", req, resp);
            } catch (NoShoppingListExist noShoppingListExist) {
                log.warn("Could not find shopping list {}", id);
                resp.sendError(404, "Shopping list does not exist");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setup(req, resp);
        ShoppingListFactory factory = api.createShoppingList();
        factory.setName(req.getParameter("name"));
        factory.setDescription(req.getParameter("description"));
        try {
            ShoppingList list = factory.validateAndCommit();
            resp.sendRedirect(req.getContextPath() + "/lists/" + list.getId());
        } catch (ValidationException e) {
            resp.sendError(400, e.getMessage());
        }
    }
}
