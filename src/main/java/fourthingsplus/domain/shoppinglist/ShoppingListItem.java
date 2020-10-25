package fourthingsplus.domain.shoppinglist;

import fourthingsplus.domain.common.IntId;

public class ShoppingListItem {
    private final Id id;
    private final int description;
    private final boolean status;

    public ShoppingListItem(Id id, int description, boolean status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public static class Id extends IntId {
        public Id(int id) {
            super(id);
        }
    }

    ;
}
