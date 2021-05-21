package com.flicklist.service.user;

import com.flicklist.model.User;
import com.flicklist.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;


	@Override
	public User findByUsernameAndPassword(String username, String password) {
		//TODO make sure username is unique
		return userRepository.findByUsernameAndPassword(username,password);

	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(String id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User create(User user) {
		if (user.getId() == null) {
			try{
				return userRepository.save(user);
			}catch(Exception exception){
				//Exception when  username or email already exists
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public User update(User user){
		if(user.getId() == null){
			return null;
		}else{
			try{
				return userRepository.save(user);
			}catch(Exception exception){
				//Exception when  username or email already exists
				return null;
			}
		}
	}

	@Override
	public long delete(User user) {
		return userRepository.removeById(user.getId());
	}
}
