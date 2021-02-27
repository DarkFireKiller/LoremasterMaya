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

public class SearchCommand extends CommandPublic {

    private static final String url = "http://aqwwiki.wikidot.com/search:site/q/";

    public SearchCommand(Bot botOwner) {
        super(
                botOwner,
                new String[]{"search", "wiki"},
                "search <search query>",
                "Searches the AQW wiki - Previews top search result and links full results",
                "-h: Shows this help message",
                botOwner.prefix + "search Battleon",
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

        Document firstRes = SearchUtil.getFirstResult(content, SearchUtil.SITE.AQW);
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
