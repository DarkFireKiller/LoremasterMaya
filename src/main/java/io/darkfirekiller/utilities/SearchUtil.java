package io.darkfirekiller.utilities;

import io.darkfirekiller.core.Bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SearchUtil {

    final static String SEARCH = "/search:site/q/";

    public enum SITE {
        AQW("https://aqwwiki.wikidot.com"), AQ3D("https://aq-3d.wikidot.com");
        final String site; SITE(String site) {this.site=site;}
    }

    public static Document getFirstResult(String query, SITE site) {
        Document searchPage;

        String directLink = "/" + query.replaceAll(" ", "-");
        if (pageExists(site.site + directLink))
            return getPage(site, site.site + directLink);

        String queryLink = site.site + SEARCH + URLEncoder.encode(query, StandardCharsets.UTF_8);
        try {
            searchPage = Jsoup.connect(queryLink).get();
        } catch (IOException e) {
            Bot.lastError = e;
            return null;
        }
        Element result = searchPage.selectFirst("div.title");
        if (result == null) return null;
        String firstResLink = result.selectFirst("a").attr("href");

        return getPage(site, firstResLink);
    }

    public static Document getPage(SITE site, String resultLink) {
        Document firstResultPage;
        try {
            firstResultPage = Jsoup.connect(resultLink).get();
            if (firstResultPage.selectFirst("#page-content").text().contains("usually refers to:"))
                return Jsoup.connect(
                        site.site + firstResultPage.selectFirst("#page-content")
                                .selectFirst("a").attr("href")
                ).get();
            return firstResultPage;
        } catch (IOException e) {
            Bot.lastError = e;
            return null;
        }
    }

    public static boolean pageExists(String resultLink) {
        try {
            Jsoup.connect(resultLink).get();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
