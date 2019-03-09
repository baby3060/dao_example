package net.example.model;

/**
 * CREATE TABLE `user` (
 *   `user_id` varchar(10) NOT NULL,
 *   `pass_wd` varchar(15) DEFAULT NULL,
 *   `name` varchar(10) DEFAULT NULL,
 *   `age` int(11) DEFAULT NULL,
 *   PRIMARY KEY (`user_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class User {
	private String user_id;
	private String pass_wd;
	private String name;
	private int age;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPass_wd() {
		return pass_wd;
	}
	public void setPass_wd(String pass_wd) {
		this.pass_wd = pass_wd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
