package fourthingsplus.domain.shoppinglist;

import fourthingsplus.domain.common.IntId;

import java.text.ParseException;
import java.util.Objects;

public abstract class ShoppingList {
    private final Id id;
    private final String name;
    private final String description;

    protected ShoppingList(Id id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Id getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public abstract Iterable<ShoppingListItem> findAllItems();

    public abstract ShoppingListItem.Id createItem(String description);

    public abstract void findItem(ShoppingListItem.Id id);

    public abstract void updateItem(ShoppingListItem item);

    public abstract void deleteItem(ShoppingListItem.Id id);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Id idFromInt(int anInt) {
        return new ShoppingList.Id(anInt);
    }

    public static Id idFromString(String id) throws ParseException {
        try {
            return idFromInt(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    public static class Id extends IntId {
        private Id(int id) {
            super(id);
        }
    }
}
