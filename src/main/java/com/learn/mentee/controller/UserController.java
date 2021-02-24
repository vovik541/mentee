package com.learn.mentee.controller;

import com.learn.mentee.entity.User;
import com.learn.mentee.service.UserService;
import com.learn.mentee.util.exporter.impl.XMLToUserExporter;
import com.learn.mentee.xml.impl.UserXMLBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserXMLBuilder userXMLBuilder;

    @Resource
    private XMLToUserExporter xmlToUserExporter;

    @GetMapping("/{id}")

    public String getUserById(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/xml")
    public void getXml(@RequestParam long id, HttpServletResponse response) throws IOException {
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=user" + id + ".xml";
        response.setHeader(headerKey, headerValue);
        userXMLBuilder.buildXml(userService.getUserById(id), response.getOutputStream());
    }

    @GetMapping("/preloadUser")
    public String preloadTickets() {
        User user = xmlToUserExporter.exportUserFromXml();
        userService.createUser(user);
        return "redirect:/user/" + user.getId();
    }

    //TODO make @GetMapping("/preloadUsers") to read list of users from xml file

    @GetMapping("/getByEmail")
    public String getUserByEmail(Model model, @RequestParam String email) {
        User foundUser = userService.getUserByEmail(email);
        model.addAttribute("user", foundUser);
        return "redirect:/user/" + foundUser.getId();
    }

    @GetMapping("/getByName")
    public String getUsersByNamePerPage(@RequestParam("name") String name,
                                        @RequestParam("pageNum") int pageNum,
                                        @RequestParam("pageSize") int pageSize,
                                        Model model) {
        model.addAttribute("users", userService.getUsersByName(name, pageSize, pageNum));
        return "users";
    }

    @PostMapping("/delete")
    public String deleteUserById(@RequestParam long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateUserById(@RequestParam long id,
                                 @RequestParam String name,
                                 @RequestParam String email,
                                 Model model) {

        User user = new User(id, name, email);

        model.addAttribute("user", userService.updateUser(user));

        return "redirect:/user/" + id;
    }

    @PostMapping("/create")
    public String createUser(@RequestParam long id,
                             @RequestParam String name,
                             @RequestParam String email,
                             Model model) {

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);

        model.addAttribute("user", userService.createUser(user));

        return "redirect:/user/" + id;
    }
}
