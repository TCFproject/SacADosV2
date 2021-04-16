package fr.ixiondata.plugin.commandes;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandeTest implements CommandExecutor {
	private Player joueur;
	private boolean CommandeEntré(String CommandeName, Command cmd) {
		return cmd.getName().equalsIgnoreCase(CommandeName);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Player> listPlayer = new ArrayList<>(Bukkit.getOnlinePlayers());
		if (sender instanceof Player) {
			this.joueur = (Player) sender;
		}
		
		if (this.CommandeEntré("teste", cmd)) {
			/*
			 * FileConfiguration fc =
			 * YamlConfiguration.loadConfiguration(this.main.getFile("lesSacADos"));
			 * 
			 * ConfigurationSection ConfigSection =
			 * fc.getConfigurationSection("Les sac à dos de"); //String test =
			 * ConfigSection.getString(uuid.toString()); String test =
			 * ConfigSection.getKeys(true).toString().replace("[", "");
			 * joueur.sendMessage(test.replace("]", ""));
			 * 
			 * //joueur.sendMessage(test);
			 */
			return true;
		}
		if (this.CommandeEntré("aide", cmd)) {
			joueur.sendMessage("Appuyez sur votre touche de drop pour faire réapparaitre votre sac a dos");
			System.out.println("test");
			return true;
		}
		if (this.CommandeEntré("teleportation", cmd)) {
			int destination = 1;
			if (args.length < destination || args.length > destination) {
				joueur.sendMessage("Arguments invalides");
			}
			if (args.length == destination) {
				for (int i = 0; i < listPlayer.size(); i++) {
					if (listPlayer.get(i).getName() == args[0]) {
						Player ceJoueur = listPlayer.get(i);
						joueur.teleport(ceJoueur.getLocation());
					} else {
						joueur.sendMessage("Echec");
					}
				}
			}
			return true;
		}
		return false;
	}
}