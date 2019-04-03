package com.gmail.adam.chareyre;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public void onEnable() {
		instance = this;
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ArmorStandGen(this), (this));
	}

	public void onDisable() {

	}
	
}
