package model;

import Base.model.BaseEntity;

public class ShoppingCart extends BaseEntity<Integer> {
    int user_id;
    int price;
    String productName;

    public ShoppingCart(int user_id, int price, String productName) {
        this.user_id = user_id;
        this.price = price;
        this.productName = productName;
    }

    public ShoppingCart(Integer id, int user_id, int price, String productName) {
        super(id);
        this.user_id = user_id;
        this.price = price;
        this.productName = productName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "user_id=" + user_id +
                ", price=" + price +
                ", productName='" + productName + '\'' +
                ", id=" + id +
                '}';
    }
}

