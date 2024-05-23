package pl.chudziudgi.core.feature.chat;

import eu.okaeri.configs.OkaeriConfig;

import java.util.Arrays;
import java.util.List;

public class ChatConfig extends OkaeriConfig {


    private String chatFormat = "%luckperms_prefix%%rel_funnyguilds_tag%&7{PLAYER} &8| &f";
    private List<String> listAutoMessage = Arrays.asList("DMG z siekier jest zmiejsznone o 50%.", "Ułatw sobie rozgrywkę, korzystając z opcji &b/ustawienia.", "Potrzebujesz wsparcia w założeniu klanu? &b/klan.", "Sprawdź harmonogram edycji na naszym &9&nDiscordzie", "Masz problem? Załóż zgłoszenie na naszym &9&nDiscordzie", "Możliwość podbijania terytorium klanów w godzinach &b19:00 - 22:00", "Event piekło dostępny w godzinach &b16:00 - 18:00 ");
    private List<String> listaDozwolonychZnakow = Arrays.asList(" ","A", "Ą", "B", "C", "Ć", "D", "E", "Ę", "F", "G", "H", "I", "J", "K", "L", "Ł", "M", "N", "Ń", "O", "Ó", "P", "Q", "R", "S", "Ś", "T", "U", "V", "W", "X", "Y", "Z", "Ź", "Ż", "a", "ą", "b", "c", "ć", "d", "e", "ę", "f", "g", "h", "i", "j", "k", "l", "ł", "m", "n", "ń", "o", "ó", "p", "q", "r", "s", "ś", "t", "u", "v", "w", "x", "y", "z", "ź", "ż", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "!", "?", ".", ",", ";", ":", "-", "_", "(", ")", "[", "]", "{", "}", "<", ">", "'", "\"", "@", "#", "$", "^", "&", "*", "+", "=", "/", "\\", "|", "~", "`");
    private Boolean chatMessageBlock = false;
    private Boolean chatAutoMessage = true;

    public List<String> getListaDozwolonychZnakow() {
        return listaDozwolonychZnakow;
    }

    public List<String> getListAutoMessage() {
        return listAutoMessage;
    }

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
        return this.listAutoMessage;
    }

}
