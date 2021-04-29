package commands;

import other.App;
import other.Organization;

import java.util.HashSet;
import java.util.Hashtable;

public class CommandInfo extends Command{

    private App application;

    @Override
    public String execute(App application){
        this.application = application;
        StringBuilder result = new StringBuilder();
        result.append("Information about collection:").append("\n");
        result.append("Number of elements: ").append(getCollection().size()).append("\n");
        if (!getCollection().isEmpty()) {
            result.append("Type of data stored in the collection: ").append(getCollection().getClass().toString()).append("\n");
        }
        return result.deleteCharAt(result.length()-1).toString();

    }

    @Override
    String getCommandInfo() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
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
        return "info";
    }

    public void act(String argument){   }
}
