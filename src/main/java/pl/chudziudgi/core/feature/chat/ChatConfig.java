package pl.chudziudgi.core.feature.chat;

import eu.okaeri.configs.OkaeriConfig;

import java.util.Arrays;
import java.util.List;

public class ChatConfig extends OkaeriConfig {


    private Boolean chatMessageBlock = false;
    private Boolean chatAutoMessage = false;
    private String chatFormat = "%luckperms_prefix%%rel_funnyguilds_tag%&7{PLAYER} &8| &f";
    private List<String> ListAutoMessage = Arrays.asList("a wiadomość 1", "a wiadomość 2");

    public Boolean getChatAutoMessage() {
        return chatAutoMessage;
    }

    public void setChatAutoMessage(Boolean chatAutoMessage) {
        this.chatAutoMessage = chatAutoMessage;
    }

    public Boolean getChatMessageBlock() {
        return chatMessageBlock;
    }

    public void setChatMessageBlock(Boolean chatMessageBlock) {
        this.chatMessageBlock = chatMessageBlock;
    }

    public String getChatFormat() {
        return chatFormat;
    }

    public List<String> getListaAutoMessage() {
        return ListAutoMessage;
    }

}
