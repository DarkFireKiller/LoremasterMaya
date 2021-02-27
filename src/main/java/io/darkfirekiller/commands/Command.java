package io.darkfirekiller.commands;

import io.darkfirekiller.core.Bot;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public abstract class Command {

    public Bot owner;
    public boolean ownerOnly;
    public String[] names;
    public String usage;
    public String desc;
    public String args;
    public String examples;
    public Color colour;

    public Command(Bot botOwner, String[] names, String usage, String desc, String args, String examples, Color col, boolean owner) {
        this.owner = botOwner;
        this.names = names;
        this.usage = usage;
        this.desc = desc;
        this.args = args;
        this.examples = examples;
        this.colour = col;
        this.ownerOnly = owner;
    }

    public abstract void execute(MessageReceivedEvent context, String args, String content);

    public int compareTo(Command c) {
        return this.names[0].compareTo(c.names[0]);
    }
}
