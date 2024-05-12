package pl.chudziudgi.core.feature.chat;

import org.bukkit.command.CommandSender;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "chat",
        player = true,
        usage = "clear/switch/auto",
        perm = "core.command.chat.admin"
)
public class ChatCommand extends PluginCommand {

    private final ChatManager chatManager;
    private final ChatConfig chatConfig;

    public ChatCommand(ChatManager chatManager, ChatConfig chatConfig) {
        this.chatManager = chatManager;
        this.chatConfig = chatConfig;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("switch")) {
            chatManager.switchChat(sender);
            return;
        }
        if (args[0].equalsIgnoreCase("clear")) {
            chatManager.clearChat(sender);
            return;
        }
        if (args[0].equalsIgnoreCase("auto")) {
            chatConfig.setChatAutoMessage(!chatConfig.getChatAutoMessage());
            ChatUtil.success(sender, "Zmieniono status autowiadomości: " + (chatConfig.getChatAutoMessage() ? "&awłączony" : "&cwłączony"));
        }
        else {
            sendUsage(sender);
        }
    }
}