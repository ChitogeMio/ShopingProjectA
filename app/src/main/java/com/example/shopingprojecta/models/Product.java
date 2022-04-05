package com.example.shopingprojecta.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class Product {

    private String id_a;
    private String name_a;
    private Double price_a;
    private boolean isAvailable_a;
    private String image_url_a;

    public Product(String id_a, String name_a, Double price_a, boolean isAvailable_a, String image_url_a) {
        this.id_a = id_a;
        this.name_a = name_a;
        this.price_a = price_a;
        this.isAvailable_a = isAvailable_a;
        this.image_url_a = image_url_a;
    }

    public String getId_a() {
        return id_a;
    }

    public void setId_a(String id_a) {
        this.id_a = id_a;
    }

    public String getName_a() {
        return name_a;
    }

    public void setName_a(String name_a) {
        this.name_a = name_a;
    }

    public Double getPrice_a() {
        return price_a;
    }

    public void setPrice_a(Double price_a) {
        this.price_a = price_a;
    }

    public boolean isAvailable_a() {
        return isAvailable_a;
    }

    public void setAvailable_a(boolean available_a) {
        isAvailable_a = available_a;
    }

    public String getImage_url_a() {
        return image_url_a;
    }

    public void setImage_url_a(String image_url_a) {
        this.image_url_a = image_url_a;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id_a='" + id_a + '\'' +
                ", name_a='" + name_a + '\'' +
                ", price_a=" + price_a +
                ", isAvailable_a=" + isAvailable_a +
                ", image_url_a='" + image_url_a + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isAvailable_a() == product.isAvailable_a() &&
                getId_a().equals(product.getId_a()) &&
                getName_a().equals(product.getName_a()) &&
                getPrice_a().equals(product.getPrice_a()) &&
                getImage_url_a().equals(product.getImage_url_a());
    }

    public static DiffUtil.ItemCallback<Product>itemCallback=new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId_a().equals(newItem.getId_a());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };
}
