package fr.ixiondata.plugin.object;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class ListInventaire {
	private List<JoueurInventaire> lesInventaire = new ArrayList<>();

	public List<JoueurInventaire> getLesInventaire() {
		return lesInventaire;
	}

	public void addInvent(JoueurInventaire JouInv) {
		lesInventaire.add(JouInv);
	}
	
	public void setLesInventaire(List<JoueurInventaire> lesInventaire) {
		this.lesInventaire = lesInventaire;
	}
	
	public JoueurInventaire getUnInventaire(Player joueur) {
		for (JoueurInventaire joueurInventaire : lesInventaire) {
			if(joueurInventaire.getLeJoueur().equals(joueur.getUniqueId())) {
				return joueurInventaire;
			}
		}
		return null;
	}
}
