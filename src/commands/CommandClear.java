package commands;

import other.App;
import other.Organization;

import java.util.HashSet;
import java.util.Hashtable;

public class CommandClear extends Command{

    @Override
    public String execute(App application){
        if (application.getCollection().size()==0) return "Collection is already empty.";
        else {
            return "Collection was cleared.";
        }
    }

    @Override
    public Hashtable<Long, Organization> getCollection(){
        return new Hashtable<Long, Organization>();
    }

    @Override
    public HashSet<Long> getIdList(){
        return new HashSet<Long>();
    }

    @Override
    String getCommandInfo() {
        return "clear : очистить коллекцию";
    }

    @Override
    public boolean withArgument(){
        return false;
    }

    @Override
    public String toString(){
        return "clear";
    }

    public void act(String argument){   }
}
