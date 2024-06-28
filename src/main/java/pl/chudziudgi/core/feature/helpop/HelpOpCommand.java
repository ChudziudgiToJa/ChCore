package pl.chudziudgi.core.feature.helpop;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.DataUtils;
import pl.chudziudgi.core.util.TimeEnum;

@CommandInfo(name = "helpop", usage = "<pomoc> <wiadomosc> <gracz>", player = true)

public class HelpOpCommand extends PluginCommand {

    private final HelpOpManager helpOpManager;

    public HelpOpCommand(HelpOpManager helpOpManager) {
        this.helpOpManager = helpOpManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            sendUsage(player);
            return;
        }

        String commandType = args[0].toLowerCase();
        String message = StringUtils.join(args, " ", 1, args.length);

        switch (commandType) {
            case "wiadomosc":
                handlePrivateMessage(player, message);
                break;

            case "pomoc":
                handleHelpOpMessage(player, message);
                this.helpOpManager.getTimeCache().put(player.getUniqueId(), System.currentTimeMillis() + TimeEnum.MINUTE.getTime(5));
                break;

            default:
                sendUsage(player);
                break;
        }
    }

    private void handlePrivateMessage(Player player, String message) {
        if (!player.hasPermission("core.command.helpop.admin")) {
            ChatUtil.error(player, "Nie masz uprawnien! &8(&7core.command.helpop.admin&8)");
            return;
        }

        String targetName = message.split(" ")[0];
        String privateMessage = message.substring(targetName.length()).trim();

        Player targetPlayer = Bukkit.getPlayerExact(targetName);

        if (targetPlayer != null) {
            String formattedMessage = new MessageBuilder().setText("&c⚠ &3&lHELPOP WIADOMOŚĆ OD ADMINA &8&l┃ &7{SENDER} &8➡ &f{MESSAGE}").addField("{SENDER}", player.getName()).addField("{MESSAGE}", privateMessage).build();

            ChatUtil.msg(targetPlayer, formattedMessage);
            ChatUtil.success(player, formattedMessage + targetPlayer.getName());
        } else {
            ChatUtil.error(player, "Nie znaleziono gracza o nazwie " + targetName);
        }
    }

    private void handleHelpOpMessage(Player player, String message) {
        if (this.helpOpManager.getTimeCache().getIfPresent(player.getUniqueId()) != null && this.helpOpManager.getTimeCache().getIfPresent(player.getUniqueId()) > System.currentTimeMillis()) {
            ChatUtil.error(player, "Nastepny raz mozesz napisac za: {TIME}".replace("{TIME}", DataUtils.durationToString(this.helpOpManager.getTimeCache().getIfPresent(player.getUniqueId()))));
            return;
        }

        String adminMessage = new MessageBuilder().setText("&c⚠ &3&lHELPOP &8&l┃ &7{SENDER} &8➡ &f{MESSAGE}").addField("{SENDER}", player.getName()).addField("{MESSAGE}", message).build();

        Bukkit.getOnlinePlayers().forEach(admin -> {
            if (admin.hasPermission("core.command.helpop.admin")) {
                ChatUtil.msg(admin, adminMessage);
            }
        });

        ChatUtil.success(player, "Twoja wiadomość została wysłana do administracji");
    }
}
