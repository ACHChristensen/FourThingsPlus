package fourthingsplus.domain.shoppinglist;

import fourthingsplus.domain.validation.ValidationException;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class ShoppingListFactory {
    private static final Logger log = getLogger(ShoppingListFactory.class);
    private String name;
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected String getName() {
        return name;
    }

    protected String getDescription() {
        return description;
    }

    public ShoppingList validateAndCommit() throws ValidationException {
        ValidationException val = new ValidationException();
        if (this.name == null || this.name.isBlank())
            val.addProblem("name", "should be set and not blank");
        if (this.description == null || description.isBlank())
            val.addProblem("description", "should be set and not blank");
        val.validate();
        ShoppingList c = commit();
        log.info("Successfully created a shopping list: {}", c.getId());
        log.debug(String.valueOf(c));
        return c;
    }

    protected abstract ShoppingList commit();
}
