package fr.ixiondata.plugin.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface IdentificateurPersoInventaire {
	boolean ExistePas(Player ceJoueur);
	
	Inventory TonInventaire(Player ceJoueur);
}
