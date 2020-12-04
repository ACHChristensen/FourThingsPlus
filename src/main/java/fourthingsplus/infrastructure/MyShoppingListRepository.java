package fourthingsplus.infrastructure;

import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
import fourthingsplus.domain.shoppinglist.ShoppingListFactory;
import fourthingsplus.domain.shoppinglist.ShoppingListRepository;

public class MyShoppingListRepository implements ShoppingListRepository {

    @Override
    public Iterable<ShoppingList> findAll() {
        //TODO (ckl, 01/12/2020) implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public ShoppingList find(ShoppingList.Id id) throws NoShoppingListExist {
        //TODO (ckl, 01/12/2020) implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public ShoppingListFactory create() {
        //TODO (ckl, 01/12/2020) implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}
