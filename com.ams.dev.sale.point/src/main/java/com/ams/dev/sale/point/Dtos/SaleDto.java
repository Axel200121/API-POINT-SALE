package com.ams.dev.sale.point.Dtos;

import java.util.Date;
import java.util.Set;

public class SaleDto {
    private String id;
    private Date saleDate;
    private Double total;
    private ClientDto client;
    private UserDto user;
    private Set<SaleDetailDto> saleDetail;
    private Date createdAt;
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<SaleDetailDto> getSaleDetail() {
        return saleDetail;
    }

    public void setSaleDetail(Set<SaleDetailDto> saleDetail) {
        this.saleDetail = saleDetail;
    }
}
