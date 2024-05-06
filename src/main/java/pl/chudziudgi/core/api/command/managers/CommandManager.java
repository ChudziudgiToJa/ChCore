package pl.chudziudgi.core.api.command.managers;


import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import pl.chudziudgi.core.api.command.BukkitCommand;
import pl.chudziudgi.core.api.command.PluginCommand;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CommandManager {

    private CommandMap commandMap;

    public CommandManager(final JavaPlugin plugin) {
        try {
            final Field f = plugin.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            this.commandMap = (CommandMap)f.get(Bukkit.getServer());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerCommand(final PluginCommand pluginCommand) {
        this.commandMap.register(pluginCommand.getName(), new BukkitCommand(pluginCommand));
    }

    public void registerCommands(final PluginCommand... pluginCommands) {
        Arrays.stream(pluginCommands).forEachOrdered(this::registerCommand);
    }
}
