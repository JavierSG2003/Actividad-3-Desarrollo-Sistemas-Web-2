package com.example.tiendasara.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tiendasara.demo.models.Category;
import com.example.tiendasara.demo.models.Mark;
import com.example.tiendasara.demo.models.Product;
import com.example.tiendasara.demo.models.ProductDto;
import com.example.tiendasara.demo.servers.CategoryRepository;
import com.example.tiendasara.demo.servers.MarkRepository;
import com.example.tiendasara.demo.servers.ProductRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CategoryRepository repo2;

    @Autowired
    private MarkRepository repo3;

    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "products/index";
    }

    public List<Category> getListCategories() {
        return repo2.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<Mark> getListMarks() {
        return repo3.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        model.addAttribute("categories", getListCategories());
        model.addAttribute("marks", getListMarks());
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result,
            Model model
    ) {
        model.addAttribute("categories", getListCategories());
        model.addAttribute("marks", getListMarks());

        if (result.hasErrors()) {
            return "products/CreateProduct";
        }

        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAmount(productDto.getAmount());
        product.setCategory(productDto.getCategory());
        product.setMark(productDto.getMark());

        repo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    ) {
        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);
            model.addAttribute("categories", getListCategories());
            model.addAttribute("marks", getListMarks());

            ProductDto productDto = new ProductDto();
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setAmount(product.getAmount());
            productDto.setCategory(product.getCategory());
            productDto.setMark(product.getMark());

            model.addAttribute("productDto", productDto);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/products";
        }
        return "products/EditProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
    ) {
        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            if (result.hasErrors()) {
                return "products/EditProduct";
            }

            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setAmount(productDto.getAmount());
            product.setCategory(productDto.getCategory());
            product.setMark(productDto.getMark());

            repo.save(product);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(
            @RequestParam int id
    ) {
        try {
            Product product = repo.findById(id).get();
            repo.delete(product);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/products";
    }
}