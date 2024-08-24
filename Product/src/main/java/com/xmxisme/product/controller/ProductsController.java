package com.xmxisme.product.controller;

import com.alibaba.fastjson.JSON;
import com.xmxisme.product.annotation.RequireRole;
import com.xmxisme.product.mapper.ProductsMapper;
import com.xmxisme.product.pojo.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {


    @Autowired
    private ProductsMapper productsMapper;


    // 查询所有产品
    @GetMapping("/getProducts")
    @RequireRole({"USER", "EDITOR"})
    public Object getProducts() {
        List<Products> allProducts = productsMapper.getAllProducts();
        return JSON.toJSON(allProducts);
    }

    @GetMapping("/getProductByName")
    public Object getProductByName(@RequestParam("name") String name) {
        Products productsByName = productsMapper.getProductsByName(name);
        return productsByName != null ? JSON.toJSON(productsByName) : "产品不存在";
    }

    @GetMapping("/addProduct")
    @RequireRole({"USER", "EDITOR","PRODUCT_ADMIN"})
    public String addProduct(@RequestParam("name") String name) {
        productsMapper.insertProducts(name);
        return "新增产品成功";
    }

    @GetMapping("/updateProduct")
    @RequireRole({"USER", "EDITOR", "PRODUCT_ADMIN"})
    public String updateProduct(@RequestParam("id") Long id, @RequestParam("name") String name) {
        System.out.println(id + " " + name);
        productsMapper.updateProductsById(id, name);
        return "修改产品成功";
    }

    // 根据id删除产品
    @GetMapping("/deleteProduct")
    @RequireRole({"USER", "EDITOR"})
    public String deleteProduct(@RequestParam("id") Long id) {
        productsMapper.deleteProductsById(id);
        return "删除产品成功";
    }

}
