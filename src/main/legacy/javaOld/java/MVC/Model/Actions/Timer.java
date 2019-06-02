package java.MVC.Model.Actions;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.MVC.Model.bloc.Bloc;
import java.MVC.Model.jeu.Positionnable;

/**
 * @author jarvis
 */
public class Timer extends Observable implements Runnable {
    private int timeSleep;
    private Positionnable element;

    public Timer(int millisecond, Positionnable p) {
        this.timeSleep = millisecond;
        this.element = p;
    }

    @Override
    public void run() {
        try {
            System.out.println("Pause");
            Thread.sleep(this.timeSleep);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setChanged();
        this.notifyObservers(this.element);
        this.clearChanged();
    }
}
