package fourthingsplus.domain.shoppinglist;

import fourthingsplus.domain.validation.ValidationException;

public abstract class ShoppingListFactory {
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
        if (this.name == null || this.name.isBlank())
            throw new ValidationException("name", "should be set and not blank");
        if (this.description == null)
            throw new ValidationException("description", "should be set");

        return commit();
    }

    protected abstract ShoppingList commit();
}
