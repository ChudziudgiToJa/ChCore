package pl.chudziudgi.core.feature.combat;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.TimeEnum;

@CommandInfo(name = "antylogaut", player = true, perm = "core.command.antylogaut", usage = "<clear/add> <gracz>")


public class CombatCommand extends PluginCommand {

    private final CombatManager combatManager;

    public CombatCommand(CombatManager combatManager) {
        this.combatManager = combatManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sendUsage(sender);
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (args[0].equalsIgnoreCase("add")) {
            if (target != null && target.isOnline()) {
                combatManager.createCombat(target, TimeEnum.SECOND, 30);
                ChatUtil.success(sender, "Nadano walkę dla: &7" + target.getName());
            } else {
                ChatUtil.error(sender, "Gracz " + args[1] + " nie jest online.");
            }
            return;
        }

        if (args[0].equalsIgnoreCase("clear")) {
            if (target != null && target.isOnline()) {
                final Combat combat = combatManager.getCombat(target);
                if (combat != null) {
                    combatManager.removeCombat(combat);
                    ChatUtil.success(sender, "Walka wyczyszczona dla: &7" + target.getName());
                } else {
                    ChatUtil.error(sender, "Gracz " + target.getName() + " nie jest w trakcie walki.");
                }
            } else {
                ChatUtil.error(sender, "Gracz " + args[1] + " nie jest online.");
            }
            return;
        }
        sendUsage(sender);
    }
}