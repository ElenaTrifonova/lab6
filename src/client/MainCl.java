package client;

import commands.*;
import exceptions.WithoutArgException;
import other.Handler;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class that creates handler of the commands and sets the commands to it,
 * creates application and starts it
 */
public class MainCl {

    private static Client activeClient = null;

    public static Client getActiveClient() {
        return activeClient;
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter port: ");
            int port = Integer.parseInt(scanner.nextLine());
            if (port == 0) throw new WithoutArgException();

            Handler handler = new Handler();
            handler.addCommand(new CommandInsert().toString(), new CommandInsert());
            handler.addCommand(new CommandRemoveGreaterKey().toString(), new CommandRemoveGreaterKey());
            handler.addCommand(new CommandRemoveLowerKey().toString(), new CommandRemoveLowerKey());
            handler.addCommand(new CommandClear().toString(), new CommandClear());
            handler.addCommand(new CommandExecuteScript().toString(), new CommandExecuteScript());
            handler.addCommand(new CommandExit().toString(), new CommandExit());
            handler.addCommand(new CommandRemoveKey().toString(), new CommandRemoveKey());
            handler.addCommand(new CommandRemoveGreater().toString(), new CommandRemoveGreater());
            handler.addCommand(new CommandHelp().toString(), new CommandHelp());
            handler.addCommand(new CommandFilterPostal().toString(), new CommandFilterPostal());
            handler.addCommand(new CommandInfo().toString(), new CommandInfo());
            handler.addCommand(new CommandAverage().toString(), new CommandAverage());
            handler.addCommand(new CommandShow().toString(), new CommandShow());
            handler.addCommand(new CommandMax().toString(), new CommandMax());
            handler.addCommand(new CommandUpdateID().toString(), new CommandUpdateID());
            Client client = new Client();
            activeClient = client;
            client.connect(port);
            handler.setClient(client);
            client.setHandler(handler);
            System.out.println("Client-server application for managing a collection of objects");
            System.out.println("Author: Trifonova Elena R3135");
            System.out.println("To view the available commands: type - help, and to exit: type - exit.");
            handler.run(new Scanner(System.in));
        } catch (WithoutArgException e) {
            System.out.println("Port can't be null");
        } catch (NumberFormatException e) {
            System.out.println("Port is integer number!!!");
        } catch (IllegalArgumentException e) {
            System.out.println("Enter valid number of port...");
        } catch (IOException e) {
            System.out.println("Problems with connection, check the arguments...");
            e.printStackTrace();
        }
    }
}
