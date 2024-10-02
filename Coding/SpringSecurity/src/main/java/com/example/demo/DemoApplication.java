package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserDao userDao) {
		return runner-> {
			// createService(userDao);
			// readUser(userDao);
			// updateUser(userDao);
			// deleteUser(userDao);
		};
	}

	private void deleteUser(UserDao userDao) {
		userDao.delete(2);
	}

	private void updateUser(UserDao userDao) {
		User user = userDao.findByUsername("u1").get(0);
		user.setUsername("updated_u1");
		userDao.update(user);
	}

	private void readUser(UserDao userDao) {
		User u1= userDao.findByUsername("ifeng").get(0);
		System.out.println(u1);
	}

	private void createService(UserDao userDao) {
		User u1 = new User("yhf@gmail.com", "ifeng", "888");
		User u2 = new User("u1@gmail.com", "u1", "888");
		User u3 = new User("u2@gmail.com", "u2", "888");

		userDao.save(u1);
		userDao.save(u2);
		userDao.save(u3);
	}
}
