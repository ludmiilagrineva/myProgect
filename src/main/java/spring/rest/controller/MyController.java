package spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.rest.dao.CalculatorDAO;
import spring.rest.entity.Calculation;
import spring.rest.service.CalculatorService;


import java.util.List;

@Controller
public class MyController {
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/")
    public String mainPage(){
        return "main-page";
    }

//    @GetMapping("hr_info")
//    public String gethy(){
//        return "all-calculation";
//    }
////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/calculations")
    public String showAllCalculations(Model model){
        List<Calculation> allCalculations=calculatorService.getAllCalculations();
    model.addAttribute("allCalc", allCalculations);
        return "all-calculation";

    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("calcId") int id){
        calculatorService.deleteCalculations(id);
        return "redirect:/calculations";
    }
    /////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/calculate")
    public String newCalculate(Model model){
        Calculation calculation=new Calculation();
        model.addAttribute("calculate", calculation);
        return "calculate";
    }
    @RequestMapping("/newCalculate")
    public String newCalculate(@ModelAttribute("calculate") Calculation calculation){
       calculatorService.saveCalculation(calculation);
        return "redirect:/calculations";
    }


    @RequestMapping("/show-calculation")
    public String showCalculation(@RequestParam("calcId") int id, Model model){
       Calculation calculation= calculatorService.getCalculation(id);
       model.addAttribute("calculation", calculation);

        return "show-calculation";
    }


}

