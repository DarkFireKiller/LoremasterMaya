package io.darkfirekiller.commands.pub;

import io.darkfirekiller.core.Bot;
import io.darkfirekiller.utilities.GameFunc;
import io.darkfirekiller.utilities.SearchUtil;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Search3DCommand extends CommandPublic {

    private static final String url = "http://aq-3d.wikidot.com/search:site/q/";

    public Search3DCommand(Bot botOwner) {
        super(
                botOwner,
                new String[]{"3dsearch", "3dwiki", "search3d", "wiki3d"},
                "3dsearch <search query>",
                "Searches the AQ3D wiki - Previews top search result and links full results",
                "-h: Shows this help message",
                botOwner.prefix + "3dsearch Battleontown",
                new Color(153, 45, 34)
        );
    }

    @Override
    public void execute(MessageReceivedEvent context, String args, String content) {
        EmbedBuilder builder = new EmbedBuilder().setColor(this.colour);

        if (content == null) {
            builder.setFooter("Please give me something to search for!");
            context.getChannel().sendMessage(builder.build()).queue();
            return;
        }

        builder.setFooter("Searching...");
        Message m = context.getChannel().sendMessage(builder.build()).complete();

        builder = new EmbedBuilder().setColor(this.colour);
        final String queryLink = url + URLEncoder.encode(content, StandardCharsets.UTF_8);

        Document firstRes = SearchUtil.getFirstResult(content, SearchUtil.SITE.AQ3D);
        if (firstRes == null)
            builder.setFooter("I couldn't find any pages that match " + content + ".");
        else {

            String title = firstRes.selectFirst("div#page-title").text();
            String parent = firstRes.selectFirst("div#breadcrumbs").select("a").last().text();
            String image = null;
            ArrayList<String> tags = new ArrayList<>();
            String tagStr = "";

            for (Element img : firstRes.select("#page-content img")) {
                if (img.attr("src").contains("image-tags") || img.attr("src").contains("classes-skills")) {
                    String tag = GameFunc.imgTags.get(img.attr("alt").toLowerCase());
                    if (tag != null) tags.add(tag);
                } else image = image == null ? img.attr("src") : image;
            }
            if (tags.size() > 0) tagStr = "  < " + String.join(", ", tags) + " >";

            builder.setTitle(title, firstRes.baseUri())
                    .setFooter(parent + tagStr)
                    .addField("All Results", queryLink, false);
            if (image != null) builder.setImage(image);
        }

        m.editMessage(builder.build()).queue();
    }
}
