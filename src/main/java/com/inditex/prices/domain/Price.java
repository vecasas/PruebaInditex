package com.inditex.prices.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "price_list", nullable = false)
    private Integer priceList;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "curr", nullable = false)
    private String curr;

    public Price() {
    }

    public Price(Long productId, Long brandId, Integer priceList, LocalDateTime startDate, LocalDateTime endDate, Double price, String curr) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.curr = curr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Price price1)) return false;
        return Objects.equals(getId(), price1.getId()) && Objects.equals(getProductId(), price1.getProductId()) && Objects.equals(getBrandId(), price1.getBrandId()) && Objects.equals(getPriceList(), price1.getPriceList()) && Objects.equals(getStartDate(), price1.getStartDate()) && Objects.equals(getEndDate(), price1.getEndDate()) && Objects.equals(getPrice(), price1.getPrice()) && Objects.equals(getCurr(), price1.getCurr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductId(), getBrandId(), getPriceList(), getStartDate(), getEndDate(), getPrice(), getCurr());
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", productId=" + productId +
                ", brandId=" + brandId +
                ", priceList=" + priceList +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", curr='" + curr + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}