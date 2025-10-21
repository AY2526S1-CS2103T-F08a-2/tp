package seedu.estatemate.logic.parser;

import static seedu.estatemate.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.estatemate.logic.commands.FindJobCommand;
import seedu.estatemate.logic.parser.exceptions.ParseException;
import seedu.estatemate.model.job.JobContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindJobCommand object
 */
public class FindJobCommandParser implements Parser<FindJobCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindJobCommand
     * and returns a FindJobCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindJobCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindJobCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindJobCommand(new JobContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
