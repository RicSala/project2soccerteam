package com.imprender.project2soccerteam.model;

import java.io.Serializable;
import java.util.*;

public class Team implements Comparable<Team>, Serializable{
	private String name;
	private String coachName;
	private Set<Player> players;

	public Team(String name, String coachName) {
		this.name = name;
		this.coachName = coachName;
		players = new TreeSet<>();

	}

	public String getName() {
		return name;
	}

	public String getCoachName() {
		return coachName;
	}

	public List<Player> getPlayers() {

		List<Player> players = new ArrayList<>();
		players.addAll(this.players);
		return players;
	}

	public int getAverageExperience() {
		int averageExperience = 0;
		for (Player player : players) {
			if (player.havePreviousExperience()) {
				averageExperience++;
			}
		}
		return (averageExperience*100)/players.size();
	}

	@Override
	public int compareTo(Team otherTeam) {
		return this.getName().compareTo(otherTeam.getName());
	}

	@Override
	public String toString() {
		return "Team " + getName() + " coached by " + getCoachName();
	}

	public void addPlayer(Player player) {
		player.setTeamAssigned(true);
		players.add(player);
	}

	public void removePlayer(Player player) {
		player.setTeamAssigned(false);
		players.remove(player);
	}

	public Map<String, List<Player>> getPlayersByHeightGroups() {

		Map<String, List<Player>> playerGroups = new HashMap<>();
		playerGroups.put("35-40", getPlayersInHeightRange(35, 40));
		playerGroups.put("41-46", getPlayersInHeightRange(41, 46));
		playerGroups.put("47-50", getPlayersInHeightRange(47, 50));
		return playerGroups;
	}

	private List<Player> getPlayersInHeightRange(int min, int max) {
		List<Player> playersInRange = new ArrayList<>();
		for (Player player : this.players) {
			if (player.getHeightInInches() >= min && player.getHeightInInches() <= max) {
				playersInRange.add(player);
			}
		}
		return playersInRange;
	}
}
