package com.mycompany.app;

import java.sql.SQLException;

import com.mycompany.persistence.*;
import com.mycompany.userInput.*;

public class App {

    public static void main(String[] args) throws SQLException {
        if (DatabaseUtils.connect()) {
            Database.createTable();

            boolean recalcPercentages = GetInput.getUserGoal();

            if (recalcPercentages) {
                Database.clearPercentages();
                GetInput.getSongInputs();
                Database.savePercentages();
                Database.returnPercentages();
            } else {
                Database.returnPercentages();
            }
        } else {
            System.out.println("Didn't work");
        }
    }
}