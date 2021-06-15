package Collection;

import Data.Product;
import Exceptions.CollectionIsEmptyException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.TreeSet;

public class CollectionManager {

    private final FileM fileM;

    TreeSet<Product> products = new TreeSet<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    public CollectionManager(FileM fileM) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileM = fileM;

        loadCollection();
    }

    /**
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Last save time or null if there wasn't saving.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return products.getClass().getName();
    }

    /**
     * @return Size of the collection.
     */
    public int collectionSize() {
        return products.size();
    }


    public Product getWithMinPrice() {
        return products.stream().min(Comparator.comparingLong(Product::getPrice)).orElse(null);
    }


    public Product getWithMaxPrice() {
        return products.stream().max(Comparator.comparingLong(Product::getPrice)).orElse(null);
    }

    public Product getById(Long id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }



    public double getSumOfPrice() {
        return products.stream()
                .reduce(0.0, (sum, p) -> sum += p.getPrice(), Double::sum);
    }

    /**
     * @return Collection content or corresponding string if collection is empty.
     */
    public String showCollection() {
        if (products.isEmpty()) return "Collection is Empty!";
        return products.stream().reduce("", (sum, m) -> sum += m + "\n\n", (sum1, sum2) -> sum1 + sum2).trim();
    }


    public void addToCollection(Product product) {
        products.add(product);
    }


    public boolean removeFromCollection(Product product) {
        return products.remove(product);
    }

    /**
     * Clears the collection.
     */
    public void clearCollection() {
        products.clear();
    }


    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
        fileM.writeCSV(products);//.writeCollection(marinesCollection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        try {
            products = fileM.readCSV();//.readCollection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastInitTime = LocalDateTime.now();
    }


}





