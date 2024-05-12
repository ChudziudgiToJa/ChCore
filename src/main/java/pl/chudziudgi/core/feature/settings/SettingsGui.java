package pl.chudziudgi.core.feature.settings;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;
import pl.chudziudgi.core.feature.chat.privatemessage.PrivateMessageManager;
import pl.chudziudgi.core.feature.deposit.DepositUtil;
import pl.chudziudgi.core.feature.settings.incognito.IncognitoManager;
import pl.chudziudgi.core.util.ChatUtil;

public class SettingsGui {

    public static void open(final Player player) {
        final IncognitoManager incognitoManager = new IncognitoManager();
        final PrivateMessageManager privateMessageManager = new PrivateMessageManager();
        final InventoryBuilder inv = new InventoryBuilder("&8Ustawienia", InventoryType.HOPPER);
        final User user = UserManager.getUser(player);

        inv.setItem(1, new ItemBuilder(Material.NAME_TAG)
                        .setTitle("&fIncognito")
                        .addLore("",
                                "&8Opis",
                                " &7Zakrywa twój: nick, range, gildie",
                                "",
                                "&7Status: " + (user.incognito ? "&awlaczony" : "&cwylaczony"))
                        .build(),
                event -> {
                    incognitoManager.toggleInkognito(player);
                }
        );

        inv.setItem(3, new ItemBuilder(Material.MAP)
                        .setTitle("&fIgnorowanie wszystkich wiadomości")
                        .addLore("",
                                "&8Opis",
                                " &7Wyłącza możliwość pisania do ciebie wiadomości prywatnych",
                                " &7aby wyciszyć poszczególne osoby /ignore <gracz>",
                                "",
                                "&7Status: " + (user.ignoreStatus ? "&awlaczony" : "&cwylaczony"))
                        .build(),
                event -> {
                    privateMessageManager.toggle(player);
                }
        );
        inv.open(player);
    }
}
