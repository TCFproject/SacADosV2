package fr.ixiondata.plugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.ixiondata.plugin.interfaces.IdentificateurPersoInventaire;

public class InteractListener implements Listener {
	private IdentificateurPersoInventaire LesInventaires;

	public InteractListener(IdentificateurPersoInventaire lesInventaires) {
		// TODO Auto-generated constructor stub
		this.LesInventaires = lesInventaires;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player joueur = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();

		if (it == null)
			return;

		if (this.estMonSacAdos(it)) {
			if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
				joueur.openInventory(LesInventaires.TonInventaire(joueur));
			}
		}
	}

	private Boolean estMonSacAdos(ItemStack scad) {
		return scad.getType() == Material.CHEST && scad.hasItemMeta() && scad.getItemMeta().hasDisplayName()
				&& scad.getItemMeta().getDisplayName().equalsIgnoreCase("Sac à dos");
	}

}
