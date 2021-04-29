package commands;

import exceptions.InputIntervalException;
import other.App;
import other.Organization;
import other.Validation;

import java.util.HashSet;
import java.util.Hashtable;


public class CommandRemoveKey extends Command implements CommandWithArg{
    private HashSet<Long> idList;
    protected Organization org;
    protected Long key;
    private Hashtable<Long, Organization> collection;


    public void act(String argument){
        if(Validation.checkStringToLong(argument, "Error of entering, argument is long, try again.")) this.key = Long.valueOf(argument);
        else throw new InputIntervalException();
    }


    public String execute(App application){
        collection= application.getCollection();
        int startSize = collection.size();
        idList = application.getIdList();
        idList.remove(collection.get(key).getId());
        collection.remove(key);


        if (collection.size()<startSize){
            //idList.remove(collection.get(key).getId());
            return "Element was deleted.";
        }else return "Element wasn't deleted. Maybe key isn't exist.";
    }

    public Hashtable<Long, Organization> getCollection(){
        return collection;
    }

    public HashSet<Long> getIdList(){
        return idList;
    }

    @Override
    String getCommandInfo() {
        return "remove_key null : удалить элемент из коллекции по его ключу";
    }

    @Override
    public boolean withArgument(){
        return true;
    }

    @Override
    public String toString(){
        return "remove_key";
    }
}
