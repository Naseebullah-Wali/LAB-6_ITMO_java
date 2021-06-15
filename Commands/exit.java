package Commands;
import Exceptions.WrongAmountOfElementsException;
import Server.utility.ReponseOutputer;

/**
 * Command 'exit'. Checks for wrong arguments then do nothing.
 */
public class exit extends AbstractCommand {

    public exit() {
        super("exit", "", "End the Work of Client");
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ReponseOutputer.appendln("Using: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }
}
