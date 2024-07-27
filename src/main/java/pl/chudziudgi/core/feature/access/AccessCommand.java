package pl.chudziudgi.core.feature.access;

import org.bukkit.command.CommandSender;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.util.ChatUtil;


@CommandInfo(
        name = "sloty",
        player = false,
        perm = "core.command.slot",
        usage = "<iron/gold/all> <ilosc miejsc>"
)

public class AccessCommand extends PluginCommand {

    private final PluginConfiguration configuration;

    public AccessCommand(PluginConfiguration configuration) {
        this.configuration = configuration;
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        String arg = args[0].toLowerCase();

        switch (arg) {
            case "iron" -> {
                try {
                    int amount = Integer.parseInt(args[1]);
                    this.configuration.accessSettings.minimalForIron = amount;

                    ChatUtil.success(sender, "Ustawiono limit slotów od rangi IRON do" + amount);
                } catch (NumberFormatException e) {
                    ChatUtil.error(sender, "Ilość musi być liczbą pierwszą.");
                }
                return;
            }
            case "gold" -> {
                try {
                    int amount = Integer.parseInt(args[1]);
                    this.configuration.accessSettings.minimalForGold = amount;
                    ChatUtil.success(sender, "Ustawiono limit slotów od rangi GOLD do" + amount);
                } catch (NumberFormatException e) {
                    ChatUtil.error(sender, "Ilość musi być liczbą pierwszą.");
                }
                return;
            }
            case "all" -> {
                try {
                    int amount = Integer.parseInt(args[1]);
                    if (amount > 250) {
                        ChatUtil.error(sender, "Limit slotów można ustawić maxymalnie do 250!");
                        return;
                    }
                    this.configuration.accessSettings.maxPlayers = amount;
                    ChatUtil.success(sender, "Ustawiono limit slotów dla wszystkich na" + amount);
                } catch (NumberFormatException e) {
                    ChatUtil.error(sender, "Ilość musi być liczbą pierwszą.");
                }
                return;
            }
        }
        usage(sender);
        return;
    }

    private void usage(CommandSender sender) {
        usage(sender);
        ChatUtil.info(sender, "Aktualny status slotów:");
        ChatUtil.info(sender,
                new MessageBuilder().setText("&f&lIRON&7: &f{IRON} &e&lGOLD&7: &f{GOLD} &3&lALL&7: &f{ALL}")
                        .addField("{IRON}", String.valueOf(this.configuration.accessSettings.minimalForIron))
                        .addField("{GOLD}", String.valueOf(this.configuration.accessSettings.minimalForGold))
                        .addField("{ALL}", String.valueOf(this.configuration.accessSettings.maxPlayers))
                        .build()
        );
    }
}
