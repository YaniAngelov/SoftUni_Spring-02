package com.example.heroes.web.view.controllers;

import com.example.heroes.services.models.auth.LoginUserServiceModel;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    protected boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    protected String getUsername(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getUsername();
    }

}
