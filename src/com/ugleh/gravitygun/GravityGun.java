package com.ugleh.gravitygun;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class GravityGun extends JavaPlugin{

	public void onEnable(){
		getServer().getPluginManager().registerEvents(new GGListener(this), this);
		
		// Crafting Recipe for Speed Stick
		ItemStack stick = new ItemStack(Material.STICK, 1);
		ItemMeta stickmeta = stick.getItemMeta();
		stickmeta.setDisplayName(ChatColor.RED + "Speed Stick");
		stick.setItemMeta(stickmeta);
		ShapedRecipe stickRecipe = new ShapedRecipe(stick);
		stickRecipe.shape(" R ", " R ", " R ");
		stickRecipe.setIngredient('R', Material.STICK);
		this.getServer().addRecipe(stickRecipe);
		
		}
}