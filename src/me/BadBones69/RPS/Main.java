package me.BadBones69.RPS;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	public static SettingsManager settings = SettingsManager.getInstance();
	public static Main plugin;
	public static Economy econ = null;
	public static EconomyResponse r;
	@Override
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(new GUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		settings.setup(this);
		if (!setupEconomy()){
	   		saveDefaultConfig();
	    }
		if(!getConfig().contains("Settings.Cost")){
			settings.getConfig().set("Settings.Cost", 40);
			settings.saveConfig();
		}
		if(!getConfig().contains("Settings.NeedMoneyMessage")){
			settings.getConfig().set("Settings.NeedMoneyMessage", "&cThat game costs $40 to play.");
			settings.saveConfig();
		}
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
		if(commandLable.equalsIgnoreCase("RPS") || commandLable.equalsIgnoreCase("RockPaperScissors")){
			Player player = (Player) sender;
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("Reload")){
					if(Api.permCheck(player, "Admin") == false)return true;
					settings.reloadConfig();
					String Prefix = Api.color(Main.settings.getConfig().getString("Settings.Prefix"));
					player.sendMessage(Api.color(Prefix + "&3You have just reloaded the Config.yml"));
					return true;
				}
			}
			if(Api.permCheck(player, "Access") == false)return true;
			GUI.openGUI(player);
			return true;
		}
		return false;
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		String uuid = e.getPlayer().getUniqueId().toString();
		if(!getConfig().contains("Players.Computer")){
			getConfig().set("Players.Computer" + ".Wins", 0);
			getConfig().set("Players.Computer" + ".Losses", 0);
			getConfig().set("Players.Computer" + ".Draws", 0);
			this.saveConfig();
		}
		if(!getConfig().contains("Players." + uuid)){
			getConfig().set("Players." + uuid + ".Name", e.getPlayer().getName());
			getConfig().set("Players." + uuid + ".Wins", 0);
			getConfig().set("Players." + uuid + ".Losses", 0);
			getConfig().set("Players." + uuid + ".Draws", 0);
			this.saveConfig();
			return;
		}
	}
	private boolean setupEconomy(){
        if (getServer().getPluginManager().getPlugin("Vault") == null){
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null){
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
