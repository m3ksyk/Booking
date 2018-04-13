package pl.coderslab.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.coderslab.entity.Reservation;

public class ReservationDAO {

	//SQL queries
	private static final String CREATE_RESERVATION_QUERY =
	"INSERT INTO reservations(applyDate, fromDate, toDate, description, user_id, status_id) VALUES (?,?,?,?,?,?)";
	private static final String READ_RESERVATION_QUERY = "Select * from reservations where id = ?";
	private static final String UPDATE_RESERVATION_QUERY =
	"UPDATE reservations SET applyDate = ? , fromDate = ?, toDate = ?, description = ? , user_id = ?, status_id = ? WHERE id = ?";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM reservations where id = ?";
	private static final String READ_ALL_RESERVATION_QUERY = "SELECT * FROM reservations";
	private static final String UPDATE_RESERVATION_STATUS_QUERY = "UPDATE reservations SET status_id = ? WHERE id = ?";;
	private static final String READ_RESERVATION_DESCRIPTION_QUERY = "Select description from reservations where id = ?";

	//sprawdzic czy to wprowadzanie id dziala
	public static Reservation create(Reservation reservation) {
		reservation = new Reservation();
		String generatedColumns[] = { "ID" };
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
				connection.prepareStatement(CREATE_RESERVATION_QUERY);) {
				statement.setString(1, reservation.getApplyDate());
				statement.setString(2, reservation.getFromDate());
				statement.setString(3, reservation.getToDate());
				statement.setString(4, reservation.getDescription());
				statement.setInt(5, reservation.getUser_id());
				statement.setInt(6, reservation.getStatus_id());
				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				 if (rs.next()) {
			    	  reservation.setId(rs.getInt(1));
			      }
				} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Coś się nie powiodło.");
				}
		return reservation;
	}
	public static void delete(Integer reservationId) {
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement =
		connection.prepareStatement(DELETE_RESERVATION_QUERY);) {
		statement.setInt(1, reservationId);
		statement.executeUpdate();
		} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Coś się nie powiodło.");
		}
	}
	public static Reservation read(Integer reservationId) {
		Reservation reservation = new Reservation();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_RESERVATION_QUERY);) {
			statement.setInt(1, reservationId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					reservation.setId(resultSet.getInt("id"));
					reservation.setApplyDate(resultSet.getString("applyDate"));
					reservation.setFromDate(resultSet.getString("fromDate"));
					reservation.setToDate(resultSet.getString("toDate"));
					reservation.setDescription(resultSet.getString("description"));
					reservation.setUser_id(resultSet.getInt("user_id"));
					reservation.setStatus_id(resultSet.getInt("status_id"));
				}
			}
		} catch (Exception e) { e.printStackTrace();
		System.out.println("Coś się nie powiodło.");}
		return reservation;
	}
	public static Reservation readDescription(Integer reservationId) {
		Reservation reservation = new Reservation();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_RESERVATION_DESCRIPTION_QUERY);) {
			statement.setInt(1, reservationId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					reservation.setDescription(resultSet.getString("description"));
				}
			}
		} catch (Exception e) { e.printStackTrace();
		System.out.println("Coś się nie powiodło.");
		}
		return reservation;
	}
	public static Reservation[] readAllReservations() {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_ALL_RESERVATION_QUERY);) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Reservation reservation = new Reservation();
					reservation.setId(resultSet.getInt("id"));
					reservation.setApplyDate(resultSet.getString("applyDate"));
					reservation.setFromDate(resultSet.getString("fromDate"));
					reservation.setToDate(resultSet.getString("toDate"));
					reservation.setDescription(resultSet.getString("description"));
					reservation.setUser_id(resultSet.getInt("user_id"));
					reservation.setStatus_id(resultSet.getInt("status_id"));
					reservations.add(reservation);
				}
			}
		} catch (Exception e) { e.printStackTrace();
			System.out.println("Coś się nie powiodło.");
		}  
		Reservation[] rArray = new Reservation[reservations.size()]; 
		rArray = reservations.toArray(rArray);
		return rArray;
		
	}
	
	public static void update(Reservation reservation) {
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement =
		connection.prepareStatement(UPDATE_RESERVATION_QUERY);) {
		statement.setInt(7, reservation.getId());
		statement.setString(1, reservation.getApplyDate());
		statement.setString(2, reservation.getFromDate());
		statement.setString(3, reservation.getToDate());
		statement.setString(4, reservation.getDescription());
		statement.setInt(5, reservation.getUser_id());
		statement.setInt(6, reservation.getStatus_id());
		statement.executeUpdate();
		} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Coś się nie powiodło.");
		}
	}
	public static void updateStatus(Reservation reservation) {
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement =
		connection.prepareStatement(UPDATE_RESERVATION_STATUS_QUERY);) {
		statement.setInt(2, reservation.getId());
		statement.setInt(1, reservation.getStatus_id());
		statement.executeUpdate();
		} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Coś się nie powiodło.");
		}
	}
}

//CREATE TABLE `reservations` (
//`id` int(11) NOT NULL,
//`applyDate datetime, fromDate datetime, toDate datetime, description text, user_id int, status_id int
//) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;