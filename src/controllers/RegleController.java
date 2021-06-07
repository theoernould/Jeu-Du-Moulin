package controllers;

import java.io.IOException;

import classes.Horizons;

public class RegleController {
	public void retour() throws IOException {
		Horizons.setSceneFromFile("menu", "LineUp3");
	}
}
