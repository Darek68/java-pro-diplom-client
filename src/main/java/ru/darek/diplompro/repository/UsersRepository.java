package ru.darek.diplompro.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.darek.diplompro.entities.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

 //  List<User> findAllByAdminEquals(boolean isAdmin);
   List<User> findAllByAdmin(boolean admin);

}
