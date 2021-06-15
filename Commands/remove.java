package Commands;

import Collection.CollectionManager;
import Data.Product;
import Exceptions.CollectionIsEmptyException;
import Exceptions.ProductNotFoundException;
import Exceptions.WrongAmountOfElementsException;
import Server.utility.ReponseOutputer;

public class remove extends AbstractCommand {
    private CollectionManager collectionManager;

    public remove(CollectionManager collectionManager) {
        super("remove", "<ID>", "Delet Element from Collection By ID");
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
            if (stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long id = Long.parseLong(stringArgument);
            Product producttoremove = collectionManager.getById(id);
            if (producttoremove == null) throw new ProductNotFoundException();
            collectionManager.removeFromCollection(producttoremove);
            ReponseOutputer.appendln("Product removed Successfully!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ReponseOutputer.appendln("Using: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException exception) {
            ReponseOutputer.appenderror("Collection is Empty!");
        } catch (NumberFormatException exception) {
            ReponseOutputer.appenderror("ID must be represented by a number!");
        } catch (ProductNotFoundException exception) {
            ReponseOutputer.appenderror("Product with such ID not aviable!");
        }


        return false;
    }
}
