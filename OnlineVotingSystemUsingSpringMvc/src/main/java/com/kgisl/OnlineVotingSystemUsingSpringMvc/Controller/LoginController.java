package com.kgisl.OnlineVotingSystemUsingSpringMvc.Controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kgisl.OnlineVotingSystemUsingSpringMvc.Dao.VoterDAO;
import com.kgisl.OnlineVotingSystemUsingSpringMvc.Model.Voter;

@Controller
public class LoginController {
    @Autowired
    private VoterDAO voterDAO;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest req, HttpServletResponse resp) {
        String userMail = req.getParameter("email");
        String userPass = req.getParameter("password");
        
        List<Voter> emailList = voterDAO.getEmailPass();
        boolean flag = false;
        if (userMail.equals("admin") && userPass.equals("admin")) {
            return new ModelAndView("result");
        } else {
            for (Voter voter : emailList) {
                String email = voter.getEmail();
                String pass = voter.getPassword();
                if (userMail.equals(email) && userPass.equals(pass)) {
                    flag = true;
                    return new ModelAndView("polling");
                }
            }
            if (!flag) {
                String message = "UserEmail or Password Invalid";
                ModelAndView mav = new ModelAndView("login");
                mav.addObject("message", message);
                return mav;
            }
        }
        return null;
    }
}
