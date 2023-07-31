package fr.romainmillan.discordbotstater;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.HashMap;

import fr.romainmillan.discordbotstater.manager.*;
import fr.romainmillan.discordbotstater.states.ConsoleState;
import fr.romainmillan.discordbotstater.states.EnvironementState;
import fr.romainmillan.discordbotstater.states.messages.application.BotMessages;
import fr.romainmillan.discordbotstater.states.messages.application.SystemMessages;

public class App 
{
    private static Configuration configuration;
    private static JDA jda;
    private static HashMap<String, String> commands = new HashMap<>();

    private static EnvironementState environementState = EnvironementState.PRODUCTION;
    private static boolean debugingState = false;

    private static String guildId = "";

    public static void main( String[] args ) throws InterruptedException {
        App.configuration = new Configuration();

        if(App.configuration.getConfiguration("APP_ENV").equals("DEVELOP"))
            App.environementState = EnvironementState.DEVELOPMENT;

        if(App.configuration.getConfiguration("APP_DEBUGING").equalsIgnoreCase("true"))
            App.debugingState = true;

        App.guildId = App.configuration.getConfiguration("GUILD_ID");

        App.runJdaBot();
    }

    /**
     * Run the discord bot with JDA
     *
     * @throws InterruptedException
     */
    public static void runJdaBot() throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(App::stop));

        App.jda = JDABuilder.createDefault(App.configuration.getConfiguration("BOT_TOKEN"))
                .setIdle(true)
                .enableCache(CacheFlag.ACTIVITY, CacheFlag.VOICE_STATE)
                .setActivity(Activity.watching(BotMessages.ACTIVITY_PLAYING_BOT.getMessage()))
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_PRESENCES)
                .setEnableShutdownHook(true)
                .build();

        App.jda.awaitStatus(JDA.Status.INITIALIZING);
        ConsoleManager.getInstance().toConsole(BotMessages.JDA_BOT_INITIALIZING.getMessage(), ConsoleState.INFO);

        App.jda.awaitStatus(JDA.Status.CONNECTED);
        ConsoleManager.getInstance().toConsole(BotMessages.JDA_BOT_CONNECTED.getMessage(), ConsoleState.INFO);

        App.jda.awaitReady();
        ConsoleManager.getInstance().toConsole(BotMessages.JDA_BOT_READY.getMessage(), ConsoleState.INFO);
        UploadManager.sendConnectedMessage();

        App.updateCommands();
    }

    public static void updateCommands() {

        jda.getGuildById(App.guildId).updateCommands().addCommands(CommandManager.updateSlashCommands()).queue();
    }

    private static void stop() {
        UploadManager.sendDisconnectmessage();
    }

    public static String getConfiguration(String configurationKey) {
        return App.configuration.getConfiguration(configurationKey);
    }

    public static EnvironementState getEnvironementState() {
        return environementState;
    }

    public static boolean isDebuging() {
        return debugingState;
    }

    public static JDA getJda() {
        return jda;
    }

    public static String getGuildId() {
        return guildId;
    }

    public static void addCommand(String command, String description) {
        commands.put(command, description);

        ConsoleManager.getInstance().toConsole(
                command + " | " +  SystemMessages.PLUGIN_REGISTER_SUCCESS.getMessage(),
                ConsoleState.DEBUG
        );
    }

    public HashMap<String, String> getCommands() {
        return commands;
    }

    public static String getDescriptionCommandByCommand(String command) {
        return commands.get(command);
    }
}
