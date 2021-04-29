package commands;

import exceptions.InputIntervalException;
import other.App;
import other.Organization;
import other.Validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class CommandRemoveGreaterKey extends Command implements CommandWithArg {
    private HashSet<Long> idList;
    protected Organization org;
    protected Long key;
    private Hashtable<Long, Organization> collection;


    public void act(String argument) {
        if (Validation.checkStringToLong(argument, "Error of entering, argument is long, try again."))
            this.key = Long.valueOf(argument);
        else throw new InputIntervalException();
    }


    public String execute(App application) {
        collection = application.getCollection();
        idList = application.getIdList();
        Set<Long> set = collection.keySet();
        ArrayList<Long> for_deleted = new ArrayList<>();
        int count_del = 0;
        for (Long key_cur : set) {
            if (key_cur > this.key) {
                count_del++;
                for_deleted.add(key_cur);
            }
        }
        if (count_del > 0) {
            for (Long key_cur : for_deleted) {
                idList.remove(collection.get(key_cur).getId());
                collection.remove(key_cur);
            }
            return count_del + " key(s) were deleted";
        }
        else return "Collection wasn't changed";
    }


    public Hashtable<Long, Organization> getCollection() {
        return collection;
    }

    public HashSet<Long> getIdList() {
        return idList;
    }

    @Override
    String getCommandInfo() {
        return "remove_greater_key null : удалить из коллекции все элементы, ключ которых превышает заданный";
    }

    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String toString() {
        return "remove_greater_key";
    }
}
