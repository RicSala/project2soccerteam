package com.imprender.project2soccerteam.model;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private int heightInInches;
	private boolean previousExperience;
	private boolean teamAssigned = false;

	public Player(String firstName, String lastName, int heightInInches, boolean previousExperience) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.heightInInches = heightInInches;
		this.previousExperience = previousExperience;
	}

	public int getHeightInInches() {
		return heightInInches;
	}

	public boolean isTeamAssigned() {
		return teamAssigned;
	}

	public void setTeamAssigned(boolean teamAssigned) {
		this.teamAssigned = teamAssigned;
	}

	public boolean havePreviousExperience() {
		return previousExperience;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + "(" + getHeightInInches() + " inches - " +
				(previousExperience? "experienced " : "inexperienced)");
	}

	@Override
	public int compareTo(Player other) {
		int lastNameComparison = this.lastName.compareTo(other.lastName);
		if (lastNameComparison != 0) {
			return lastNameComparison;
		} else {
			return this.firstName.compareTo(other.firstName);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Player)) return false;

		Player player = (Player) o;

		if (heightInInches != player.heightInInches) return false;
		if (previousExperience != player.previousExperience) return false;
		if (!firstName.equals(player.firstName)) return false;
		return lastName.equals(player.lastName);

	}

	@Override
	public int hashCode() {
		int result = firstName.hashCode();
		result = 31 * result + lastName.hashCode();
		result = 31 * result + heightInInches;
		result = 31 * result + (previousExperience ? 1 : 0);
		return result;
	}
}
