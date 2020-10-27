package fourthingsplus.domain.shoppinglist;

import java.util.Objects;

public class ShoppingList {
    private final Id id;
    private final String name;
    private final String description;

    public ShoppingList(Id id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Id idFromInt(int anInt) {
        return new Id(anInt);
    }

    public static Id idFromString(String string) throws InvalidShoppingListId {
        try {
            return idFromInt(Integer.parseInt(string));
        } catch (NumberFormatException e) {
            throw new InvalidShoppingListId(string);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return id == that.id;
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

    public static class Id {
        private final int id;

        public Id(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "" + id;
        }

        public int asInt() {
            return id;
        }
    }
}
