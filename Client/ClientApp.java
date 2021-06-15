package Client;


import Client.utility.UserHandler;
import Exceptions.NotInDeclaredLimitsException;
import Exceptions.WrongAmountOfElementsException;
import utilities.Outputer;

import java.util.Scanner;


public class ClientApp {
    public static final String PS1 = "$ ";
    public static final String PS2 = "> ";

    private static final int RECONNECTION_TIMEOUT = 5 * 1000;
    private static final int MAX_RECONNECTION_ATTEMPTS = 5;

    private static String host = "localhost";
    private static int port = 1821;



    public static void main(String[] args) {

        Scanner userScanner = new Scanner(System.in);
        UserHandler userHandler = new UserHandler(userScanner);
        Client client = new Client(host, port, RECONNECTION_TIMEOUT, MAX_RECONNECTION_ATTEMPTS, userHandler);
        client.run();
        userScanner.close();
    }}
