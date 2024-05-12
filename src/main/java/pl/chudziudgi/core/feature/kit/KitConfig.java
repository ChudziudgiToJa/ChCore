package pl.chudziudgi.core.feature.kit;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

import java.util.ArrayList;
import java.util.Base64;

public class KitConfig extends OkaeriConfig {

    @Comment("Kit start")
    ArrayList<Base64.Encoder> startItems = new ArrayList<>();
}
