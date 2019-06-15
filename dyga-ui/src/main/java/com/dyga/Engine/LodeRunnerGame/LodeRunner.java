package com.dyga.Engine.LodeRunnerGame;

import com.dyga.Engine.Source.Main.Game;

public class LodeRunner {



	public static void main(String[] args) {

		int targetFPS = 60;

		// Create the game
		Game lodeRunner = new Game("com/dyga/LodeRunner", targetFPS, true);

		lodeRunner.addJsonViews(new String[] {
			"com/dyga/Engine/LodeRunnerGame/Assets/Data/Views/HomeView.json",
		});

		lodeRunner.run();
	}

}
