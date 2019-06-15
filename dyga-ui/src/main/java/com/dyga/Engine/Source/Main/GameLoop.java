package com.dyga.Engine.Source.Main;

import com.dyga.Engine.Source.MVC.Model.MainModel;
import com.dyga.Engine.Source.MVC.View.MainView;

import java.awt.*;

/**
 * This class is inspired by the book Killer Game Programming in Java by Andrew Davison.
 * See http://fivedots.coe.psu.ac.th/~ad/jg/index.html for more information.
 */
public class GameLoop {

    private static final int PWIDTH = 500;   // size of panel
    private static final int PHEIGHT = 400;
    private Thread animator;            // for the animation
    private boolean running = false;    // stops the animation
    private boolean gameOver = false;   // for game termination

    private Graphics dbg;
    private Image dbImage = null;
    private MainModel model;
    private MainView view;

    public void initGameLoop(MainModel model, MainView view) {
        this.model = model;
        this.view = view;

        System.out.println("dd");
    }

    /*
    Need it ?
    public void addNotify() {
        super.addNotify();
        startGame();
    }

    private void startGame() {
        if (animator == null || !running) {
            animator = new Thread(this);
            animator.start();
        }
    }*/




    private void gameRender() {
        if (dbImage == null) {
            dbImage = createImage(PWIDTH, PHEIGHT);
        }
        if (dbImage == null) {
            System.out.println("dbImage is null");
            return;
        } else {
            dbg = dbImage.getGraphics();
        }
        // clear the background
        dbg.setColor(Color.white);
        dbg.fillRect(0, 0, PWIDTH, PHEIGHT);

        // draw game elements
        if (gameOver) {
            //gameOverMessage(dbg);
        }
    }

    private void gameUpdate () {
        if (!gameOver) {
            // update game state ...
        }
    }


    private Image createImage ( int pwidth, int pheight) {
        return null;//new Image();
    }
}
