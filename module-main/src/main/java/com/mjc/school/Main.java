package com.mjc.school;


import com.mjc.school.controller.Controller;
import com.mjc.school.controller.ControllerImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Controller controller= new ControllerImpl();
        controller.mainController();
    }
}