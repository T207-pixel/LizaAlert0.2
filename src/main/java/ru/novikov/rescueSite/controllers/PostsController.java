package ru.novikov.rescueSite.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.novikov.rescueSite.dao.PostsDAO;
import ru.novikov.rescueSite.dao.UsersDAO;
import ru.novikov.rescueSite.models.Post;
import ru.novikov.rescueSite.models.User;

@Controller
@RequestMapping("/posts")
public class PostsController {

    private final PostsDAO postsDAO;
    private final UsersDAO usersDAO;

    @Autowired
    public PostsController(PostsDAO postsDAO, UsersDAO usersDAO) {
        this.postsDAO = postsDAO;
        this.usersDAO = usersDAO;
    }

    @GetMapping()   //fine
    public String showAllPosts(Model model) {
        model.addAttribute("postsList", postsDAO.showAll());
        return "posts/allPosts";
    }

    @GetMapping("/newPost")
    public String createPost(@ModelAttribute("post") Post post) {
        return "posts/newPost";
    }

    @PostMapping("/newPost")
    public String createPost(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors())
            return "posts/newPost";
        postsDAO.cretePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String showPost(@PathVariable("id") int id, Model model, @ModelAttribute("user") User user) {
        model.addAttribute("post", postsDAO.getPost(id));
        model.addAttribute("users", postsDAO.getUsersOnMission(id));
//        model.addAttribute("quantity", )
        model.addAttribute("allUsers", usersDAO.showAll());
        return "posts/showPost";
    }



    @PatchMapping("/{id}/assign")
    public String updatePostQuantity(@PathVariable("id") int postId, @ModelAttribute("user") User user) {
        usersDAO.addUserToSearchingTeam(user.getId(), postId);
        return "redirect:/posts/" + postId;
    }



}
