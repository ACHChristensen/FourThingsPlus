<%@ page contentType="text/html;charset=UTF-8" %>
<div class="content">

    <h1>Lav en ny liste</h1>

    <form method="post">
        <div class="form-group">
            <label for="shoppingListName">Indkøbslistens Navn</label>
            <input type="text" class="form-control" id="shoppingListName"
                   aria-describedby="shoppingListNameHelp" name="name">
            <small id="shoppingListNameHelp" class="form-text text-muted">Skriv
                et godt navn for en indkøbsliste.</small>
        </div>
        <div class="form-group">
            <label for="shoppingListDescription">Indkøbslistens
                beskrivelse</label>
            <textarea class="form-control" id="shoppingListDescription"
                      aria-describedby="shoppingListDescriptionHelp"
                      name="description">
            </textarea>
            <small id="shoppingListDescriptionHelp"
                   class="form-text text-muted">En kort beskrivelse af
                indkøbene.</small>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>

