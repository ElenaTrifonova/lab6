package commands;

import other.App;
import other.Organization;

import java.util.HashSet;
import java.util.Hashtable;

public class CommandFilterPostal extends Command implements CommandWithArg{

    private App application;
    private String postal;

    public void act(String argument) {
        this.postal = argument;
    }

    /**
     * сравнение и вывод нужных групп
     */
    @Override
    public String execute(App application) {
        this.application = application;
        Hashtable<Long,Organization> collection = getCollection();
        StringBuilder result = new StringBuilder();
        application.sort(collection).values().stream().filter(org -> org.getPostalAddress().getZipCode().equals(postal))
                .forEach(org -> result.append(org.toString()).append("\n"));
        if (collection.isEmpty()) return "Collection is empty...";
        return result.deleteCharAt(result.length() - 1).toString();
    }

    @Override
    public Hashtable<Long, Organization> getCollection() {
        return application.getCollection();
    }

    @Override
    public HashSet<Long> getIdList(){
        return application.getIdList();
    }



    @Override
    String getCommandInfo() {
        return "filter_by_postal_address postalAddress : вывести элементы, значение поля postalAddress которых равно заданному";
    }

    @Override
    public boolean withArgument(){
        return true;
    }

    @Override
    public String toString(){
        return "filter_by_postal_address";
    }
}
