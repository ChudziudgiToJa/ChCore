package pl.chudziudgi.core.feature.question;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.customitem.CustomItemStack;
import pl.chudziudgi.core.feature.deposit.DepositUtil;
import pl.chudziudgi.core.util.ChatUtil;

public class QuestionController implements Listener {

    private final QuestionManager questionManager;

    public QuestionController(ChCore plugin, QuestionManager questionManager) {
        this.questionManager = questionManager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        Question question = questionManager.getQuestion();
        if (question == null) return;
        if (!message.toLowerCase().contains(question.getAnswer().toLowerCase())) return;
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            User user = UserManager.get(player);
            if (!user.chatQuestionStatus) return;
            ChatUtil.msg(onlinePlayer, new MessageBuilder()
                    .setText("&3ⓅⓎⓉⓐⓝⒾⒺ &7Gracz &b{PLAYER}&7 odpowiedział jako pierwszy!")
                    .addField("{PLAYER}", player.getName())
                    .addField("{ANSWER}", question.getAnswer())
                    .build());
            onlinePlayer.playSound(onlinePlayer, Sound.BLOCK_PORTAL_TRIGGER, 10, 10);
        }
        DepositUtil.giveItems(player, CustomItemStack.magicCandle());
        questionManager.setQuestion(null);
        event.setCancelled(true);
    }
}