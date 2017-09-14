package com.imprender.project2soccerteam.model;

import com.imprender.project2soccerteam.data.Players;

import java.io.Serializable;
import java.util.*;

public class League implements Serializable {

	private List<Team> teams;
	private Set<Player> players;

	public League() {

		teams = new ArrayList<>();
		players = new TreeSet<>(Arrays.asList(Players.load()));

	}


	public List<Player> getAvailablePlayers() {
		List<Player> availablePlayers = new ArrayList<>();
		for (Player player : this.players) {
			if (!player.isTeamAssigned()) {
				availablePlayers.add(player);
			}
		}
		return availablePlayers;
	}


	public int numOfPlayers() {
		return players.size();
	}

	public int numOfAvailablePlayers() {

		int num = 0;
		for (Player player : players) {
			if (!player.isTeamAssigned()) {
				num++;
			}
		}

		return num;
	}

	public void addTeam(Team team) {
		teams.add(team);
	}

	public List<Team> getTeams() {
		return teams;
	}

	public List<Player> getPlayers() {

		List<Player> players = new ArrayList<>();
		players.addAll(this.players);

		return players;
	}
}
