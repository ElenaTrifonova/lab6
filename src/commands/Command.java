package commands;

import other.App;
import other.Organization;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Hashtable;

public abstract class Command implements Serializable {
    /**
     * метод исполнения команды
     * @param application - текущее работающее приложение
     */
    public abstract String execute(App application);

    public abstract Hashtable<Long, Organization> getCollection();

    public abstract HashSet<Long> getIdList();

    /**
     * @return информация о команде, которая потом выводится с командой help
     */
    abstract String getCommandInfo();

    /**
     * метод, нужный для того, чтобы из ссылки на абстрактную команды, мы знали требуется ли этой команде аргумент
     */
    public abstract boolean withArgument();

    public abstract void act(String argument);
}
