package pl.coderslab.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pl.coderslab.entity.Status;

public class StatusDAO {
	// SQL queries
	private static final String CREATE_STATUS_QUERY =
	"INSERT INTO status(reservationStatus) VALUES (?)";
	private static final String READ_STATUS_QUERY = "Select * from status where id = ?";
	private static final String UPDATE_STATUS_QUERY =
	"UPDATE status SET reservationStatus = ? WHERE id = ?";
	private static final String DELETE_STATUS_QUERY = "DELETE FROM status where id = ?";
	private static final String READ_ALL_STATUSES_QUERY = "SELECT * FROM status";
	
	public Status create() {
		Status status = new Status();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
						connection.prepareStatement(CREATE_STATUS_QUERY);) {
			statement.setInt(1, status.getReservationStatus());	
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		return status;
	}
	
	public Status[] readAllStatuses(Integer statusId) {
		ArrayList<Status> statuses = new ArrayList<Status>();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_ALL_STATUSES_QUERY);) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Status status = new Status();
					status.setId(resultSet.getInt("id"));
					status.setReservationStatus(resultSet.getInt("reservationStatus"));
					statuses.add(status);
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
			System.out.println("Error");
		}  
		Status[] sArray = new Status[statuses.size()]; 
		sArray = statuses.toArray(sArray);
		for (Status status : statuses) {
            System.out.println(status.toString());
        }
		return sArray;
		
	}
	public void delete(Integer statusId) {
		try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement =
			connection.prepareStatement(DELETE_STATUS_QUERY);) {			
			statement.setInt(1, statusId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
	}
	
	public Status read(Integer statusId) {
		Status status = new Status();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_STATUS_QUERY);) {
				statement.setInt(1, statusId);
		
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					status.setId(resultSet.getInt("id"));
					status.setReservationStatus(resultSet.getInt("id"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		return status;
	}
	
	public void update(Status status) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
						connection.prepareStatement(UPDATE_STATUS_QUERY);) {
			statement.setInt(2, status.getId());
			statement.setInt(1, status.getReservationStatus());			
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
	}

}

//CREATE TABLE status (id int(11) NOT NULL primary key auto_increment,reservation_status 
//tinyint NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
