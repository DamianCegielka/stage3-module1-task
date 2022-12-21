package com.mjc.school;


import com.mjc.school.controller.Controller;
import com.mjc.school.controller.impl.ControllerImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Controller mainController = new ControllerImpl();
        mainController.mainController();
    }
}