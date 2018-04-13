package pl.coderslab.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pl.coderslab.entity.Hotel;

public class HotelDAO {
	//ZAPYTANIA SQL
	private static final String CREATE_HOTEL_QUERY =
	"INSERT INTO hotels(name,address,phoneNumber,petsAllowed, description) VALUES (?,?,?,?,?)";
	private static final String READ_HOTEL_QUERY = "Select * from hotels where id = ?";
	private static final String UPDATE_HOTEL_QUERY =
	"UPDATE hotels SET name = ? , address = ? , phoneNumber = ? , petsAllowed = ?, description = ? WHERE id = ?";
	private static final String DELETE_HOTEL_QUERY = "DELETE FROM hotels where id = ?";
	private static final String READ_ALL_HOTELS_QUERY = "SELECT * FROM hotels";

	public static Hotel create(Hotel hotel) {
		hotel = new Hotel();
		String generatedColumns[] = { "ID" };
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
				connection.prepareStatement(CREATE_HOTEL_QUERY);) {
				statement.setString(1, hotel.getName());
				statement.setString(2, hotel.getAddress());
				statement.setString(3, hotel.getPhoneNumber());
				statement.setInt(4, hotel.isPetsAllowed());
				statement.setString(5, hotel.getDescription());				
				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				 if (rs.next()) {
					 hotel.setId(rs.getInt(1));
			      }
				} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Coś się nie powiodło.");
				}
		return hotel;
	}
	public static Hotel[] readAllHotels() {
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_ALL_HOTELS_QUERY);) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Hotel hotel = new Hotel();
					hotel.setId(resultSet.getInt("id"));
					hotel.setName(resultSet.getString("name"));
					hotel.setDescription(resultSet.getString("description"));
					hotel.setPhoneNumber(resultSet.getString("phoneNumber"));
					hotel.setPetsAllowed(resultSet.getInt("petsAllowed"));
					hotels.add(hotel);
				}
			}
		} catch (Exception e) { e.printStackTrace();
			System.out.println("Coś się nie powiodło.");
		}  
		Hotel[] hArray = new Hotel[hotels.size()]; 
		hArray = hotels.toArray(hArray);
		return hArray;
		
	}
	
	public static void delete(Integer hotelId) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
						connection.prepareStatement(DELETE_HOTEL_QUERY);) {
			statement.setInt(1, hotelId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Coś się nie powiodło.");
		}
	}
	public Hotel read(Integer hotelId) {
		Hotel hotel = new Hotel();
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(READ_HOTEL_QUERY);) {
		statement.setInt(1, hotelId);
		try (ResultSet resultSet = statement.executeQuery()) {
		while (resultSet.next()) {
			hotel.setId(resultSet.getInt("id"));
			hotel.setName(resultSet.getString("name"));
			hotel.setDescription(resultSet.getString("description"));
			hotel.setPhoneNumber(resultSet.getString("phoneNumber"));
			hotel.setPetsAllowed(resultSet.getInt("petsAllowed")); }}
		} catch (Exception e) { e.printStackTrace();
		System.out.println("Coś się nie powiodło.");
		}
		return hotel;
	}

	public static void update(Hotel hotel) {
	try (Connection connection = DbUtil.getConnection();
	PreparedStatement statement =
	connection.prepareStatement(UPDATE_HOTEL_QUERY);) {
	statement.setInt(6, hotel.getId());
	statement.setString(1, hotel.getName());
	statement.setString(2, hotel.getAddress());
	statement.setString(3, hotel.getPhoneNumber());
	statement.setInt(4, hotel.isPetsAllowed());
	statement.setString(5, hotel.getDescription());
	statement.executeUpdate();
	} catch (Exception e) {
	e.printStackTrace();
	System.out.println("Coś się nie powiodło.");
	}
	}
}

//CREATE TABLE `hotels` (
//`id` int(11) NOT NULL,
//`name` varchar(500) COLLATE utf8_polish_ci NOT NULL,
//`address` varchar(500) COLLATE utf8_polish_ci NOT NULL,
//`description` varchar(500) COLLATE utf8_polish_ci NOT NULL,
//'phoneNumber' varchar(25),
// 'petsAllowed' tinyint
//) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;