
/**
 * Copyright (c) 2017, HeroWise. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * The name of the author may not be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.topobon.nrtntdm.gameprocessing;

import org.bukkit.entity.Player;
import com.topobon.nrtntdm.TeamDeathMatch;
import com.topobon.nrtntdm.TeamDeathMatchNRTN;
import com.topobon.nrtntdm.arena.TDMLocation;

import com.topobon.nrtntdm.utils.Utility;

import me.winterguardian.easyscoreboards.ScoreboardUtil;

public class QueueProcess {

	TeamDeathMatchNRTN instance;

	public QueueProcess(TeamDeathMatchNRTN instance) {
		this.instance = instance;

	}

	public static void setPlayerInQueue(Player player) {
		for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
			if (player.equals(p)) {
				player.sendMessage(Utility.messageToPlayer("&4You are already in &c&lRed&4 Team!"));

				return;

			}
		}
		for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
			if (player.equals(p)) {
				player.sendMessage(Utility.messageToPlayer("&4You are already in &b&lBlue&4 Team!"));
				return;
			}
		}
		if (TeamDeathMatch.getPlayersInRedTeam().size() >= TeamDeathMatch.getPlayersInBlueTeam().size()) {
			
			TeamDeathMatch.setIndividualPlayerKills(player, 0);
			TeamDeathMatch.setIndividualPlayerDeaths(player, 0);
			ScoreboardUtil.unrankedSidebarDisplay(player,
					new String[] { Utility.decodeMessage("&b&lTeam Death Match"),
							Utility.decodeMessage("&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()),
							Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()),
							Utility.decodeMessage("&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills()),
							Utility.decodeMessage("&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths()),
							Utility.decodeMessage("&8&m&l----------") });
			TeamDeathMatch.addPlayerInBlueTeam(player);
			player.sendMessage(Utility.messageToPlayer("&aYou have joined &b&lBlue&a Team!"));
			TDMLocation.teleportPlayerToTeamBlueSpawn(player);
			//player.setBedSpawnLocation(LocationManager.teamBlueSpawn);

		} else {
			TeamDeathMatch.setIndividualPlayerKills(player, 0);
			TeamDeathMatch.setIndividualPlayerDeaths(player, 0);
			ScoreboardUtil.unrankedSidebarDisplay(player,
					new String[] { Utility.decodeMessage("&c&lTeam Death Match"),
							Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()),
							Utility.decodeMessage("&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()),
							Utility.decodeMessage("&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills()),
							Utility.decodeMessage("&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths()),
							Utility.decodeMessage("&8&m&l----------") });
			
			TeamDeathMatch.addPlayerInRedTeam(player);
			player.sendMessage(Utility.messageToPlayer("&aYou have joined &c&lRed&a Team!"));
			TDMLocation.teleportPlayerToTeamRedSpawn(player);
		//	player.setBedSpawnLocation(LocationManager.teamRedSpawn);
			// BarAPI.setMessage(player, "Time Left till Team Death Match Ends",
			// 15 /* minutes*/ * 60);
		}
	}

}
