package fr.romainmillan.discordbotstater.states.command;

public enum PluginStatesInterfaces {
    COMMAND_NAME("PluginName"),

    COMMAND_COMMAND_X_PREFIX("x");

    private final String state;

    PluginStatesInterfaces(String message) {
        this.state = message;
    }

    public String getState() {
        return state;
    }
}
