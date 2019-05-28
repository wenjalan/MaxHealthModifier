package wenjalan.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import wenjalan.MaxHealthModifier;

public class MaxHealthModifierListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // set their max health to whatever the server standard is
        MaxHealthModifier.setMaxHealth(e.getPlayer(), MaxHealthModifier.serverMaxHealth);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        // set their max health to vanilla
        // MaxHealthModifier.setMaxHealth(e.getPlayer(), MaxHealthModifier.VANILLA_MAX_HEALTH);
    }

}
