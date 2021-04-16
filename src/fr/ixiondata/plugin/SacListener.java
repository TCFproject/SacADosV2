package fr.ixiondata.plugin;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SacListener implements Listener {

	private ItemStack sacADos() {
		ItemStack sacAdos = new ItemStack(Material.CHEST, 1);
		ItemMeta customM = sacAdos.getItemMeta();
		customM.setDisplayName("Sac à dos");
		customM.addEnchant(Enchantment.ARROW_FIRE, 1, false);

		sacAdos.setItemMeta(customM);
		return sacAdos;
	}
	
	private Boolean estMonSacAdos(ItemStack scad) {
		return scad.getType() == Material.CHEST && scad.hasItemMeta() && scad.getItemMeta().hasDisplayName()
				&& scad.getItemMeta().getDisplayName().equalsIgnoreCase("Sac à dos");
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent joinEvent) {
		Player joueur = joinEvent.getPlayer();

		if (!joueur.getInventory().contains(sacADos())) {
			joueur.getInventory().setItem(8, sacADos());
		}
	}
	
	@EventHandler
	public void onDisconnect(PlayerQuitEvent event) {
		Player joueur = event.getPlayer();
		
		if (joueur.getInventory().contains(sacADos())) {
			joueur.getInventory().setItem(8, null);
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Player joueur = event.getPlayer();
		
		if (!joueur.getInventory().contains(sacADos())) {
			joueur.getInventory().setItem(8, sacADos());
		}
	}

	@EventHandler
	public void onPlaceChest(BlockPlaceEvent event) {
		if (this.estMonSacAdos(event.getItemInHand())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onDragItem(InventoryClickEvent event) {
		if (this.estMonSacAdos(event.getCursor()) || this.estMonSacAdos(event.getCurrentItem())) {
			event.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void onJoueurDrop(PlayerDropItemEvent e) {
		if (this.estMonSacAdos(e.getItemDrop().getItemStack())) {
			e.setCancelled(true);
		}
	}
}
