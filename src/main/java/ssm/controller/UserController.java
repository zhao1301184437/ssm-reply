package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.pojo.User;
import ssm.service.UserService;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/regist.html")
    public String regist(User user, String repwd, Model model) {
        try {
            //可以使用springmvc校验框架代替下面的验证
            if (user == null) {
                throw new Exception("必须输入参数");
            }
            if (user.getUsername() == null || user.getUsername().trim().length() == 0) {
                throw new Exception("必须输入用户名");
            }
            if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
                throw new Exception("必须输入密码");
            }
            if (!user.getPassword().equals(repwd)) {
                throw new Exception("两次输入密码不一致");
            }
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            return "/regist.jsp";
        }
        model.addAttribute("message", "注册成功");
        return "/login.jsp";
    }

    @RequestMapping("check.html")
    @ResponseBody
    public Map<String, Object> check(String username) {
        int count = userService.getUserCountByName(username);
        Map<String, Object> res = new HashMap<>();
        if (count > 0) {
            res.put("status", false);
            res.put("message", "用户名已存在");
        } else {
            res.put("status", true);
            res.put("message", "用户名可以使用");
        }
        return res;
    }

    @RequestMapping("login.html")
    public String login(String username, String password, HttpSession session, Map map){

        try {
            if (username == null || username.trim().length() == 0) {
                throw new Exception("用户名不能为空");
            }
            if (password == null || password.trim().length() == 0) {
                throw new Exception("密码不能为空");
            }
            User user = userService.doLogin(username, password);
            session.setAttribute("SESSION_USER", user);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", e.getMessage());
            return "/login.jsp";
        }
        return "redirect:/question/list.html";
    }

    @RequestMapping("logout.html")
    public String logout(HttpSession session) {
        session.invalidate();
        //session.setAttribute("SESSION_USER",null);
        return "redirect:/login.jsp";
    }
}

