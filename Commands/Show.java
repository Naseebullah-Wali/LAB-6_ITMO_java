package Commands;
import Exceptions.WrongAmountOfElementsException;
import Collection.CollectionManager;
import Server.utility.ReponseOutputer;

/**
 * Command 'show'. Shows information about all elements of the collection.
 */
public class Show extends AbstractCommand {
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("Show", "", "Shows all Element of Collecion ");
        this.collectionManager = collectionManager;
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
            ReponseOutputer.appendln(collectionManager.showCollection());
            return true;
        }
        catch (WrongAmountOfElementsException exception) {
            ReponseOutputer.appendln("Using: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }
}

