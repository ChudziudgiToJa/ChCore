package pl.chudziudgi.core.feature.question;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class QuestionTask extends BukkitRunnable {

    private final QuestionManager questionManager;
    private final PluginConfiguration config;
    private final ChCore plugin;

    public QuestionTask(QuestionManager questionManager, PluginConfiguration config, ChCore plugin) {
        this.questionManager = questionManager;
        this.config = config;
        this.plugin = plugin;
        this.runTaskTimerAsynchronously(plugin, 120 * 20, (60 * 60) * 20);
    }


    @Override
    public void run() {
        if (this.config.chatSettings.chatMessageBlock) return;
        Question question = questionManager.getRandomQuestion(this.config.questionSettings.questionList);
        questionManager.setQuestion(question);

        TextComponent messageComponent = new TextComponent();
        messageComponent.setText(ChatUtil.fixColor(new MessageBuilder().setText("&3ⓅⓎⓉⓐⓃⒾⒺ &7{QUESTION}").addField("{QUESTION}", questionManager.getQuestion().getQuestion()).build()));
        messageComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatUtil.fixColor("&8[&d&l!&8] &7Odpowiedz na pytanie jako pierwszy na chat'cie i wygraj nagrodę!\n&7Na odpowiedz masz 30 sekund")).create()));


        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            User user = UserManager.get(onlinePlayer);
            if (!user.chatQuestionStatus) return;
            onlinePlayer.spigot().sendMessage(messageComponent);
            onlinePlayer.playSound(onlinePlayer, Sound.BLOCK_END_GATEWAY_SPAWN, 10, 10);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(questionManager.getQuestion() == null)) {
                    questionManager.setQuestion(null);
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        User user = UserManager.get(player);
                        if (!user.chatQuestionStatus) return;
                        ChatUtil.msg(player, "&3ⓅⓎⓉⓐⓃⒾⒺ &7Nikt nie odpowiedział. Odpowiedź: &f" + question.getAnswer());
                        player.playSound(player, Sound.ENTITY_VILLAGER_NO, 10, 10);
                    });
                }
            }
        }.runTaskLaterAsynchronously(plugin, 30 * 20);
    }
}
