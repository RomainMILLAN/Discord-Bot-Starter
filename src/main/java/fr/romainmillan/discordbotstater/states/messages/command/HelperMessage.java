package fr.romainmillan.discordbotstater.states.messages.command;

public enum HelperMessage {

    COMMAND_DESCRIPTION("Command qui permet de vous aider avec le bot"),

    COMMAND_COMMAND_HELP("/help"),

    MESSAGE("MESSAGE");

    private final String message;

    HelperMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
