package com.oxyrus.scream.commands;

import com.oxyrus.scream.ScreamPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ScareCommand implements CommandExecutor {
    private final ScreamPlugin plugin;

    public ScareCommand(ScreamPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        if (args.length == 0) {
            sender.sendMessage("You must provide a target player");
        }

        Bukkit
            .getServer()
            .getOnlinePlayers()
            .stream()
            .filter(player -> player.getName().equalsIgnoreCase(args[0]))
            .forEach(this::ScarePlayer);

        return false;
    }

    private void ScarePlayer(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (4 * 20), 1));
        player.setSprinting(false);
        player.setWalkSpeed(0.01F);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1.0F, 0.2F);

        new BukkitRunnable() {
            int count = 0;
            public void run() {
                count++;
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BREATH, 0.7F, 0.5F);
                if (count >= 5) {
                    player.setWalkSpeed(0.2F);
                    cancel();
                }
            }
        }.runTaskTimer(this.plugin, 0, 20);
    }
}
