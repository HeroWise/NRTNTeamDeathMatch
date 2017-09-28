package com.topobon.nrtntdm.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.topobon.nrtntdm.TeamDeathMatch;
import com.topobon.nrtntdm.TeamDeathMatchNRTN;
import com.topobon.nrtntdm.arena.LocationManager;
import com.topobon.nrtntdm.arena.TDMLocation;
import com.topobon.nrtntdm.utils.Utility;

import me.winterguardian.easyscoreboards.ScoreboardUtil;

public class PlayerDeath implements Listener {

	TeamDeathMatchNRTN instance;

	public PlayerDeath(TeamDeathMatchNRTN instance) {
		this.instance = instance;
	}

	@EventHandler
	public void playerRespawn(PlayerRespawnEvent e) {
		Player player = e.getPlayer();
		// Check if game is runningS
		if (TeamDeathMatch.isGameRunning()) {
			
			//Check player team
			if (TeamDeathMatch.getPlayersInRedTeam().contains(player)) {
				TDMLocation.teleportPlayerToTeamRedSpawn(player);
				
				ScoreboardUtil.unrankedSidebarDisplay(player,
						new String[] { Utility.decodeMessage("&c&lTeam Death Match"),
								Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()),
								Utility.decodeMessage("&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()),
								Utility.decodeMessage("&0|&aKills&7:&4 " +  TeamDeathMatch.getIndividualPlayerKills().get(player)),
								Utility.decodeMessage("&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths().get(player)),
								Utility.decodeMessage("&8&m&l----------") });
				e.setRespawnLocation(LocationManager.teamRedSpawn);
			} else if (TeamDeathMatch.getPlayersInBlueTeam().contains(player)) {
				ScoreboardUtil.unrankedSidebarDisplay(player,
						new String[] { Utility.decodeMessage("&b&lTeam Death Match"),
								Utility.decodeMessage("&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()),
								Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()),
								Utility.decodeMessage("&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills().get(player)),
								Utility.decodeMessage("&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths().get(player)),
								Utility.decodeMessage("&8&m&l----------") });
				TDMLocation.teleportPlayerToTeamBlueSpawn(player);
				e.setRespawnLocation(LocationManager.teamBlueSpawn);
			}
		}
	}

	@EventHandler
	public void playerDeath(PlayerDeathEvent e) {
		Player killedPlayer = e.getEntity().getPlayer();
		Player killer = e.getEntity().getKiller();

		if (TeamDeathMatch.isGameRunning()) {
			// TODO add pointers for friendly fire

			// RED kills RED
			if (TeamDeathMatch.getPlayersInRedTeam().contains(killer)
					&& TeamDeathMatch.getPlayersInRedTeam().contains(killedPlayer)) {
				killer.sendMessage(
						Utility.messageToPlayer("&4You cant kill your Ally! Points would'nt be removed or added!"));
				Bukkit.broadcastMessage(Utility.messageToPlayer("&c&l" + killedPlayer.getDisplayName()
						+ "&a has been killed by &c&l" + killer.getDisplayName()
						+ "! No points would be deducted this time due to friendly fire on, but if problems persist points would be decducted..."));
			
				TDMLocation.teleportPlayerToTeamRedSpawn(killedPlayer);
			}
			// RED kills BLUE
			if (TeamDeathMatch.getPlayersInRedTeam().contains(killer)
					&& TeamDeathMatch.getPlayersInBlueTeam().contains(killedPlayer)) {
				killer.sendMessage(Utility.messageToPlayer("&aYou have killed &b" + killedPlayer.getDisplayName()));
				Bukkit.broadcastMessage(
						Utility.messageToPlayer("&b&l" + killedPlayer.getDisplayName() + "&a has been killed by &c&l"
								+ killer.getDisplayName() + "! &l+1&a points have been added to &4&lRed Team&a!"));
				TeamDeathMatch.setRedPoints(TeamDeathMatch.getRedPoints() + 1);
				TeamDeathMatch.setIndividualPlayerKills(killer, TeamDeathMatch.getIndividualPlayerKills().get(killer)+1);
				TeamDeathMatch.setIndividualPlayerDeaths(killedPlayer, TeamDeathMatch.getIndividualPlayerDeaths().get(killedPlayer)+1);
				e.setDeathMessage("BAD LIFE ");
				// Update Scoreboard for everyone
				for (Player player : TeamDeathMatch.getPlayersInRedTeam()) {
					ScoreboardUtil.unrankedSidebarDisplay(player,
							new String[] { Utility.decodeMessage("&c&lTeam Death Match"),
									Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()),
									Utility.decodeMessage(
											"&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()),
									Utility.decodeMessage("&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills().get(player)),
									Utility.decodeMessage("&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths().get(player)),
									Utility.decodeMessage("&8&m&l----------") });
				}
				for (Player player : TeamDeathMatch.getPlayersInBlueTeam()) {
					ScoreboardUtil.unrankedSidebarDisplay(player,
							new String[] { Utility.decodeMessage("&b&lTeam Death Match"),
									Utility.decodeMessage(
											"&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()),
									Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()),
									Utility.decodeMessage("&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills().get(player)),
									Utility.decodeMessage("&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths().get(player)),
									Utility.decodeMessage("&8&m&l----------") });
				}

				TDMLocation.teleportPlayerToTeamBlueSpawn(killedPlayer);
			}
			// BLUE kills BLUE
			if (TeamDeathMatch.getPlayersInBlueTeam().contains(killer)
					&& TeamDeathMatch.getPlayersInBlueTeam().contains(killedPlayer)) {
				killer.sendMessage(
						Utility.messageToPlayer("&4You cant kill your Ally! Points would'nt be removed or added!"));
				Bukkit.broadcastMessage(Utility.messageToPlayer("&b&l" + killedPlayer.getDisplayName()
						+ "&a has been killed by &b&l" + killer.getDisplayName()
						+ "! No points would be deducted this time due to friendly fire on, but if problems persist points would be decducted..."));
		
				TDMLocation.teleportPlayerToTeamBlueSpawn(killedPlayer);
			}
			// BLUE kills RED
			if (TeamDeathMatch.getPlayersInBlueTeam().contains(killer)
					&& TeamDeathMatch.getPlayersInRedTeam().contains(killedPlayer)) {
				killer.sendMessage(Utility.messageToPlayer("&aYou have killed &c" + killedPlayer.getDisplayName()));
				Bukkit.broadcastMessage(
						Utility.messageToPlayer("&c&l" + killedPlayer.getDisplayName() + "&a has been killed by &b&l"
								+ killer.getDisplayName() + "! &l+1&a points have been added to &b&lBlue Team&a!"));
				TeamDeathMatch.setBluePoints(TeamDeathMatch.getBluePoints() + 1);
				TeamDeathMatch.setIndividualPlayerKills(killer, TeamDeathMatch.getIndividualPlayerKills().get(killer)+1);
				TeamDeathMatch.setIndividualPlayerDeaths(killedPlayer, TeamDeathMatch.getIndividualPlayerDeaths().get(killedPlayer)+1);
	
				// Displaying to Blue Team
				for (Player player : TeamDeathMatch.getPlayersInBlueTeam()) {
					ScoreboardUtil.unrankedSidebarDisplay(player,
							new String[] { Utility.decodeMessage("&b&lTeam Death Match"),
									Utility.decodeMessage(
											"&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()),
									Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()),
									Utility.decodeMessage("&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills().get(player)),
									Utility.decodeMessage("&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths().get(player)),
									Utility.decodeMessage("&8&m&l----------") });
				}
				// DIsplaying to Red Team
				for (Player player : TeamDeathMatch.getPlayersInRedTeam()) {
					ScoreboardUtil.unrankedSidebarDisplay(player,
							new String[] { Utility.decodeMessage("&c&lTeam Death Match"),
									Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()),
									Utility.decodeMessage(
											"&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()),
									Utility.decodeMessage("&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills().get(player)),
									Utility.decodeMessage("&0|&aDeaths&7:&4 " +  TeamDeathMatch.getIndividualPlayerKills().get(player)),
									Utility.decodeMessage("&8&m&l----------") });
				}

				TDMLocation.teleportPlayerToTeamRedSpawn(killedPlayer);
			}
		}
	}

}
