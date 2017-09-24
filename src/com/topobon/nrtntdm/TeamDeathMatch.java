package com.topobon.nrtntdm;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class TeamDeathMatch {
	private boolean isGameOn; // Need to set
	private int totalNumberOfPoints; // Need to set
	private ArrayList<Player> teamRed = new ArrayList<Player>();
	private ArrayList<Player> teamBlue = new ArrayList<Player>();

	public void clearAllTeams() {
		teamRed.clear();
		teamBlue.clear();
	}

	public void addPlayerInBlueTeam(Player player) {
		teamBlue.add(player);
	}

	public void addPlayerInRedTeam(Player player) {
		teamRed.add(player);

	}

	public void removePlayerInBlueTeam(Player player) {
		teamBlue.remove(player);
	}

	public void removePlayerInRedTeam(Player player) {
		teamRed.remove(player);
	}

	public Boolean isGameRunning() {
		return isGameOn;
	}

	public void setGameRunning(boolean gameOn) {
		isGameOn = gameOn;
	}

	public void startGame() {
		setGameRunning(true);
	}

	public void stopGame() {
		setGameRunning(false);

	}
	public Integer getPoints(){
		return totalNumberOfPoints;
	}
	public void setPoints(int numberOfPoints){
		totalNumberOfPoints = numberOfPoints;
	}
}
