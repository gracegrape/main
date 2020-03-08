package seedu.address.logic.commands.wallet;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliPrefix.WALLET_COMMAND_TYPE;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Expense;

import java.util.List;

public class WalletDeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the expense/income identified by the index number used in the displayed transaction list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + WALLET_COMMAND_TYPE + " "
            + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TRANSACTION_SUCCESS = "Deleted Income/Expense: %1$s";

    private final Index targetIndex;

    public WalletDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Expense>lastShownList = model.getFilteredExpenseList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
        }

        Expense expenseToDelete = lastShownList.get(targetIndex.getZeroBased());

        model.deleteExpense(expenseToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_TRANSACTION_SUCCESS, expenseToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WalletDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((WalletDeleteCommand) other).targetIndex)); // state check
    }
}
