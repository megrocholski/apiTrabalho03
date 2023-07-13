package dw.trabalho03.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dw.trabalho03.model.Product;
import dw.trabalho03.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByName(String name);

	@Query(value = "SELECT * FROM user1 WHERE username =?1 and password =?2", nativeQuery = true)
	Optional<User> login(String username, String password);
	
}
