package fr.ixiondata.plugin.object;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.ixiondata.plugin.interfaces.IdentificateurPersoInventaire;
import fr.ixiondata.plugin.interfaces.RecuperateurInventaire;

public class ChargerInventaire implements RecuperateurInventaire, IdentificateurPersoInventaire {

	private ListInventaire lesInventaire = new ListInventaire();

	@SuppressWarnings("unchecked")
	public void viaFile(File fichier) {
		FileConfiguration fc = YamlConfiguration.loadConfiguration(fichier);
		ConfigurationSection ConfSec = fc.getConfigurationSection("Les sacs a dos de");
		try {
			for (String test1 : fc.getConfigurationSection("Les sacs a dos de").getKeys(false)) {

				List<ItemStack> testItem = (List<ItemStack>) ConfSec.get(test1);
				ItemStack[] stack = new ItemStack[testItem.size()];
				testItem.toArray(stack);
				Inventory inv = Bukkit.createInventory(null, 27);
				inv.setContents(stack);
				this.lesInventaire.addInvent(new JoueurInventaire(UUID.fromString(test1), inv));
			}
		} catch (NullPointerException e) {
			System.out.println(e);
		}
	}

	@Override
	public ListInventaire charger() {
		// TODO Auto-generated method stub
		if (this.lesInventaire.getLesInventaire().isEmpty()) {
			this.lesInventaire.setLesInventaire(new ArrayList<>());
		}
		return this.lesInventaire;
	}

	@Override
	public boolean ExistePas(Player ceJoueur) {
		return this.lesInventaire.getUnInventaire(ceJoueur) == null;
	}
	
	@Override
	public Inventory TonInventaire(Player ceJoueur) {
		// TODO Auto-generated method stub
		if (this.ExistePas(ceJoueur)) {
			this.lesInventaire.addInvent(
					new JoueurInventaire(ceJoueur.getUniqueId(), Bukkit.createInventory(null, 27, "Mon sac à dos")));
		}
		return this.lesInventaire.getUnInventaire(ceJoueur).getSonInventaire();
	}
}
