/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author barth
 */
public class ReturnStockItem {
    private String stockID;
    private String productName;
    private String productWeight;
    private String buyingPrice;
    private String qty;

    /**
     * @return the stockID
     */
    public String getStockID() {
        return stockID;
    }

    /**
     * @param stockID the stockID to set
     */
    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the buyingPrice
     */
    public String getBuyingPrice() {
        return buyingPrice;
    }

    /**
     * @param buyingPrice the buyingPrice to set
     */
    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    /**
     * @return the qty
     */
    public String getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(String qty) {
        this.qty = qty;
    }

    /**
     * @return the productWeight
     */
    public String getProductWeight() {
        return productWeight;
    }

    /**
     * @param productWeight the productWeight to set
     */
    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }
}
