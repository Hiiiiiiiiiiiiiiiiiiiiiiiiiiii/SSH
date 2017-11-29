package com.kaishengit.ssm.collector;

import com.kaishengit.ssm.pojo.Product;
import com.kaishengit.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductCollector {

    @Autowired
    ProductService productService;
    @GetMapping
    public String home(Model model){
        List<Product> productList = productService.findAll();
        model.addAttribute("productList",productList);
        return "home";
    }

}
