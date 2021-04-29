package commands;

import other.App;
import other.Organization;

import java.util.HashSet;
import java.util.Hashtable;

public class CommandAverage extends Command{

    private App application;

    @Override
    public String execute(App application){
        this.application=application;
        return "Average of employees count is " + application.getSumOfEmployeesCount()/application.getCollection().size();
    }

    @Override
    String getCommandInfo() {
        return "average_of_employees_count : вывести среднее значение поля employeesCount для всех элементов коллекции";
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
        return "average_of_employees_count";
    }

    public void act(String argument){   }

}
