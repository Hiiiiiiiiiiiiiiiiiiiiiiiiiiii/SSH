package com.kaishengit.ssm.collector;

import com.kaishengit.ssm.pojo.Product;
import com.kaishengit.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductCollector {

    @Autowired
    ProductService productService;
    @GetMapping
    public String list(Model model, HttpServletRequest request){
        List<Product> productList = productService.getList(request);
        model.addAttribute("productList",productList);
        return "list";
    }

//    跳转到新增页面
    @GetMapping("/add")
    public String add(){
        return "add";
    }
//    提交新增信息，并重定向到list页面
    @PostMapping("/add")
    public String add(Product product){
        productService.add(product);
        return "redirect:/product";
    }
//    跳转到详细页面
    @GetMapping("/message/{id}")
    public String message(@PathVariable Integer id,Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "message";
    }
}
