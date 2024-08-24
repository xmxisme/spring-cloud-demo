package com.xmxisme.product.mapper;


import com.xmxisme.product.pojo.Products;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface ProductsMapper {

    @Select("SELECT id, name from products where name = #{name}")
    Products getProductsByName(String name);

    // 新增产品
    @Insert("INSERT INTO products(name) VALUES(#{name})")
    int insertProducts(String name);

    @Select("SELECT id, name from products")
    List<Products> getAllProducts();

    // 根据id修改产品
    @Update("UPDATE products SET `name` = #{name} WHERE id = #{id}")
    int updateProductsById(@Param("id")Long id, @Param("name")String name);

    // 根据id删除产品
    @Delete("DELETE FROM products WHERE id = #{id}")
    int deleteProductsById(Long id);
}
