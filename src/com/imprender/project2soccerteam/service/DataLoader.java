package com.imprender.project2soccerteam.service;

import com.imprender.project2soccerteam.model.League;

import java.io.*;


public class DataLoader {

	public static void save(League league) {
		try (
				FileOutputStream fos = new FileOutputStream("league.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
		) {
			oos.writeObject(league);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static League load() {
		League league = null;
		try (FileInputStream fis = new FileInputStream("league.ser");
		     ObjectInputStream ois = new ObjectInputStream(fis)) {
			league = (League) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return league;
	}



}
