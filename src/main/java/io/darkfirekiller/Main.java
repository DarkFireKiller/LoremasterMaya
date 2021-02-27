package io.darkfirekiller;

import io.darkfirekiller.core.Bot;
import io.darkfirekiller.settings.*;

public class Main {

    public static final boolean TESTING = true;

    public static Bot mayaBot;

    public static void main(String[] args) {

        String dir = args.length > 0 ? args[0] : null;
        Settings.load(dir);

        mayaBot = new Bot(Settings.token, Settings.prefix, Settings.aList).start();
    }
}
