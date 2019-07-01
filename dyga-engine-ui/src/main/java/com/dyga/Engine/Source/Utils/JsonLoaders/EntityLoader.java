package com.dyga.Engine.Source.Utils.JsonLoaders;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dyga.Engine.Source.MVC.Model.Game.EntityModel;
import com.dyga.Engine.Source.MVC.Model.Game.Structs.Stats;
import com.dyga.Engine.Source.Utils.Math.Position2D;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class EntityLoader extends JsonLoader<EntityModel> {

    @Override
    public EntityModel parse(String path) {
        EntityModel entity = null;
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        try {
            FileReader fileReader = new FileReader(url.getPath());
            entity = createNewEntity(fileReader);
        } catch (IOException | ParseException e) {
            System.out.println("Cannot load the following json file = " + path);
            entity = null;
        }
        return entity;
    }

    private EntityModel createNewEntity(FileReader fileReader) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject entityObject = (JSONObject) parser.parse(fileReader);

        String name = (String)entityObject.get("name");
        String imageUrl = (String)entityObject.get("sprite");
        Stats stats = assignStats((JSONObject)entityObject.get("stats"));
        Position2D position = assignPosition((JSONArray) entityObject.get("spawnPoint"));

        System.out.println((JSONObject)entityObject.get("stats"));
        System.out.println((JSONArray)entityObject.get("animations"));
        System.out.println((JSONArray)entityObject.get("inventory"));

        EntityModel entity = new EntityModel(name, imageUrl, stats, position);

        System.out.println(entity);

        return entity;
    }

    private static Stats assignStats(JSONObject jsonObject) {
        Stats stats = new Stats();
        stats.maxHealth = Math.toIntExact((long)jsonObject.get("maxHealth"));
        stats.currentHealth = Math.toIntExact((long)stats.maxHealth);
        stats.maxSpeed = (long)jsonObject.get("maxSpeed");
        stats.currentSpeed = 0.0f;
        return stats;
    }

    private static Position2D assignPosition(JSONArray jsonArray) {
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);

        Position2D position = new Position2D();
        position.setLocation(Math.toIntExact((Long) jsonObject.get("x")), Math.toIntExact((Long) jsonObject.get("y")));
        return position;
    }
}
