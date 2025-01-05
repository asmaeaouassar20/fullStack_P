package com.algostyle.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")  // Cette ligne de code permet à une application front-end (x: Angular) tournant sur http://localhost:4200 d'accéder à l'API Spring Boot sans être bloquée par les restrictions CORS du navigateur
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }


    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody Product productDetails){
        Product newProduct=productRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Product noy found"));

        newProduct.setName(productDetails.getName());
        newProduct.setDescription(productDetails.getDescription());
        newProduct.setPrice(productDetails.getPrice());
        return productRepository.save(newProduct);
    }



    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
    }
}
