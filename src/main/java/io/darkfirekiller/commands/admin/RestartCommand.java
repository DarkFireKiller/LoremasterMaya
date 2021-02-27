package io.darkfirekiller.commands.admin;

import io.darkfirekiller.core.Bot;
import io.darkfirekiller.utilities.Utilities;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RestartCommand extends CommandAdmin {

    public RestartCommand(Bot botOwner) {
        super(
                botOwner,
                new String[]{"restart"},
                "restart",
                "Restarts the bot (owner only)",
                "-h: Shows this help message\n-s: Shutdown.",
                botOwner.prefix + "restart"
        );
    }

    @Override
    public void execute(MessageReceivedEvent context, String args, String content) {
        if (Utilities.isBotAdmin(context.getAuthor()))
            Utilities.restart(owner, args != null && args.contains("s"));
    }
}

