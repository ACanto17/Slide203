package edu.byuh.cis.cs203.slide203splash.util;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * This class pumps out "timer" events at
 * regular intervals. Anyone who wants to
 * receive these events can add themselves
 * as "tick listeners".
 */
public class Timer extends Handler {

    private List<TickListener> observers;
    private boolean paused;
    private static Timer singleton;
    private final static int DELAY = 50;

    private Timer() {
        observers = new ArrayList<>();

    }

    public static Timer getInstance() {
        if (singleton == null) {
            singleton = new Timer();
        }
        singleton.sendMessageDelayed(singleton.obtainMessage(0), DELAY);
        return singleton;
    }

    public void subscribe(TickListener t) {
        observers.add(t);
    }

    public void unsubscribe(TickListener t) {
        observers.remove(t);
    }

    public void deregisterAll() {
        observers.clear();
    }


    public void pause() {
        paused = true;
    }

    public void restart() {
        paused = false;
    }

    @Override
    public void handleMessage(Message m) {
        if (!paused) {
            for (TickListener t : observers) {
                t.onTick();
            }
        }
        sendMessageDelayed(obtainMessage(0), DELAY);
    }

    public void destroySingleton() {
        removeMessages(0);
        singleton = null;
    }
}

