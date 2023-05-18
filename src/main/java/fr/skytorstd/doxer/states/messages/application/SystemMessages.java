package fr.skytorstd.doxer.states.messages.application;

public enum SystemMessages {
    CONFIGURATION_LOADED("Configuration chargée"),
    CONFIGURATION_REGISTER("Configuration enregistrée");

    private String message;

    SystemMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}