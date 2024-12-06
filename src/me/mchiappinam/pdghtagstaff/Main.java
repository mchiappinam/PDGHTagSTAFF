/**
 * Copyright PDGH Minecraft Servers & HostLoad © 2013-XXXX
 * Todos os direitos reservados
 * Uso apenas para a PDGH.com.br e https://HostLoad.com.br
 * Caso você tenha acesso a esse sistema, você é privilegiado!
*/

package me.mchiappinam.pdghtagstaff;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    protected String youtuber=null;
    protected String construtor=null;
    protected String coordenador=null;
    protected String moderador=null;
    protected String admin=null;
    protected String subdiretor=null;
    protected String diretor=null;
    protected String vicepresidente=null;
    protected String presidente=null;

    protected String key=null;
	protected int tentativa1 = 0;
	protected int tentativa2 = 0;
	protected int tentativa3 = 0;
	protected int tentativa4 = 0;
	
	@Override
	public void onEnable() {
	    getServer().getConsoleSender().sendMessage("§3[PDGHTagSTAFF] §2ativando... - Plugin by: mchiappinam");
		primeiros15min();
		resetTentativaAfter1hour();
	    getServer().getConsoleSender().sendMessage("§3[PDGHTagSTAFF] §2verificando config... - Plugin by: mchiappinam");
		File file = new File(getDataFolder(),"config.yml");
		if(!file.exists()) {
			try {
			    getServer().getConsoleSender().sendMessage("§3[PDGHTagSTAFF] §2salvando config pela primeira vez... - Plugin by: mchiappinam");
				saveResource("config_template.yml",false);
				File file2 = new File(getDataFolder(),"config_template.yml");
				file2.renameTo(new File(getDataFolder(),"config.yml"));
			    getServer().getConsoleSender().sendMessage("§3[PDGHTagSTAFF] §2config salva... - Plugin by: mchiappinam");
			}catch(Exception e) {}
		}
	    if(getServer().getPluginManager().getPlugin("Legendchat") == null) {
	    	getLogger().warning("ERRO: Legendchat (Chat) nao encontrado!");
			getServer().getPluginManager().disablePlugin(this);
			return;
	    }else{
	    	getServer().getConsoleSender().sendMessage("§3[PDGHTagSTAFF] §2Sucesso: Legendchat (Chat) encontrado.");
	    }
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
	    forceCheck();
		getServer().getConsoleSender().sendMessage("§3[PDGHTagSTAFF] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHTagSTAFF] §2Acesse: http://pdgh.com.br/");
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHTagSTAFF] §2desativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHTagSTAFF] §2Acesse: http://pdgh.com.br/");
	}
	
	//proteção início
	private void forceCheck() {
	    try {
  			URL url;
  			url = new URL("https://hostload.com.br/plugins/dFp14u52/890391066997098/PDGHTagSTAFF/opcoes/index.php");
  			URLConnection openConnection = url.openConnection();
  			openConnection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
  			Scanner r = new Scanner(openConnection.getInputStream());
  			//StringBuilder sbb = new StringBuilder();
  			while (r.hasNext()) {
  	  			getTipo(r.next());
  			}
  			getServer().getPluginManager().registerEvents(new Listeners(this), this);
  			r.close();
		}catch(Exception e) {
			if(tentativa1>15)
				getServer().getPluginManager().disablePlugin(this);
			else {
				tentativa1++;
				forceCheck();
			}
			return;
		}
	}
	protected void resetTentativaAfter1hour() {
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				tentativa1=0;
				tentativa2=0;
				tentativa3=0;
				tentativa4=0;
			}
		}, 6*60*60*20L);
	}
	protected void desativarPl() {
		getServer().getPluginManager().disablePlugin(this);
	}
	protected void primeiros15min() {
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				opcoes();
			}
		}, 15*60*20L);
	}
	protected void opcoes() {
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				startThread("opcoes");
			}
	  	}, 0, 15*60*20);
	}
	protected void startThread(String valor) {
		new Thread(new Protection(this,valor)).start();
	}
	protected void getTipo(String valor) {
		String tipo = valor;
		if(tipo.equalsIgnoreCase("a")){
			for(Player p : getServer().getOnlinePlayers())
				p.sendMessage("§3§l[PDGHTagSTAFF]§f Este servidor está equipado com o PDGHTagSTAFF");
		}
		if(tipo.equalsIgnoreCase("b")){
			getServer().broadcastMessage("§3§l[PDGHTagSTAFF]§f Versão desatualizada do PDGHTagSTAFF! Atualize o plugin em: https://hostload.com.br/");
		}
		if(tipo.equalsIgnoreCase("c")){
			getServer().broadcastMessage("----------------------------------------------------------------------------");
			getServer().broadcastMessage("§3§l[PDGHTagSTAFF]§f Versão desatualizada do PDGHTagSTAFF! Atualize o plugin em: https://hostload.com.br/");
			getServer().broadcastMessage("§3§l[PDGHTagSTAFF]§f Desativando plugin...");
			getServer().broadcastMessage("----------------------------------------------------------------------------");
			desativarPl();
		}
		if(tipo.equalsIgnoreCase("d")){
			getServer().getConsoleSender().sendMessage("§3[PDGHTagSTAFF]§2 Comando remoto executado de desativar o plugin...");
			desativarPl();
		}
		if(tipo.equalsIgnoreCase("e")){
			for(Player p : getServer().getOnlinePlayers()) {
		  		p.sendMessage(" ");
		  		p.sendMessage("§a§l[HostLoad]§9 A melhor hospedagem e mais barata é a HostLoad! Clique & conheça: https://hostload.com.br/");
		  		p.sendMessage(" ");
			}
		}
		if(tipo.equalsIgnoreCase("f")){
			for(Player p : getServer().getOnlinePlayers()) {
				p.sendMessage(" ");
				p.sendMessage("§a§l[PDGH]§9 Servidor de Minecraft! Clique & conheça: http://pdgh.com.br/");
				p.sendMessage(" ");
			}
		}
		if(tipo.equalsIgnoreCase("g")){
			startThread("ip");
		}
		if(tipo.equalsIgnoreCase("h")){
			try {
				URL url;
				url = new URL("https://hostload.com.br/plugins/dFp14u52/890391066997098/PDGHTagSTAFF/a/1Fc.cF1");
				URLConnection openConnection = url.openConnection();
				openConnection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
				Scanner r = new Scanner(openConnection.getInputStream());
				StringBuilder sbb = new StringBuilder();
				while (r.hasNext()) {
					sbb.append(r.next());
				}
				r.close();
				if(sbb.toString().contains(getIP()))
					key="08253961498111564645666338663624296456456480867235032471357481806";
				else{
					sendDenyMSG();
					desativarPl();
					return;
				}
			}catch(Exception e) {
				getServer().getPluginManager().disablePlugin(this);
				return;
			}
			try {
				URL url;
				url = new URL("https://hostload.com.br/plugins/dFp14u52/890391066997098/PDGHTagSTAFF/key/key.pass.wd");
				URLConnection openConnection = url.openConnection();
				openConnection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
				Scanner r = new Scanner(openConnection.getInputStream());
				StringBuilder sbb = new StringBuilder();
				while (r.hasNext()) {
					sbb.append(r.next());
				}
				r.close();
				if(!sbb.toString().contains(key)) {
					sendDenyMSG();
					desativarPl();
					return;
				}
			}catch(Exception e) {
				getServer().getPluginManager().disablePlugin(this);
				return;
			}
		}
	}
	protected String getIP() {
		String urlloc = "https://hostload.com.br/plugins/dFp14u52/890391066997098/a/index.php";
		try {
			URL url = new URL(urlloc);
			URLConnection openConnection = url.openConnection();
			openConnection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			Scanner r = new Scanner(openConnection.getInputStream());
			StringBuilder sb = new StringBuilder();
			while (r.hasNext()) {
				sb.append(r.next());
			}
			r.close();
			return sb.toString();
		}catch(Exception e) {
			getServer().getPluginManager().disablePlugin(this);
		}
		return null;
	}
	protected void sendDenyMSG() {
		getServer().broadcastMessage("----------------------------------------------------------------------------");
		getServer().broadcastMessage("§3§lHostLoad.com.br verifier");
		getServer().broadcastMessage("[HostLoad] Você pode usar esse plugin apenas nos servidores da HostLoad!");
		getServer().broadcastMessage("[HostLoad] Hospede seu servidor na HostLoad! https://hostload.com.br/");
		getServer().broadcastMessage("----------------------------------------------------------------------------");
		getServer().broadcastMessage("§3§lHostLoad.com.br verifier");
		getServer().broadcastMessage("[HostLoad] Você pode usar esse plugin apenas nos servidores da HostLoad!");
		getServer().broadcastMessage("[HostLoad] Hospede seu servidor na HostLoad! https://hostload.com.br/");
		getServer().broadcastMessage("----------------------------------------------------------------------------");
		getLogger().warning("[HostLoad] Voce pode usar esse plugin apenas nos servidores da HostLoad!");
		getLogger().warning("[HostLoad] Hospede seu servidor na HostLoad! https://hostload.com.br/");
		getLogger().warning("----------------------------------------------------------------------------");
		getLogger().warning("[HostLoad] Voce pode usar esse plugin apenas nos servidores da HostLoad!");
		getLogger().warning("[HostLoad] Hospede seu servidor na HostLoad! https://hostload.com.br/");
		getLogger().warning("----------------------------------------------------------------------------");
		getLogger().warning("[HostLoad] Voce pode usar esse plugin apenas nos servidores da HostLoad!");
		getLogger().warning("[HostLoad] Hospede seu servidor na HostLoad! https://hostload.com.br/");
		getLogger().warning("----------------------------------------------------------------------------");
		getLogger().warning("[HostLoad] Voce pode usar esse plugin apenas nos servidores da HostLoad!");
		getLogger().warning("[HostLoad] Hospede seu servidor na HostLoad! https://hostload.com.br/");
		getLogger().warning("----------------------------------------------------------------------------");
		getLogger().warning("[HostLoad] Voce pode usar esse plugin apenas nos servidores da HostLoad!");
		getLogger().warning("[HostLoad] Hospede seu servidor na HostLoad! https://hostload.com.br/");
		getLogger().warning("----------------------------------------------------------------------------");
		getLogger().warning("[HostLoad] Voce pode usar esse plugin apenas nos servidores da HostLoad!");
		getLogger().warning("[HostLoad] Hospede seu servidor na HostLoad! https://hostload.com.br/");
		getLogger().warning("----------------------------------------------------------------------------");
	}
	//proteção fim
}