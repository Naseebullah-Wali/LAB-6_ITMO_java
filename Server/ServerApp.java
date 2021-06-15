package Server;
import Collection.*;
import Commands.*;

import Server.utility.RequestHandler;

import java.util.Scanner;

public class ServerApp {
    public static final int Port =1821;
    public static final int CONNECTION_TIMEOUT =60*100;

    public static Logging logger = new Logging();
   // FileM fileM = new FileM();
    public static void main(String [] args) {
        FileM fileM = new FileM();

//        TreeSet<Product> products = fileM.readCSV();

        CollectionManager collectionManager = new CollectionManager(fileM);
        System.out.println("Welcome to program!");


        CommandManager commandManager = new CommandManager(
                new add_element(collectionManager),
                new clear(collectionManager),
                new exit(),
                new HelpCommand(),
                new info(collectionManager),
                new Show(collectionManager),
                new print_field_ascending_price(collectionManager),
                new sum_of_price(collectionManager),
                new add_if_max(collectionManager),
                new add_if_min(collectionManager),
                new executerScriptCommand(),
                new remove(collectionManager),
                new count_greater_than_part_number(collectionManager),
                new save(collectionManager),
                new update_id(collectionManager),
                new HistoryCommand(), //todo
                new Savecom(collectionManager)
        );

        RequestHandler requestHandler = new RequestHandler(commandManager);
        Server server = new Server(Port, CONNECTION_TIMEOUT, requestHandler);

        Runnable save = () -> {
            Scanner scanner = new Scanner(System.in, "windows-1251");
            while (true) {
                String com = scanner.nextLine().trim();
                if (com.equals("save")) {
                    CommandManager.SaveCom("save", null);
                } else if (com.equals("stop")) {
                    server.stop();
                    break;
                }
            }
        };

        Thread th = new Thread(save);
        th.start();

        server.run();
    }
}


