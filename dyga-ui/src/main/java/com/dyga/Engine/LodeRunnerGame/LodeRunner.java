package com.dyga.Engine.LodeRunnerGame;

import com.dyga.Engine.Source.Main.Game;

public class LodeRunner {

	public static void main(String[] args) {

		// Create the game
		Game lodeRunner = new Game("com/dyga/LodeRunner");

		lodeRunner.addJsonViews(new String[] {
			"/Assets/Data/Views/HomeView.json",
		});

		lodeRunner.Run();
	}

}
