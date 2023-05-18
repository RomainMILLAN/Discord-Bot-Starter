package fr.skytorstd.doxer;

import fr.skytorstd.doxer.manager.Configuration;
import fr.skytorstd.doxer.manager.ConsoleManager;
import fr.skytorstd.doxer.states.ConsoleState;
import fr.skytorstd.doxer.states.EnvironementState;
import fr.skytorstd.doxer.states.messages.application.BotMessages;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class App 
{
    private static Configuration configuration;
    private static JDA jda;

    private static EnvironementState environementState = EnvironementState.PRODUCTION;

    public static void main( String[] args ) throws InterruptedException {
        App.configuration = new Configuration();

        if(App.configuration.getConfiguration("APP_ENV").equals("DEVELOP"))
            App.environementState = EnvironementState.DEVELOPMENT;


        App.jda = JDABuilder.createDefault(App.configuration.getConfiguration("BOT_TOKEN"))
            .setIdle(true)
            .enableCache(CacheFlag.ACTIVITY, CacheFlag.VOICE_STATE)
            .setActivity(Activity.watching(BotMessages.ACTIVITY_PLAYING_BOT.getMessage()))
            .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_PRESENCES)
            .build();

        App.jda.awaitStatus(JDA.Status.INITIALIZING);
        ConsoleManager.getInstance().toConsole(BotMessages.JDA_BOT_INITIALIZING.getMessage(), ConsoleState.INFO);

        App.jda.awaitStatus(JDA.Status.CONNECTED);
        ConsoleManager.getInstance().toConsole(BotMessages.JDA_BOT_CONNECTED.getMessage(), ConsoleState.INFO);

        App.jda.awaitReady();
        ConsoleManager.getInstance().toConsole(BotMessages.JDA_BOT_READY.getMessage(), ConsoleState.INFO);
    }


    public static Configuration getConfiguration() {
        return configuration;
    }

    public static EnvironementState getEnvironementState() {
        return environementState;
    }
}
