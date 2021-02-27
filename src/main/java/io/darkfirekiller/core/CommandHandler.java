package io.darkfirekiller.core;

import java.util.HashMap;

import io.darkfirekiller.commands.Command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandHandler {

    private Bot owner;

    public CommandHandler(Bot owner) {
        this.owner = owner;
    }

    public HashMap<String, Command> commands = new HashMap<>();

    public void handleEvent(MessageReceivedEvent e) {
        final String[] split = e.getMessage().getContentRaw().split(" ", 3);
        String cmd = split[0].replace(owner.prefix, "");
        String args = (split.length > 1 && split[1].startsWith("-")) ? split[1].substring(1) : null;
        String content;
        if (split.length <= 1) {
            final String[] nlSplit = e.getMessage().getContentRaw().split("\n", 2);
            cmd = nlSplit[0].replace(owner.prefix, "");
            if (nlSplit.length > 1) content = nlSplit[1].trim();
            else content = null;
        }
        else if (args != null && split.length > 2) content = split[2];
        else content = e.getMessage().getContentRaw().split(" ", 2)[1];

        Command command;

        if (args != null && args.contains("h")) {
            command = commandFromName("help");
            command.execute(e, null, cmd);
            return;
        }

        command = commandFromName(cmd);
        if (command == null) return;

        command.execute(e, args, content);

    }

    public CommandHandler registerCommand(Command command) {
        for (String name : command.names)
            commands.put(name, command);
        return this;
    }

    public Command commandFromName(String name) {
        return commands.get(name.toLowerCase());
    }

}

