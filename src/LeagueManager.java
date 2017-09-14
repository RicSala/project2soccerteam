import com.imprender.project2soccerteam.model.League;
import com.imprender.project2soccerteam.model.Player;
import com.imprender.project2soccerteam.model.Team;
import com.imprender.project2soccerteam.service.DataLoader;
import com.imprender.project2soccerteam.view.Prompter;

import java.io.File;

public class LeagueManager {

	public static void main(String[] args) {

		League league = getLeague();
		Prompter prompter = new Prompter();

		System.out.printf("There are currently %d registered players.%n", league.numOfPlayers());

		String option = "";
		while (!option.equals("quit")) {
			option = prompter.mainMenu();
			switch (option) {
				case "create":
					if (league.numOfAvailablePlayers() < 5) {
						prompter.notEnoughtPlayers();
					} else {
						league.addTeam(prompter.newTeam());
					}
					break;

				case "add":
					Team teamToAddPlayer = prompter.promptForTeam(league.getTeams());
					Player player = prompter.promptForPlayer(league.getAvailablePlayers());
					teamToAddPlayer.addPlayer(player);
					break;

				case "remove":
					Team teamToRemovePlayer = prompter.promptForTeam(league.getTeams());
					player = prompter.promptForPlayer(teamToRemovePlayer.getPlayers());
					teamToRemovePlayer.removePlayer(player);
					break;

				case "report":
					Team teamToReport = prompter.promptForTeam(league.getTeams());
					prompter.reportTeam(teamToReport);
					break;

				case "balance":
					prompter.showLeagueBalanceReport(league);
					break;

				case "roster":
					prompter.showRoster(league.getTeams());
					break;

				case "quit":
					break;

				default:
					System.out.println("Please, introduce a valid option :)");
			}
		}
		DataLoader.save(league);
	}

	private static League getLeague() {
		League league;
		if (new File("league.ser").exists()) {
			league = DataLoader.load();
		} else {
			league = new League();
		}
		return league;
	}
}




