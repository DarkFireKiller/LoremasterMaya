package io.darkfirekiller.core;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

import io.darkfirekiller.commands.HelpCommand;
import io.darkfirekiller.commands.admin.RestartCommand;
import io.darkfirekiller.commands.pub.*;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Bot {

    public JDA botJDA;
    public ArrayList<String> adminList;
    public String prefix;
    String token;
    public CommandHandler commandHandler;
    public ReactionHandler reactionHandler;
    public static Exception lastError;

    public Bot(String key, String prefix, ArrayList<String> adminList) {
        this.token = key;
        this.prefix = prefix;
        this.adminList = adminList;
    }

    public Bot start() {
        try {
            startBot();
            return this;
        } catch (LoginException e) {
            System.out.println("Could not log in");
            return null;
        }
    }

    private void startBot() throws LoginException {
        botJDA = JDABuilder.createDefault(token).addEventListeners(new MessageListener(this)).build();
        botJDA.getPresence().setActivity(Activity.listening(" prefix " + this.prefix));

        commandHandler = new CommandHandler(this)
                .registerCommand(new HelpCommand(this))

                .registerCommand(new SellbackCommand(this))
                .registerCommand(new Sellback3DCommand(this))
                .registerCommand(new CharCommand(this))
                .registerCommand(new Char3DCommand(this))
                .registerCommand(new SearchCommand(this))
                .registerCommand(new Search3DCommand(this))

                .registerCommand(new RestartCommand(this));

        reactionHandler = new ReactionHandler();
    }
}