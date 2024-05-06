package pl.chudziudgi.core.api;


import org.apache.commons.lang3.StringUtils;

public final class MessageBuilder {

    private String text;

    public MessageBuilder setText(String text) {
        this.text = text;
        return this;
    }
    public MessageBuilder addField(String field, String replacer) {
        this.text = StringUtils.replace(this.text, field, replacer);
        return this;
    }

    public String build() {
        return this.text;
    }
}