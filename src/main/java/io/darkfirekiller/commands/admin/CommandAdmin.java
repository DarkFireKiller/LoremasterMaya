package io.darkfirekiller.commands.admin;

import io.darkfirekiller.commands.Command;
import io.darkfirekiller.core.Bot;

import java.awt.*;

public abstract class CommandAdmin extends Command {

    protected CommandAdmin(Bot botOwner, String[] names, String usage, String desc, String args, String examples) {
        super(
                botOwner,
                names,
                usage,
                desc,
                args,
                examples,
                Color.red,
                true
        );
    }
}