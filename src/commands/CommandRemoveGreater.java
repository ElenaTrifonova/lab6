package commands;

import exceptions.InputIntervalException;
import other.App;
import other.Organization;
import other.Validation;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class CommandRemoveGreater extends Command implements CommandWithArg{
    private HashSet<Long> idList;
    protected Organization org;
    protected Long id;
    private Hashtable<Long, Organization> collection;


    public void act(String argument){
        if((!Validation.checkStringToLong(argument, "Error of entering, argument is long, try again."))
          ||(!Validation.checkInterval(Long.valueOf(argument), 0, false, "Error of entering"))) throw new InputIntervalException();
        this.id = Long.valueOf(argument);

    }

    @Override
    public String execute(App application){
        collection = application.getCollection();
        idList = application.getIdList();

        Set<Long> set = collection.keySet();

        int count_del = 0;
        for (Organization org : collection.values()) {
            while (set.iterator().hasNext()) {
                Long key = set.iterator().next();
                if (org.getId() > this.id) {
                    count_del++;
                    idList.remove(org.getId());
                    collection.remove(key);
                }
            }
        }
            if (count_del > 0) return count_del + " elements were deleted";
            else return "Nothing was changed. There are no elements bigger than given.";

    }

    @Override
    public Hashtable<Long, Organization> getCollection(){
        return collection;
    }

    @Override
    public HashSet<Long> getIdList(){
        return idList;
    }

    @Override
    String getCommandInfo() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }

    @Override
    public boolean withArgument(){
        return true;
    }

    @Override
    public String toString(){
        return "remove_greater";
    }
}
