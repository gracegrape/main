package seedu.address.logic.commands.wallet;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Expense;
import seedu.address.model.transaction.Transaction;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class WalletDeleteCommand extends Command  {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the transaction identified by the index number used in the displayed transaction list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: wallet " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EXPENSE_SUCCESS = "Deleted Transaction: %1$s";

    private final Index targetIndex;

    public WalletDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Transaction> lastShownList = model.getFilteredTransactionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Transaction transactionToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTransaction(transactionToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EXPENSE_SUCCESS, transactionToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WalletDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((WalletDeleteCommand) other).targetIndex)); // state check
    }
}
