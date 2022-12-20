package com.mjc.school.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mjc.school.service.Service;
import com.mjc.school.service.impl.ServiceImpl;

public class ControllerImpl implements Controller {

    private static final String MENU_TEXT =
            """
                    Enter the number of operation:
                    1 - Get all news.
                    2 - Get news by id.
                    3 - Create news.
                    4 - Update news.
                    5 - Remove news by id.
                    0 - Exit.
                    """;

    private int chosenNumber = -1;

    @Override
    public void mainController(){

        Service service = new ServiceImpl();
        service.loadAllData();
        try {
            while (chosenNumber != 0) {
                System.out.println(MENU_TEXT);
                chosenNumber = takeNumberFromKeyboard();

                switch (chosenNumber) {
                    case 1 -> service.readAllNews();
                    case 2 -> service.readNewsById();
                    case 3 -> service.createNews();
                    case 4 -> service.updateNews();
                    case 5 -> service.removeNews();
                    case 0 -> System.out.println("By by!");
                    default -> System.out.println("Error!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int takeNumberFromKeyboard() throws IOException {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(bufferedreader.readLine());
    }


}
