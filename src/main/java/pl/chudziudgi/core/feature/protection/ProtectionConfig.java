package pl.chudziudgi.core.feature.protection;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public class ProtectionConfig extends OkaeriConfig {

    public String getProtectionMessage() {
        return protectionMessage;
    }

    public String getProtectionEndMessage() {
        return protectionEndMessage;
    }

    @Comment("wiadomość o ochronie")
    private String protectionMessage = "&3⌚ Ochrona &7pozostalo &3{TIME}";

    @Comment("wiadomość o końcu ochronie")
    private String protectionEndMessage = "&3⌚ Ochrona &bdobiegla konca";
}
