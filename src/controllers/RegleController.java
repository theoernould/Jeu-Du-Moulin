package controllers;

import java.io.IOException;

import horizons_ihm.Horizons;

public class RegleController {
	public void retour() throws IOException {
		Horizons.setSceneFromFile("menu", "LineUp3");
	}
}
