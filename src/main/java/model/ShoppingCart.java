package model;

import Base.model.BaseEntity;

public class ShoppingCart extends BaseEntity<Integer> {
    int user_id;
    String productName;
    int price;

    public ShoppingCart(int user_id, String productName, int price) {
        this.user_id = user_id;
        this.productName = productName;
        this.price = price;
    }

    public ShoppingCart(Integer id, int user_id, String productName, int price) {
        super(id);
        this.user_id = user_id;
        this.productName = productName;
        this.price = price;
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

