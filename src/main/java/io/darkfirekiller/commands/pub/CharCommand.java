package io.darkfirekiller.commands.pub;

import io.darkfirekiller.core.Bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CharCommand extends CommandPublic {

    public CharCommand(Bot botOwner) {
        super(
                botOwner,
                new String[]{"char", "aqwchar"},
                "char <character name>",
                "Gives a link to the AQW charpage of the given player",
                "-h: Shows this help message",
                botOwner.prefix + "char DFK",
                new Color(52, 152, 219)
        );
    }

    @Override
    public void execute(MessageReceivedEvent context, String args, String content) {
        if (content == null) {
            context.getChannel().sendMessage("You must provide me with a character name!").queue();
            return;
        }
        context.getChannel().sendMessage("https://aq.com/character.asp?id=" + URLEncoder.encode(content, StandardCharsets.UTF_8)).queue();
    }
}