package com.example.eventmanagment.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.eventmanagment.domain.User;
import com.example.eventmanagment.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
	private final UserRepository userrepository;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserController(UserRepository userrepository, PasswordEncoder passwordEncoder) {
		super();
		this.userrepository = userrepository;
		this.passwordEncoder = passwordEncoder;
	}
	// Handles GET requests to the "/signup" endpoint, creates a new User object, adds it to the model, and returns the "signup" view.
	@GetMapping("/signup")
	public String signupForm(Model model) {
	    User user = new User();
	    model.addAttribute("user", user);
	    return "signup";
	}

	@PostMapping("/signup")
	public String registerUser(@ModelAttribute("User") User user, Model model) {
	    // Check if the user already exists
	    if (userrepository.findByUsername(user.getUsername()) != null) {
	        model.addAttribute("error", "User already exists.");
	        return "signup";
	    }
	    
	    // Check if user's password is null and add error message to the model if it is.
	    if (user.getPassword() == null) {
	        model.addAttribute("error", "Password cannot be null.");
	        return "signup";
	    }
	        
	    // Encrypt the user's password before saving to the database
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    
	    //Save the user to the database
	    User savedUser = userrepository.save(user);
	    model.addAttribute("success", "User registered successfully.");
	    return "login";
	}
	
	//This is a simple GET request mapping for the "/login" endpoint. It returns the "login" template file. The purpose of this code is to display the login form to the user.
	@GetMapping(value="/login")
	public String login(Model model) {
		return "login";
	}
	
	/*
	 * This method handles GET requests to '/listofusers' endpoint and retrieves all the users from the database.
	 * The users are then added to the 'listUsers' attribute of the Model object, which is then used to display the list of users 
	 * on the 'listofusers' page.
	 */
	@GetMapping("/listofusers")
	public String listUsers(Model model) {
	    List<User> users = (List<User>) userrepository.findAll();
	    model.addAttribute("listUsers", users);
	    return "listofusers";
	}
	
	//This method is used to delete a user with the given userId.
	@GetMapping("/delete/{userId}")
	public String deleteUser (@PathVariable("userId") Long userId) {
		userrepository.deleteById(userId);
		return "redirect:/listofusers";
	}
	
	/*
	 * This method handles GET requests for editing user details.
	 * It takes the userId as a path variable to identify which user's details to edit.
	 */
	@RequestMapping(value="/edit/{userId}")
	public String editUser(@PathVariable("userId") Long userId, Model model) {
		model.addAttribute("user", userrepository.findById(userId));
		return "edituser";
	}
	
	//This method updates the user details after editting
	@PostMapping(value="/save")
	public String updateUser(User user, @RequestParam("password") String password) {
	    User existingUser = userrepository.findById(user.getId().longValue()).orElse(null);
	    //if user is not null, it sets the password to the user's original password
	    if(existingUser != null) {
	        user.setPassword(existingUser.getPassword());
	    }
	    userrepository.save(user);
	    return "redirect:listofusers";
	}


}
