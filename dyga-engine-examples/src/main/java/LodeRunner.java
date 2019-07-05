import Scripts.Entities.SteeringBehavior.SB_MadMonk;
import com.dyga.Engine.Source.Components.Component;
import com.dyga.Engine.Source.Components.Physics.Transform;
import com.dyga.Engine.Source.Components.Renderer.SpriteRenderer;
import com.dyga.Engine.Source.MVC.View.Game.Scene;
import com.dyga.Engine.Source.Main.Game;
import com.dyga.Engine.Source.Entity.Entity;
import com.dyga.Engine.Source.Utils.Math.Position2D;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LodeRunner {

	public static void main(String[] args) {
		// Create the game
		Game lodeRunner = new Game("com/dyga/LodeRunner", 60, true);

		lodeRunner.setWrapScreen(true);

		// Create a scene
		Scene fpsView = new Scene();

		// Create a madMonk and add it to the scene
		Entity madMonk = new Entity();
		List<Component> components = Stream.of(
			new Transform(new Position2D(100,100), new Position2D(5, 5)),
			new SpriteRenderer("Assets/Sprites/Enemy/Falls/mad_monk_fall1.png"),
			new SB_MadMonk()
		).collect(Collectors.toList());
		madMonk.addComponents(components);
		fpsView.addEntity(madMonk);


		// Create a wall
		Entity wall = new Entity();
		components = Stream.of(
			new Transform(new Position2D(0,0), new Position2D(1, 1)),
			new SpriteRenderer("Assets/Sprites/Blocs/Ground/bloc_pierre.png")
		).collect(Collectors.toList());
		wall.addComponents(components);
		fpsView.addEntity(wall);


		// Add the scene to game
		lodeRunner.addScene(fpsView);

		// Give JSON view to the game
		// Or give the right folder to do that ?
		/*
		lodeRunner.addJsonViews(new String[] {
			"/Assets/Data/Views/FpsView.json",
			/*
			"/Assets/Data/Views/HomeView.json",
			"Data/Views/HomeView.json",
			"Data/Views/WorldSelectionView.json",
			"Data/Views/CreditsView.json",
			//"Data/Views/SettingsView.json",	// TODO : sliders & co.
			"Data/Views/LevelSelectionView.json",
			"Data/Views/GameView.json",
		});
		*/

		/*
		A level should be equal to a view...
		lodeRunner.addJsonLevels(new String[] {
			"Data/Levels/Level_1-1.json",
		});

		lodeRunner.addJsonEntities(new String[] {
			"Data/Gameplay/Enemy/DogeMasque.json",
		});*/

		// TODO : Should add more stuffs here

		lodeRunner.init();

		lodeRunner.start();

		// useless ??
		//modele.addObserver(gameVue);

		//launchWindow(); // ??



		//MainControler.LoadGameScripts();

		/*
		try {
			MenuLoader.LoadMenuesModels();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}*/
	}

}
