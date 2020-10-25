package fourthingsplus.domain.shoppinglist;

import fourthingsplus.domain.Utils;
import fourthingsplus.domain.validation.ValidationError;

public abstract class ShoppingListFactory {
    private String name;
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String getDescription() {
        return description;
    }

    protected String getName() {
        return name;
    }

    public void validate() throws ValidationError {
        if (name == null || name.isBlank()) throw new ValidationError("name", "Should be set and not blank");
        if (description == null) throw new ValidationError("description", "Should be set");
    }

    public ShoppingList.Id validateAndCommit() throws ValidationError {
        validate();
        return commit();
    }

    protected abstract ShoppingList.Id commit();
}
