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
}
