package pl.chudziudgi.core.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.feature.drop.Drop;
import pl.chudziudgi.core.feature.kit.Kit;
import pl.chudziudgi.core.feature.question.Question;
import pl.chudziudgi.core.feature.shop.time.TimeShop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PluginConfiguration extends OkaeriConfig {

    public AccessSettings accessSettings = new AccessSettings();
    public BlockerSettings blockerSettings = new BlockerSettings();
    public ChatSettings chatSettings = new ChatSettings();
    public CombatSettings combatSettings = new CombatSettings();
    public MagicCandleSettings magicCandleSettings = new MagicCandleSettings();
    public DepositSettings depositSettings = new DepositSettings();
    public DropSettings dropSettings = new DropSettings();
    public KitSettings kitSettings = new KitSettings();
    public ProtectionSettings protectionSettings = new ProtectionSettings();
    public QuestionSettings questionSettings = new QuestionSettings();
    public TimeShopSettings timeShopSettings = new TimeShopSettings();
    public RandomTpSettings randomTpSettings = new RandomTpSettings();

    public static class AccessSettings extends OkaeriConfig {
        public int maxPlayers = 250;
        public int minimalForIron = 150;
        public int minimalForGold = 200;
    }

    public static class BlockerSettings extends OkaeriConfig {
        public List<Material> disableCrafting = Arrays.asList(
                Material.OAK_BOAT,
                Material.SPRUCE_BOAT,
                Material.BIRCH_BOAT,
                Material.ACACIA_BOAT,
                Material.DARK_OAK_BOAT,
                Material.ACACIA_BOAT,
                Material.MANGROVE_BOAT,
                Material.BAMBOO_RAFT,
                Material.SPRUCE_BOAT,
                Material.LIGHT_BLUE_CANDLE,
                Material.DIAMOND_HELMET,
                Material.DIAMOND_CHESTPLATE,
                Material.DIAMOND_LEGGINGS,
                Material.DIAMOND_BOOTS,
                Material.DIAMOND_SWORD,
                Material.ARMOR_STAND,
                Material.SHIELD
        );
    }

    public static class ChatSettings extends OkaeriConfig {
        public String chatFormat = "%luckperms_prefix%%rel_funnyguilds_tag%&7{PLAYER} &8| &f";
        public List<String> listAutoMessage = Arrays.asList("DMG z siekier jest zmiejsznone o 50%.", "Ułatw sobie rozgrywkę, korzystając z opcji &b/ustawienia.", "Potrzebujesz informacji w założeniu klanu? &b/klan.", "Sprawdź harmonogram edycji na naszym &9&nDiscordzie", "Masz problem? Załóż zgłoszenie na naszym &9&nDiscordzie", "Możliwość podbijania terytorium klanów w godzinach &b19:00 - 21:00");
        public List<String> listaDozwolonychZnakow = Arrays.asList(" ", "A", "Ą", "B", "C", "Ć", "D", "E", "Ę", "F", "G", "H", "I", "J", "K", "L", "Ł", "M", "N", "Ń", "O", "Ó", "P", "Q", "R", "S", "Ś", "T", "U", "V", "W", "X", "Y", "Z", "Ź", "Ż", "a", "ą", "b", "c", "ć", "d", "e", "ę", "f", "g", "h", "i", "j", "k", "l", "ł", "m", "n", "ń", "o", "ó", "p", "q", "r", "s", "ś", "t", "u", "v", "w", "x", "y", "z", "ź", "ż", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "!", "?", ".", ",", ";", ":", "-", "_", "(", ")", "[", "]", "{", "}", "<", ">", "'", "\"", "@", "#", "$", "^", "&", "*", "+", "=", "/", "\\", "|", "~", "`");
        public Boolean chatMessageBlock = false;
        public Boolean chatAutoMessage = true;
    }

    public static class CombatSettings extends OkaeriConfig {
        public List<String> commandsList = Arrays.asList("gracz", "info");
        public String COMMAND_BLOCK_MESSAGE = "Komenda zablokowana w trakcie PvP!";
        public String LOGOUT_MESSAGE = "Gracz: &c{PLAYER} &7wylogowal się podczas walki!";
        public String COMBAT_MESSAGE = "&3⌚ AntyLogout &7pozostalo &3{TIME}";
        public String COMBAT_END_MESSAGE = "&3⌚ AntyLogout &bdobiega konca";
    }

    public static class MagicCandleSettings extends OkaeriConfig {
        public List<ItemStack> dropList = Arrays.asList(

                new ItemBuilder(Material.IRON_HELMET)
                        .setTitle("&7Hełm &34/3")
                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                        .addEnchant(Enchantment.DURABILITY, 3)
                        .build(),

                new ItemBuilder(Material.IRON_CHESTPLATE)
                        .setTitle("&7Klata &34/3")
                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                        .addEnchant(Enchantment.DURABILITY, 3)
                        .build(),

                new ItemBuilder(Material.IRON_LEGGINGS)
                        .setTitle("&7Spodnie &34/3")
                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                        .addEnchant(Enchantment.DURABILITY, 3)
                        .build(),

                new ItemBuilder(Material.IRON_BOOTS)
                        .setTitle("&7Buty &34/3")
                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                        .addEnchant(Enchantment.DURABILITY, 3)
                        .build(),

                new ItemBuilder(Material.IRON_SWORD)
                        .setTitle("&7Miecz &34/3")
                        .addEnchant(Enchantment.DAMAGE_ALL, 4)
                        .addEnchant(Enchantment.DURABILITY, 3)
                        .build(),

                new ItemBuilder(Material.BOW)
                        .setTitle("&7łuk &32/2")
                        .addEnchant(Enchantment.ARROW_DAMAGE, 2)
                        .addEnchant(Enchantment.ARROW_FIRE, 2)
                        .build(),

                new ItemBuilder(Material.ARROW, 16)
                        .build(),

                new ItemBuilder(Material.ARROW, 48)
                        .build(),

                new ItemBuilder(Material.ENDER_PEARL, 4)
                        .build(),

                new ItemBuilder(Material.TOTEM_OF_UNDYING)
                        .build(),

                new ItemBuilder(Material.GOLD_INGOT, 16)
                        .build(),

                new ItemBuilder(Material.GRAY_GLAZED_TERRACOTTA, 16)
                        .setTitle("&fGenerator kamienia &e❀")
                        .addLore(
                                "",
                                "&8Opis",
                                "&7Generator kamienia to przedmiot,",
                                "&7który po użyciu generuje kamienia 1 blok w &bgórę ⬆&7.",
                                "&7Pamiętaj wymagane jest puste pole aby generator &n&fzadziałał",
                                "",
                                "&7Postaw &3▜&7▛&7, aby użyć."
                        )
                        .setGlow(true)
                        .build(),

                new ItemBuilder(Material.PURPLE_GLAZED_TERRACOTTA, 16)
                        .setTitle("&fGenerator obsydianiu &e❀")
                        .addLore(
                                "",
                                "&8Opis",
                                "&7Generator obsydianiu to przedmiot,",
                                "&7który po użyciu generuje obsydian w &bdół ⬇&7.",
                                "&7Pamiętaj wymagane jest puste pole aby generator &n&fzadziałał",
                                "",
                                "&7Postaw &3▜&7▛&7, aby użyć."
                        )
                        .setGlow(true)
                        .build(),

                new ItemBuilder(Material.DIAMOND_BLOCK, 8)
                        .build(),

                new ItemBuilder(Material.COPPER_BLOCK, 32)
                        .build(),

                new ItemBuilder(Material.LIGHT_BLUE_CANDLE, 2)
                        .setTitle("&fMagiczne świeca &e★")
                        .addLore(
                                "",
                                "&8Opis",
                                "&7Magiczna świeca to tajemniczy przedmiot,",
                                "&7który po użyciu losowo przemienia się w cenny skarb.",
                                "",
                                "&7Kliknij &7▜&3▛&7, aby użyć."
                        )
                        .setGlow(true)
                        .build(),

                new ItemBuilder(Material.APPLE, 32)
                        .build(),

                new ItemBuilder(Material.ENDER_PEARL, 8)
                        .build(),

                new ItemBuilder(Material.TOTEM_OF_UNDYING)
                        .build(),

                new ItemBuilder(Material.GOLD_INGOT, 48)
                        .build(),

                new ItemBuilder(Material.GRAY_GLAZED_TERRACOTTA, 32)
                        .setTitle("&fGenerator kamienia &e❀")
                        .addLore(
                                "",
                                "&8Opis",
                                "&7Generator kamienia to przedmiot,",
                                "&7który po użyciu generuje kamienia 1 blok w &bgórę ⬆&7.",
                                "&7Pamiętaj wymagane jest puste pole aby generator &n&fzadziałał",
                                "",
                                "&7Postaw &3▜&7▛&7, aby użyć."
                        )
                        .setGlow(true)
                        .build(),

                new ItemBuilder(Material.PURPLE_GLAZED_TERRACOTTA, 32)
                        .setTitle("&fGenerator obsydianiu &e❀")
                        .addLore(
                                "",
                                "&8Opis",
                                "&7Generator obsydianiu to przedmiot,",
                                "&7który po użyciu generuje obsydian w &bdół ⬇&7.",
                                "&7Pamiętaj wymagane jest puste pole aby generator &n&fzadziałał",
                                "",
                                "&7Postaw &3▜&7▛&7, aby użyć."
                        )
                        .setGlow(true)
                        .build(),

                new ItemBuilder(Material.DIAMOND_BLOCK, 16)
                        .build(),

                new ItemBuilder(Material.EXPERIENCE_BOTTLE, 32)
                        .build(),

                new ItemBuilder(Material.LIGHT_BLUE_CANDLE, 4)
                        .setTitle("&fMagiczne świeca &e★")
                        .addLore(
                                "",
                                "&8Opis",
                                "&7Magiczna świeca to tajemniczy przedmiot,",
                                "&7który po użyciu losowo przemienia się w cenny skarb.",
                                "",
                                "&7Kliknij &7▜&3▛&7, aby użyć."
                        )
                        .setGlow(true)
                        .build(),

                new ItemBuilder(Material.EXPERIENCE_BOTTLE, 64)
                        .build()
        );
    }

    public static class DepositSettings extends OkaeriConfig {
        public int enchantedGoldenAppleLimit = 1;
        public int goldenAppleLimit = 8;
        public int enderPearlLimit = 2;
        public int totemOfUndyingLimit = 1;
        public int arrowLimit = 16;
        public int chorusLimit = 5;
        public int iceLimit = 16;
    }

    public static class DropSettings extends OkaeriConfig {
        public List<Drop> dropList = Arrays.asList(
                new Drop("Diamenty",
                        0.02,
                        true,
                        1,
                        1,
                        2,
                        "DIAMOND"),

                new Drop("Czerwony proch",
                        0.05,
                        true,
                        1,
                        2,
                        1,
                        "REDSTONE"),

                new Drop("Węgiel",
                        0.06,
                        true,
                        1,
                        4,
                        1,
                        "COAL"),

                new Drop("Lapiz",
                        0.04,
                        true,
                        1,
                        4,
                        1,
                        "LAPIS_LAZULI"),

                new Drop("Złoto",
                        0.03,
                        true,
                        1,
                        2,
                        2,
                        "GOLD_ORE"),

                new Drop("Żelazo",
                        0.02,
                        true,
                        1,
                        2,
                        2,
                        "IRON_ORE"),

                new Drop("Miedź",
                        0.05,
                        true,
                        1,
                        3,
                        1,
                        "COPPER_ORE"),

                new Drop("Emerald",
                        0.02,
                        false,
                        1,
                        1,
                        2,
                        "EMERALD"),

                new Drop("Obsydian",
                        0.02,
                        true,
                        1,
                        3,
                        1,
                        "OBSIDIAN"),

                new Drop("Perła",
                        0.02,
                        false,
                        1,
                        1,
                        3,
                        "ENDER_PEARL"),

                new Drop("Jabłko",
                        0.04,
                        false,
                        1,
                        2,
                        2,
                        "APPLE"),

                new Drop("Skóra",
                        0.03,
                        false,
                        1,
                        1,
                        3,
                        "LEATHER"),

                new Drop("Proch",
                        0.02,
                        false,
                        1,
                        2,
                        2,
                        "GUNPOWDER")
        );
    }

    public static class KitSettings extends OkaeriConfig {

        public final List<Kit> kits = Arrays.asList(

                new Kit(
                        "start",
                        "core.kit.start",
                        1,
                        Material.LEATHER_HELMET,
                        new ArrayList<>(Arrays.asList(
                                new ItemBuilder(Material.OAK_LOG, 4)
                                        .build(),
                                new ItemBuilder(Material.COOKED_RABBIT, 8)
                                        .build(),
                                new ItemBuilder(Material.STONE_PICKAXE)
                                        .setTitle("&7Kilof &3startowy")
                                        .addEnchant(Enchantment.DIG_SPEED, 4)
                                        .build()
                        ))
                ),
                new Kit(
                        "iron",
                        "core.kit.iron",
                        24,
                        Material.IRON_HELMET,
                        new ArrayList<>(Arrays.asList(
                                new ItemBuilder(Material.IRON_HELMET)
                                        .setTitle("&7Hełm &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_CHESTPLATE)
                                        .setTitle("&7Klata &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_LEGGINGS)
                                        .setTitle("&7Spodnie &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_BOOTS)
                                        .setTitle("&7Buty &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_SWORD)
                                        .setTitle("&7Miecz &34/3")
                                        .addEnchant(Enchantment.DAMAGE_ALL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_PICKAXE)
                                        .setTitle("&7Kilof &35/3/3")
                                        .addEnchant(Enchantment.DIG_SPEED, 5)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3)
                                        .build(),

                                new ItemBuilder(Material.BOW)
                                        .setTitle("&7łuk &32/2")
                                        .addEnchant(Enchantment.ARROW_DAMAGE, 2)
                                        .addEnchant(Enchantment.ARROW_FIRE, 2)
                                        .build(),

                                new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 1)
                                        .build(),

                                new ItemBuilder(Material.GOLDEN_APPLE, 8)
                                        .build(),

                                new ItemBuilder(Material.ENDER_PEARL, 2)
                                        .build(),

                                new ItemBuilder(Material.ARROW, 16)
                                        .build(),

                                new ItemBuilder(Material.CHORUS_FRUIT, 5)
                                        .build(),

                                new ItemBuilder(Material.PACKED_ICE, 16)
                                        .build(),

                                new ItemBuilder(Material.TOTEM_OF_UNDYING)
                                        .build(),

                                new ItemBuilder(Material.TOTEM_OF_UNDYING)
                                        .build(),

                                new ItemBuilder(Material.WATER_BUCKET)
                                        .build(),

                                new ItemBuilder(Material.COOKED_RABBIT, 32)
                                        .build()
                        ))

                ),
                new Kit(
                        "gold",
                        "core.kit.gold",
                        48,
                        Material.GOLDEN_HELMET,
                        new ArrayList<>(Arrays.asList(
                                new ItemBuilder(Material.IRON_HELMET)
                                        .setTitle("&7Hełm &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_CHESTPLATE)
                                        .setTitle("&7Klata &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_LEGGINGS)
                                        .setTitle("&7Spodnie &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_BOOTS)
                                        .setTitle("&7Buty &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_HELMET)
                                        .setTitle("&7Hełm &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_CHESTPLATE)
                                        .setTitle("&7Klata &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_LEGGINGS)
                                        .setTitle("&7Spodnie &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_BOOTS)
                                        .setTitle("&7Buty &34/3")
                                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_SWORD)
                                        .setTitle("&7Miecz &34/3")
                                        .addEnchant(Enchantment.DAMAGE_ALL, 4)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .build(),

                                new ItemBuilder(Material.IRON_PICKAXE)
                                        .setTitle("&7Kilof &35/3/3")
                                        .addEnchant(Enchantment.DIG_SPEED, 5)
                                        .addEnchant(Enchantment.DURABILITY, 3)
                                        .addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3)
                                        .build(),

                                new ItemBuilder(Material.BOW)
                                        .setTitle("&7łuk &32/2")
                                        .addEnchant(Enchantment.ARROW_DAMAGE, 2)
                                        .addEnchant(Enchantment.ARROW_FIRE, 2)
                                        .build(),

                                new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 2)
                                        .build(),

                                new ItemBuilder(Material.GOLDEN_APPLE, 8 * 2)
                                        .build(),

                                new ItemBuilder(Material.ENDER_PEARL, 2 * 2)
                                        .build(),

                                new ItemBuilder(Material.ARROW, 16 * 2)
                                        .build(),

                                new ItemBuilder(Material.CHORUS_FRUIT, 5 * 2)
                                        .build(),

                                new ItemBuilder(Material.PACKED_ICE, 16 * 2)
                                        .build(),

                                new ItemBuilder(Material.TOTEM_OF_UNDYING)
                                        .build(),

                                new ItemBuilder(Material.TOTEM_OF_UNDYING)
                                        .build(),

                                new ItemBuilder(Material.TOTEM_OF_UNDYING)
                                        .build(),

                                new ItemBuilder(Material.WATER_BUCKET)
                                        .build(),

                                new ItemBuilder(Material.WATER_BUCKET)
                                        .build(),

                                new ItemBuilder(Material.COOKED_RABBIT, 64)
                                        .build()
                        ))
                )
        );
        public boolean kitStatus = true;
    }

    public static class ProtectionSettings extends OkaeriConfig {
        public String PROTECTION_MESSAGE = "&3⌚ Ochrona &7pozostalo &3{TIME}";
        public String PROTECTION_END_MESSAGE = "&3⌚ Ochrona &bdobiegla konca";
    }


    public static class QuestionSettings extends OkaeriConfig {

        public List<Question> questionList = Arrays.asList(
                new Question("Jak nazywa się stolica Polski?", "Warszawa"),
                new Question("Jaka planeta jest najbliżej Słońca?", "Merkury"),
                new Question("Jakie jest największe jezioro w Polsce?", "Śniardwy"),
                new Question("Jaki pierwiastek chemiczny ma symbol O?", "Tlen"),
                new Question("Jakie zwierzę jest symbolem USA?", "Orzeł"),
                new Question("Jakie jest najdłuższe rzeki w Polsce?", "Wisła"),
                new Question("Jakie państwo graniczy z Polską na południu?", "Czechy"),
                new Question("Jakie miasto jest znane z Wieży Eiffla?", "Paryż"),
                new Question("Jakie jest najgłębsze jezioro na świecie?", "Bajkał"),
                new Question("Jaka jest stolica Niemiec?", "Berlin"),
                new Question("Jakie jest najmniejsze państwo na świecie?", "Watykan"),
                new Question("Jaki jest główny język w Hiszpanii?", "Hiszpański"),
                new Question("Jaki jest najwyższy szczyt świata?", "Everest"),
                new Question("Jakie jest najbardziej zaludnione miasto na świecie?", "Tokio"),
                new Question("Jakie zwierzę jest znane z noszenia muszli?", "Ślimak"),
                new Question("Jak nazywa się największy ocean?", "Spokojny"),
                new Question("Jak nazywa się największa wyspa na świecie?", "Grenlandia"),
                new Question("Jaki kraj jest znany z kangurów?", "Australia"),
                new Question("Jakie jest najbardziej wysunięte na północ państwo?", "Norwegia"),
                new Question("Jaki jest najdłuższy dzień w roku?", "Przesilenie"),
                new Question("Jaki jest najdłuższy miesiąc w roku?", "Styczeń"),
                new Question("Jakie zwierzę ma trąbę?", "Słoń"),
                new Question("Jak nazywa się prezydent USA (2024)?", "Biden"),
                new Question("Jakie państwo graniczy z Polską na zachodzie?", "Niemcy"),
                new Question("Jakie jest najstarsze miasto w Polsce?", "Gniezno"),
                new Question("Jaki jest najpopularniejszy sport na świecie?", "Piłka"),
                new Question("Jakie jest najzimniejsze miejsce na Ziemi?", "Antarktyda"),
                new Question("Jakie miasto jest stolicą Włoch?", "Rzym"),
                new Question("Jakie państwo jest największym producentem kawy?", "Brazylia"),
                new Question("Jakie jest najludniejsze państwo na świecie?", "Chiny"),
                new Question("Jak nazywa się królowa Wielkiej Brytanii (2024)?", "Elżbieta"),
                new Question("Jakie jest główne miasto Japonii?", "Tokio"),
                new Question("Jakie zwierzę jest znane z długiej szyi?", "Żyrafa"),
                new Question("Jakie miasto jest stolicą Rosji?", "Moskwa"),
                new Question("Jakie państwo jest znane z piramid?", "Egipt"),
                new Question("Jakie miasto jest stolicą Francji?", "Paryż"),
                new Question("Jakie zwierzę jest znane z miodu?", "Pszczoła"),
                new Question("Jakie miasto jest stolicą Hiszpanii?", "Madryt"),
                new Question("Jakie państwo jest największym eksporterem ropy?", "Arabia"),
                new Question("Jakie miasto jest stolicą Kanady?", "Ottawa"),
                new Question("Jakie jest najstarsze drzewo na świecie?", "Sekwoja"),
                new Question("Jakie miasto jest stolicą Turcji?", "Ankara"),
                new Question("Jakie jest najgorętsze miejsce na Ziemi?", "Dolina"),
                new Question("Jakie jest główne miasto Australii?", "Canberra"),
                new Question("Jakie zwierzę jest symbolem Indii?", "Tygrys"),
                new Question("Jakie miasto jest stolicą Brazylii?", "Brasilia"),
                new Question("Jaki kolor ma Redstone?", "Czerwony"),
                new Question("Jak nazywa się trójząb, który można zdobyć w grze?", "Trójząb"),
                new Question("Jakie zwierzę można oswoić jako psa?", "Wilk"),
                new Question("Jakie narzędzie jest używane do zbierania kamienia?", "Kilof"),
                new Question("Jakie moby pojawiają się tylko w Netherze?", "Pigliny"),
                new Question("Jakie narzędzie jest używane do kopania ziemi?", "Łopata"),
                new Question("Jak nazywa się portal do innego wymiaru?", "Nether"),
                new Question("Jaki materiał jest potrzebny do stworzenia stołu do zaklinania?", "Obsydian"),
                new Question("Jak nazywa się świat, gdzie można walczyć z Ender Dragonem?", "End"),
                new Question("Jak nazywa się bloki, które można znaleźć w dżungli?", "Bambus"),
                new Question("Jak nazywa się mob, który wybucha?", "Creeper"),
                new Question("W jaki dzień idzie się na automaty?", "Wtorek"),
                new Question("Jakie jest młodzieżowe słowo roku 2024?", "Rel")

        );
    }

    public static class RandomTpSettings extends OkaeriConfig {
        @Comment("nether")
        public int minimalNetherReachTp = 10;
        public int maxNetherReachTp = 250;
        @Comment("overworld")
        public int worldSize = 1100;
        public int minimalReachTp = 10;
        public int maxReachTp = 600;
    }

    public static class TimeShopSettings extends OkaeriConfig {
        public List<TimeShop> timeShopList = Arrays.asList(

                new TimeShop(
                        Material.IRON_HELMET,
                        "&fRanga &f&lIRON",
                        1150,
                        "lp user {PLAYER} group add iron"
                ),
                new TimeShop(
                        Material.GOLDEN_HELMET,
                        "&fRanga &e&lGOLD",
                        2000,
                        "lp user {PLAYER} group add gold"
                ),
                new TimeShop(
                        Material.LIGHT_BLUE_CANDLE,
                        "&fMagiczna świeca &e★",
                        200,
                        "customitem give candle 1 {PLAYER}"
                ),
                new TimeShop(
                        Material.IRON_SWORD,
                        "&fKit &f&lIRON",
                        450,
                        "kit daj {PLAYER} iron"
                ),
                new TimeShop(
                        Material.GOLDEN_SWORD,
                        "&fKit &e&lGOLD",
                        750,
                        "kit daj {PLAYER} gold"
                ),
                new TimeShop(
                        Material.WATER_BUCKET,
                        "&fWiadro wody",
                        5,
                        "give {PLAYER} minecraft:water_bucket"
                ),
                new TimeShop(
                        Material.LAVA_BUCKET,
                        "&fWiadro lawy",
                        5,
                        "give {PLAYER} minecraft:lava_bucket"
                ),
                new TimeShop(
                        Material.SUGAR_CANE,
                        "&fTrzcina cukrowa",
                        10,
                        "give {PLAYER} minecraft:sugar_cane"
                ),
                new TimeShop(
                        Material.TOTEM_OF_UNDYING,
                        "&ftotem",
                        200,
                        "give {PLAYER} minecraft:totem_of_undying"
                ),
                new TimeShop(
                        Material.PURPLE_GLAZED_TERRACOTTA,
                        "&fGenerator obsydianiu &e❀ &8x5",
                        35,
                        "customitem give obsydian 5 {PLAYER}"
                ),
                new TimeShop(
                        Material.GRAY_GLAZED_TERRACOTTA,
                        "&fGenerator kamienia &e❀ &8x5",
                        25,
                        "customitem give stone 5 {PLAYER}"
                )
        );
    }
}