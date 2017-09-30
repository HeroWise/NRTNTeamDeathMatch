package com.topobon.nrtntdm.events;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.topobon.nrtntdm.TeamDeathMatch;
import com.topobon.nrtntdm.TeamDeathMatchNRTN;
import com.topobon.nrtntdm.arena.LocationManager;
import com.topobon.nrtntdm.arena.TDMLocation;
import com.topobon.nrtntdm.utils.Utility;

import org.bukkit.craftbukkit.v1_10_R1.CraftServer;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_10_R1.ChatComponentText;
import net.minecraft.server.v1_10_R1.EntityPlayer;
import net.minecraft.server.v1_10_R1.MinecraftServer;
import net.minecraft.server.v1_10_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_10_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_10_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_10_R1.PlayerInteractManager;
import net.minecraft.server.v1_10_R1.WorldServer;

public class PlayerDeath implements Listener {

	TeamDeathMatchNRTN instance;

	public PlayerDeath(TeamDeathMatchNRTN instance) {
		this.instance = instance;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void playerRespawn(PlayerRespawnEvent e) {

		Player player = e.getPlayer();
		// Check if game is runningS
		if (TeamDeathMatch.isGameRunning()) {

			// Check player team
			if (TeamDeathMatch.getPlayersInRedTeam().contains(player)) {
				TDMLocation.teleportPlayerToTeamRedSpawn(player);
				player.sendMessage(Utility.decodeMessage("&cYou have respawned back!"));
				TeamDeathMatch.getRedSideBar(player).showTo(player);
				e.setRespawnLocation(LocationManager.teamRedSpawn);
				e.setRespawnLocation(LocationManager.teamRedSpawn);

			} else if (TeamDeathMatch.getPlayersInBlueTeam().contains(player)) {
				TDMLocation.teleportPlayerToTeamBlueSpawn(player);
				TeamDeathMatch.getBlueSideBar(player).showTo(player);
				player.sendMessage(Utility.decodeMessage("&bYou have respawned back!"));
				e.setRespawnLocation(LocationManager.teamBlueSpawn);
				e.setRespawnLocation(LocationManager.teamBlueSpawn);

			}
		}
	}

	@EventHandler
	public void playerDeath(PlayerDeathEvent e) {
		Player killedPlayer = e.getEntity().getPlayer();
		Player killer = e.getEntity().getKiller();

		if (TeamDeathMatch.isGameRunning()) {

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
				TeamDeathMatch.setIndividualPlayerKills(killer,
						TeamDeathMatch.getIndividualPlayerKills().get(killer) + 1);
				TeamDeathMatch.setIndividualPlayerDeaths(killedPlayer,
						TeamDeathMatch.getIndividualPlayerDeaths().get(killedPlayer) + 1);

				// Update Scoreboard for everyone
				for (Player player : TeamDeathMatch.getPlayersInRedTeam()) {

					TeamDeathMatch.getRedSideBar(player).showTo(player);
				}
				for (Player player : TeamDeathMatch.getPlayersInBlueTeam()) {
					TeamDeathMatch.getBlueSideBar(player).showTo(player);
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
				TeamDeathMatch.setIndividualPlayerKills(killer,
						TeamDeathMatch.getIndividualPlayerKills().get(killer) + 1);
				TeamDeathMatch.setIndividualPlayerDeaths(killedPlayer,
						TeamDeathMatch.getIndividualPlayerDeaths().get(killedPlayer) + 1);

				// Displaying to Blue Team
				for (Player player : TeamDeathMatch.getPlayersInBlueTeam()) {

					System.out.println("test");
					TeamDeathMatch.getBlueSideBar(player).showTo(player);
				}
				// DIsplaying to Red Team
				for (Player player : TeamDeathMatch.getPlayersInRedTeam()) {

					TeamDeathMatch.getRedSideBar(player).showTo(player);
				}

				TDMLocation.teleportPlayerToTeamRedSpawn(killedPlayer);
			}
		}
	}

}
