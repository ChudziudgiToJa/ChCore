package pl.chudziudgi.core.feature.chat;

import eu.okaeri.configs.OkaeriConfig;

import java.util.Arrays;
import java.util.List;

public class ChatConfig extends OkaeriConfig {


    private Boolean chatMessageBlock = false;
    private Boolean chatAutoMessage = true;
    private String chatFormat = "%luckperms_prefix%%rel_funnyguilds_tag%&7{PLAYER} &8| &f";
    private List<String> ListAutoMessage = Arrays.asList(
            "DMG z siekier jest zmiejsznone o 50%.",
            "Ułatw sobie rozgrywkę, korzystając z opcji &b/ustawienia.",
            "Potrzebujesz wsparcia w założeniu klanu? &b/klan.",
            "Sprawdź harmonogram edycji na naszym &9&nDiscordzie",
            "Masz problem? Załóż zgłoszenie na naszym &9&nDiscordzie",
            "Możliwość podbijania terytorium klanów w godzinach &b19:00 - 22:00",
            "Event piekło dostępny w godzinach &b16:00 - 18:00 "
    );

    public Boolean getChatAutoMessage() {
        return chatAutoMessage;
    }

    public void setChatAutoMessage(Boolean chatAutoMessage) {
        this.chatAutoMessage = chatAutoMessage;
    }

    public Boolean getChatMessageBlock() {
        return this.chatMessageBlock;
    }

    public void setChatMessageBlock(Boolean chatMessageBlock) {
        this.chatMessageBlock = chatMessageBlock;
    }

    public String getChatFormat() {
        return this.chatFormat;
    }

    public List<String> getListaAutoMessage() {
        return this.ListAutoMessage;
    }

}
