package com.topobon.nrtntdm.gameprocessing;

import org.bukkit.entity.Player;

import com.topobon.nrtntdm.TeamDeathMatch;
import com.topobon.nrtntdm.arena.TDMLocation;
import com.topobon.nrtntdm.utils.Utility;

public class QueueProcess {

	public static void setPlayerInQueue(Player player) {
			
		if (TeamDeathMatch.getPlayersInRedTeam().size() >= TeamDeathMatch.getPlayersInBlueTeam().size()) {
			
			TeamDeathMatch.addPlayerInBlueTeam(player);
			player.sendMessage(Utility.messageToPlayer("You have joined Blue Team!"));
			TDMLocation.teleportPlayerToTeamBlueSpawn(player);
		} else {
			TeamDeathMatch.addPlayerInRedTeam(player);
			player.sendMessage(Utility.messageToPlayer("You have joined Red Team!"));
			TDMLocation.teleportPlayerToTeamRedSpawn(player);
		}
	}

}
