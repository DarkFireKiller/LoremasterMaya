package io.darkfirekiller.commands.pub;

import io.darkfirekiller.commands.Command;
import io.darkfirekiller.core.Bot;

import java.awt.*;

public abstract class CommandPublic extends Command {

    protected CommandPublic(Bot botOwner, String[] names, String usage, String desc, String args, String examples, Color colour) {
        super(
                botOwner,
                names,
                usage,
                desc,
                args,
                examples,
                colour,
                false
        );
    }
}