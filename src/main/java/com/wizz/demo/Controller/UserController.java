package com.wizz.demo.Controller;

import com.wizz.demo.model.User;
import com.wizz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/manage/insert")
    public String addUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            userService.insert(user);
        }
        return "redirect:/user/manage";//重定向
    }

    @RequestMapping("/manage/delete")
    public String deleteUser(@RequestBody User user){
        userService.deleteUserByName(user.getName());
        return "redirect:/user/manage";
    }

    @RequestMapping("/manage/password")
    public String updatePassword(@RequestBody User user){//requestbody注解的使用
        User user2=userService.getUserByName(user.getName());
        user2.setPassword(user.getPassword());
        userService.update(user2);
        return "redirect:/user/manage";
    }
    @RequestMapping("/manage/power")
    public String updatePower(@RequestBody User user){
        User user2=userService.getUserByName(user.getName());
        user2.setPower(user.getPower());
        userService.update(user2);
        return "redirect:/user/manage";
    }

    @ResponseBody//响应直接写到http正文中
    @RequestMapping("/list")
    public List<User> manage(Model model){
        List<User>  admins=userService.getUserByPower(User.Power.ROLE_ADMIN);
        //model.addAttribute("admins",admins);
        return admins;
    }

}
