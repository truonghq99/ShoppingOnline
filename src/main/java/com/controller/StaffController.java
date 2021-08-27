package com.controller;

import org.springframework.ui.Model;
import com.model.Address;
import com.model.Book;
import com.model.Fullname;
import com.model.Member;
import com.model.Staff;
import com.service.AddressService;
import com.service.FullnameService;
import com.service.StaffService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private FullnameService fullnameService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger log = LoggerFactory.getLogger(StaffController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
  
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
    }

    @GetMapping(value = "/login")
    public String showLoginForm(){
        return "login";
    }
    // @PreAuthorize("hasAnyRole('staff')")
    // @RequestMapping(value = "/login",method = RequestMethod.POST)
    // public String checkLogin(Staff staff){
    //     boolean response=staffService.checkLogin(staff.getUsername(), staff.getPassword());
    //     if(response){
    //         System.out.println("Login successful");
    //         return "redirect:/home";
    //     }else{
    //         System.out.println("Login failed");
    //         return "login";
    //     }
    // }
    // show home page admin
    @RequestMapping(value="/home")
    public String showHomeAdminPage(){
        return "home_page";
    }

    @RequestMapping(value="/failed-page")
    public String showFailedPage(){
        return "base/404";
    }

    // Show list member
    @RequestMapping(value = "/list-staff")
    public String showListStaff(Model model, Staff staff) {
        model.addAttribute("staff", staffService.getAllStaff());
        return "list_staff";
    }


    /* PHAN NGUOI DUNG
    PHAN NGUOI DUNG
    PHAN NGUOI DUNG
    PHAN NGUOI DUNG
    PHAN NGUOI DUNG
    PHAN NGUOI DUNG
    PHAN NGUOI DUNG
    PHAN NGUOI DUNG
    PHAN NGUOI DUNG
    */

    //Tao tai khoan cho nhan vien
    @RequestMapping(value ="/signup", method= RequestMethod.GET)
    public String showRegistrationForm2(Model model, Fullname fullname, Address address, Staff staff) {
        model.addAttribute("staff",staff);
        model.addAttribute("fullname", fullname);
        model.addAttribute("address", address);
        return "signup";
    }

    //Save dang ki tai khoan
    @PostMapping(value="/saveInfo")
    public String saveInfo(Staff staff,Fullname fullname, Address address){
        String password= staff.getPassword();
        String passwordEncoder = bCryptPasswordEncoder.encode(password);
        staff.setPassword(passwordEncoder);
        addressService.saveAddress(address);
        fullnameService.saveFullName(fullname);
        staff.setFullnameId(fullname);
        staff.setAddressId(address);
        staffService.createStaff(staff);
        return "redirect:/login";
    }

    // @RequestMapping(value="/member/{id}", method= RequestMethod.PUT)
    // public void updateUserProfile(@RequestBody Member member, @PathVariable int id){
 
    // }

    // //show Profile
    // @RequestMapping(value="user-profile/{id}")
    // public String showUserProfile(Member member){
    //     service.findById(member.getId());
    //     return "redirect:/user-profile/{id}";
    // }

    // //show member home pageY
    // @RequestMapping(value="/home/{id}")
    // public String showHomePage(){
    //     return "client/client_home_page";
    // }

}