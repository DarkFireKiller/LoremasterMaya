package io.darkfirekiller.core;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    private Bot owner;
    public MessageListener(Bot owner) {
        this.owner = owner;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.isFromType(ChannelType.PRIVATE)) return;
        if (event.getAuthor().isBot()) return;
        if (event.getMessage().getContentStripped().startsWith(owner.prefix))
            owner.commandHandler.handleEvent(event);
    }


    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getUser().isBot()) return;
        owner.reactionHandler.handleEvent(event);
    }

}
