package server;

import exceptions.WithoutArgException;
import other.App;
import other.FileManager;
import other.OrgGeneration;
import other.Organization;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

import java.util.logging.*;


public class MainS {
    public static final Logger log = Logger.getLogger(String.valueOf(MainS.class));
    public static void main(String[] args) {

        try {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter port: ");
            int port = Integer.parseInt(scanner1.nextLine());
            if (port == 0) throw new WithoutArgException();

            App application = new App(new Hashtable<>());
            String filepath = System.getenv("FilePath");
            FileManager manFile = new FileManager();
            Hashtable<Long, Organization> collection = manFile.loadCollection(filepath);
            if (collection != null) application = new App(collection);
            else {
                collection = new Hashtable<>();
                Scanner scanner2 = new Scanner(System.in);
                int count;
                try {
                    System.out.println("The number of elements in collection?: ");
                    count = Integer.parseInt(scanner2.nextLine());
                    if (count > 0) {
                        for (int i = 1; i <= count; i++) {
                            Organization org = OrgGeneration.generate();
                            System.out.println(org.toString());
                            System.out.println("key: " + org.getEmployeesCount() % 10 + org.getId() % 10 + 94);
                            collection.put(org.getEmployeesCount() % 10 + org.getId() % 10 + 94, org);
                            application = new App(collection);
                        }
                    } else {
                        System.out.println("Anyway one element will be created.");
                        Organization org = OrgGeneration.generate();
                        collection.put(org.getEmployeesCount() % 10 + org.getId() % 10 + 98, org);
                        application = new App(collection);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error of entering");
                }
            }
            application.sort(collection);
            System.out.println("The server application is running...");
            Server server = new Server();
            server.connect(port);
            log.info("Connection is established, listen port: " + port);
            server.run(application);
        } catch (WithoutArgException e) {
            System.out.println("Port can't be null!");
        } catch (NumberFormatException e) {
            System.out.println("Port is an integer number!");
        } catch (IOException e) {
            System.out.println("Problems with connection...");
        }
    }
}
