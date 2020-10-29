package fourthingsplus.api;

import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
import fourthingsplus.domain.shoppinglist.ShoppingListFactory;
import fourthingsplus.domain.shoppinglist.ShoppingListRepository;

import java.util.ArrayList;
import java.util.List;

public class ListShoppingListRepository implements ShoppingListRepository {
    public List<ShoppingList> shoppingListList = new ArrayList<ShoppingList>();

    @Override
    public Iterable<ShoppingList> findAll() {
        return shoppingListList;
    }

    @Override
    public ShoppingList find(ShoppingList.Id id) throws NoShoppingListExist {
        return shoppingListList.get(id.asInt());
    }

    @Override
    public ShoppingListFactory create() {
        return new ShoppingListFactory() {
            @Override
            protected ShoppingList commit() {
                ShoppingList list = new ShoppingList(ShoppingList.idFromInt(shoppingListList.size()), getName(), getDescription());
                shoppingListList.add(list);
                return list;
            }
        };
    }
}
