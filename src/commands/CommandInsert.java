package commands;

import exceptions.InputIntervalException;
import other.App;
import other.OrgGeneration;
import other.Organization;
import other.Validation;

import java.util.HashSet;
import java.util.Hashtable;


public class CommandInsert extends Command{
    private HashSet<Long> idList;
    protected Organization org;
    protected Long argument;
    private Hashtable<Long, Organization> collection;



    public void act(String arg){
        org = OrgGeneration.generate();
        if(Validation.checkStringToLong(arg, "Error of entering, argument is long, try again.")) this.argument = Long.valueOf(arg);
          else throw new InputIntervalException();

    }

    @Override
    public String execute(App application) {
        collection = application.getCollection();
        idList = application.getIdList();
        idList.add(org.getId());
        collection.put(argument, org);
        application.sort(collection);
        return "Element with key" + argument + " was added to the collection";
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
        return "insert null {element} : добавить новый элемент с заданным ключом";
    }



    @Override
    public boolean withArgument(){
        return true;
    }

    @Override
    public String toString(){
        return "insert";
    }
}
