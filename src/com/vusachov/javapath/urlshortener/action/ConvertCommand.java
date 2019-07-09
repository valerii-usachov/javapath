package com.vusachov.javapath.urlshortener.action;

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

    public ConvertAction getAction() {
        return action;
    }

    public String getUrl() {
        return url;
    }
}
