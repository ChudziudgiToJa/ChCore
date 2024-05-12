package pl.chudziudgi.core.feature.combat;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

import java.util.Arrays;
import java.util.List;

public class CombatConfig extends OkaeriConfig {

    @Comment("Lista cmd on podczas antyloga")
    private List<String> commandsList = Arrays.asList("gracz", "info");

    @Comment("Wiadomość o blokadzie commands")
    private String commandBlockMessage = "Komenda nieaktywna w trakcie PvP!";

    @Comment("Wiadomość o logaucie")
    private String logautMessage = "Gracz: &c{PLAYER} &6wylogowal sie podczas walki!";

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