package me.ai;

import me.ai.repository.Repository;
import me.ai.service.Service;
import me.ai.ui.Console;

public class Main {

    public static void main(String[] args) {
        Repository repo = new Repository();
        Service serv = new Service(repo);
        Console ui = new Console(serv);

        ui.run();
    }
}
