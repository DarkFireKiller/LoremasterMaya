package io.darkfirekiller.commands.pub;

import io.darkfirekiller.core.Bot;
import io.darkfirekiller.utilities.GameFunc;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class Sellback3DCommand extends CommandPublic {

    public Sellback3DCommand(Bot botOwner) {
        super(
                botOwner,
                new String[]{"3dsellback", "sellback3d"},
                "3dsellback <amount>",
                "Lists the sellback for an item in AQ3D",
                "-h: Shows this help message",
                botOwner.prefix + "3dsellback 5000\n" +
                        botOwner.prefix + "3dsellback 2,402",
                new Color(230, 126, 34)
        );
    }

    @Override
    public void execute(MessageReceivedEvent context, String args, String content) {
        EmbedBuilder embed = new EmbedBuilder()
                .setColor(this.colour);

        if (content == null) {
            embed.setFooter("All items in AQ3D sell back in Gold for 10% of their initial value, rounded to the nearest integer.");
            context.getChannel().sendMessage(embed.build()).queue();
            return;
        }

        int cost;
        String[] spl;
        try {
            spl = content.split(" ", 2);
            cost = Integer.parseInt(spl[0].replace(",", ""));
        } catch (Exception e) {
            context.getChannel().sendMessage("I'm not sure what number that is.").queue();
            return;
        }
        if (cost < 0) {
            context.getChannel().sendMessage("You can't buy something for a negative amount.").queue();
            return;
        }

        embed.addField("Price", "%,d Gold/DC".formatted(cost), false)
                .addField("Sellback", GameFunc.sellback3d(cost), false);

        context.getChannel().sendMessage(embed.build()).queue();
    }
}
