package fourthingsplus.domain.common;

import fourthingsplus.domain.shoppinglist.ShoppingList;

import java.text.ParseException;
import java.util.Objects;

public class IntId {
    private final int id;

    public IntId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntId id1 = (IntId) o;
        return id == id1.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "" + id;
    }


    public int toInt() {
        return id;
    }
}
