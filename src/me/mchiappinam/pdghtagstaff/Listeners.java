/**
 * Copyright PDGH Minecraft Servers & HostLoad © 2013-XXXX
 * Todos os direitos reservados
 * Uso apenas para a PDGH.com.br e https://HostLoad.com.br
 * Caso você tenha acesso a esse sistema, você é privilegiado!
*/

package me.mchiappinam.pdghtagstaff;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;

public class Listeners implements Listener {
	
	private Main plugin;
	public Listeners(Main main) {
		plugin=main;
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	private void onChat(ChatMessageEvent e) {
		for(String perm : plugin.getConfig().getConfigurationSection("tags").getKeys(false)) {
			if ((e.getTags().contains("staff")) && (e.getSender().hasPermission("coloredtags."+perm))) {
				e.setTagValue("staff", plugin.getConfig().getString("tags."+perm));
				return;
			}
		}
	}
}