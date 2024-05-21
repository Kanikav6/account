package com.assignment.newaccount.rest;

import com.assignment.newaccount.service.AccountService;
import com.assignment.newaccount.vo.NewAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountUIController {

    @Autowired
    AccountService accountService;


    @GetMapping("/create")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("newAccountRequest", new NewAccountRequest());
        return "createNewAccount";
    }

    @PostMapping("/createNewAccount")
    public String createAccount(@ModelAttribute NewAccountRequest request, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("message", "Validation errors occurred");
            return "createNewAccount";
        }

        try {
            accountService.createNewAccount(request.getCustomerId(), request.getInitialCredit());
            model.addAttribute("message", "Account created successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error: " + e.getMessage());
        }
        return "createNewAccount";
    }

}
