package io.darkfirekiller.commands.pub;

import io.darkfirekiller.core.Bot;
import io.darkfirekiller.utilities.GameFunc;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class SellbackCommand extends CommandPublic {

    public SellbackCommand(Bot botOwner) {
        super(
                botOwner,
                new String[]{"sellback"},
                "sellback <amount> (currency)",
                "Lists the sellback for an item in AQW (default currency is AC)",
                "-h: Shows this help message\n" +
                        "-g: Lists the gold sellback (same as suffixing Gold)",
                botOwner.prefix + "sellback 1,543\n" +
                        botOwner.prefix + "sellback 2,231 Gold\n" +
                        botOwner.prefix + "sellback -g 3,214",
                new Color(230, 126, 34)
        );
    }

    @Override
    public void execute(MessageReceivedEvent context, String args, String content) {
        EmbedBuilder embed = new EmbedBuilder()
                .setColor(this.colour);

        if (content == null) {
            embed.setFooter("AC Items in AQW sell back for 90% of their initial value before 24 hours, and 25% after 25 hours. " +
                    "Gold items sell back for 25% of their initial value. All values are ceiling'd to the nearest integer.");
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
        if (cost < 0) cost = -cost;

        boolean gold = false;
        if ((args != null && args.contains("g")) || (spl.length > 1) && spl[1].toLowerCase().contains("gold")) gold = true;

        embed.addField("Price", "%,d %s".formatted(cost, gold?"Gold":"AC"), false)
                .addField("Sellback", GameFunc.uncodedStringSellback(cost, !gold), false);

        context.getChannel().sendMessage(embed.build()).queue();
    }
}
