package pl.chudziudgi.core.feature.combat;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

import java.util.Arrays;
import java.util.List;

public class CombatConfig extends OkaeriConfig {

    @Comment("Lista cmd on podczas antyloga")
    private List<String> commandsList = Arrays.asList("gracz", "info");

    @Comment("Wiadomość o blokadzie commands")
    private String commandBlockMessage = "Komenda zablokowana w trakcie PvP!";

    @Comment("Wiadomość o logaucie")
    private String logautMessage = "Gracz: &c{PLAYER} &7wylogowal się podczas walki!";
    @Comment("Wiadomość o walce")
    private String combatMessage = "&3⌚ AntyLogout &7pozostalo &3{TIME}";
    @Comment("Wiadomość o koncu walce")
    private String combatEndMessage = "&3⌚ AntyLogout &bdobiega konca";

    public String getCombatMessage() {
        return combatMessage;
    }

    public String getCombatEndMessage() {
        return combatEndMessage;
    }

    public String getLogautMessage() {
        return logautMessage;
    }

    public String getCommandBlockMessage() {
        return commandBlockMessage;
    }

    public List<String> getCommandsList() {
        return commandsList;
    }
}