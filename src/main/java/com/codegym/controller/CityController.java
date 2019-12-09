package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.CityService;
import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> countries(){
        return countryService.findAll();
    }


    @GetMapping("/")
    public ModelAndView showList() {
        Iterable<City> cities = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("city/list");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create-city")
    public ModelAndView saveCreate(City city) {
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("city/create");
        modelAndView.addObject("city", city);
        modelAndView.addObject("message", "them thanh pho thanh cong");
        return modelAndView;
    }

    @GetMapping("/edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        City city = cityService.findById(id);
        if (city != null) {
            ModelAndView modelAndView = new ModelAndView("city/edit");
            modelAndView.addObject("city", city);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }

    @PostMapping("/edit-city")
    public ModelAndView save(@ModelAttribute("city") City city) {
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("city/edit");
        modelAndView.addObject("city", city);
        modelAndView.addObject("message", "sua thanh pho thanh cong");
        return modelAndView;
    }

    @GetMapping("/delete-city/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        City city = cityService.findById(id);
        if (city != null) {
            ModelAndView modelAndView = new ModelAndView("/city/delete");
            modelAndView.addObject("city", city);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }



        @PostMapping("/delete-city")
        public String deleteCity(@ModelAttribute("city") City city){
            cityService.remove(city.getId());
            return "redirect:/";
        }
    }
