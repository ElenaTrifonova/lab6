package commands;

import other.App;
import other.Organization;

import java.util.HashSet;
import java.util.Hashtable;

public class CommandHelp extends Command{

    private App application;

    @Override
    public String execute(App application){
        this.application = application;
        return "The available commands: " + "\n" +
                new CommandHelp().getCommandInfo() + "\n" +
                new CommandInfo().getCommandInfo() + "\n" +
                new CommandShow().getCommandInfo() + "\n" +
                new CommandInsert().getCommandInfo() + "\n" +
                new CommandUpdateID().getCommandInfo() + "\n" +
                new CommandRemoveKey().getCommandInfo() + "\n" +
                new CommandClear().getCommandInfo() + "\n" +
                new CommandExecuteScript().getCommandInfo() + "\n" +
                new CommandExit().getCommandInfo() + "\n" +
                new CommandAverage().getCommandInfo() + "\n" +
                new CommandRemoveGreater().getCommandInfo() + "\n" +
                new CommandRemoveGreaterKey().getCommandInfo() + "\n" +
                new CommandRemoveLowerKey().getCommandInfo() + "\n" +
                new CommandFilterPostal().getCommandInfo() + "\n" +
                new CommandMax().getCommandInfo();

    }

    @Override
    String getCommandInfo() {
        return "help: вывести справку по доступным командам";
    }

    @Override
    public  Hashtable<Long, Organization> getCollection(){
        return application.getCollection();
    }

    @Override
    public  HashSet<Long> getIdList(){
        return application.getIdList();
    }

    @Override
    public boolean withArgument(){
        return false;
    }

    @Override
    public String toString(){
        return "help";
    }

    public void act(String argument){   }
}
