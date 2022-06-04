package org.syspoe;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener {

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
        if(nativeEvent.getRawCode() == 96 && nativeEvent.getModifiers() == 2) {
            if(AutoClicker.clicker.isRunning) {
                AutoClicker.clicker.stopThread();
                System.out.println("Autoclicker stopped.");
            } else {
                AutoClicker.createClicker();
            }
        } else NativeKeyListener.super.nativeKeyTyped(nativeEvent);
    }
}
