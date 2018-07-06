package monitor.service;

import monitor.domain.Role;
import monitor.domain.User;
import monitor.repos.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

	@Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }
    
    public User findByGameId(Long gameId) {
        return userRepo.findByGameid(gameId);
    }
    
	public User findUserByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			return null;
		}
		return user;
	}

        
    public boolean addUser(User user, String role) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getUsername() + user.getGameid()));
        user.setRoles(Collections.singleton(Role.valueOf(role)));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setActive(true);
        userRepo.save(user);
       // sendMessage(user);
        return true;
    }
	
	public boolean saveChangesOfUser(User oldman, String role) {
		User newbie = userRepo.findByGameid(oldman.getGameid());
		if (newbie == null) {
			return false;
		}
		newbie.setUsername(oldman.getUsername());
		newbie.setFullname(oldman.getFullname());
		newbie.setDepartment(oldman.getDepartment());
		newbie.setTeam(oldman.getTeam());
		newbie.getRoles().clear();
		newbie.getRoles().add(Role.valueOf(role));
		userRepo.save(newbie);
		return true;
	}

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public boolean passwordUpdate(User user, String password, String confirm) {
    	
    	if (!password.equals(confirm)) {
    		return false;
    	}
    	user.setPassword(passwordEncoder.encode(password));
    	userRepo.save(user);
    	return true;
    }
    
    
}
