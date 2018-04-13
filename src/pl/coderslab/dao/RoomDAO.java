package pl.coderslab.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pl.coderslab.entity.Room;
import pl.coderslab.entity.User;

public class RoomDAO {
//int id;
//	int internalNumber;
//	int peopleLimit;
//	String description;
//	double price;
//	int hotel_id;
	
	
	//CREATE TABLE rooms (id int(11) NOT NULL auto_increment primary key,internalNumber int, peopleLimit int, description varchar(500) COLLATE utf8_polish_ci NOT NULL, price decimal(7,2),hotel_id int(11) NOT NULL, foreign key (hotel_id) references hotels(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

	
	//ZAPYTANIA SQL
	private static final String CREATE_ROOM_QUERY =
	"INSERT INTO rooms(internalNumber,peopleLimit,description, price, hotel_id) VALUES (?,?,?,?,?)";
	private static final String READ_ROOM_QUERY = "Select * from rooms where id = ?";
	private static final String UPDATE_ROOM_QUERY =
	"UPDATE rooms SET internalNumber =? ,peopleLimit = ? ,description = ?, price = ?, hotel_id = ? WHERE id = ?";
	private static final String DELETE_ROOM_QUERY = "DELETE FROM rooms where id = ?";
	private static final String READ_ALL_ROOMS_QUERY = "SELECT * FROM rooms";
	private static final String ASSIGN_ROOM_TO_HOTEL_QUERY = "UPDATE rooms SET hotel_id = ? WHERE id = ?";
	
	public static Room create(Room room) {
		room = new Room();
		//String generatedColumns[] = { "ID" };
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
						connection.prepareStatement(CREATE_ROOM_QUERY);) {
			statement.setInt(1, room.getInternalNumber());
			statement.setInt(2, room.getPeopleLimit());
			statement.setString(3, room.getDescription());
			statement.setDouble(4, room.getPrice());
			statement.setInt(5, room.getHotel_id());				
			statement.executeUpdate();
//			ResultSet rs = statement.getGeneratedKeys();
//			 if (rs.next()) {
//				 room.setId(rs.getInt(1));
//		      }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		return room;
	}
	public static Room[] readAllRooms() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_ALL_ROOMS_QUERY);) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Room room = new Room();
					room.setId(resultSet.getInt("id"));
					room.setInternalNumber(resultSet.getInt("internalNumber"));
					room.setPeopleLimit(resultSet.getInt("peopleLimit"));
					room.setDescription(resultSet.getString("description"));
					room.setPrice(resultSet.getDouble("price"));
					room.setHotel_id(resultSet.getInt("hotel_id"));
					rooms.add(room);
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
			System.out.println("Error");
		}  
		Room[] rArray = new Room[rooms.size()]; 
		rArray = rooms.toArray(rArray);
		for (Room room : rooms) {
            System.out.println(room.toString());
        }
		return rArray;
		
	}
	
	public static void delete(Integer roomId) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
						connection.prepareStatement(DELETE_ROOM_QUERY);) {
			statement.setInt(1, roomId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
	}
	public static Room read(Integer roomId) {
		Room room = new Room();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_ROOM_QUERY);) {
		
			statement.setInt(1, roomId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					room.setId(resultSet.getInt("id"));
					room.setInternalNumber(resultSet.getInt("internalNumber"));
					room.setPeopleLimit(resultSet.getInt("peopleLimit"));
					room.setDescription(resultSet.getString("description"));
					room.setPrice(resultSet.getDouble("price"));
					room.setHotel_id(resultSet.getInt("hotel_id"));
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
			System.out.println("Error");
		}
		return room;
	}

	public static void update(Room room) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
						connection.prepareStatement(UPDATE_ROOM_QUERY);) {
			statement.setInt(6, room.getId());
			statement.setInt(1, room.getInternalNumber());
			statement.setInt(2, room.getPeopleLimit());
			statement.setString(3, room.getDescription());
			statement.setDouble(4, room.getPrice());
			statement.setInt(5, room.getHotel_id());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
	}
	
	//method for assigning room to a hotel ( hotel_id as foreign key!!)
	public static void assignToHotel(Room room, int hotelId) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
						connection.prepareStatement(ASSIGN_ROOM_TO_HOTEL_QUERY);) {
			statement.setInt(2, room.getId());
			statement.setInt(1, hotelId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
	}
	
}


