package service;

import org.springframework.stereotype.Service;

import dao.UserDAO;
import exception.UserNotFoundException;
import model.User;

@Service
public class UserService {
	
	private final UserDAO userDAO;
	
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User create(User user) throws UserNotFoundException {
		User u = this.userDAO.findById(user.getId());
		
		if(u == null) {
			throw new UserNotFoundException("User not found!");
		}
		
		
		return this.userDAO.save(user);
	}
	

	public User findByLogin(String login) throws UserNotFoundException{
		User user = this.userDAO.findByLogin(login);
		
		if(user == null) {
			throw new UserNotFoundException("User not found!");
		}
		
		return user;
	}

}
