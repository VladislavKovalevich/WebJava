
package org.labs.wt.tour;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.labs.wt.tour.command.CommandProcessor;

import java.util.Scanner;


public class TourAgencyAppl {

    private static final Logger LOGGER = LogManager.getLogger(TourAgencyAppl.class);


    public static void main(String args[]) {

        LOGGER.info("tour agency application started");

        System.out.println("Вас приветствует турагенство (exit/help): ");

        Scanner sc = new Scanner(System.in);

        System.out.print("> ");
        String str = sc.nextLine();

        while(!str.equals("exit")) {
            CommandProcessor.getInstance().processCommand(str);
            System.out.print("> ");
            str = sc.nextLine();
        }

        LOGGER.info("tour agency application stopped");
    }

}
