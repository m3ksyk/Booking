package pl.coderslab.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pl.coderslab.entity.User;

public class UserDAO {
	// ZAPYTANIA SQL
	private static final String CREATE_USER_QUERY =
	"INSERT INTO users(name,email,password,user_group_id) VALUES (?,?,?,?)";
	private static final String READ_USER_QUERY = "Select * from users where id = ?";
	private static final String UPDATE_USER_QUERY =
	"UPDATE users SET name = ? , email = ?, password = ?, user_group_id =? WHERE id = ?";
	private static final String DELETE_USER_QUERY = "DELETE FROM users where id = ?";
	private static final String READ_ALL_USERS_QUERY = "SELECT * FROM users";
	private static final String READ_USER_BY_MAIL_QUERY = "Select * from users where email = ?";
	private static final String READ_USER_BY_GROUP_QUERY = "Select * from users where user_group_id = ?";
	private static final String ADD_USER_TO_GROUP_QUERY = "UPDATE users SET user_group_id =? WHERE id = ?";
	
//	Dodawanie użytkownika.
	public static User create(User user) {
		user = new User();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
				connection.prepareStatement(CREATE_USER_QUERY);) {
					statement.setInt(6, user.getId());
					statement.setString(1, user.getName());
					statement.setString(3, user.getEmail());
					statement.setString(4, user.getPassword());
					statement.setInt(5, user.getUserGroupId());
					statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		return user;
	}
	//dodanie do grupy
	public static void addToGroup(User user) {
			try (Connection connection = DbUtil.getConnection();
					PreparedStatement statement =
							connection.prepareStatement(ADD_USER_TO_GROUP_QUERY);) {
				statement.setInt(2, user.getId());
				statement.setInt(1, user.getUserGroupId());
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error");
			}
	}
	
//	Pobieranie użytkownika po adresie email.
	public static User readByMail(String email) {
		User user = new User();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_USER_BY_MAIL_QUERY);) {
			statement.setString(1, email);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user.setId(resultSet.getInt("id"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
					user.setUserGroupId(resultSet.getInt("user_group_id")); 
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
			System.out.println("Error");
		}
		return user;
	}
//	Pobieranie listy wszystkich użytkowników.
	public static User[] readAllUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_ALL_USERS_QUERY);) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					User user = new User();
				    user.setId(resultSet.getInt("id"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
					user.setUserGroupId(resultSet.getInt("user_group_id"));
				    users.add(user);
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
			System.out.println("Error");
		}  
		    
		User[] uArray = new User[users.size()]; 
		uArray = users.toArray(uArray);
		for (User user : users) {
            System.out.println(user.toString());
        }
		return uArray;
			
	}
	
//	Pobranie użytkownika, należącego do określonej grupy.
	public static User readByGroup(int groupId) {
		User user = new User();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_USER_BY_GROUP_QUERY);) {
			statement.setInt(1, groupId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user.setId(resultSet.getInt("id"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
					user.setUserGroupId(resultSet.getInt("user_group_id")); 
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
			System.out.println("Error");
		}
		return user;
	}
	
	public static void delete(Integer userId) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
						connection.prepareStatement(DELETE_USER_QUERY);) {
			statement.setInt(1, userId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
		}
	}
	
	public static User read(Integer userId) {
		User user = new User();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);) {
			statement.setInt(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user.setId(resultSet.getInt("id"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
					user.setUserGroupId(resultSet.getInt("user_group_id")); 
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
			System.out.println("Error");
		}
		return user;
	}
	
	public static void update(User user) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
						connection.prepareStatement(UPDATE_USER_QUERY);) {
			statement.setInt(6, user.getId());
			statement.setString(1, user.getName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getUserGroupId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
	}
}
//	CREATE TABLE `users` (`id` int(11) NOT NULL auto_increment primary key, `name` 
//varchar(500) COLLATE utf8_polish_ci NOT NULL,`email` varchar(500) COLLATE utf8_polish_ci
//NOT NULL,`password` varchar(500) COLLATE utf8_polish_ci NOT NULL,`user_group_id` int(11) NOT NULL, 
//foreign key(user_group_id) references groups(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

