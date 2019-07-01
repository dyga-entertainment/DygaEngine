package com.dyga.Engine.Source.MVC.Model.Game;

import com.dyga.Engine.Source.MVC.Model.Game.Structs.Stats;
import java.util.Dictionary;

public class MovingEntity {

    /** The name of the entity (used as id for JSON) */
    private String name;

    /** image associated to the entity */
    private String profilImage;

    /** All the stats that belong to the entity */
    private Stats stats;

    /** The current inventory of the entity. This contains item usable or not. */
    private Dictionary<Item, Integer> inventory;

    /// OLD ////

    /** tempsDeJeu */
    //private Time tempsDeJeu; ??

    /** Hummm... */
    private int unlockedWorld;
    /** Hummm.... unlockLevel */
    private int unlockedLevel;
}
