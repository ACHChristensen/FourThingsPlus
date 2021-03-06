package fourthingsplus.domain.shoppinglist;

import fourthingsplus.domain.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListFactoryTest {

    TestShoppingListFactory factory;

    @BeforeEach
    void setupFactory() {
        factory = new TestShoppingListFactory();
    }

    @Test
    void shouldCreateAFactory() throws ValidationException {
        // setup
        factory.setName("World");
        factory.setDescription("Hello");

        // Action
        ShoppingList list = factory.validateAndCommit();

        // Test
        assertTrue(factory.wasCommitted);
        assertEquals(new ShoppingList(ShoppingList.idFromInt(0), "World", "Hello"), list);
    }

    @Test
    void shouldFindProblemInNameAndDescription() {
        factory.setName("");
        factory.setDescription(null);

        // Action
        ValidationException e = assertThrows(ValidationException.class, () -> factory.validateAndCommit());

        // Test
        assertFalse(factory.wasCommitted);
        assertEquals(2, e.getProblems().size());
    }

    @Nested
    @DisplayName("When given an empty list")
    class WhenGivenAnEmptyList {
        private List list;

        @BeforeEach
        void setList() {
            list = List.of();
        }

        @Test
        @DisplayName("length should be 0")
        void lengthShouldBe0() {
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("length should be 1")
        void lengthShouldBe1() {
            assertEquals(1, list.size());
        }

    }


    @Nested
    class Should {
        @Test
        @DisplayName("run smoothly")
        void runSmoothly() {
            fail("Not Implemented Yet!");
        }

        @Test
        @DisplayName("adds up nicely")
        void addsUpNicely() {
            fail("Not Implemented Yet!");
        }
    }

    @Nested
    @DisplayName("Should not")
    class ShouldNot {

        @Test
        @DisplayName("do something rash")
        void addsUpNicely() {
            fail("Not Implemented Yet!");
        }
    }


    private class TestShoppingListFactory extends ShoppingListFactory {
        boolean wasCommitted = false;

        @Override
        protected ShoppingList commit() {
            wasCommitted = true;
            return new ShoppingList(ShoppingList.idFromInt(0), getName(), getDescription());
        }
    }
}