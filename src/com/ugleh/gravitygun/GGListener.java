package com.ugleh.gravitygun;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class GGListener implements Listener{
	public static HashMap<String, FallingBlock> blockStorage = new HashMap<String, FallingBlock>();

	public static BukkitTask task = null;
	private GravityGun plugin;
	
	public GGListener(GravityGun p) {
	    plugin = p;
	}
	
	@EventHandler
	public void EntityChangeBlock(EntityChangeBlockEvent event){
		Iterator<Map.Entry<String,FallingBlock>> it = blockStorage.entrySet().iterator();  
        while (it.hasNext()) {  
        	Entry<String, FallingBlock> entry = it.next(); 
        	if(entry.getValue().getEntityId() == event.getEntity().getEntityId()){
        		event.setCancelled(true);
        	}
            }
	}
	@EventHandler
	public void PlayerRightClick(PlayerInteractEvent event) throws IOException {
		if(event.getAction().equals(Action.LEFT_CLICK_AIR)){
			final Player p = event.getPlayer(); 
		    task = Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
		        @Override  
		        public void run() {
					Player pp = Bukkit.getPlayer(p.getName());
					Boolean freeblock = false;
					Block a = null;
					Integer distance = 5;
					while(!freeblock){
						if(distance == 0){
							freeblock = true;
						}else{
						a = pp.getTargetBlock(null, distance);
						if(a.getLocation().getBlock().getType().equals(Material.AIR)){
							freeblock = true;
						}else{
							distance--;
						}
						}
					}
					Byte blockData = 0x0;
					if(blockStorage.containsKey(pp.getName())){
						blockStorage.get(pp.getName()).remove();
					}
					FallingBlock newt = a.getWorld().spawnFallingBlock(a.getLocation(), Material.BEDROCK, blockData);
					newt.setDropItem(false);
					newt.setFallDistance(0);
					newt.setVelocity(new Vector(0,0,0));
					blockStorage.put(pp.getName(), newt);
					}
		    }, 0L, 1L);
		    }
		}
	}