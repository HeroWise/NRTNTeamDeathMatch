package com.topobon.nrtntdm.gameprocessing;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.topobon.nrtntdm.TeamDeathMatch;
import com.topobon.nrtntdm.utils.Utility;

public class GameManager {
	public static boolean checkIfGameWon() {
		
		if (TeamDeathMatch.getRedPoints() >= TeamDeathMatch.getTotalPoints()) {
			Bukkit.broadcastMessage(Utility.decodeMessage("&c&lRed Team&a has claimed victory!"));
			Bukkit.broadcastMessage(Utility.decodeMessage("&c&lRed Team &aincluded:"));
			for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
				
				Bukkit.broadcastMessage(Utility.decodeMessage("&a◇&c " + p.getName()));
				
			}
			
			TeamDeathMatch.stopGame();
			return true;
		} else if (TeamDeathMatch.getBluePoints() >= TeamDeathMatch.getTotalPoints()) {
			Bukkit.broadcastMessage(Utility.decodeMessage("&b&lBlue Team&a has claimed victory!"));
			Bukkit.broadcastMessage(Utility.decodeMessage("&b&lBlue Team &aincluded:"));
			for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
				
				Bukkit.broadcastMessage(Utility.decodeMessage("&a◇&b " + p.getName()));
				
			}
			TeamDeathMatch.stopGame();
			return true;
		}
		return false;
	}

	// TIME ran out here so this method gets triggered
	public static void selectWinningTeam() {
		Bukkit.broadcastMessage(Utility.decodeMessage("&a&lTIME IS UP! "));
		if (TeamDeathMatch.getRedPoints() > TeamDeathMatch.getBluePoints()) {
			Bukkit.broadcastMessage(Utility.decodeMessage("&c&lRed Team&a has claimed victory!"));
			for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
				Bukkit.broadcastMessage(Utility.decodeMessage("&c&lRed Team &aincluded:"));
				Bukkit.broadcastMessage(Utility.decodeMessage("&a◇&c " + p.getName()));
			}
			TeamDeathMatch.stopGame();

		} else if (TeamDeathMatch.getRedPoints() < TeamDeathMatch.getBluePoints()) {
			Bukkit.broadcastMessage(Utility.decodeMessage("&b&lBlue Team&a has claimed victory!"));
			for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
				Bukkit.broadcastMessage(Utility.decodeMessage("&b&lBlue Team &aincluded:"));
				Bukkit.broadcastMessage(Utility.decodeMessage("&a◇&b " + p.getName()));
			}
			TeamDeathMatch.stopGame();

		}

		if (TeamDeathMatch.getBluePoints() == TeamDeathMatch.getRedPoints() && TeamDeathMatch.isGameRunning()) {
			Bukkit.broadcastMessage(
					Utility.decodeMessage("&c&lRed Team &aand &b&lBlue Team &aboth has drawn the match! "));
		}
		TeamDeathMatch.stopGame();
	}

}
