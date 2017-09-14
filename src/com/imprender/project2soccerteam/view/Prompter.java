package com.imprender.project2soccerteam.view;

import com.imprender.project2soccerteam.model.League;
import com.imprender.project2soccerteam.model.Player;
import com.imprender.project2soccerteam.model.Team;

import java.util.*;

public class Prompter {
	private Scanner scanner = new Scanner(System.in);


	public Prompter() {
	}

	private Map<String, String> mainMenuOptions() {
		Map<String, String> options = new LinkedHashMap<>();

		options.put("Create", "Create a new team");
		options.put("Add", "Add a player to a team");
		options.put("Remove", "Remove a player from a team");
		options.put("Report", "View report of a team by height");
		options.put("Balance", "View the League balance report");
		options.put("Roster", "View the roster");
		options.put("Quit", "Exits the program");


		return options;
	}

	public String mainMenu() {

		System.out.printf("%n%nMenu%n");
		for (Map.Entry<String, String> entry : mainMenuOptions().entrySet()) {
			System.out.printf("%s - %s%n", entry.getKey(), entry.getValue());
		}
		System.out.println("Select option: ");
		return scanner.nextLine().toLowerCase();
	}

	public Team newTeam() {
		System.out.printf("%nWhat is the team name? ");
		String name = scanner.nextLine();
		System.out.printf("%nWhat is the coach name? ");
		String coachName = scanner.nextLine();
		Team team = new Team(name, coachName);
		System.out.printf("%nTeam %s coached by %s", team.getName(), team.getCoachName());
		return team;
	}


	public Team promptForTeam(List<Team> teams) {
		System.out.printf("%nSelect a team:%n");
		Collections.sort(teams);
		return teams.get(promptForOption(new ArrayList<>(teams)));
	}

	public Player promptForPlayer(List<Player> players) {
		System.out.printf("%nSelect a player:%n");
		return players.get(promptForOption(new ArrayList<>(players)));
	}

	private int promptForOption(List<Object> options) {

		int option = -1;
		while (!(option >= 0 && option < options.size())) {
			int i = 1;
			for (Object object : options) {
				System.out.printf("%s.) %s%n", i, object.toString());
				i++;
			}
			System.out.printf("Select an option: ");
			option = Integer.parseInt(scanner.nextLine()) - 1;
		}
		return option;
	}


	public void reportTeam(Team team) {
		System.out.println("List of players by height:");

		for (Player player : team.getPlayers()) {
			System.out.println(player);
		}

		System.out.printf("The average experience of the team is %d%%", team.getAverageExperience());
		System.out.printf("%n%nNow by height group! :)%n");

		for (String key : team.getPlayersByHeightGroups().keySet()) {
			System.out.printf("[" + key + "]%n");
			System.out.printf("______________________________%n");

			for (Player player : team.getPlayersByHeightGroups().get(key)) {
				System.out.println(player);
			}
			System.out.printf("%n%n");

		}
	}

	public void showRoster(List<Team> teams) {
		for (Team team : teams) {
			System.out.printf("%n%n[[%s : %d players ]]%n", team, team.getPlayers().size());
			for (Player player : team.getPlayers()) {
				System.out.printf("%s%n", player);
			}
		}
	}


	public void showLeagueBalanceReport(League league) {
		for (Team team : league.getTeams()) {
			System.out.printf("%n%s", team);
			System.out.printf("%n[35-40] --> %d", team.getPlayersByHeightGroups().get("35-40").size());
			System.out.printf("%n[41-46] --> %d", team.getPlayersByHeightGroups().get("41-46").size());
			System.out.printf("%n[47-50] --> %d", team.getPlayersByHeightGroups().get("47-50").size());
		}
	}

	public void notEnoughtPlayers() {
		System.out.println("There are not enough players to create a new team (You need at least 5 available players!)");
	}

}
