package Commands;
import Exceptions.WrongAmountOfElementsException;
import Collection.CollectionManager;
import Server.utility.ReponseOutputer;

/**
 * Command 'save'. Saves the collection to a file.
 */
public class save extends AbstractCommand {
    private CollectionManager collectionManager;

    public save(CollectionManager collectionManager) {
        super("save", "", "Save Collecion in a File ");
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
            collectionManager.saveCollection();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ReponseOutputer.appendln("Using: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }
}
