package com.topobon.nrtntdm.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.coloredcarrot.api.sidebar.Sidebar;
import com.coloredcarrot.api.sidebar.SidebarString;
import com.topobon.nrtntdm.TeamDeathMatch;
import com.topobon.nrtntdm.TeamDeathMatchNRTN;
import com.topobon.nrtntdm.utils.Utility;

import me.winterguardian.easyscoreboards.ScoreboardUtil;

public class PlayerFriendlyFire implements Listener {
	TeamDeathMatchNRTN instance;

	public PlayerFriendlyFire(TeamDeathMatchNRTN instance) {
		this.instance = instance;
	}

	@EventHandler
	public void playerFriendlyFireOn(EntityDamageByEntityEvent e) {
		if (TeamDeathMatch.isGameRunning() && e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player hitter = (Player) e.getDamager();
			Player victim = (Player) e.getEntity();

			if (TeamDeathMatch.getPlayersInRedTeam().contains(hitter)
					&& TeamDeathMatch.getPlayersInRedTeam().contains(victim)) {
				e.setCancelled(true);
			}

			if (TeamDeathMatch.getPlayersInBlueTeam().contains(hitter)
					&& TeamDeathMatch.getPlayersInBlueTeam().contains(victim)) {
				e.setCancelled(true);
			}

		}

	}

//	@EventHandler
//	public void onPlayerJoin(PlayerJoinEvent e) {
//		System.out.println("d");
//		Player p = e.getPlayer();
//		
//		SidebarString line2 = new SidebarString(
//				Utility.decodeMessage("&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()));
//
//		SidebarString line3 = new SidebarString(
//				Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()));
//		SidebarString line4 = new SidebarString(
//				Utility.decodeMessage("&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills().get(p)));
//		SidebarString line5 = new SidebarString(
//				Utility.decodeMessage("&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths().get(p)));
//		SidebarString line6 = new SidebarString(Utility.decodeMessage("&8&m&l----------"));
//
//		// SidebarString line1 = new SidebarString(ChatColor.RED + "Line 1!");	
//
//		Sidebar mySidebar = new Sidebar(Utility.decodeMessage("&b&lTeam Death Match"), instance, 60, line2, line3, line4, line5,line6);
//		// SidebarString line3 = new SidebarString("Hello", "World");
//
//		// Add the entry
//	
//
//		// Remove the entry
//		// mySidebar.removeEntry(line3);
//		mySidebar.showTo(e.getPlayer());
//		// mySidebar.hideFrom(player);
//	}
}
