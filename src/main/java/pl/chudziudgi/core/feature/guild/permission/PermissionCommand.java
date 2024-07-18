package pl.chudziudgi.core.feature.guild.permission;

import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.user.User;
import net.dzikoysk.funnyguilds.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import panda.std.Option;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;


@CommandInfo(
        name = "uprawnienia",
        usage = "<gracz>",
        player = true
)

public class PermissionCommand extends PluginCommand {

    private final ChCore plugin;

    public PermissionCommand(ChCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            sendUsage(sender);
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            ChatUtil.error(sender, "Gracz jest offline");
            return;
        }

        if (target == player) {
            ChatUtil.error(sender, "Nie możesz zmieniać swoich uprawnień");
            return;
        }

        FunnyGuilds funnyGuilds = this.plugin.getFunnyGuilds();
        UserManager userManager = funnyGuilds.getUserManager();

        Option<User> userOption = userManager.findByPlayer(player);
        if (!userOption.isPresent()) {
            ChatUtil.error(sender, "Nie znaleziono użytkownika.");
            return;
        }

        User user = userOption.get();
        Option<Guild> guildOption = user.getGuild();
        if (!guildOption.isPresent()) {
            ChatUtil.error(sender, "Nie posiadasz klanu!");
            return;
        }

        Guild guild = guildOption.get();

        if (!user.isOwner() && !user.isDeputy()) {
            ChatUtil.error(sender, "Musisz być liderem lub zastępcą, aby użyć tej komendy.");
            return;
        }

        Option<User> userTargetOption = userManager.findByPlayer(target);
        if (!userTargetOption.isPresent()) {
            ChatUtil.error(sender, "Nie znaleziono użytkownika docelowego.");
            return;
        }

        User userTarget = userTargetOption.get();
        Option<Guild> targetGuildOption = userTarget.getGuild();
        if (!targetGuildOption.isPresent() || !targetGuildOption.get().equals(guild)) {
            ChatUtil.error(sender, "Gracz nie jest w twoim klanie.");
            return;
        }

        if (userTarget.isDeputy() || userTarget.isOwner()) {
            ChatUtil.error(sender, "Nie możesz zmienić uprawnień zastępcy lub lidera.");
            return;
        }

        PermissionGui.open(player, target);
    }
}