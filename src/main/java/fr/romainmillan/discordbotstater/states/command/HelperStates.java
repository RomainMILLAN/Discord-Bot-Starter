package fr.romainmillan.discordbotstater.states.command;

public enum HelperStates {
    COMMAND_NAME("Helper"),

    COMMAND_COMMAND_X_PREFIX("help");

    private final String state;

    HelperStates(String message) {
        this.state = message;
    }

    public String getState() {
        return state;
    }
}
