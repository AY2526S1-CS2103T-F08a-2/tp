package seedu.estatemate.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.estatemate.commons.util.ToStringBuilder;
import seedu.estatemate.logic.Messages;
import seedu.estatemate.logic.commands.exceptions.CommandException;
import seedu.estatemate.model.Model;

/**
 * Deletes a Job identified using it's displayed id number from the address book.
 */
public class DeleteJobCommand extends Command {

    public static final String COMMAND_WORD = "djob";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the job identified by the id number used in the displayed job.\n"
            + "Parameters: id (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_JOB_SUCCESS = "Deleted Job: %1$s";

    private final Integer targetId;

    public DeleteJobCommand(int targetId) {
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.getFilteredJobList().stream().anyMatch(j -> j.getId() == targetId)
                && !model.getFilteredJobList().stream().anyMatch(j -> j.getId() == targetId)) {
            // fall back to whole list if filtered list doesn't contain it
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_ID);
        }
        model.deleteJobById(targetId);
        return new CommandResult(String.format("Deleted job: #%d", targetId));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTenantCommand)) {
            return false;
        }

        DeleteJobCommand otherDeleteJobCommand = (DeleteJobCommand) other;
        return targetId.equals(otherDeleteJobCommand.targetId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetId", targetId)
                .toString();
    }
}

