package com.citiustech.service.impl;

import com.citiustech.config.proxy.PatientClient;
import com.citiustech.dto.Patient;
import com.citiustech.model.User;
import com.citiustech.model.UserRole;
import com.citiustech.repository.RoleRepository;
import com.citiustech.repository.UserRepository;
import com.citiustech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl  implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PatientClient patientclient;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User local = this.userRepository.findByUsername(user.getUsername());
		if(local !=null){
			System.out.println("User already present");
			throw new Exception("User already present");
		}else{

			for(UserRole userRole : userRoles){
				roleRepository.save(userRole.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);

		}
		//call Patient-visit micro service create patient

		if(local.getRole() != null && local.getRole().equalsIgnoreCase("PATIENT")) {

			Patient p = new Patient();
			p.setId(local.getId());
			p.setFirstName(local.getFirstName());
			p.setFirstName(local.getLastName());
			p.setEmail(local.getEmail());
			patientclient.register(p);

		}      
		return local;
	}
	//getting user by username
	@Override
	public User getUser(String username) {

		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public List<User> listOfUsers() {
		return this.userRepository.findAll();
	}
	@Override
	public User getUserById(Long id) {
		
		return this.userRepository.findById(id).get();
	}

}
