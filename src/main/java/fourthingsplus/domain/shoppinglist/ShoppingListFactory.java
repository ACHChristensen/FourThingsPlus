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
        ValidationException val = new ValidationException();
        if (this.name == null || this.name.isBlank())
            val.addProblem("name", "should be set and not blank");
        if (this.description == null || description.isBlank())
            val.addProblem("description", "should be set and not blank");
        val.validate();
        return commit();
    }

    protected abstract ShoppingList commit();
}
