package wenjalan.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wenjalan.MaxHealthModifier;

public class SetMaxHealthCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            // if no arguments were specified, return false
            if (strings.length == 0) {
                throw new IllegalArgumentException("Specify an amount");
            }
            // if no specific player was specified, set the server max health
            else if (strings.length == 1) {
                float f = Float.parseFloat(strings[0]);
                // check if it's a valid health value
                verifyValidity(f);

                // set the server max health
                MaxHealthModifier.serverMaxHealth = f;

                // set all players max health
                MaxHealthModifier.setAllPlayersMaxHealth(MaxHealthModifier.serverMaxHealth);
            }
            // otherwise, set the health of the player
            else {
                float f = Float.parseFloat(strings[1]);
                // check if it's a valid health value
                verifyValidity(f);

                // find the player
                Player p = Bukkit.getPlayer(strings[0]);

                // set their max health
                if (p != null) {
                    MaxHealthModifier.setMaxHealth(p, f);
                    commandSender.sendMessage("Set max health of player " + p.getDisplayName() + " to " + f);
                }
                else {
                    throw new IllegalArgumentException("Couldn't find player " + strings[0]);
                }
            }
            return true;
        } catch (IllegalArgumentException e) {
            commandSender.sendMessage("Invalid argument: " + e.getMessage());
            return false;
        }
    }

    // checks whether a given health value is valid
    // throws an IllegalArgumentException if not
    protected void verifyValidity(float healthValue) {
        if (healthValue > 1024.0f || healthValue <= 0.0f) {
            throw new IllegalArgumentException("Invalid health value: " + healthValue);
        }
    }

}
