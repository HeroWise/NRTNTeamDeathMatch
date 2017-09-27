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

import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.event.hanging.HangingBreakEvent.RemoveCause;

public class TeamDeathMatch {
	private static boolean isGameOn; // Need to set
	private static int totalNumberOfPoints; // Need to set
	private static int numberOfTeamRedPoints; 
	private static int numberOfTeamBluePoints;
	private static ArrayList<Player> teamRed = new ArrayList<Player>();
	private static ArrayList<Player> teamBlue = new ArrayList<Player>();

	/**
	 * Clears every players in Team Red and Team Blue
	 */
	public static void clearAllTeams() {
		teamRed.clear();
		teamBlue.clear();
	}

	/**
	 * Adds a player in Blue Team
	 * 
	 * @param player
	 */
	public static void addPlayerInBlueTeam(Player player) {
		teamBlue.add(player);
	}

	/**
	 * Adds a player in Red Team
	 * 
	 * @param player
	 */
	public static void addPlayerInRedTeam(Player player) {
		teamRed.add(player);
	}

	/**
	 * Returns instance of Team Red Arraylist
	 * 
	 * @return Arraylist<Player> teamRed
	 */
	public static ArrayList<Player> getPlayersInRedTeam() {
		return teamRed;
	}
	/**
	 * Returns instance of Team Blue ArrayList
	 * @return  Arraylist<Player> teamBlue
	 */
	public static ArrayList<Player> getPlayersInBlueTeam() {
		return teamBlue;
	}

	public void removePlayerInBlueTeam(Player player) {
		teamBlue.remove(player);
	}

	public void removePlayerInRedTeam(Player player) {
		teamRed.remove(player);
	}

	public static Boolean isGameRunning() {
		return isGameOn;
	}

	public static void setGameRunning(boolean gameOn) {
		isGameOn = gameOn;
	}

	public static void startGame() {
		setGameRunning(true);
		clearAllTeams();
		resetAllTeamPoints();
	}

	public static void stopGame() {
		setGameRunning(false);
		clearAllTeams();
		resetAllTeamPoints();
	}

	public static Integer getTotalPoints() {
		return totalNumberOfPoints;
	}

	public static void setTotalPoints(int numberOfPoints) {
		totalNumberOfPoints = numberOfPoints;
	}

	public static Integer getRedPoints() {
		return numberOfTeamRedPoints;
	}

	public static Integer getBluePoints() {
		return numberOfTeamBluePoints;
	}

	public static void setRedPoints(int numberOfPoints) {
		numberOfTeamRedPoints = numberOfPoints;
	}

	public static void setBluePoints(int numberOfPoints) {
		numberOfTeamBluePoints = numberOfPoints;
	}

	public static void resetAllTeamPoints() {
		setRedPoints(0);
		setBluePoints(0);
	}

}
