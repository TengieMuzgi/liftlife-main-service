package com.liftlife.liftlife.product;

import com.liftlife.liftlife.diet.Diet;
import com.liftlife.liftlife.diet.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository repository;

    @Autowired
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/find")
    public Product getById(@RequestParam("id") String productId) {
        return repository.findById(productId);
    }

}
