package pl.chudziudgi.core.feature.settings;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.chat.ChatManager;
import pl.chudziudgi.core.feature.privatemessage.PrivateMessageManager;
import pl.chudziudgi.core.feature.customitem.magiccandle.MagicCandleManager;
import pl.chudziudgi.core.feature.question.QuestionManager;
import pl.chudziudgi.core.feature.settings.incognito.IncognitoManager;
import pl.chudziudgi.core.feature.shop.time.TimeShopManager;
import pl.chudziudgi.core.util.ChatUtil;

public class SettingsGui {

    public static void open(final Player player) {
        final IncognitoManager incognitoManager = new IncognitoManager();
        final PrivateMessageManager privateMessageManager = new PrivateMessageManager();
        final MagicCandleManager magicCandleManager = new MagicCandleManager();
        final TimeShopManager timeShopManager = new TimeShopManager();
        final QuestionManager questionManager = new QuestionManager();
        final InventoryBuilder inv = new InventoryBuilder("&9Ustawienia", 9);
        final User user = UserManager.get(player);

        inv.setItem(0, new ItemBuilder(Material.NAME_TAG)
                        .setTitle("&fIncognito")
                        .addLore("",
                                "&8Opis",
                                " &7Zakrywa twój: nick, range, gildie",
                                "",
                                "&7Status: " + ChatUtil.booleanString(user.incognito),
                                "",
                                "&7Kliknij &3▜&7▛, aby zmienić",
                                ""
                        )
                        .build(),
                event -> {
                    incognitoManager.toggleInkognito(player);
                }
        );

        inv.setItem(1, new ItemBuilder(Material.MAP)
                        .setTitle("&fIgnorowanie wszystkich wiadomości")
                        .addLore("",
                                "&8Opis",
                                " &7Wyłącza możliwość pisania do ciebie wiadomości prywatnych",
                                " &7aby wyciszyć poszczególne osoby /ignore <gracz>",
                                " &7masz również możliwość odciszenia.",
                                "",
                                "&7Status: " + ChatUtil.booleanString(user.ignoreStatus),
                                "",
                                "&7Kliknij &3▜&7▛, aby zmienić",
                                ""
                        )
                        .build(),
                event -> {
                    privateMessageManager.toggle(player);
                }
        );

        inv.setItem(5, new ItemBuilder(Material.KNOWLEDGE_BOOK)
                        .setTitle("&fWiadomości automatyczne")
                        .addLore("",
                                "&8Opis",
                                " &7Wyłącza możliwość widzenia automatycznych wiadomości na chacie",
                                "",
                                "&7Status: " + ChatUtil.booleanString(user.chatAutoMessageStatus),
                                "",
                                "&7Kliknij &3▜&7▛, aby zmienić",
                                ""
                        )
                        .build(),
                event -> {
                    ChatManager.changeAutoMessageUserStatus(player);
                }
        );

        inv.setItem(6, new ItemBuilder(Material.KNOWLEDGE_BOOK)
                        .setTitle("&fWiadomości Magicznej świecy &e★")
                        .addLore("",
                                "&8Opis",
                                " &7Wyłącza możliwość widzenia wiadomości o otworzeniu",
                                " &7magicznej świecy na chacie.",
                                "",
                                "&7Status: " + ChatUtil.booleanString(user.chatMagicCandleStatus),
                                "",
                                "&7Kliknij &3▜&7▛, aby zmienić",
                                ""
                        )
                        .build(),
                event -> {
                    magicCandleManager.toggle(player);
                }
        );
        inv.setItem(7, new ItemBuilder(Material.KNOWLEDGE_BOOK)
                        .setTitle("&fWiadomości o otrzymaniu monety czasu")
                        .addLore("",
                                "&8Opis",
                                " &7Wyłącza możliwość widzenia wiadomości o otworzeniu",
                                " &7otrzymaniu monety czasu.",
                                "",
                                "&7Status: " + ChatUtil.booleanString(user.timeMessage),
                                "",
                                "&7Kliknij &3▜&7▛, aby zmienić",
                                ""
                        )
                        .build(),
                event -> {
                    timeShopManager.toggle(player);
                }
        );
        inv.setItem(8, new ItemBuilder(Material.KNOWLEDGE_BOOK)
                        .setTitle("&fWiadomości o pytaniach")
                        .addLore("",
                                "&8Opis",
                                " &7Wyłącza możliwość widzenia wiadomości o otworzeniu",
                                " &7otrzymaniu monety czasu.",
                                "",
                                "&7Status: " + ChatUtil.booleanString(user.chatQuestionStatus),
                                "",
                                "&7Kliknij &3▜&7▛, aby zmienić",
                                ""
                        )
                        .build(),
                event -> {
                    questionManager.toggle(UserManager.get(player));
                }
        );
        inv.open(player);
    }
}
