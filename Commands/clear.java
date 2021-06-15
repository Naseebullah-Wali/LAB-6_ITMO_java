package Commands;
import Exceptions.WrongAmountOfElementsException;
import Collection.CollectionManager;
import Server.utility.ReponseOutputer;

/**
 * Command 'clear'. Clears the collection.
 */
public class clear extends AbstractCommand {
    private CollectionManager collectionManager;

    public clear(CollectionManager collectionManager) {
        super("clear", "", "For Cleaning all collection");
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
            collectionManager.clearCollection();
            ReponseOutputer.appendln("Collection Cleared!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ReponseOutputer.appendln("Using: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }}
