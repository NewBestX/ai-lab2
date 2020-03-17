package me.ai.ui;

import me.ai.service.Service;

import java.io.IOException;
import java.util.Scanner;

public class Console {
    private Service serv;

    public Console(Service serv) {
        this.serv = serv;
    }

    public void run() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Introduceti numele fisierului din care va fi citita matricea, fara extensie (se considera implicit extensia .txt)");
        System.out.println("Fisierul trebuie sa se afle in folderul /src/data.");

        while(true) {
            System.out.println("Fisier:");
            String input = keyboard.nextLine();

            if(input.isEmpty())
                break;

            try {
                serv.generateSolution(input);
                System.out.println("Solutia a fost generata!");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println("Introduceti un nou fisier daca doriti generarea unei alte solutii.");
        }
    }
}
