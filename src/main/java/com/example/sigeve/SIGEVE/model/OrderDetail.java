package com.example.sigeve.SIGEVE.model;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;
@Entity
@Table(name = "[Order Details]")
@IdClass(OrderDetail.OrderDetailId.class)
public class OrderDetail {
    @Id
    @Column(name = "OrderID")
    private int orderId;

    @Id
    @Column(name = "ProductID")
    private int productId;

    @Column(name = "UnitPrice")
    private float unitPrice;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Discount")
    private float discount;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
    public static class OrderDetailId implements Serializable {
        private int orderId;
        private int productId;

        public OrderDetailId() {}

        public OrderDetailId(int orderId, int productId) {
            this.orderId = orderId;
            this.productId = productId;
        }

        // Getters and Setters
        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderDetailId that = (OrderDetailId) o;
            return orderId == that.orderId && productId == that.productId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(orderId, productId);
        }
    }
}


