package pl.chudziudgi.core.feature.abyss;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.List;

@CommandInfo(name = "otchlan", perm = "core.command.abyss")

public class AbyssCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        ChatUtil.success(sender, "Przedmioty z ziemi zostały usunięte.");
        itemClear();
    }

    public void itemClear() {
        for (World world : Bukkit.getWorlds()) {
            List<Entity> entList = world.getEntities();
            for (Entity current : entList) {
                if (current instanceof Item) {
                    current.remove();
                }
            }
        }
    }
}
