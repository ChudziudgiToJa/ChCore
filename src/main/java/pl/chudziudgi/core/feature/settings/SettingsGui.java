package pl.chudziudgi.core.feature.settings;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class SettingsGui {

    public static void open(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Ustawienia", InventoryType.DROPPER);
        final User user = UserManager.get(player);

        inv.setItem(0, new ItemBuilder(Material.MAP)
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
                    user.ignoreStatus = !user.ignoreStatus;
                    open(player);
                }
        );

        inv.setItem(1, new ItemBuilder(Material.KNOWLEDGE_BOOK)
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
                    user.chatAutoMessageStatus = !user.chatAutoMessageStatus;
                    open(player);
                }
        );

        inv.setItem(2, new ItemBuilder(Material.KNOWLEDGE_BOOK)
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
                    user.chatMagicCandleStatus = !user.chatMagicCandleStatus;
                    open(player);
                }
        );
        inv.setItem(3, new ItemBuilder(Material.KNOWLEDGE_BOOK)
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
                    user.timeMessage = !user.timeMessage;
                    open(player);
                }
        );
        inv.setItem(4, new ItemBuilder(Material.KNOWLEDGE_BOOK)
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
                    user.chatQuestionStatus = !user.chatQuestionStatus;
                    open(player);
                }
        );
        inv.setItem(5, new ItemBuilder(Material.KNOWLEDGE_BOOK)
                        .setTitle("&fWiadomości o koszu")
                        .addLore("",
                                "&8Opis",
                                " &7Wyłącza możliwość widzenia wiadomości",
                                " &7automatycznego czyszczenia przedmiotów.",
                                "",
                                "&7Status: " + ChatUtil.booleanString(user.chatAbyssStatus),
                                "",
                                "&7Kliknij &3▜&7▛, aby zmienić",
                                ""
                        )
                        .build(),
                event -> {
                    user.chatAbyssStatus = !user.chatAbyssStatus;
                    open(player);
                }
        );
        inv.open(player);
    }
}
