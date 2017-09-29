
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
package com.topobon.nrtntdm.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import com.topobon.nrtntdm.TeamDeathMatch;
import com.topobon.nrtntdm.TeamDeathMatchNRTN;
import com.topobon.nrtntdm.gameprocessing.GameManager;
import com.topobon.nrtntdm.gameprocessing.QueueProcess;
import com.topobon.nrtntdm.utils.Utility;

import me.confuser.barapi.BarAPI;
import me.winterguardian.easyscoreboards.ScoreboardUtil;



public class TeamDeathMatchNRTNCommands implements CommandExecutor {
	TeamDeathMatchNRTN instance;

	protected int numberOfSeconds;

	public TeamDeathMatchNRTNCommands(TeamDeathMatchNRTN instance) {
		this.instance = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Check for permissions
		if (cmd.getName().equalsIgnoreCase("tdm")) {
			if (args.length == 0) {
				CommandSender player = sender;

				player.sendMessage(Utility.messageToPlayer(("&8&m========&b&lTeamDeathMatch&8&m========")));
				player.sendMessage(Utility.messageToPlayer("&7/&c&ltdm &8- &ajoin"));
				player.sendMessage(Utility.messageToPlayer("&8&m=============================="));
			}

			if (args.length > 0) {

				if (args[0].equalsIgnoreCase("start") && (!TeamDeathMatch.isGameRunning())&& sender.isOp()) {
					TeamDeathMatch.startGame();
					TeamDeathMatchNRTN.setGameInfo();
				
					/**
					 * Setting Timer in minutes
					 */
					startTimer(TeamDeathMatch.getTimeLimit()); // Setting TImer
																// value
					sender.sendMessage(Utility.messageToPlayer("&aTeam Death Match has started!"));

					return true;
				}
				if (args[0].equalsIgnoreCase("stop") && (TeamDeathMatch.isGameRunning()) && sender.isOp()) {
					TeamDeathMatch.stopGame();
					sender.sendMessage(Utility.messageToPlayer("&aTeam Death Match has stopped!"));
					return true;
				}
				if (args[0].equalsIgnoreCase("join") && (TeamDeathMatch.isGameRunning()) && sender instanceof Player) {
					Player pSender = (Player) sender;
					QueueProcess.setPlayerInQueue(pSender);
					pSender.sendMessage("gay");
				}
				if (args[0].equalsIgnoreCase("setscore") && (TeamDeathMatch.isGameRunning()) && sender instanceof Player && sender.isOp()) {
					Player pSender = (Player) sender;
					Bukkit.broadcastMessage(Utility.messageToPlayer("&aScore has been set to:&7 " + args[1] + "!"));
					TeamDeathMatch.setTotalPoints(Integer.valueOf(args[1]));
					
				}
				
				if (args[0].equalsIgnoreCase("timelimit") && (TeamDeathMatch.isGameRunning()) && sender instanceof Player && sender.isOp()) {
					Player pSender = (Player) sender;
					Bukkit.broadcastMessage(Utility.messageToPlayer("&aTime has been set to:&7 " + args[1] + " mins!"));
					
					TeamDeathMatch.setTimeLimit(Integer.valueOf(args[1]));
					startTimer(TeamDeathMatch.getTimeLimit()); // Setting TImer
					
					

				}
				

			}

		}
		return true;
	}

	/**
	 * 
	 * @param seconds
	 * @return
	 */
	String convertSeconds(int seconds) {
		int h = seconds / 3600;
		int m = (seconds % 3600) / 60;
		int s = seconds % 60;
		String sh = (h > 0 ? String.valueOf(h) + " " + "h" : "");
		String sm = (m < 10 && m > 0 && h > 0 ? "0" : "")
				+ (m > 0 ? (h > 0 && s == 0 ? String.valueOf(m) : String.valueOf(m) + " " + "min") : "");
		String ss = (s == 0 && (h > 0 || m > 0) ? ""
				: (s < 10 && (h > 0 || m > 0) ? "0" : "") + String.valueOf(s) + " " + "s");
		return sh + (h > 0 ? " " : "") + sm + (m > 0 ? " " : "") + ss;
	}

	public void startTimer(int minutes) {
		numberOfSeconds = minutes * 60;
		new BukkitRunnable() {

			@Override
			public void run() {
				
				for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
				
					int seconds = numberOfSeconds;
					String duration = convertSeconds(seconds);
					BarAPI.setMessage(p, Utility.decodeMessage("&cTeam Death Match Ends in &a" + duration + ""), 2);
					
				}
				for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
				
					int seconds = numberOfSeconds;
					String duration = convertSeconds(seconds);
					BarAPI.setMessage(p, Utility.decodeMessage("&bTeam Death Match Ends in &a" + duration + ""), 2);
				}
				for (Player p : Bukkit.getOnlinePlayers()) {
				
					if (TeamDeathMatch.getPlayersInRedTeam().contains(p)) {
//						ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {
//								Utility.decodeMessage("&c&lTeam Death Match"),
//								Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()),
//								Utility.decodeMessage("&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()),
//								Utility.decodeMessage(
//										"&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills().get(p)),
//								Utility.decodeMessage(
//										"&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths().get(p)),
//								Utility.decodeMessage("&8&m&l----------") });
						
						TeamDeathMatch.getRedSideBar(p).showTo(p);
					} else if (TeamDeathMatch.getPlayersInBlueTeam().contains(p)) {
//						ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {
//								Utility.decodeMessage("&b&lTeam Death Match"),
//								Utility.decodeMessage("&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()),
//								Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()),
//								Utility.decodeMessage(
//										"&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills().get(p)),
//								Utility.decodeMessage(
//										"&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths().get(p)),
//								Utility.decodeMessage("&8&m&l----------") });
						TeamDeathMatch.getBlueSideBar(p).showTo(p);
					}}
				

				numberOfSeconds--;
				if (GameManager.checkIfGameWon()) {
					this.cancel();

					for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
						BarAPI.removeBar(p);
						//ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {});
						TeamDeathMatch.getRedSideBar(p).hideFrom(p);
					}
					for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
						BarAPI.removeBar(p);
					//	ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {});
						TeamDeathMatch.getBlueSideBar(p).hideFrom(p);
					}
				}
				if (numberOfSeconds == 0) {
					this.cancel();

					for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
						BarAPI.removeBar(p);
						//ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {});
						TeamDeathMatch.getRedSideBar(p).hideFrom(p);
					}
					for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
						BarAPI.removeBar(p);
					//	ScoreboardUtil.unrankedSidebarDisplay(p, new String[] {});
						TeamDeathMatch.getBlueSideBar(p).hideFrom(p);
					}

					GameManager.selectWinningTeam();
				}

			}

		}.runTaskTimer(instance, 0, 20);
	}
}
