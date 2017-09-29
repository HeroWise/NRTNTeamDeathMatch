package com.topobon.nrtntdm.gameprocessing;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.topobon.nrtntdm.TeamDeathMatch;
import com.topobon.nrtntdm.TeamDeathMatchNRTN;
import com.topobon.nrtntdm.utils.Utility;

import me.confuser.barapi.BarAPI;
import me.winterguardian.easyscoreboards.ScoreboardUtil;

public class GameManager {
	static TeamDeathMatchNRTN instance;

	public GameManager(TeamDeathMatchNRTN instance) {
		this.instance = instance;
	}

	public static boolean checkIfGameWon() {

		if (TeamDeathMatch.getRedPoints() >= TeamDeathMatch.getTotalPoints() && TeamDeathMatch.isGameRunning()) {
			Bukkit.broadcastMessage(Utility.decodeMessage("&c&lRed Team&a has claimed victory!"));
			Bukkit.broadcastMessage(Utility.decodeMessage("&c&lRed Team &aincluded:"));
			for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {

				Bukkit.broadcastMessage(Utility.decodeMessage(
						"&a◇&c " + p.getName() + " &8- &aKills: " + TeamDeathMatch.getIndividualPlayerKills().get(p)));
				// p.sendMessage(Utility.messageToPlayer("&bJust removing your
				// sins... Steel is my body Fire is my Blood"));
				// p.setHealth(0);

			}
			for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
				BarAPI.removeBar(p);
				// ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {});
				TeamDeathMatch.getRedSideBar(p).hideFrom(p);
			}
			for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
				BarAPI.removeBar(p);
				TeamDeathMatch.getBlueSideBar(p).hideFrom(p);
			}
			TeamDeathMatch.stopGame();
			return true;
		} else if (TeamDeathMatch.getBluePoints() >= TeamDeathMatch.getTotalPoints() && TeamDeathMatch.isGameRunning()) {
			Bukkit.broadcastMessage(Utility.decodeMessage("&b&lBlue Team&a has claimed victory!"));
			Bukkit.broadcastMessage(Utility.decodeMessage("&b&lBlue Team &aincluded:"));
			for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {

				Bukkit.broadcastMessage(Utility.decodeMessage(
						"&a◇&b " + p.getName() + " &8- &aKills: " + TeamDeathMatch.getIndividualPlayerKills().get(p)));
				// p.sendMessage(Utility.messageToPlayer("&bJust removing your
				// sins... Unaware to death Nor known to life"));
				// p.setHealth(0);
			}

			// for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
			// BarAPI.removeBar(p);
			// ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {});
			// }
			// for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
			// BarAPI.removeBar(p);
			// ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {});
			// }
			for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
				BarAPI.removeBar(p);
				// ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {});
				TeamDeathMatch.getRedSideBar(p).hideFrom(p);
			}
			for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
				BarAPI.removeBar(p);
				TeamDeathMatch.getBlueSideBar(p).hideFrom(p);
			}
			TeamDeathMatch.stopGame();
			return true;
		}
		return false;
	}

	// TIME ran out here so this method gets triggered
	public static void selectWinningTeam() {
		Bukkit.broadcastMessage(Utility.decodeMessage("&a&lTIME IS UP! "));
		if (TeamDeathMatch.getRedPoints() > TeamDeathMatch.getBluePoints() && TeamDeathMatch.isGameRunning()) {
			Bukkit.broadcastMessage(Utility.decodeMessage("&c&lRed Team&a has claimed victory!"));
			for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
				Bukkit.broadcastMessage(Utility.decodeMessage("&c&lRed Team &aincluded:"));
				Bukkit.broadcastMessage(Utility.decodeMessage(
						"&a◇&c " + p.getName() + " &8- &aKills: " + TeamDeathMatch.getIndividualPlayerKills().get(p)));
				p.sendMessage(Utility.messageToPlayer("&bJust removing your sins...Steel is my body Fire is my Blood"));
				p.setHealth(0);
			}

			

		} else if (TeamDeathMatch.getRedPoints() < TeamDeathMatch.getBluePoints() && TeamDeathMatch.isGameRunning()) {
			Bukkit.broadcastMessage(Utility.decodeMessage("&b&lBlue Team&a has claimed victory!"));
			Bukkit.broadcastMessage(Utility.decodeMessage("&b&lBlue Team &aincluded:"));
			for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
			
				Bukkit.broadcastMessage(Utility.decodeMessage(
						"&a◇&b " + p.getName() + " &8- &aKills: " + TeamDeathMatch.getIndividualPlayerKills().get(p)));
				p.sendMessage(Utility.messageToPlayer("&bJust removing your sins...Steel is my body Fire is my Blood"));
				p.setHealth(0);
			} 
			

		}

		if (TeamDeathMatch.getBluePoints() == TeamDeathMatch.getRedPoints() && TeamDeathMatch.isGameRunning()) {
			Bukkit.broadcastMessage(
					Utility.decodeMessage("&c&lRed Team &aand &b&lBlue Team &aboth has drawn the match! "));
			for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
				p.sendMessage(
						Utility.messageToPlayer("&bJust removing your sins... Unaware to death Nor known to life"));
				p.setHealth(0);
			}
			for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {

				p.sendMessage(Utility
						.messageToPlayer("&bJust removing your sins... I have forged over thousands of weapons"));
				p.setHealth(0);
			}
		}

		// for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
		// BarAPI.removeBar(p);
		// ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {});
		// }
		// for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
		// BarAPI.removeBar(p);
		// ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {});
		// }
		for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
			BarAPI.removeBar(p);
			// ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {});
			TeamDeathMatch.getRedSideBar(p).hideFrom(p);
		}
		for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
			BarAPI.removeBar(p);
			TeamDeathMatch.getBlueSideBar(p).hideFrom(p);
		}
		TeamDeathMatch.stopGame();
	}

}
