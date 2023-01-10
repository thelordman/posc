package me.lord.posc;

import me.lord.posc.data.Database;
import me.lord.posc.utilities.Cmd;
import me.lord.posc.utilities.Event;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ServiceLoader;

public final class Posc extends JavaPlugin {
    private static Posc instance;

    public static String DB_URI;

    @Override
    public void onEnable() {
        instance = this;

        registerListeners();
        registerCommands();

        DB_URI = getConfig().getString("db_uri");
        Database.init();
    }

    public static Posc get() {
        return instance;
    }

    /**
     * A method to automate the registration of listeners.
     * When adding a new listener add the path of the class to
     * META-INF\services\me.lord.posc.utilities.Event.
     */
    private static void registerListeners() {
        ServiceLoader<Event> loader = ServiceLoader.load(Event.class, Event.class.getClassLoader());
        for (Event event : loader) {
            Bukkit.getPluginManager().registerEvents(event, get());
        }
    }

    /**
     * A method to automate the registration of commands.
     * When adding a new command add the path of the class to
     * META-INF\services\me.lord.posc.utilities.Cmd.
     * Also add the command to plugin.yml.
     */
    private static void registerCommands() throws NullPointerException {
        ServiceLoader<Cmd> loader = ServiceLoader.load(Cmd.class, Cmd.class.getClassLoader());
        for (Cmd cmd : loader) {
            if (cmd.getName() == null && cmd.getNames() == null) {
                throw new NullPointerException("getName() and getNames() cannot both return null; " + cmd.getClass().getSimpleName());
            }
            if (cmd.getNames() == null) {
                get().getCommand(cmd.getName()).setExecutor(cmd);
            } else {
                for (String name : cmd.getNames()) {
                    get().getCommand(name).setExecutor(cmd);
                }
            }
        }
    }
}
