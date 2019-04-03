package com.gmail.adam.chareyre;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class ArmorStandGen implements Listener {

	private Main main;

	public ArmorStandGen(Main main) {
		this.main = main;
	}

	@EventHandler
	public void CommandSender(PlayerCommandPreprocessEvent e) {
		
		String msg = e.getMessage();
		String[] args = msg.split(" ");

		if (args[0].equalsIgnoreCase("/armorgen")) {
			e.setCancelled(true);
			Player p = e.getPlayer();
			World w = p.getWorld();
			float X = 0;
			int Y = w.getHighestBlockYAt(0, 0) + 5;
			int Z = 0;
			@SuppressWarnings("deprecation")
			ItemStack slab = new ItemStack(126, 1);
			for (int nbr = 0; nbr < 30; nbr++) {
				ArmorStand arm = (ArmorStand) p.getWorld().spawnEntity(new Location(p.getWorld(), X, Y, Z, 47.7F, 0F),
						EntityType.ARMOR_STAND);
				new BukkitRunnable() {
					float angle = 0;
					// float angle2 = (float) Math.ceil(angle);
					@SuppressWarnings("unused")
					int nbr2 = 0;

					@Override
					public void run() {
						angle += 0.1;
						arm.setRightArmPose(new EulerAngle(angle, 0.0D, 0.0D));
						if (arm.getCustomName().equals("armor1")) {
							System.out.println("[ArmorStand 1] est a " + angle + " degres !");
						}
						nbr2++;
						if (angle > 4.8) {
							cancel();
						}
					}
				}.runTaskTimer(main, 0, 10);

				arm.setSmall(true);
				arm.setGravity(false);
				arm.setArms(true);
				arm.setBasePlate(false);
				arm.setItemInHand(slab);
				arm.setCustomName("armor" + nbr);
				X += 0.188;

				if (nbr == 29) {
					Bukkit.broadcastMessage(ChatColor.BLUE + "[" + ChatColor.DARK_AQUA + "ArmorStand" + ChatColor.BLUE
							+ "] " + ChatColor.YELLOW + "L'action à été effectuée " + nbr + " fois !");
				}
			}
		}

		if (args[0].equalsIgnoreCase("/armorgen2")) {
			e.setCancelled(true);
			Player p = e.getPlayer();
			World w = p.getWorld();
			
			float X = 0.0F;
			int Y = w.getHighestBlockYAt(0, 0) + 50;
			float Z = 0.0F;
			
			@SuppressWarnings("deprecation")
			ItemStack slab = new ItemStack(126, 1);
			
			for (int nbr = 0; nbr < 1999; nbr++) {
				
				ArmorStand arm = (ArmorStand) w.spawnEntity(new Location(w, X, Y, Z, 47.7F, 0F),
						EntityType.ARMOR_STAND);
				arm.setRightArmPose(new EulerAngle(2.55D, 0.0D, 0.0D));
				arm.setSmall(true);
				arm.setGravity(false);
				arm.setArms(true);
				arm.setBasePlate(false);
				arm.setItemInHand(slab);
				X -= 0.1875;

				if (nbr > 200) {
					Z += 0.1875;
					X = 0;
					nbr = 0;
				}
				if (Z > 2.4) {
					break;
				}
			}
		}

	}
}