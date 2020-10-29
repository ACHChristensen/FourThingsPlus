package fourthingsplus.api;

import com.mysql.cj.exceptions.AssertionFailedException;
import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
import fourthingsplus.domain.shoppinglist.ShoppingListFactory;
import fourthingsplus.domain.shoppinglist.ShoppingListRepository;
import fourthingsplus.domain.validation.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FourThingsPlusTest {

    @Test
    void testThatFindIsCalled() throws ValidationException, NoShoppingListExist {
        // Setup
        FourThingsPlus api = new FourThingsPlus(new ListShoppingListRepository());
        ShoppingListFactory factory = api.createShoppingList();
        factory.setDescription("hello");
        factory.setName("world");
        ShoppingList list = factory.validateAndCommit();

        // Action
        ShoppingList e = api.find(list.getId());

        // Test
        assertEquals(list, e);
    }

    @Test
    void testThatFindIsCalled2() throws ValidationException, NoShoppingListExist {
        // Setup
        FourThingsPlus api = new FourThingsPlus(new ShoppingListRepository() {
            @Override
            public Iterable<ShoppingList> findAll() {
                throw new AssertionFailedException("should not use findall");
            }

            @Override
            public ShoppingList find(ShoppingList.Id id) throws NoShoppingListExist {
                return new ShoppingList(ShoppingList.idFromInt(0), "hell", "world");
            }

            @Override
            public ShoppingListFactory create() {
                throw new AssertionFailedException("Should not create stuff");
            }
        });

        // action
        ShoppingList a = api.find(ShoppingList.idFromInt(0));

        // test
        assertEquals(ShoppingList.idFromInt(0), a.getId());

    }
}