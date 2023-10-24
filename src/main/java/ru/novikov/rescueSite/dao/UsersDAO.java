package ru.novikov.rescueSite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.novikov.rescueSite.models.Post;
import ru.novikov.rescueSite.models.User;
import ru.novikov.rescueSite.models.UsersMissions;

import java.util.List;

@Component
public class UsersDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> showAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public void creteUser(User user) {
        jdbcTemplate.update("INSERT INTO users(name, surname, patronymic, hometown, age)" +
                "VALUES  (?, ?, ?, ?, ?)", user.getName(), user.getSurname(), user.getPatronymic(),
                user.getHometown(), Integer.parseInt(user.getAge()));
    }

    public User getUser(int id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).
                stream().findAny().orElse(null);
    }

    public void updateUserPersonalData(User user) {
        jdbcTemplate.update("UPDATE users " +
                "SET name = ?, surname = ?, patronymic = ?, hometown = ?,  age = ?" +
                "WHERE id = ?", user.getName(), user.getSurname(), user.getPatronymic(), user.getHometown(), Integer.parseInt(user.getAge()),
                user.getId());
    }

    public void assignUserToMission(int missionId, User selectedUser) {
        jdbcTemplate.update("INSERT INTO userOperations(userId, postId) VALUES (?, ?)", selectedUser.getId(), missionId);
    }

    public void deleteUser(int userId) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", userId);
    }

    public List<Post> getMissionsWithUserId(int id) {
        return jdbcTemplate.query("SELECT p.* \n" +
                "FROM posts AS p\n" +
                "INNER JOIN userOperations AS uo ON uo.postId = p.id\n" +
                "INNER JOIN users AS u ON u.id = uo.userId\n" +
                "WHERE u.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Post.class));
    }

    public void addUserToSearchingTeam(int personId, int missionId) {
        List<UsersMissions> usersMissions = jdbcTemplate.query("SELECT * FROM userOperations WHERE userId = ? AND postId = ?",
                new Object[]{personId, missionId}, new BeanPropertyRowMapper<>(UsersMissions.class));
        if (usersMissions.isEmpty())
            jdbcTemplate.update("INSERT INTO userOperations(userId, postId) VALUES  (?, ?)", personId, missionId);
    }

}
