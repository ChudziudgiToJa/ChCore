package pl.chudziudgi.core.feature.settings;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.chat.ChatManager;
import pl.chudziudgi.core.feature.chat.privatemessage.PrivateMessageManager;
import pl.chudziudgi.core.feature.settings.incognito.IncognitoManager;

public class SettingsGui {

    public static void open(final Player player) {
        final IncognitoManager incognitoManager = new IncognitoManager();
        final PrivateMessageManager privateMessageManager = new PrivateMessageManager();
        final InventoryBuilder inv = new InventoryBuilder("&9Ustawienia", InventoryType.HOPPER);
        final User user = UserManager.get(player);

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

        inv.setItem(2, new ItemBuilder(Material.CLOCK)
                        .setTitle("&fWiadomości automatyczne")
                        .addLore("",
                                "&8Opis",
                                " &7Wyłącza możliwość widzenia automatycznych wiadomości na chacie",
                                "",
                                "&7Status: " + (user.chatAutoMessageStatus ? "&awlaczony" : "&cwylaczony"))
                        .build(),
                event -> {
                    ChatManager.changeAutoMessageUserStatus(player);
                }
        );
        inv.open(player);

        inv.setItem(3, new ItemBuilder(Material.MAP)
                        .setTitle("&fIgnorowanie wszystkich wiadomości")
                        .addLore("",
                                "&8Opis",
                                " &7Wyłącza możliwość pisania do ciebie wiadomości prywatnych",
                                " &7aby wyciszyć poszczególne osoby /ignore <gracz>",
                                " &7masz również możliwość odciszenia.",
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
