package fr.ixiondata.plugin;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import fr.ixiondata.plugin.commandes.CommandeTest;
import fr.ixiondata.plugin.interfaces.RecuperateurInventaire;
import fr.ixiondata.plugin.object.ChargerInventaire;
import fr.ixiondata.plugin.object.JoueurInventaire;

public class mainSacADos extends JavaPlugin {
	private ChargerInventaire LesInventaires = new ChargerInventaire();

	@Override
	public void onEnable() {
		saveDefaultConfig();
		super.onEnable();

		createFile("lesSacADos");

		this.LesInventaires.viaFile(getFile("lesSacADos"));
		
		System.out.println("PREMIER PLUGIN");
		this.getCommand("teleportation").setExecutor(new CommandeTest());
		this.getCommand("teste").setExecutor(new CommandeTest());
		this.getServer().getPluginManager().registerEvents(new SacListener(), this);
		this.getServer().getPluginManager().registerEvents(new InteractListener(this.LesInventaires), this);
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub

		enregistrement(this.LesInventaires);
		System.out.println("PLUGIN FERMEZ");
		
		super.onDisable();
	}
	
/*********************************************************************************************************/
	
	private void enregistrement(RecuperateurInventaire recuperateur) {
		FileConfiguration fc = YamlConfiguration.loadConfiguration(getFile("lesSacADos"));

		for (JoueurInventaire Element : recuperateur.charger().getLesInventaire()) {
			fc.set("Les sacs a dos de." + Element.getLeJoueur(), Element.getSonInventaire().getContents());
		}

		try {
			fc.save(getFile("lesSacADos"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createFile(String nomDossier) {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}

		File dossier = new File(getDataFolder(), nomDossier + ".yml");

		if (!dossier.exists()) {
			try {
				dossier.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private File getFile(String nomDossier) {
		return new File(getDataFolder(), nomDossier + ".yml");
	}

}
