package fourthingsplus.web.widget;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Navbar {
    private final HttpServletRequest request;

    public Navbar(HttpServletRequest request) {
        this.request = request;
    }

    public String getWebpageName() {
        return "FourThings+";
    }

    private final List<Item> items = List.of(
            new Item("Home", "/", false),
            new Item("Lister", "/lists", false),
            new Item("Admin", "/admin", true)
    );

    public List<Item> getItems() {
        String name = (String) request.getSession().getAttribute("user");
        if (name != null && name.equals("ADMIN")) {
            return items;
        } else {
            List<Item> list = new ArrayList<>();
            for (Item x : items) {
                if (!x.adminOnly) {
                    list.add(x);
                }
            }
            return list;
        }
    }

    public class Item {
        private final String name;
        private final String url;
        private final boolean adminOnly;

        public Item(String name, String url, boolean adminOnly) {
            this.name = name;
            this.url = url;
            this.adminOnly = adminOnly;
        }

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }

        public boolean isActive() {
            return request.getRequestURI().endsWith(url);
        }
    }
}
