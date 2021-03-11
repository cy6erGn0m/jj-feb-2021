package org.levelp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    public User findByLogin(String login);

    public User findByLoginAndPassword(String login, String password);

    public List<User> findByBirthDateIsBefore(Date referenceDate);

    @Query(name = "findByIsAdmin")
    public List<User> findByIsAdmin(boolean isAdmin);
}
