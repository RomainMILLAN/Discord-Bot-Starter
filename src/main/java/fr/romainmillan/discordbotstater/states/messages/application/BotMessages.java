package fr.romainmillan.discordbotstater.states.messages.application;

public enum BotMessages {
    BOT_NAME("Discord Bot Starter"),
    ACTIVITY_PLAYING_BOT(BOT_NAME.message),
    JDA_BOT_INITIALIZING("Initialization de " + BOT_NAME.message),
    JDA_BOT_CONNECTED(BOT_NAME.message + " est connecté"),
    JDA_BOT_READY(BOT_NAME.message + " est prêt");

    private String message;

    BotMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
