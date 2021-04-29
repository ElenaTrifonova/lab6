package commands;

import other.App;
import other.Organization;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class CommandMax extends Command {

    private App application;

    @Override
    public String execute(App application) {
        this.application = application;
        LocalDateTime max;
        Long maxKey;
        if (application.getCollection().size() > 0) {

            try {
                maxKey = application.getCollection().keySet().iterator().next();
                max = application.getCollection().get(maxKey).getCreationDate();
                Set<Long> set = application.getCollection().keySet();
                for (Long key : set) {
                    Organization org = application.getCollection().get(key);
                    if (org.getCreationDate().isAfter(max)) {
                        maxKey = key;
                        max = org.getCreationDate();
                    }
                }
                return "Information about organization with max creation date:" + application.getCollection().get(maxKey).toString();
            } catch (NullPointerException e) {
                return "Error";
            }
        } else return "Collection is empty.";
    }

    @Override
    String getCommandInfo() {
        return "max_by_creation_date : вывести любой объект из коллекции, значение поля creationDate которого является максимальным";
    }

    @Override
    public Hashtable<Long, Organization> getCollection(){
        return application.getCollection();
    }

    @Override
    public HashSet<Long> getIdList(){
        return application.getIdList();
    }

    @Override
    public boolean withArgument(){
        return false;
    }

    @Override
    public String toString(){
        return "max_by_creation_date";
    }

    public void act(String argument){   }
}
