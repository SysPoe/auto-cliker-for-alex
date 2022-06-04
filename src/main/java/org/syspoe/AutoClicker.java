package org.syspoe;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AutoClicker {
    public static Clicker clicker;
    public static long msSleep;
    public static int nsSleep;

    public static void main(String[] args) {
        regClicker();
        try {
            GlobalScreen.registerNativeHook();
        } catch( NativeHookException ex) {
            System.err.println("There was an error registering the native hook for global key listeners.");
            System.err.println("----- TRACE -----");
            ex.printStackTrace();
            System.err.println("----- REASON -----");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new KeyListener());
    }
    public static void createClicker() {
        clicker = new Clicker(msSleep, nsSleep);
        clicker.start();
        System.out.println("Clicker started! Press [Ctrl + `] To stop!");
    }
    public static void regClicker() {
        long msSleep = -1;
        int nsSleep = -1;
        while (msSleep == -1 && nsSleep == -1) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Sleep time (ms) ==> ");
                msSleep = Long.parseLong(reader.readLine());
                System.out.print("Sleep time (ns) ==> ");
                nsSleep = Integer.parseInt(reader.readLine());
                if(msSleep == 0) nsSleep = Math.max(nsSleep, 1);
            } catch (NumberFormatException ex) {
                System.out.println("Failed to parse number!");
                msSleep = -1;
                nsSleep = -1;
            } catch (IOException ignored) {}
        }
        AutoClicker.msSleep = msSleep;
        AutoClicker.nsSleep = nsSleep;
        createClicker();
    }
}
