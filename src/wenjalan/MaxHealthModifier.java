package wenjalan;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import wenjalan.commands.SetMaxHealthCommand;
import wenjalan.listeners.MaxHealthModifierListener;

public class MaxHealthModifier extends JavaPlugin {

    // the vanilla max health of players
    public static final float VANILLA_MAX_HEALTH = 20.0f;

    // the server standard max health to set to
    public static float serverMaxHealth = VANILLA_MAX_HEALTH;

    @Override
    public void onEnable() {
        // attach listeners
        Bukkit.getServer().getPluginManager().registerEvents(new MaxHealthModifierListener(), this);

        // attach commands
        getCommand("setmaxhealth").setExecutor(new SetMaxHealthCommand());
    }

    @Override
    public void onDisable() {
        // set everyone's max health back to normal
        setAllPlayersMaxHealth(VANILLA_MAX_HEALTH);
    }

    // sets the max health of all players on the server
    public static void setAllPlayersMaxHealth(float health) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            setMaxHealth(p, health);
        }
    }

    // sets the max health of a player
    public static void setMaxHealth(Player p, float health) {
        // set their max health to whatever the current standard is
        AttributeInstance maxHealth = p.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (maxHealth != null) {
            maxHealth.setBaseValue(health);

            // change their actual health if it's above the new max
            if (p.getHealth() > health) {
                p.setHealth(health);
            }
        }
        else {
            System.err.println("Couldn't set the max health of player " + p.getDisplayName());
        }
    }

}
