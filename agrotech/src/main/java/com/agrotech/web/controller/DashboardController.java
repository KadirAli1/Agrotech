package com.agrotech.web.controller;

import com.agrotech.config.layout.Layout;
import com.agrotech.model.Country;
import com.agrotech.model.Product;
import com.agrotech.model.ProductType;
import com.agrotech.model.User;
import com.agrotech.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Controller
@PreAuthorize("isAuthenticated()")
public class DashboardController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Boolean currentPasswordError = false;

    private Boolean newPasswordError = false;

    private Boolean uploadError = false;

    @Layout("layout/dash_default")
    @GetMapping("/dashboard")
    public String dashboardHome(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("activeDashNav", "home");
        model.addAttribute("name", user.getName());
        model.addAttribute("countUsers", userService.count());
        model.addAttribute("countProducts", productService.count());
        model.addAttribute("countProductTypes", productTypeService.count());
        return "dash_home";
    }

    @Layout("layout/dash_default")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard/users")
    public String dashboardUsers(Model model) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("activeDashNav", "users");
        model.addAttribute("name", user.getName());
        model.addAttribute("userRoles", userRoleService.findAllByOrderByIdDesc());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", new User());
        return "dash_users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/dashboard/users")
    public String registerNewUser(@ModelAttribute User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/dashboard/users";
    }

    @Layout("layout/dash_default")
    @GetMapping("/dashboard/products")
    public String dashboardProducts(Model model) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("activeDashNav", "products");
        model.addAttribute("name", user.getName());
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("product", new Product());
        model.addAttribute("country", new Country());

        if(uploadError) {
            model.addAttribute("uploadError", "You can upload images only!");
            uploadError = false;
        }

        return "dash_products";
    }

    @PostMapping(value = "/dashboard/products")
    public String addNewProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile file, Model model) throws IOException {

        if(isExtension(file, ".jpeg")
                || isExtension(file, ".jpg")
                || isExtension(file, ".png")
                || isExtension(file, ".gif")) {
            product.setImageName(file.getOriginalFilename());
            productService.save(product);
            storageService.saveFile(product, file);
            return "redirect:/dashboard/products";
        }

        uploadError = true;
        return "redirect:/dashboard/products";
    }

    private Boolean isExtension(MultipartFile file, String ext) {
        return file.getOriginalFilename().endsWith(ext);
    }

    @Layout("layout/dash_default")
    @GetMapping("/dashboard/product-types")
    public String dashboardProductTypes(Model model) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("activeDashNav", "product-types");
        model.addAttribute("name", user.getName());
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("productType", new ProductType());
        return "dash_productTypes";
    }

    @Layout("layout/dash_default")
    @PostMapping("/dashboard/product-types")
    public String dashboardProductTypes(@ModelAttribute ProductType productType) {
        productTypeService.save(productType);
        return "redirect:/dashboard/product-types";
    }

    @Layout("layout/dash_default")
    @GetMapping("/dashboard/settings")
    public String dashboardSettings(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String name = ((User)principal).getName();
        String surname = ((User)principal).getSurname();
        String email = ((User)principal).getEmail();
        String username = ((User)principal).getUsername();

        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("email", email);
        model.addAttribute("username", username);

        if(this.currentPasswordError) {
            model.addAttribute("currentPasswordError", "Entered current password is incorrect!");
            this.currentPasswordError = false;
        }

        if(this.newPasswordError) {
            model.addAttribute("newPasswordError", "Please make sure that your new password and confirm password fields match.");
            this.newPasswordError = false;
        }

        model.addAttribute("activeDashNav", "settings");

        return "dash_settings";
    }

    @PostMapping("/dashboard/settings/personal")
    @Transactional
    public String updateUserPersonalSettings(@ModelAttribute User user) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserId = ((User)principal).getId();
        User currentUser = userService.findOne(currentUserId);

        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        currentUser.setEmail(user.getEmail());

        userService.save(currentUser);

        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                currentUser,
                                currentUser.getPassword(),
                                currentUser.getAuthorities()
                        )
                );

        return "redirect:/dashboard/settings";
    }

    @PostMapping("/dashboard/settings/username")
    @Transactional
    public String updateUserUsernameSettings(@ModelAttribute User user) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserId = ((User)principal).getId();
        User currentUser = userService.findOne(currentUserId);

        currentUser.setUsername(user.getUsername());

        userService.save(currentUser);

        return "redirect:/logout";
    }

    @PostMapping("/dashboard/settings/password")
    @Transactional
    public String updateUserPasswordSettings(@ModelAttribute User user) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserId = ((User)principal).getId();
        User currentUser = userService.findOne(currentUserId);

        Boolean passwordEqual = passwordEncoder.matches(user.getPassword(),currentUser.getPassword());

        if(!passwordEqual) {
            this.currentPasswordError = true;
            return "redirect:/dashboard/settings";
        }

        if(!user.getNewPassword().equals(user.getConfirmNewPassword())) {
            this.newPasswordError = true;
            return "redirect:/dashboard/settings";
        }

        currentUser.setPassword(passwordEncoder.encode(user.getNewPassword()));

        userService.save(currentUser);

        return "redirect:/logout";
    }
}
