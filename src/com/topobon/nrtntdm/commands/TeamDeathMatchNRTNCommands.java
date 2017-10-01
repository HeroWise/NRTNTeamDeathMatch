
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
			
		
				if (args[0].equalsIgnoreCase("join") && (TeamDeathMatch.isGameRunning()) && sender instanceof Player) {
					Player pSender = (Player) sender;
					System.out.println("test");
					QueueProcess.setPlayerInQueue(pSender);
					
				}
				if (args[0].equalsIgnoreCase("stop") && (TeamDeathMatch.isGameRunning()) && sender.hasPermission("tdm.nrtn")) {
					TeamDeathMatch.stopGame();
					sender.sendMessage(Utility.messageToPlayer("&aTeam Death Match has stopped!"));
					
				}
				
				if (args[0].equalsIgnoreCase("setscore") && (TeamDeathMatch.isGameRunning()) && sender instanceof Player
						&& sender.hasPermission("tdm.nrtn")) {
					Player pSender = (Player) sender;
					Bukkit.broadcastMessage(Utility.sendInfo("&aScore has been set to:&7 " + args[1] + "! You now need " +args[1]+" total points to win!"));
					TeamDeathMatch.setTotalPoints(Integer.valueOf(args[1]));

				}
				if (args[0].equalsIgnoreCase("leave") && (TeamDeathMatch.isGameRunning()) && sender instanceof Player) {
					Player pSender = (Player) sender;
					if (TeamDeathMatch.getPlayersInBlueTeam().contains(pSender)
							|| TeamDeathMatch.getPlayersInRedTeam().contains(pSender)) {

						pSender.sendMessage(Utility.sendInfo(("&aYou have left the Match!")));
						Bukkit.broadcastMessage(Utility.sendInfo("&l&a" + pSender.getName() + " has left!"));
						TeamDeathMatch.removePlayerFromGame(pSender);
						pSender.setHealth(0);
					}

				}
				if (args[0].equalsIgnoreCase("timelimit") && (TeamDeathMatch.isGameRunning())
						&& sender instanceof Player && sender.isOp()) {

					Bukkit.broadcastMessage(Utility.sendInfo("&aTime has been set to:&7 " + args[1] + " mins!"));

					TeamDeathMatch.setTimeLimit(Integer.valueOf(args[1]));
					counter = 1;
					startTimer(TeamDeathMatch.getTimeLimit()); // Setting TImer
					

				}

			
			
			if (args[0].equalsIgnoreCase("start") && (!TeamDeathMatch.isGameRunning()) && sender.hasPermission("tdm.nrtn")) {
				TeamDeathMatch.startGame();

				TeamDeathMatchNRTN.setGameInfo();

				/**
				 * Setting Timer in minutes
				 */
				startTimer(TeamDeathMatch.getTimeLimit()); // Setting TImer
															// value
				sender.sendMessage(Utility.messageToPlayer("&a Team Death Match has started!"));
				Bukkit.broadcastMessage(Utility
						.decodeMessage("&l&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
			
				Bukkit.broadcastMessage(Utility.sendInfo(
						"&c&lTeam Death Match\n&b ◈  A team based game where two teams fight for victory\n ◈  First to a certain amount of points win\n ◈  There is a time limit too, after a certaim time the team with the highest points win. \n ◈  If the points are equal, both teams draw!"));
				Bukkit.broadcastMessage(Utility.sendInfo(
						"&3There are certain procedures you need to do to join a game and have the best experience with:\n &8► &3Please initiate commands /tdm join to enter this round\n &8► &3You also need to initiate commands /fb toggle if you need to see scoreboard &7(&l&3Highly recommended&7)"));
				Bukkit.broadcastMessage(Utility
						.decodeMessage("&l&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
				
			}}
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
	int counter = 0;
	public void startTimer(int minutes) {
		numberOfSeconds = minutes * 60;
		new BukkitRunnable() {

			@Override
			public void run() {
				if(counter==1){
					counter=0;
					this.cancel();
					
				}
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

						TeamDeathMatch.getRedSideBar(p).showTo(p);
					} else if (TeamDeathMatch.getPlayersInBlueTeam().contains(p)) {
						TeamDeathMatch.getBlueSideBar(p).showTo(p);
					}
				}

				if (!TeamDeathMatch.isGameRunning()) {
					this.cancel();
				}
				numberOfSeconds--;
				if (GameManager.checkIfGameWon()) {
					this.cancel();

					for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
						BarAPI.removeBar(p);

						TeamDeathMatch.getRedSideBar(p).hideFrom(p);
					}
					for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
						BarAPI.removeBar(p);

						TeamDeathMatch.getBlueSideBar(p).hideFrom(p);
					}
				}
				if (numberOfSeconds == 0) {
					this.cancel();

					for (Player p : TeamDeathMatch.getPlayersInRedTeam()) {
						BarAPI.removeBar(p);

						TeamDeathMatch.getRedSideBar(p).hideFrom(p);
					}
					for (Player p : TeamDeathMatch.getPlayersInBlueTeam()) {
						BarAPI.removeBar(p);

						TeamDeathMatch.getBlueSideBar(p).hideFrom(p);
					}

					GameManager.selectWinningTeam();
				}

			}

		}.runTaskTimer(instance, 0, 20);
	}
}
