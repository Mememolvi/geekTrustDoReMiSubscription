package com.example.geektrust;

import com.example.geektrust.applicationsetup.ApplicationSetup;
import com.example.geektrust.command.CommandInvoker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        args = new String[1];
//        args[0] = "sample_input/input1.txt";
        ApplicationSetup applicationSetup = ApplicationSetup.getInstanceOfApplicationSetup();
        CommandInvoker commandInvoker = applicationSetup.getCommandInvoker();
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                List<String> commandParts = Arrays.asList(command.split(" "));
                commandInvoker.executeCommand(commandParts.get(0), commandParts);
            }

            sc.close(); // closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
