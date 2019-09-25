package com.stack.respository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.stack.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public	User findByEmailAndPassword(String mobileNo, String password);
}
