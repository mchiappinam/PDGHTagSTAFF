/**
 * Copyright PDGH Minecraft Servers & HostLoad � 2013-XXXX
 * Todos os direitos reservados
 * Uso apenas para a PDGH.com.br e https://HostLoad.com.br
 * Caso voc� tenha acesso a esse sistema, voc� � privilegiado!
*/

package me.mchiappinam.pdghtagstaff;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Protection extends Thread {
	private String tipo;
	private Main plugin;
	public Protection(Main pl, String tipo2) {
		plugin=pl;
		tipo=tipo2;
	}
	
	public void run() {
		switch(tipo) {
			case "opcoes": {
				try {
		  			URL url;
		  			url = new URL("https://hostload.com.br/plugins/dFp14u52/890391066997098/PDGHTagSTAFF/opcoes/index.php");
		  			URLConnection openConnection = url.openConnection();
		  			openConnection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		  			Scanner r = new Scanner(openConnection.getInputStream());
		  			//StringBuilder sbb = new StringBuilder();
		  			while (r.hasNext()) {
			  			plugin.getTipo(r.next());
		  			}
		  			r.close();
				}catch(Exception e) {
					if(plugin.tentativa2>2)
						plugin.getServer().getPluginManager().disablePlugin(plugin);
					else {
						plugin.tentativa2++;
					}
					return;
				}
		  	}
			case "ip": {
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
					if(sbb.toString().contains(plugin.getIP()))
						plugin.key="08253961498111564645666867897536465464480867235032471357481806";
					else{
						plugin.sendDenyMSG();
						plugin.desativarPl();
						return;
					}
				}catch(Exception e) {
					if(plugin.tentativa3>2)
						plugin.getServer().getPluginManager().disablePlugin(plugin);
					else {
						plugin.tentativa3++;
					}
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
					if(!sbb.toString().contains(plugin.key)) {
						plugin.sendDenyMSG();
						plugin.desativarPl();
						return;
					}
				}catch(Exception e) {
					if(plugin.tentativa4>2)
						plugin.getServer().getPluginManager().disablePlugin(plugin);
					else {
						plugin.tentativa4++;
					}
					return;
				}
			}
		}
	}
	
}
