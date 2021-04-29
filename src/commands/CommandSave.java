package commands;

import other.App;
import other.FileManager;
import other.Organization;

import java.util.HashSet;
import java.util.Hashtable;

public class CommandSave extends Command {
    private App application;

    public String execute(App application) {
        this.application = application;
        FileManager man = new FileManager();
        if (man.saveCollection(application.getCollection())) return "Collection was saved to the file";
        else return "Problems with the environment variable and the default file on the server, the collection is not saved...";


    }

    public Hashtable<Long, Organization> getCollection() {
        return application.getCollection();
    }

    public HashSet<Long> getIdList() {
        return application.getIdList();
    }

    @Override
    String getCommandInfo() {
        return "save : сохранить коллекцию в файл";
    }

    @Override
    public boolean withArgument() {
        return false;
    }



    @Override
    public String toString() {
        return "save";
    }

    public void act(String argument){   }
}
