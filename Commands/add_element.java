package Commands;


import Data.Product;
import Exceptions.WrongAmountOfElementsException;
import Collection.CollectionManager;
import Server.utility.ReponseOutputer;



/**
 * Command 'add'. Adds a new element to collection.
 */
public class add_element extends AbstractCommand {
    private CollectionManager collectionManager;

    public add_element(CollectionManager collectionManager) {
        super("add", "{element}", "Add new Element to the Collection");
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
            if (!stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();

            collectionManager.addToCollection((Product) objectArgument);
            ReponseOutputer.appendln("Product Added!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ReponseOutputer.appendln("Using: '" + getName() + " " + getUsage() + "'");
        } catch (ClassCastException exception) {
            ReponseOutputer.appenderror("The object submitted by the client is invalid!");
        }
        return false;
    }
}
