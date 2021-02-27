package io.darkfirekiller.commands;

import io.darkfirekiller.commands.pub.CommandPublic;
import io.darkfirekiller.core.Bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class HelpCommand extends CommandPublic {

    public HelpCommand(Bot botOwner) {
        super(
                botOwner,
                new String[]{"help"},
                "help",
                "Shows a list of all commands or information about a specific command",
                "-h: Shows this help message",
                botOwner.prefix + "help",
                Color.LIGHT_GRAY
        );
    }

    @Override
    public void execute(MessageReceivedEvent context, String args, String content) {
        MessageEmbed msg = content == null ? cmdList(context) : cmdHelp(context, content);

        if (msg == null) return;
        context.getChannel().sendMessage(msg).queue();
    }

    private MessageEmbed cmdList(MessageReceivedEvent ctx) {
        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("Here is everything I can help you with")
                .setColor(Color.LIGHT_GRAY)
                .setFooter("Use " + owner.prefix + "help (command) or " + owner.prefix + "(command) -h to get more information about a single command", null);
        List<Command> commands = new ArrayList<>(owner.commandHandler.commands.values());
        commands.sort(Command::compareTo);
        for(Command c : new LinkedHashSet<>(commands)) {
            if (c.ownerOnly) continue;
            builder.addField(c.names[0], c.desc, false);
        }
        return builder.build();

    }

    private MessageEmbed cmdHelp(MessageReceivedEvent ctx, String name) {
        Command cmd = owner.commandHandler.commandFromName(name.trim());
        if (cmd == null) return cmdList(ctx);

        return new EmbedBuilder()
                .setTitle(String.join(" / ", cmd.names).toUpperCase())
                .addField("Description", cmd.desc, false)
                .addField("How to use", cmd.usage, false)
                .addField("Examples", cmd.examples, false)
                .setColor(cmd.colour)
                .setFooter("Variables: (optional) <required>",null)
                .build();
    }
}
