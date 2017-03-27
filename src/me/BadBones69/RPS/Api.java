package me.BadBones69.RPS;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

public class Api{
	
	static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("RockPaperScissors");
	@SuppressWarnings("static-access")
	public Api(Plugin plugin){
		this.plugin = plugin;
	}
	public static String color(String msg){
		msg = msg.replaceAll("(&([a-f0-9]))", "\u00A7$2");
		msg = msg.replaceAll("&l", ChatColor.BOLD + "");
		msg = msg.replaceAll("&o", ChatColor.ITALIC + "");
		msg = msg.replaceAll("&k", ChatColor.MAGIC + "");
		msg = msg.replaceAll("&n", ChatColor.UNDERLINE + "");
		return msg;
	}
	public static boolean isOnline(String name){
		for(Player player : Bukkit.getServer().getOnlinePlayers()){
			if(player.getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	public static boolean permCheck(Player player, String perm){
		if(!player.hasPermission("RPS." + perm)){
			player.sendMessage(color("&cYou do not have permission to use that command!"));
			return false;
		}
		return true;
	}
	static int pickAI(){
		Random i = new Random();
		int chance;
		chance = 1 + i.nextInt(3);
		return chance;
	}
	static String findWinner(int AI, int player){
		String A = "";
		String P = "";
		if(AI==1)A="Rock";
		if(AI==2)A="Paper";
		if(AI==3)A="Scissors";
		if(player==47)P="Rock";
		if(player==49)P="Paper";
		if(player==51)P="Scissors";
		if(A.equals("Rock")){
			if(P.equals("Rock"))return "Draw";
			if(P.equals("Paper"))return "AI";
			if(P.equals("Scissors"))return "Player";
		}
		if(A.equals("Paper")){
			if(P.equals("Rock"))return "Player";
			if(P.equals("Paper"))return "Draw";
			if(P.equals("Scissors"))return "AI";
		}
		if(A.equals("Scissors")){
			if(P.equals("Rock"))return "AI";
			if(P.equals("Paper"))return "Player";
			if(P.equals("Scissors"))return "Draw";
		}
		return "Draw";
	}
	static ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore){
		ItemStack item = new ItemStack(material, amount, (short) type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(name);
		m.setLore(lore);
		item.setItemMeta(m);
		return item;
	}
	static ItemStack makeItem(Material material, int amount, int type, String name){
		ItemStack item = new ItemStack(material, amount, (short) type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(name);
		item.setItemMeta(m);
		return item;
	}
	@SuppressWarnings("deprecation")
	static ItemStack getPlayerHead(String name, String displayname){
		ItemStack head = new ItemStack(397, 1, (short)3);
		SkullMeta m = (SkullMeta) head.getItemMeta();
		m.setOwner(name);
		m.setDisplayName(color(displayname));
		head.setItemMeta(m);
		return head;
	}
	static int getAIWins(){
		return Main.settings.getConfig().getInt("Players.Computer.Wins");
	}
	static int getAILosses(){
		return Main.settings.getConfig().getInt("Players.Computer.Losses");
	}
	static int getAIDraws(){
		return Main.settings.getConfig().getInt("Players.Computer.Draws");
	}
	static int getPlayerWins(Player player){
		return Main.settings.getConfig().getInt("Players."+ player.getUniqueId().toString() + ".Wins");
	}
	static int getPlayerLosses(Player player){
		return Main.settings.getConfig().getInt("Players."+ player.getUniqueId().toString() + ".Losses");
	}
	static int getPlayerDraws(Player player){
		return Main.settings.getConfig().getInt("Players."+ player.getUniqueId().toString() + ".Draws");
	}
	static void addAIWin(){
		Main.settings.getConfig().set("Players.Computer.Wins", getAIWins() + 1);
		Main.settings.saveConfig();
	}
	static void addAILosses(){
		Main.settings.getConfig().set("Players.Computer.Losses", getAILosses() + 1);
		Main.settings.saveConfig();
	}
	static void addAIDraw(){
		Main.settings.getConfig().set("Players.Computer.Draws", getAIDraws() + 1);
		Main.settings.saveConfig();
	}
	static void addPlayerWin(Player player){
		Main.settings.getConfig().set("Players."+ player.getUniqueId().toString() + ".Wins", getPlayerWins(player) + 1);
		Main.settings.saveConfig();
	}
	static void addPlayerLosses(Player player){
		Main.settings.getConfig().set("Players."+ player.getUniqueId().toString() + ".Losses", getPlayerLosses(player) + 1);
		Main.settings.saveConfig();
	}
	static void addPlayerDraws(Player player){
		Main.settings.getConfig().set("Players."+ player.getUniqueId().toString() + ".Draws", getPlayerDraws(player) + 1);
		Main.settings.saveConfig();
	}
	static int getCost(){
		return plugin.getConfig().getInt("Settings.Cost");
	}
	static double getMoney(Player player){
		return Main.econ.getBalance(player);
	}
}