package com.xmxisme.product.pojo;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class Products implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
