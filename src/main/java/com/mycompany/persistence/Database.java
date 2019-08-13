package com.mycompany.persistence;

import java.sql.*;

import com.mycompany.userInput.*;

public class Database {
    static Connection db = DatabaseUtils.db;

    public static void createTable() throws SQLException {

        DatabaseMetaData dbm = DatabaseUtils.db.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "songs", null);
        if (!rs.next()) {
            Statement createTableStmt = db.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS songs " +
                "(id INT PRIMARY KEY    NOT NULL," +
                "title           TEXT   NOT NULL," +
                "artist          TEXT   NOT NULL," +
                "percentage      REAL)";
            createTableStmt.executeUpdate(createTable);
            createTableStmt.close();
            System.out.println("Table created successfully.");
            addSongDetails();
        } else {
            System.out.println("Table exists already.");
        }
    }

    public static void addSongDetails() throws SQLException {
        Statement addSongsStmt = db.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String addSong1 = "INSERT INTO songs VALUES (0, 'Spaceships', 'Nicki Minaj', 10)";
        String addSong2 = "INSERT INTO songs VALUES (1, 'Dark Horse', 'Katy Perry', 10)";
        String addSong3 = "INSERT INTO songs VALUES (2, 'Enter Sandman', 'Metallica', 10)";
        String addSong4 = "INSERT INTO songs VALUES (3, 'Down with the Sickness', 'Disturbed', 10)";
        String addSong5 = "INSERT INTO songs VALUES (4, 'Some Beach', 'Blake Shelton', 10)";
        String addSong6 = "INSERT INTO songs VALUES (5, 'No Shoes, No Shirt, No Problem', 'Kenny Chesney', 10)";
        String addSong7 = "INSERT INTO songs VALUES (6, 'Symphony #9', 'Beethoven', 10)";
        String addSong8 = "INSERT INTO songs VALUES (7, 'Eine Kleine Nachtmusik', 'Mozart', 10)";
        String addSong9 = "INSERT INTO songs VALUES (8, 'In Da Club', '50 Cent', 10)";
        String addSong10 = "INSERT INTO songs VALUES (9, 'Gin and Juice', 'Snoop Dogg', 10)";
        
        db.setAutoCommit(false);

        addSongsStmt.addBatch(addSong1);
        addSongsStmt.addBatch(addSong2);
        addSongsStmt.addBatch(addSong3);
        addSongsStmt.addBatch(addSong4);
        addSongsStmt.addBatch(addSong5);
        addSongsStmt.addBatch(addSong6);
        addSongsStmt.addBatch(addSong7);
        addSongsStmt.addBatch(addSong8);
        addSongsStmt.addBatch(addSong9);
        addSongsStmt.addBatch(addSong10);

        System.out.println("Added songs.");
      
        ResultSet rs = addSongsStmt.executeQuery("select * from songs");
        rs.last();
        System.out.println("Rows before batch execution= "+ rs.getRow());
        addSongsStmt.executeBatch();
        db.commit();
      
        System.out.println("Batch executed.");
        rs = addSongsStmt.executeQuery("select * from songs");
        rs.last();
        System.out.println("Rows after batch execution = "+ rs.getRow());
    }

    public static boolean clearPercentages() throws SQLException {
        String resetPercentage = "UPDATE songs SET percentage = NULL";
        Statement clearPercentagesStmt = db.createStatement();
        clearPercentagesStmt.executeUpdate(resetPercentage);
        clearPercentagesStmt.close();
        System.out.println("Cleared percentages.");
        return true;
    }

    public static void savePercentages() throws SQLException {
        int[] songs = { 0, 0, 0, 0, 0}; 

        int total = 0;

        if (GetInput.firstResponse) {
            songs[0]++;
            total++;
        }

        if (GetInput.secondResponse) {
            songs[1]++;
            total++;
        }

        if (GetInput.thirdResponse) {
            songs[2]++;
            total++;
        }

        if (GetInput.fourthResponse) {
            songs[3]++;
            total++;
        }

        if (GetInput.fifthResponse) {
            songs[4]++;
            total++;
        }

        db.setAutoCommit(false);

        PreparedStatement prepStmt = db.prepareStatement("UPDATE songs SET percentage = ? WHERE id = ?");
        
        for (int i = 0; i < 5; i++) {
            prepStmt.setInt(1, songs[i]*50/total);
            prepStmt.setInt(2, i*2);
            prepStmt.addBatch();
        
            prepStmt.setInt(1, songs[i]*50/total);
            prepStmt.setInt(2, (i*2)+1);
            prepStmt.addBatch();
        }

        System.out.println("Added percentages.");
        
        prepStmt.executeBatch();
        db.commit();

        System.out.println("Batch executed.");
    }

    public static void returnPercentages() throws SQLException {
        PreparedStatement prepStmt = db.prepareStatement("SELECT * FROM songs");
        ResultSet rs = prepStmt.executeQuery();
        System.out.println("Chances of the following songs playing:");
        while (rs.next()) {
            System.out.print(rs.getString(2));
            System.out.print(" by ");
            System.out.print(rs.getString(3));
            System.out.print(": ");
            System.out.print(rs.getInt(4));
            System.out.println("%.");
        }
    }

}
