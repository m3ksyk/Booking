package pl.coderslab.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pl.coderslab.entity.Group;

public class GroupDAO {
	//SQL queries
	private static final String CREATE_GROUP_QUERY =
	"INSERT INTO groups(name) VALUES (?)";
	private static final String READ_GROUP_QUERY = "Select * from groups where id = ?";
	private static final String UPDATE_GROUP_QUERY =
	"UPDATE groups SET name = ? WHERE id = ?";
	private static final String DELETE_GROUP_QUERY = "DELETE FROM groups where id = ?";
	private static final String READ_ALL_GROUPS_QUERY = "SELECT * FROM groups";
	
	public static Group create(Group group) {
		group = new Group();
	String generatedColumns[] = { "ID" };
	try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement =
			connection.prepareStatement(CREATE_GROUP_QUERY);) {
			statement.setString(1, group.getName());			
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			 if (rs.next()) {
				 group.setId(rs.getInt(1));
		      }
			} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Coś się nie powiodło.");
			}
		return group;
	
	}
	
	public static Group[] readAllGroups() {
		ArrayList<Group> groups = new ArrayList<Group>();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_ALL_GROUPS_QUERY);) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Group group = new Group();
					group.setId(resultSet.getInt("id"));					
					groups.add(group);
				}
			}
		} catch (Exception e) { e.printStackTrace();
			System.out.println("Coś się nie powiodło.");
		}  
		Group[] gArray = new Group[groups.size()]; 
		gArray = groups.toArray(gArray);
		return gArray;
		
	}
	
	public static void delete(Integer groupId) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
						connection.prepareStatement(DELETE_GROUP_QUERY);) {
			
			statement.setInt(1, groupId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Coś się nie powiodło.");
		}
	}
	
	public Group read(Integer groupId) {
		Group group = new Group();
		try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(READ_GROUP_QUERY);) {
			statement.setInt(1, groupId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					group.setId(resultSet.getInt("id"));
					group.setName(resultSet.getString("name"));
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
			System.out.println("Coś się nie powiodło.");
		}
		return group;
	}
	
	public static void update(Group group) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement =
						connection.prepareStatement(UPDATE_GROUP_QUERY);) {
			
			statement.setInt(2, group.getId());
			statement.setString(1, group.getName());
			statement.executeUpdate();
		} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Coś się nie powiodło.");
		}
	}
	//viewing users in group is already in user, no need to implement it here again
}

//CREATE TABLE `groups` (
//`id` int(11) NOT NULL,
//`name` varchar(500) COLLATE utf8_polish_ci NOT NULL,
// ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;