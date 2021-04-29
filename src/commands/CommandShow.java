package commands;

import other.App;
import other.Organization;

import java.util.HashSet;
import java.util.Hashtable;

public class CommandShow extends Command{

    private App application;

    @Override
    public String execute(App application){
        this.application = application;
        StringBuilder result = new StringBuilder();
        if (application.getCollection().size() == 0){
            return "Collection is empty.";
        }
        else{
            System.out.println("Elements of collection:");
            application.getCollection().forEach ((k, v) ->{
                result.append("Key: ").append(k).append("\n").append("Value:").append("\n").append(v.toString()).append("\n");
            });
            return result.deleteCharAt(result.length()-1).toString();
        }
    }


    @Override
    String getCommandInfo() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
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
        return "show";
    }

    public void act(String argument){   }
}
