package com.colini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.colini.db.FabricaConexoes;
import com.colini.models.LoggedUser;
import com.colini.models.Trip;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class TripsDAO {
    
    FabricaConexoes fabrica = FabricaConexoes.getInstance();
    
    public void insertTrip(Trip trip) {
        String sql = "INSERT INTO Trips (trip_name, trip_destination, trip_date, trip_estimated_value, trip_notepad, user_id_fk) VALUES (?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = fabrica.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, trip.getTripName());
            stmt.setString(2, trip.getTripDestination());
            stmt.setString(3, trip.getTripDate());
            stmt.setString(4, trip.getTripEstimatedValue());
            stmt.setString(5, trip.getTripNotepad());
            stmt.setString(6, LoggedUser.getInstance().getUser().getId()); 
    
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateTrip(Trip trip) {
        String sql = "UPDATE Trips SET trip_name = ?, trip_destination = ?, trip_date = ?, trip_estimated_value = ?, trip_notepad = ? WHERE trip_id = ?";
        
        try (Connection conn = fabrica.getConnection(); 
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, trip.getTripName());
            stmt.setString(2, trip.getTripDestination());
            stmt.setString(3, trip.getTripDate());
            stmt.setString(4, trip.getTripEstimatedValue());
            stmt.setString(5, trip.getTripNotepad()); // Add tripNotepad to the update stmt
            stmt.setString(6, trip.getTripId());
            
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTrip(int id) {
        String sql = "DELETE FROM Trips WHERE trip_id = ?";
        
        try (Connection conn = fabrica.getConnection(); 
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Trip> findById(int id){
        String sql = "SELECT * FROM Trips WHERE user_id_fk = ?";
        List<Trip> trips = new ArrayList<>();
        try (Connection conn = fabrica.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = null;

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                String trip_id = rs.getString("trip_id");
                String trip_name = rs.getString("trip_name");
                String trip_destination = rs.getString("trip_destination");
                String trip_date = rs.getString("trip_date");
                String trip_estimated_value = rs.getString("trip_estimated_value");
                String user_id = rs.getString("user_id_fk");
                Trip trip = new Trip(trip_id, trip_name, trip_destination, trip_date, trip_estimated_value, null, user_id);
                System.out.println("hello: "+ trip_id + trip_name + trip_destination + trip_date + trip_estimated_value + null + user_id);
                trips.add(trip);
            }

            return trips;

        } catch (SQLException e) {
            // Tratar exceção
            e.printStackTrace();
        } 
        return trips;
    }

    public void updateTripNotepad(Trip trip) throws SQLException {
        String sql = "UPDATE Trips SET trip_notepad = ? WHERE trip_id =?";
        
        try (Connection conn = fabrica.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
                stmt.setString(1, trip.getTripNotepad());
                stmt.setString(2, trip.getTripId());
                stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void insertUserHasTrip(Trip userHasTrip) {
        String sql = "INSERT INTO UserHasTrip (user_id, trip_id) VALUES (?, ?)";

        try (Connection conn = fabrica.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                 
                 stmt.setString(2, userHasTrip.getUserId());
                 stmt.setString(3, userHasTrip.getTripId());
                 
                 stmt.executeUpdate();
                 
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            public void deleteUserHasTrip(String userId, String tripId) {
                String sql = "DELETE FROM Trips WHERE user_id_fk = ? AND trip_id = ?";
                
                try (Connection conn = fabrica.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                    
                    stmt.setString(1, userId);
                    stmt.setString(2, tripId);
                    
                    stmt.executeUpdate();
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            public void searchTrips(String input, TableView<Trip> tripsTable){
        String sql = "SELECT * FROM Trips WHERE trip_name LIKE ? OR trip_destination LIKE ? OR trip_date LIKE ? OR trip_estimated_value LIKE ? OR trip_notepad LIKE ?";
        ObservableList<Trip> trips = FXCollections.observableArrayList();
        
        try (Connection conn = fabrica.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + input + "%");
            stmt.setString(2, "%" + input + "%");
            stmt.setString(3, "%" + input + "%");
            stmt.setString(4, "%" + input + "%");
            stmt.setString(5, "%" + input + "%");
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                String trip_name = rs.getString("trip_name");
                String trip_destination = rs.getString("trip_destination");
                String trip_date = rs.getString("trip_date");
                String trip_estimated_value = rs.getString("trip_estimated_value");
                String trip_notepad = rs.getString("trip_notepad");
                
                trips.add(new Trip(trip_name,trip_destination,trip_date,trip_estimated_value,trip_notepad, LoggedUser.getInstance().getUser().getId()));
            }
            
            tripsTable.setItems(trips);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void getUpcomingMonthTrips(TableView<Trip> tripsTable) {
        ObservableList<Trip> trips = FXCollections.observableArrayList();
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(30);
        
        String sql = "SELECT * FROM Trips WHERE trip_date BETWEEN ? AND ? ORDER BY trip_date ASC";
        
        try (Connection conn = fabrica.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, today.toString());
            stmt.setString(2, futureDate.toString());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                String trip_name = rs.getString("trip_name");
                String trip_destination = rs.getString("trip_destination");
                String trip_date = rs.getString("trip_date");
                String trip_estimated_value = rs.getString("trip_estimated_value");
                String trip_notepad = rs.getString("trip_notepad");
                
                trips.add(new Trip(trip_name,trip_destination,trip_date,trip_estimated_value,trip_notepad, LoggedUser.getInstance().getUser().getId()));
            }
            
            tripsTable.setItems(trips);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUpcomingYearTrips(TableView<Trip> tripsTable) {
        ObservableList<Trip> trips = FXCollections.observableArrayList();
        int currentYear = LocalDate.now().getYear();
        
        String sql = "SELECT * FROM Trips WHERE YEAR(trip_date) = ? ORDER BY trip_date ASC";
        
        try (Connection conn = fabrica.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, String.valueOf(currentYear));
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                String trip_name = rs.getString("trip_name");
                String trip_destination = rs.getString("trip_destination");
                String trip_date = rs.getString("trip_date");
                String trip_estimated_value = rs.getString("trip_estimated_value");
                String trip_notepad = rs.getString("trip_notepad");
                
                trips.add(new Trip(trip_name,trip_destination,trip_date,trip_estimated_value,trip_notepad, LoggedUser.getInstance().getUser().getId()));
            }
            
            tripsTable.setItems(trips);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String searchLastNote(int id) {
        String sql = "SELECT trip_notepad FROM Trips WHERE trip_id = ?";
        try (Connection conn = fabrica.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("trip_notepad");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public boolean noteExists(int id) {
        String sql = "SELECT trip_notepad FROM Trips WHERE trip_id = ?";
        try (Connection conn = fabrica.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void updateNote(String note, int id) throws SQLException {
        String sql = "UPDATE Trips SET trip_notepad = ? WHERE trip_id = ?";
        try (Connection conn = fabrica.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, note);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
}
