package Commands;
import Data.Product;
import Exceptions.WrongAmountOfElementsException;

import Collection.CollectionManager;
import Server.utility.ReponseOutputer;

/**
 * Command 'add_if_min'. Adds a new element to collection if it's less than the minimal one.
 */
public class update_id extends AbstractCommand {
    private CollectionManager collectionManager;

    public update_id(CollectionManager collectionManager) {
        super("Update_id", "{element}", "Use For UPdate a Product");
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
            if (stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException("stringArgument.isEmpty(): " + stringArgument.isEmpty() + ", objectArgument == null: " + (objectArgument == null));
            Long id = Long.parseLong(stringArgument);
            Product productToAdd = (Product) objectArgument;
            Product existingProduct = collectionManager.getById(id);
            if (existingProduct != null) {
                collectionManager.removeFromCollection(existingProduct);
                collectionManager.addToCollection(productToAdd);
                return true;
            } else {
                return false;
            }
        } catch (WrongAmountOfElementsException exception) {
            ReponseOutputer.appendln("Using: '" + getName() + " " + getUsage() + "'");
        } catch (ClassCastException exception) {
            ReponseOutputer.appenderror("Invide object request from client !");
        }
        return false;
    }
}
