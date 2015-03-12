package com.team1;

import com.team1.gui.Controller;
import com.team1.gui.Model;
import com.team1.gui.View;

public class Main {
    public static void main(String args[]) {
        new Controller(new View(), new Model());
    }
}