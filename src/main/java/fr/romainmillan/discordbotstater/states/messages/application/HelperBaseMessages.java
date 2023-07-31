package fr.romainmillan.discordbotstater.states.messages.application;

public enum HelperBaseMessages {

    MESSAGES("");

    private String message;

    HelperBaseMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
