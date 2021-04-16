package fr.ixiondata.plugin.object;

import java.util.UUID;

import org.bukkit.inventory.Inventory;

public class JoueurInventaire {
	private UUID LeJoueur;
	private Inventory SonInventaire;

	public JoueurInventaire(UUID leJoueur, Inventory sonInventaire) {
		// TODO Auto-generated constructor stub
		this.LeJoueur = leJoueur;
		this.SonInventaire = sonInventaire;
	}

	public UUID getLeJoueur() {
		return LeJoueur;
	}

	public Inventory getSonInventaire() {
		return SonInventaire;
	}
}
