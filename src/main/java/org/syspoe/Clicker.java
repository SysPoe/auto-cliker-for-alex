package org.syspoe;

import java.awt.*;
import java.awt.event.InputEvent;

public class Clicker extends Thread {
    private final long msSleep;
    private final int nsSleep;
    public boolean isRunning = true;
    Clicker(long msSleep, int nsSleep) {
        this.msSleep = msSleep;
        this.nsSleep = nsSleep;
    }

    @Override
    public void run() {
        try {
            Robot bot = new Robot();
            while(true) {
                try {
                    if(!isRunning) return;
                    Thread.sleep(msSleep, nsSleep);
                    bot.mousePress(InputEvent.BUTTON1_MASK);
                    bot.mouseRelease(InputEvent.BUTTON1_MASK);
                } catch (InterruptedException ignored) {}
            }
        } catch(AWTException ignored) {}
    }

    public void stopThread() {
        isRunning = false;
    }
}
