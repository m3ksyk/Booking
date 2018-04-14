package pl.coderslab.entity;

import java.util.Arrays;

public class Group {
	int id;
	String name;
	User[] users;
	
	
	public Group(String name, User[] users) {
		this.name = name;
		this.users = users;
	}
	public Group() {
	
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User[] getUsers() {
		return users;
	}
	public void setUsers(User[] users) {
		this.users = users;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", users=" + Arrays.toString(users) + "]";
	}
	
}
