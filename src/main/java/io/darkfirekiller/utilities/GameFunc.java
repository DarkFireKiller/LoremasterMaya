package io.darkfirekiller.utilities;

import java.util.HashMap;
import java.util.Map;

public class GameFunc {

    public static final Map<String, String> imgTags = new HashMap<>();

    static {
        imgTags.put("chaoskind.png", "ChaosKind");imgTags.put("elementalkind.png", "ElementalKind");imgTags.put("dragonkind.png", "DragonKind");
        imgTags.put("humankind.png", "HumanKind");imgTags.put("undeadkind.png", "UndeadKind");imgTags.put("aclarge.png", "AC");
        imgTags.put("betalarge.png", "Beta");imgTags.put("founderlarge.png", "Founder");imgTags.put("legendlarge.png", "Legend");
        imgTags.put("pseudolarge.png", "Pseudo-Rare");imgTags.put("ptrlarge.png", "PTR");imgTags.put("rarelarge.png", "Rare");
        imgTags.put("seasonallarge.png", "Seasonal");imgTags.put("speciallarge.png", "Special Offer");imgTags.put("upholderlarge.png", "Upholder");
        imgTags.put("removedlarge.png", "Removed");imgTags.put("kickstarterlarge.png", "Kickstarter");imgTags.put("guardianlarge.png", "Guardian");
        imgTags.put("heromartlarge.png", "HeroMart");imgTags.put("dclarge.png", "DC");imgTags.put("awesomelarge.png", "Awesome Rarity");
        imgTags.put("commonlarge.png", "Common Rarity");imgTags.put("epiclarge.png", "Epic Rarity");imgTags.put("junklarge.png", "Junk Rarity");
        imgTags.put("legendarylarge.png", "Legendary Rarity");imgTags.put("uncommonlarge.png", "Uncommon Rarity");
    }

    public static Utilities.Tuple<String, String> sellback(int cost, boolean isAC) {
        String sellBackFormatHigh = "First 24 Hours: %,d AC";
        String sellBackFormatLow = "After 24 Hours: %,d AC";
        String sellBackFormatGold = " %,d %s";

        int sellHigh = (int) Math.ceil(cost * 0.9);
        int sellLow = (int) Math.ceil(cost * 0.25);

        if (isAC && cost != 0)
            return new Utilities.Tuple<>(sellBackFormatHigh.formatted(sellHigh), sellBackFormatLow.formatted(sellLow));
        else
            return new Utilities.Tuple<>(null, sellBackFormatGold.formatted(sellLow, isAC ? "AC" : "Gold"));

    }

    public static String uncodedStringSellback(int cost, boolean isAC) {
        Utilities.Tuple<String, String> sells = sellback(cost, isAC);
        if (sells.a != null)
            return sells.a + "\n" + sells.b;
        else return sells.b;
    }

    public static String sellback3d(int cost) {
        String sellBackFormatGold = "%,d Gold";

        int sell = (int) Math.round(cost * 0.1);
        return sellBackFormatGold.formatted(sell);
    }

}
