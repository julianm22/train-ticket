package dao;

import model.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class TrainDAO {
    private static final String driverName = "";
    private static final String DB_URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD= "";

    public static Train findTrain(int trainNo)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        Train train = null;
        try {
            con = new MyConnection().getMyConnection();
            // "select * from trains where train_no = ?"
            String query = "select * from trains where train_no = "+trainNo;
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while ( rs.next() ) {
                train = new Train();
                train.setTrainNo(rs.getInt("train_no"));
                train.setTrainName(rs.getString("train_name"));
                train.setSource(rs.getString("source"));
                train.setDestination(rs.getString("destination"));
                train.setTicketPrice(rs.getDouble("ticket_price"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return train;
    }








}
