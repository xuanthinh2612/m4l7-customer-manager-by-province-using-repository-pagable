package com.codeGym.controllers;


import com.codeGym.model.Customer;
import com.codeGym.model.Province;
import com.codeGym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.codeGym.service.customer.ICustomerService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class CustomerController {

    @Autowired

    private ICustomerService customerService;
    @Autowired
    private IProvinceService provinceService;

    @GetMapping("/show")
    public ModelAndView showAll(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Customer> customerList = customerService.findALl(pageable);
        modelAndView.addObject("customerList", customerList);
        return modelAndView;

    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        List<Province> provinceList = provinceService.findALl();
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("provinceList", provinceList);

        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/home/show";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdate(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;

    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable long id, @ModelAttribute Customer customer) {
        customer.setId(id);
        customerService.save(customer);
        return "redirect:/home/show";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable long id) {
        customerService.deleteById(id);
        return "redirect:/home/show";
    }
}
