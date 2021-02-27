package io.darkfirekiller.utilities;

import io.darkfirekiller.Main;
import io.darkfirekiller.core.Bot;
import io.darkfirekiller.settings.Settings;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Utilities {

    public static void restart(Bot botOwner, boolean shutdown) {

        botOwner.botJDA.shutdown();
        Main.mayaBot = null;

        if (shutdown) return;

        try {
            Thread.sleep(2000);
            Main.mayaBot = new Bot(Settings.token, Settings.prefix, Settings.aList).start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isBotAdmin(User u) {
        return Main.mayaBot.adminList.contains(u.getId());
    }

    public static boolean userHasRole(MessageReceivedEvent ctx, String roleName) {
        List<Role> roles = ctx.getGuild().getRoles();

        for (Role role : roles) {
            if (!role.getName().equals(roleName)) continue;
            if (ctx.getGuild().getMembersWithRoles(role).contains(ctx.getMember())) return true;
        }
        return false;
    }

    public static TextChannel getTextChannelFromID(Guild guild, String id) {
        for ( TextChannel chan : guild.getTextChannels()) {
            if (chan.getId().equals(id)) return chan;
        }
        return null;
    }

    public static TextChannel getTextChannelFromName(Guild guild, String name) {
        for ( TextChannel chan : guild.getTextChannels()) {
            if (chan.getName().equals(name))
                return chan;
        }
        return null;
    }

    public static class Tuple<A, B> {
        public A a;
        public B b;
        public Tuple(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }
}
