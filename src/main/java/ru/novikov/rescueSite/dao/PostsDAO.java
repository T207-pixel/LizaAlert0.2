package ru.novikov.rescueSite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.novikov.rescueSite.models.Post;
import ru.novikov.rescueSite.models.User;

import java.util.List;

@Component
public class PostsDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Post> showAll() {
        return jdbcTemplate.query("SELECT * FROM posts", new BeanPropertyRowMapper<>(Post.class));
    }

    public Post getPost(int id) {
        return jdbcTemplate.query("SELECT * FROM posts WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Post.class)).
                stream().findAny().orElse(null);
    }

    public void cretePost(Post post) {
        jdbcTemplate.update("INSERT INTO posts(lostPersonName, lostPersonSurname, lostPersonAge, lostPersonSpecialSigns," +
                " whereGotLost, whenGotLost, lostPersonClothing) " +
                "VALUES  (?, ?, ?, ?, ?, ?, ?)", post.getLostPersonName(), post.getLostPersonSurname(), Integer.parseInt(post.getLostPersonAge()),
                post.getLostPersonSpecialSigns(), post.getWhereGotLost(), post.getWhenGotLost(), post.getLostPersonClothing());
    }

    public void updatePostStatus(int postId) {
        jdbcTemplate.update("UPDATE posts SET postStatus = true WHERE id = ?", postId);
    }

    public void updatePostPeopleQuantity(int postId) {
        jdbcTemplate.update("UPDATE posts SET searchingPeopleCurrentQuantity = searchingPeopleCurrentQuantity + 1 WHERE id = ?",
                postId);
    }

    public List<User> getUsersOnMission(int id) {
        return jdbcTemplate.query("SELECT u.* \n" +
                "FROM users AS u\n" +
                "INNER JOIN userOperations AS uo ON uo.userId = u.id\n" +
                "INNER JOIN posts AS p ON p.id = uo.postId\n" +
                "WHERE p.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().toList();
    }


}
