package seedu.estatemate.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.estatemate.commons.core.index.Index;
import seedu.estatemate.commons.util.ToStringBuilder;
import seedu.estatemate.logic.Messages;
import seedu.estatemate.logic.commands.exceptions.CommandException;
import seedu.estatemate.model.Model;
import seedu.estatemate.model.person.Person;

/**
 * Deletes a tenant identified using it's displayed index from the address book.
 */
public class DeleteTenantCommand extends Command {

    public static final String COMMAND_WORD = "deletet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tenant using the index number displayed in the tenant list.\n";

    public static final String MESSAGE_DELETE_TENANT_SUCCESS = "Deleted tenant successfully: %1$s";

    private final Index targetIndex;

    public DeleteTenantCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TENANT_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object tenantToDelete) {
        if (tenantToDelete == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(tenantToDelete instanceof DeleteTenantCommand)) {
            return false;
        }

        DeleteTenantCommand otherDeleteCommand = (DeleteTenantCommand) tenantToDelete;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
