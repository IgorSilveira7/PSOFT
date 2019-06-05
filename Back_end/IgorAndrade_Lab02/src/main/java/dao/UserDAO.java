package dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Product;
import model.User;

@Repository
public interface UserDAO extends JpaRepository<User, String> {
	
	User save(User user);
	
	User findById(long id);
	
	@Query(value="Select u from User u where u.login=:plogin")
	User findByLogin(@Param("plogin") String login);
}
