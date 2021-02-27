package io.darkfirekiller.commands.pub;

import io.darkfirekiller.core.Bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Char3DCommand extends CommandPublic {

    public Char3DCommand(Bot botOwner) {
        super(
                botOwner,
                new String[]{"3dchar", "char3d"},
                "3dchar <character name>",
                "Gives a link to the AQ3D charpage of the given player",
                "-h: Shows this help message",
                botOwner.prefix + "3dchar Apus",
                new Color(52, 152, 219)
        );
    }

    @Override
    public void execute(MessageReceivedEvent context, String args, String content) {
        if (content == null) {
            context.getChannel().sendMessage("You must provide me with a character name!").queue();
            return;
        }
        context.getChannel().sendMessage("https://game.aq3d.com/account/Character?id=" + URLEncoder.encode(content, StandardCharsets.UTF_8)).queue();
    }
}
