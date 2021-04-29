package commands;

import other.App;
import other.Organization;

import java.util.HashSet;
import java.util.Hashtable;

public class CommandExit extends Command{

    private App application;

    @Override
    public String execute(App application){
        this.application = application;
        return "The program is finished without saving collection. Have a good day!.";

    }

    @Override
    String getCommandInfo() {
        return "exit : завершить программу (без сохранения в файл)";
    }

    @Override
    public String toString() {
        return "exit";
    }

    @Override
    public boolean withArgument() {
        return false;
    }

    @Override
    public Hashtable<Long, Organization> getCollection() {
        return application.getCollection();
    }

    @Override
    public HashSet<Long> getIdList() {
        return application.getIdList();
    }

    public void act(String argument){   }
}
