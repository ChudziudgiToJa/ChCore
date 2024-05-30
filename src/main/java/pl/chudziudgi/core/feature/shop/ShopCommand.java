package pl.chudziudgi.core.feature.shop;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "sklep", player = true, usage = "<czas,egg> <czas=add/set/clear> <egg=add/set>")


public class ShopCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            ShopGui.open(player);
            return;
        }

        if (!player.hasPermission("core.command.sklep.admin")) {
            ChatUtil.error(sender, "Nie masz uprawnień! &8(&7core.command.sklep.admin&8)");
            return;
        }

        if (args[0].equalsIgnoreCase("egg")) {
            String subCommand = args[1].toLowerCase();
            switch (subCommand) {
                case "add" -> handleAddEgg(player, args);
                case "set" -> handleSetEgg(player, args);
                default -> sendUsage(player);
            }
        }

        if (args[0].equalsIgnoreCase("czas")) {
            String subCommand = args[1].toLowerCase();
            switch (subCommand) {
                case "add" -> handleAddTime(player, args);
                case "clear" -> handleClearTime(player, args);
                case "set" -> handleSetTime(player, args);
                default -> sendUsage(player);
            }
        }
    }

    private void handleSetEgg(Player player, String[] args) {
        if (args.length < 4) {
            sendUsage(player);
            return;
        }

        Player target = Bukkit.getPlayer(args[2]);
        if (target == null || (!UserManager.isExists(player))) {
            ChatUtil.error(player, "Nie odnaleziono gracza w bazie danych");
            return;
        }
        User user = UserManager.get(target);

        try {
            int amountToSet = Integer.parseInt(args[3]);
            user.answerCandle = amountToSet;
            ChatUtil.success(player, "Ustawiono eggi na " + amountToSet + " dla " + target.getName());
        } catch (NumberFormatException e) {
            noInt(player);
        }
    }

    private void handleAddEgg(Player player, String[] args) {
        if (args.length < 4) {
            sendUsage(player);
            return;
        }

        Player target = Bukkit.getPlayer(args[2]);
        if (target == null || (!UserManager.isExists(player))) {
            ChatUtil.error(player, "Nie odnaleziono gracza w bazie danych");
            return;
        }
        User user = UserManager.get(target);

        try {
            int amountToAdd = Integer.parseInt(args[3]);
            user.answerCandle += amountToAdd;
            ChatUtil.success(player, "Dodano " + amountToAdd + " dla " + target.getName() + " do odebrania eggi.");
        } catch (NumberFormatException e) {
            noInt(player);
        }
    }

    private void handleAddTime(Player player, String[] args) {
        if (args.length < 4) {
            sendUsage(player);
            return;
        }

        Player target = Bukkit.getPlayer(args[2]);
        if (target == null) {
            ChatUtil.error(player, "Nie odnaleziono gracza");
            return;
        }

        User user = UserManager.get(target);

        try {
            int amountToAdd = Integer.parseInt(args[3]);
            user.timeShop += amountToAdd;
            ChatUtil.success(player, "Dodano " + amountToAdd + " dla " + target.getName() + " do sklepu czasu.");
        } catch (NumberFormatException e) {
            noInt(player);
        }
    }

    private void handleClearTime(Player player, String[] args) {
        if (args.length < 3) {
            sendUsage(player);
            return;
        }

        Player target = Bukkit.getPlayer(args[2]);
        if (target == null) {
            ChatUtil.error(player, "Nie odnaleziono gracza");
            return;
        }

        User user = UserManager.get(target);
        user.timeShop = 0;

        ChatUtil.success(player, "Wyczyszczono monety czasu " + target.getName() + ".");
    }

    private void handleSetTime(Player player, String[] args) {
        if (args.length < 4) {
            sendUsage(player);
            return;
        }

        Player target = Bukkit.getPlayer(args[2]);
        if (target == null) {
            ChatUtil.error(player, "Nie odnaleziono gracza");
            return;
        }

        User user = UserManager.get(target);

        try {
            int amountToSet = Integer.parseInt(args[3]);
            user.timeShop = amountToSet;
            ChatUtil.success(player, "Ustawiono monety czasu na " + amountToSet + " dla " + target.getName());
        } catch (NumberFormatException e) {
            noInt(player);
        }
    }

    private void noInt(Player player) {
        ChatUtil.error(player, "Błędny format liczby monet czasu.");
    }
}