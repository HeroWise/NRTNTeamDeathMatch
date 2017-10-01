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
package com.topobon.nrtntdm;

/**
 * <b>Team Death Match Extreme - NRTN </b>
 * <p>
 * This plugin was created by @HeroWise and allows you to create a TDM
 * simulation
 * <p>
 * You are welcome to use it or redistribute it under the following conditions:
 * <ul>
 * <li>Don't claim these classes or the whole as your own
 * <li>Tell the author before distributing
 * <li>Ask permission from server owner of NRTN
 * <li>Don't remove this disclaimer
 * </ul>
 * <i>It would be nice if you provide credit to me if you use this class or
 * plugin in a published / a already created project</i>
 * 
 * @author HeroWise
 * @version 1.10
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.coloredcarrot.api.sidebar.Sidebar;
import com.coloredcarrot.api.sidebar.SidebarString;
import com.topobon.nrtntdm.utils.Utility;

import me.confuser.barapi.BarAPI;
import me.winterguardian.easyscoreboards.ScoreboardUtil;

/**
 * <b>TeamDeathMatch </b>
 * <p>
 * This plugin was created by @HeroWise and allows you to create a TDM
 * simulation
 * <p>
 * You are welcome to use it or redistribute it under the following conditions:
 * <ul>
 * <li>Don't claim these classes or the whole as your own
 * <li>Tell the author before distributing
 * <li>Ask permission from server owner of NRTN
 * <li>Don't remove this disclaimer
 * </ul>
 * <i>It would be nice if you provide credit to me if you use this class or
 * plugin in a published / a already created project</i>
 * 
 * @author HeroWise
 * @version 1.10
 * 
 */
public class TeamDeathMatch {
	private static boolean isGameOn; // Need to set
	private static int totalNumberOfPoints; // Need to set
	private static int numberOfTeamRedPoints;
	private static int numberOfTeamBluePoints;
	private static boolean isFriendlyFireOn;
	private static int timeLimit;
	private static ArrayList<Player> teamRed = new ArrayList<Player>();
	private static ArrayList<Player> teamBlue = new ArrayList<Player>();
	private static HashMap<Player, Integer> playerKills = new HashMap<>();
	private static HashMap<Player, Integer> playerDeaths = new HashMap<>();

	private static TeamDeathMatchNRTN instance;

	public TeamDeathMatch(TeamDeathMatchNRTN instance) {
		this.instance = instance;
	}

	/**
	 * <b> Clear All Team <b> Method: Clears every players in Team Red and Team
	 * Blue
	 */
	public static void clearAllTeams() {
		teamRed.clear();
		teamBlue.clear();
	}

	/**
	 * <b> Add player in Blue Team <b> Method: Adds a player in Blue Team
	 * 
	 * @param player
	 */
	public static void addPlayerInBlueTeam(Player player) {
		teamBlue.add(player);
	}

	/**
	 * <b> Add player in Red Team <b> Method: Adds a player in Red Team
	 * 
	 * @param player
	 */
	public static void addPlayerInRedTeam(Player player) {
		teamRed.add(player);
	}

	/**
	 * <b> List players in Red Team <b> Method: Returns instance of Team Red
	 * Arraylist
	 * 
	 * @return Arraylist<Player> teamRed
	 */
	public static ArrayList<Player> getPlayersInRedTeam() {
		return teamRed;
	}

	/**
	 * Method: Returns instance of Team Blue ArrayList
	 * 
	 * @return Arraylist<Player> teamBlue
	 */
	public static ArrayList<Player> getPlayersInBlueTeam() {
		return teamBlue;
	}

	/**
	 * Method: Removes given player from Blue Team
	 * 
	 * @param player
	 */
	public void removePlayerInBlueTeam(Player player) {
		teamBlue.remove(player);
	}

	/**
	 * Method: Removes given player from Red Team
	 * 
	 * @param player
	 */
	public void removePlayerInRedTeam(Player player) {
		teamRed.remove(player);
	}

	public static void removePlayerFromGame(Player player) {
		if (teamBlue.contains(player)) {
			teamBlue.remove(player);
			playerKills.remove(player);
			playerDeaths.remove(player);
		} else {
			teamRed.remove(player);
			playerKills.remove(player);
			playerDeaths.remove(player);

		}
	}

	/**
	 * Method: Returns the Game Running state
	 * 
	 * @return isGameOn Boolean
	 */
	public static Boolean isGameRunning() {
		return isGameOn;
	}

	/**
	 * Method: Sets the Game Runining state
	 * 
	 * @param gameOn
	 *            to set Boolean isGameOn
	 */
	public static void setGameRunning(boolean gameOn) {
		isGameOn = gameOn;
	}

	/**
	 * <b> Starts game <b> Method: The Methods it triggers are:
	 * <ul>
	 * <li>setGameRunning() = @param (Boolean) -setting game to TRUE which
	 * starts the GAME
	 * <li>clearAllTeams() = clears team red and team blue players
	 * (ArrayList<Player>)
	 * <li>resetAllTeamPoints() = resets accumulated team points for both teams
	 * - sets them to '0'
	 * <ul>
	 * 
	 */
	public static void startGame() {
		setGameRunning(true);
		clearAllTeams();
		resetAllTeamPoints();
		getIndividualPlayerKills().clear();
		getIndividualPlayerDeaths().clear();
	}

	/**
	 * <b> Stops game <b> Method: The Methods it triggers are:
	 * <ul>
	 * <li>setGameRunning() = @param (Boolean) -setting game to FALSE which
	 * stops the GAME
	 * <li>clearAllTeams() = clears team red and team blue players
	 * (ArrayList<Player>)
	 * <li>resetAllTeamPoints() = resets accumulated team points for both teams
	 * - sets them to '0'
	 * <ul>
	 * 
	 */
	public static void stopGame() {
		setGameRunning(false);
		clearAllTeams();
		resetAllTeamPoints();
		getIndividualPlayerKills().clear();
		getIndividualPlayerDeaths().clear();
	}

	/**
	 * <b> Get Total Points <b> Method: get Total Points for players to win
	 * 
	 * @return totalNumberOfPoints (Integer)
	 */
	public static Integer getTotalPoints() {
		return totalNumberOfPoints;
	}

	/**
	 * <b> Set Total Points <b> Method: Sets the total points foe teams to win
	 * 
	 * @sets totalNumberOfPoints from @param numberOfPoints (Integer)
	 */
	public static void setTotalPoints(int numberOfPoints) {
		totalNumberOfPoints = numberOfPoints;
	}

	/**
	 * <b> Get Points scored by Red Team <b> Method: Get points, which are
	 * earned by kills,
	 * 
	 * @return numberOfTeamRedPoints (Integer)
	 */
	public static Integer getRedPoints() {
		return numberOfTeamRedPoints;
	}

	/**
	 * <b> Get Points scored by Blue Team <b> Method: Get points, which are
	 * earned by kills,
	 * 
	 * @return numberOfTeamBluePoints (Integer)
	 */
	public static Integer getBluePoints() {
		return numberOfTeamBluePoints;
	}

	/**
	 * <b> Set Points scored by Red Team <b> Method: Get points, which are
	 * earned by kills,
	 * 
	 * @return numberOfTeamRedPoints = @param numberOfPoints (Integer)
	 */
	public static void setRedPoints(int numberOfPoints) {
		numberOfTeamRedPoints = numberOfPoints;
	}

	/**
	 * <b> Set Points scored by Blue Team <b> Method: Get points, which are
	 * earned by kills,
	 * 
	 * @return numberOfTeamBluePoints = @param numberOfPoints (Integer)
	 */
	public static void setBluePoints(int numberOfPoints) {
		numberOfTeamBluePoints = numberOfPoints;
	}

	/**
	 * <b> Reset All Points <b> Method: The Methods it triggers are:
	 * <ul>
	 * <li>setRedPoints() = @param (Integer) - setting points to 0 for Blue Team
	 * <li>setBluePoints() = @param (Integer) - setting points to 0 for Blue
	 * Team
	 * <ul>
	 * 
	 */
	public static void resetAllTeamPoints() {
		setRedPoints(0);
		setBluePoints(0);
	}

	public static Boolean isFriendlyFireOn() {
		return TeamDeathMatch.isFriendlyFireOn;
	}

	public static void setFriendlFireOn(boolean isFriendlyFireOn) {
		TeamDeathMatch.isFriendlyFireOn = isFriendlyFireOn;
	}

	public static HashMap<Player, Integer> getIndividualPlayerKills() {
		return playerKills;
	}

	public static void setIndividualPlayerKills(Player player, int kills) {
		playerKills.put(player, kills);
	}

	public static HashMap<Player, Integer> getIndividualPlayerDeaths() {
		return playerDeaths;
	}

	public static void setIndividualPlayerDeaths(Player player, int kills) {
		playerDeaths.put(player, kills);
	}

	public static int getTimeLimit() {
		return timeLimit;
	}

	public static void setTimeLimit(int timeInMinutes) {
		timeLimit = timeInMinutes;
	}

	public static void givePlayerRewards(Player player) {

		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		String command = "battlelevels addscore " + player + " 10";
		Bukkit.dispatchCommand(console, command);
		command = "mi give custom CrateKey " + player + " 1";
		Bukkit.dispatchCommand(console, command);

		Player highestKillsPlayer = getMaxEntry(getIndividualPlayerKills()).getKey();
		if (highestKillsPlayer == player) {
			player.sendMessage(Utility.messageToPlayer(
					"&aDue to having the highest number of kills and participation, you are getting extra rewards!"));
			command = "eco give " + player + " 2000";
			Bukkit.dispatchCommand(console, command);
		}
	}

	/**
	 * Method: Get Max Entry
	 * 
	 * @param hashMap
	 * @return
	 */
	public static Entry<Player, Integer> getMaxEntry(HashMap<Player, Integer> hashMap) {
		Entry<Player, Integer> maxEntry = null;
		Integer max = Collections.max(hashMap.values());

		for (Entry<Player, Integer> entry : hashMap.entrySet()) {
			Integer value = entry.getValue();
			if (null != value && max == value) {
				maxEntry = entry;
			}
		}
		return maxEntry;
	}

	/**
	 * Tentative
	 * 
	 * @param instance
	 * @param p
	 * @return
	 */
	public static Sidebar getBlueSideBar(Player p) {
		SidebarString line1 = new SidebarString(
				Utility.decodeMessage("&0|&3Win Score&7:&4 " + TeamDeathMatch.getTotalPoints()));
		SidebarString line2 = new SidebarString(
				Utility.decodeMessage("&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()));

		SidebarString line3 = new SidebarString(
				Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()));
		SidebarString line4 = new SidebarString(
				Utility.decodeMessage("&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills().get(p)));
		SidebarString line5 = new SidebarString(
				Utility.decodeMessage("&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths().get(p)));
		SidebarString line6 = new SidebarString(Utility.decodeMessage("&8&m&l----------"));

		Sidebar mySidebar = new Sidebar(Utility.decodeMessage("&b&lTeam Death Match"), instance, 20, line1, line2,
				line3, line4, line5, line6);

		return mySidebar;
	}

	public static Sidebar getRedSideBar(Player p) {
		SidebarString line1 = new SidebarString(
				Utility.decodeMessage("&0|&3Win Score&7:&4 " + TeamDeathMatch.getTotalPoints()));
		SidebarString line2 = new SidebarString(
				Utility.decodeMessage("&0|&1Blue Team Kills&7:&4 " + TeamDeathMatch.getBluePoints()));

		SidebarString line3 = new SidebarString(
				Utility.decodeMessage("&0|&4Red Team Kills&7:&4 " + TeamDeathMatch.getRedPoints()));
		SidebarString line4 = new SidebarString(
				Utility.decodeMessage("&0|&aKills&7:&4 " + TeamDeathMatch.getIndividualPlayerKills().get(p)));
		SidebarString line5 = new SidebarString(
				Utility.decodeMessage("&0|&aDeaths&7:&4 " + TeamDeathMatch.getIndividualPlayerDeaths().get(p)));
		SidebarString line6 = new SidebarString(Utility.decodeMessage("&8&m&l----------"));

		Sidebar mySidebar = new Sidebar(Utility.decodeMessage("&c&lTeam Death Match"), instance, 20, line1, line3,
				line2, line4, line5, line6);

		return mySidebar;
	}

}
