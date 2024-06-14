package com.example.springdemo.controllers;


import com.example.springdemo.beans.FirstBean;
import com.example.springdemo.beans.SecondBean;
import com.example.springdemo.beans.TestBean;
import com.example.springdemo.db.DBManager;
import com.example.springdemo.db.Item;
import com.example.springdemo.entities.Categories;
import com.example.springdemo.entities.Countries;
import com.example.springdemo.entities.ShopItems;
import com.example.springdemo.entities.Users;
import com.example.springdemo.services.ItemService;
import com.example.springdemo.services.TestService;
import com.example.springdemo.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("currentUser", getUserData());

        List<ShopItems> items = itemService.getAllItems();
        model.addAttribute("tovary", items);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        return "index";
    }

    @GetMapping(value = "/about")
    public String about(){
        return "about";
    }

    @PostMapping(value = "/addItem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addItem(@RequestParam(name = "country_id", defaultValue = "0") Long id,
                          @RequestParam(name = "item_name", defaultValue = "...") String name,
                          @RequestParam(name = "item_price", defaultValue = "0") int price,
                          @RequestParam(name = "item_amount", defaultValue = "0") int amount){

        Countries country = itemService.getCountry(id);

        ShopItems shopItems = new ShopItems();
        shopItems.setName(name);
        shopItems.setPrice(price);
        shopItems.setAmount(amount);
        shopItems.setCountries(country);
        itemService.addItem(shopItems);

        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id){
        model.addAttribute("currentUser", getUserData());

        ShopItems item = itemService.getItem(id);
        model.addAttribute("item",item);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);


        return "details";
    }

    @GetMapping(value = "/editItem/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String editItem(Model model, @PathVariable(name = "id") Long id){
        model.addAttribute("currentUser", getUserData());

        ShopItems item = itemService.getItem(id);
        model.addAttribute("item",item);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        categories.removeAll(item.getCategories());
        return "editItem";
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String delete(Model model,@PathVariable(name = "id") Long id){
        model.addAttribute("currentUser", getUserData());
        ShopItems shopItems = itemService.getItem(id);
        itemService.deleteItem(shopItems);
        return "redirect:/";
    }



    @PostMapping(value = "/saveitem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String saveItem(@RequestParam(name = "country_id", required = false) Long countryId,
                           @RequestParam(name = "item_id", required = false) Long id,
                           @RequestParam(name = "item_name",defaultValue = "") String name,
                           @RequestParam(name = "item_price",defaultValue = "0") int price,
                           @RequestParam(name = "item_amount", defaultValue = "0") int amount){

        ShopItems item = itemService.getItem(id);

        Countries countries = itemService.getCountry(countryId);
        if (item != null){
            item.setName(name);
            item.setPrice(price);
            item.setAmount(amount);
            item.setCountries(countries);
            itemService.saveItem(item);
        }
        return "redirect:/editItem/"+id;
    }


    @PostMapping(value = "/assigncategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String assignCategory(@RequestParam(name = "item_id") Long id,
                                 @RequestParam(name = "category_id") Long categoryId){
        Categories cat = itemService.getCategory(categoryId);

        if (cat != null){
            ShopItems item = itemService.getItem(id);
            if (item != null){

                List<Categories> categories = item.getCategories();
                if (categories == null){
                    categories = new ArrayList<>();
                }
                categories.add(cat);
                itemService.saveItem(item);

                return "redirect:/editItem/" + id + "#categoriesDiv";
            }
        }
        return "redirect:/";
    }

    @PostMapping(value = "/deletecategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String deleteCategory(@RequestParam(name = "item_id") Long itemId,
                                 @RequestParam(name = "category_id") Long categoryId){


        Categories cat = itemService.getCategory(categoryId);

        if (cat != null){
            ShopItems item = itemService.getItem(itemId);
            if (item != null){

                List<Categories> categories = item.getCategories();
                if (categories == null){
                    categories = new ArrayList<>();
                    categories.add(cat);
                }
                categories.remove(cat);
                itemService.saveItem(item);

                return "redirect:/editItem/" + itemId + "#categoriesDiv";
            }
        }
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login(Model model){
        return "login";
    }


    @GetMapping(value = "/registry")
    public String registry(Model model){
        model.addAttribute("currentUser", getUserData());
        return "registry";

    }


    @PostMapping(value = "/registry")
    public String toRegistry(@RequestParam(name = "user_fullName") String fullName,
                             @RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "re_user_password") String rePassword){

        if (password.equals(rePassword)){

            Users newUser = new Users();
            newUser.setFullName(fullName);
            newUser.setEmail(email);
            newUser.setPassword(password);

            if (userService.createUser(newUser) != null){
                return "redirect:/registry?success";
            }
        }

        return "redirect:/registry?error";

    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        model.addAttribute("currentUser", getUserData());
        return "profile";
    }

    @GetMapping(value = "/addItem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addItem(Model model){

        model.addAttribute("currentUser", getUserData());


        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        return "addItem";
    }

    private Users getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        String username;

        if (principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        }else{
            username = principal.toString();
        }

        Users myUser = userService.getUsersByEmail(username);

        return myUser;
    }
}
