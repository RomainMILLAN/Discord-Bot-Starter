package fr.romainmillan.discordbotstater.manager;

import fr.romainmillan.discordbotstater.App;
import fr.romainmillan.discordbotstater.manager.embedCrafter.UploadCrafter;
import fr.romainmillan.discordbotstater.states.EnvironementState;
import fr.romainmillan.discordbotstater.states.messages.application.UploadMessages;

public class UploadManager {

    public static void sendConnectedMessage() {
        if(App.getEnvironementState() != EnvironementState.PRODUCTION){
            return;
        }

        App.getJda().getGuildById(App.getConfiguration("GUILD_ID"))
                .getTextChannelById(
                        App.getConfiguration("TC_SENTRY")
                )
                .sendMessageEmbeds(
                        UploadCrafter.craftConnectEmbed(App.getJda().getSelfUser())
                                .build()
                )
                .queue();

        DiscordWebhookApi.sendConnectedNotification();;
    }
    public static void sendDisconnectmessage() {
        if(App.getEnvironementState() != EnvironementState.PRODUCTION){
            return;
        }

        DiscordWebhookApi.sendDisconnectedNotification();
    }

}
