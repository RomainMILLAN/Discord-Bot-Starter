package fr.romainmillan.discordbotstater.states.messages.command;

public enum CommandMessageInterface {

    COMMAND_DESCRIPTION("PLUGIN_DESCRIPTION"),

    COMMAND_X("/COMMAND <1>"),

    MESSAGE("MESSAGE");

    private final String message;

    CommandMessageInterface(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
