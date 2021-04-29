package commands;

import client.Client;
import client.MainCl;
import other.App;
import other.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;

public class CommandExecuteScript extends Command implements CommandWithArg{
    private Hashtable<Long, Organization> collection;
    private HashSet<Long> idList;
    private String status;


    public void act(String argument){
        try {
            File file = new File(argument);
            Scanner fileScanner = new Scanner(file);
            Client client = MainCl.getActiveClient();
            client.incrementScriptCounter();
            client.getHandler().run(fileScanner);
            client.decrementScriptCounter();
            status = "Script " + argument + " finished the execution.";
            return;
        } catch (FileNotFoundException e) {
            System.out.println("Script wasn't found...");
        } catch (NullPointerException e) {
            System.out.println("No active client was found...");
        }
        status="There is no such script! All scripts must be in the same folder as the jar or src.";

    }

    public String execute(App application){
        collection = application.getCollection();
        idList = application.getIdList();
        return status;
    }

    public Hashtable<Long, Organization> getCollection(){
        return collection;
    }

    public HashSet<Long> getIdList(){
        return idList;
    }


    @Override
    String getCommandInfo() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    @Override
    public boolean withArgument(){
        return true;
    }

    @Override
    public String toString(){
        return "execute_script";
    }
}
