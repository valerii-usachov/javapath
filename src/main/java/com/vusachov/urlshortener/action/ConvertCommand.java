package com.vusachov.urlshortener.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertCommand {

    final private ConvertAction action;

    final private String url;

    public ConvertCommand(String actionAbbr, String url) throws IllegalArgumentException {
        switch (actionAbbr) {
            case "S":
                this.action = ConvertAction.SHORTEN;
                break;
            case "U":
                this.action = ConvertAction.DESHORTEN;
                break;
            default:
                throw new IllegalArgumentException("Invalid ConvertAction abbr");
        }
        this.url = url;
    }

    public static ConvertCommand fromString(String commandStr) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("([S|U]) ([^\\s]+)");
        Matcher matcher = pattern.matcher(commandStr);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid command format. `[S|U] OriginUrlGet`");
        }

        return new ConvertCommand(matcher.group(1), matcher.group(2));
    }

    public ConvertAction getAction() {

        return action;
    }

    public String getUrl() {

        return url;
    }
}
