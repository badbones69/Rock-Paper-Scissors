package me.BadBones69.RPS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class GUI implements Listener{
	static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("RockPaperScissors");
	@SuppressWarnings("static-access")
	public GUI(Plugin plugin){
		this.plugin = plugin;
	}
	static HashMap<Player, Integer> invClose = new HashMap<Player, Integer>();
	static void openPVP(){
		String invName = Api.color(Main.settings.getConfig().getString("Settings.PvPInventoryName"));
	}
	static void openGUI(Player player){
		String invName = Api.color(Main.settings.getConfig().getString("Settings.InventoryName"));
		ArrayList<Integer> Rock = new ArrayList<Integer>();
		ArrayList<Integer> Paper = new ArrayList<Integer>();
		ArrayList<Integer> Scissors = new ArrayList<Integer>();
		ArrayList<Integer> B = new ArrayList<Integer>();
		ArrayList<Integer> R = new ArrayList<Integer>();
		ArrayList<Integer> Boo = new ArrayList<Integer>();
		Inventory inv = Bukkit.createInventory(null, 54, invName);
		B.add(1);B.add(3);B.add(5);B.add(7);B.add(8);B.add(45);B.add(46);B.add(48);B.add(50);B.add(52);
		for(int i : B){
			inv.setItem(i, Api.makeItem(Material.STAINED_GLASS_PANE, 1, 15, " "));
		}
		R.add(9);R.add(10);R.add(11);R.add(12);R.add(13);R.add(14);R.add(15);R.add(16);R.add(17);R.add(18);R.add(26);R.add(27);R.add(35);R.add(36);R.add(37);R.add(38);R.add(39);R.add(40);R.add(41);R.add(42);R.add(43);R.add(44);
		for(int i : R){
			inv.setItem(i, Api.makeItem(Material.STAINED_GLASS_PANE, 1, 14, " "));
		}
		inv.setItem(22, Api.getPlayerHead("VerizonHD720p", "&c&l&n&oComputer/AI"));
		inv.setItem(31, Api.getPlayerHead(player.getName(), "&a&l&n&o" + player.getName()));
		inv.setItem(0, Api.makeItem(Material.DOUBLE_PLANT, 1, 0, Api.color("&c&lComputer/AI's W/L/D"),
				Arrays.asList(Api.color("&3" + Main.settings.getConfig().getString("Players.Computer.Wins") + "/"
						+ Main.settings.getConfig().getString("Players.Computer.Losses") + "/"
						+ Main.settings.getConfig().getString("Players.Computer.Draws")))));
		String uuid = player.getUniqueId().toString();
		inv.setItem(53, Api.makeItem(Material.DOUBLE_PLANT, 1, 0, Api.color("&6&l" + player.getName() + "'s W/L/D"),
				Arrays.asList(Api.color("&3" + Main.settings.getConfig().getString("Players." + uuid + ".Wins") + "/"
						+ Main.settings.getConfig().getString("Players." + uuid + ".Losses") + "/"
						+ Main.settings.getConfig().getString("Players." + uuid + ".Draws")))));
		Boo.add(19);Boo.add(20);Boo.add(21);Boo.add(23);Boo.add(24);Boo.add(25);Boo.add(28);Boo.add(29);Boo.add(30);Boo.add(32);Boo.add(33);Boo.add(34);
		for(int i : Boo){
			inv.setItem(i, Api.makeItem(Material.STAINED_GLASS_PANE, 1, 11, " "));
		}
		Rock.add(2);Rock.add(47);
		for(int i : Rock){
			inv.setItem(i, Api.makeItem(Material.STONE, 1, 0, Api.color("&8&l&n&oRock")));
		}
		Paper.add(4);Paper.add(49);
		for(int i : Paper){
			inv.setItem(i, Api.makeItem(Material.PAPER, 1, 0, Api.color("&f&l&n&oPaper")));
		}
		Scissors.add(6);Scissors.add(51);
		for(int i : Scissors){
			inv.setItem(i, Api.makeItem(Material.SHEARS, 1, 0, Api.color("&7&l&n&oScissors")));
		}
		player.openInventory(inv);
	}
	static void openLostGUI(Inventory inv, Player player){
		ArrayList<Integer> R = new ArrayList<Integer>();
		R.add(9);R.add(10);R.add(11);R.add(12);R.add(13);R.add(14);R.add(15);R.add(16);R.add(17);R.add(18);R.add(26);R.add(27);R.add(35);R.add(36);R.add(37);R.add(38);R.add(39);R.add(40);R.add(41);R.add(42);R.add(43);R.add(44);
		for(int i : R){
			inv.setItem(i, Api.makeItem(Material.BARRIER, 1, 0, Api.color("&c&l&nLost")));
		}
	}
	static void openWonGUI(Inventory inv, Player player){
		ArrayList<Integer> R = new ArrayList<Integer>();
		R.add(9);R.add(10);R.add(11);R.add(12);R.add(13);R.add(14);R.add(15);R.add(16);R.add(17);R.add(18);R.add(26);R.add(27);R.add(35);R.add(36);R.add(37);R.add(38);R.add(39);R.add(40);R.add(41);R.add(42);R.add(43);R.add(44);
		for(int i : R){
			inv.setItem(i, Api.makeItem(Material.EMERALD, 1, 0, Api.color("&a&l&nWinner")));
		}
	}
	static void openDrawGUI(Inventory inv, Player player){
		ArrayList<Integer> R = new ArrayList<Integer>();
		R.add(9);R.add(10);R.add(11);R.add(12);R.add(13);R.add(14);R.add(15);R.add(16);R.add(17);R.add(18);R.add(26);R.add(27);R.add(35);R.add(36);R.add(37);R.add(38);R.add(39);R.add(40);R.add(41);R.add(42);R.add(43);R.add(44);
		for(int i : R){
			inv.setItem(i, Api.makeItem(Material.DEAD_BUSH, 1, 0, Api.color("&7&l&nDraw")));
		}
	}
	@EventHandler
	public void onInvClick(InventoryClickEvent e){
		String invName = Api.color(Main.settings.getConfig().getString("Settings.InventoryName"));
		String Prefix = Api.color(Main.settings.getConfig().getString("Settings.Prefix"));
		if(e.getWhoClicked() instanceof Player){
			final Player player = (Player) e.getWhoClicked();
			int slot = e.getSlot();
			if(e.getClickedInventory() == null)return;
			if(e.getClickedInventory().getName().equals(invName)){
				e.setCancelled(true);
				if(slot == 47 || slot == 49 || slot == 51){
					if(!invClose.containsKey(player)){
						if(Api.getCost()>Api.getMoney(player)){
							player.sendMessage(Api.color(Main.settings.getConfig().getString("Settings.NeedMoneyMessage")));
							player.closeInventory();
							return;
						}
						EconomyResponse r = Main.econ.withdrawPlayer(player, Api.getCost());
						if(r.transactionSuccess()){
							String Winner = Api.findWinner(Api.pickAI(), slot);
							if(Winner.equals("AI")){
								String msg = plugin.getConfig().getString("Settings.LostMessage");
								msg = msg.replaceAll("%Player%", player.getName());
								player.sendMessage(Api.color(Prefix + msg));
								Api.addAIWin();
								Api.addPlayerLosses(player);
								openLostGUI(e.getClickedInventory(), player);
								player.playSound(player.getLocation(), Sound.VILLAGER_HIT, 1, 1);
								invClose.put(player, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
									@Override
									public void run() {
										player.closeInventory();
										invClose.remove(player);
									}
								}, 7*10));
								return;
							}
							if(Winner.equals("Player")){
								String msg = plugin.getConfig().getString("Settings.WinningMessage");
								msg = msg.replaceAll("%Player%", player.getName());
								player.sendMessage(Api.color(Prefix + msg));
								Api.addPlayerWin(player);
								Api.addAILosses();
								openWonGUI(e.getClickedInventory(), player);
								player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 0);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
									@Override
									public void run() {
										for(String command : Main.settings.getConfig().getStringList("Settings.WinningReward")){
											command = command.replace("%Player%", player.getName());
											command = command.replace("%player%", player.getName());
											Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
										}
									}
								}, 7*10);
								invClose.put(player, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
									@Override
									public void run() {
										player.closeInventory();
										invClose.remove(player);
									}
								}, 7*10));
								return;
							}
							if(Winner.equals("Draw")){
								String msg = plugin.getConfig().getString("Settings.DrawMessage");
								msg = msg.replaceAll("%Player%", player.getName());
								player.sendMessage(Api.color(Prefix + msg));
								Api.addAIDraw();
								Api.addPlayerDraws(player);
								openDrawGUI(e.getClickedInventory(), player);
								player.playSound(player.getLocation(), Sound.SKELETON_HURT, 1, 1);
								invClose.put(player, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
									@Override
									public void run() {
										player.closeInventory();
										invClose.remove(player);
									}
								}, 7*10));
								return;
							}
						}
					}
				}
				return;
			}
		}
	}
	@EventHandler
	public void onInvClose(InventoryCloseEvent e){
		String invName = Api.color(Main.settings.getConfig().getString("Settings.InventoryName"));
		if(e.getInventory().getName().equals(invName)){
			if(invClose.containsKey(e.getPlayer())){
				Bukkit.getScheduler().cancelTask(invClose.get(e.getPlayer()));
				invClose.remove(e.getPlayer());
				return;
			}
		}
	}
}