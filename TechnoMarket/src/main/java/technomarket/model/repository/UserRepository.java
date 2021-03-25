package technomarket.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technomarket.model.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);
    User findByUsername(String username);
}
