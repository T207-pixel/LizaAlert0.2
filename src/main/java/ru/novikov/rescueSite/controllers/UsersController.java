package ru.novikov.rescueSite.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.novikov.rescueSite.dao.UsersDAO;
import ru.novikov.rescueSite.models.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersDAO usersDAO;

    @Autowired
    public UsersController(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @GetMapping()   //fine
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", usersDAO.showAll());
        return "users/allUsers";
    }

    @GetMapping("/{id}")
    public String showCurrentUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersDAO.getUser(id));
        model.addAttribute("missions", usersDAO.getMissionsWithUserId(id));
        return "users/showUser";
    }

    @GetMapping("/newUser")   //fine
    public String newUser(@ModelAttribute("user") User user) {
        return "users/createUser";
    }

    @PostMapping("/newUser")   //fine
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/createUser";
        usersDAO.creteUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")   //fine
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersDAO.getUser(id));
        return "users/editUser";
    }

    @PatchMapping("/{id}/edit")   //fine
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/editUser";
        usersDAO.updateUserPersonalData(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteAccount(@PathVariable("id") int userId) {
        usersDAO.deleteUser(userId);
        return "redirect:/users";
    }

}
