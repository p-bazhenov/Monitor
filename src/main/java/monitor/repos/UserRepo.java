package monitor.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import monitor.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    
	User findByUsername(String username);

    User findByActivationCode(String code);

	User findByGameid(Long gameid);

}
