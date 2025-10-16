package com.tool.dto;

import java.util.Set;

public class ProductRequest {
    private String type;
    private String sku;
    private String gtin;
    private String name;
    private Integer published;
    private Integer isFeatured;
    private String visibilityInCatalog;
    private String shortDescription;
    private String description;
    private String dateSalePriceStarts;
    private String dateSalePriceEnds;
    private String taxStatus;
    private String taxClass;
    private Integer inStock;
    private Integer stock;
    private Integer lowStockAmount;
    private Integer backordersAllowed;
    private Integer soldIndividually;
    private Integer weight;
    private Integer length;
    private Integer width;
    private Integer height;
    private Integer allowCustomerReviews;
    private String purchaseNote;
    private Integer salePrice;
    private Integer regularPrice;
    private String categories;
    private String tags;
    private String shippingClass;
    private String images;
    private String downloadLimit;
    private Integer downloadExpiryDays;
    private String parent;
    private String groupedProducts;
    private String upsells;
    private String crossSells;
    private String externalURL;
    private String buttonText;
    private Integer position;
    private String brands;

    private String attribute1Name;
    private String attribute1Value;
    private Integer attribute1Visible;
    private Integer attribute1Global;

    private String attribute2Name;
    private String attribute2Value;
    private Integer attribute2Visible;
    private Integer attribute2Global;

    private String attribute3Name;
    private String attribute3Value;
    private Integer attribute3Visible;
    private Integer attribute3Global;

    private String attribute4Name;
    private String attribute4Value;
    private Integer attribute4Visible;
    private Integer attribute4Global;

    private String attribute5Name;
    private String attribute5Value;
    private Integer attribute5Visible;
    private Integer attribute5Global;

    private String attribute6Name;
    private String attribute6Value;
    private Integer attribute6Visible;
    private Integer attribute6Global;

    private String attribute7Name;
    private String attribute7Value;
    private Integer attribute7Visible;
    private Integer attribute7Global;

    private String attribute8Name;
    private String attribute8Value;
    private Integer attribute8Visible;
    private Integer attribute8Global;

    private String attribute9Name;
    private String attribute9Value;
    private Integer attribute9Visible;
    private Integer attribute9Global;

    private Set<Long> productCategoryIds;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public Integer getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getVisibilityInCatalog() {
        return visibilityInCatalog;
    }

    public void setVisibilityInCatalog(String visibilityInCatalog) {
        this.visibilityInCatalog = visibilityInCatalog;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateSalePriceStarts() {
        return dateSalePriceStarts;
    }

    public void setDateSalePriceStarts(String dateSalePriceStarts) {
        this.dateSalePriceStarts = dateSalePriceStarts;
    }

    public String getDateSalePriceEnds() {
        return dateSalePriceEnds;
    }

    public void setDateSalePriceEnds(String dateSalePriceEnds) {
        this.dateSalePriceEnds = dateSalePriceEnds;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLowStockAmount() {
        return lowStockAmount;
    }

    public void setLowStockAmount(Integer lowStockAmount) {
        this.lowStockAmount = lowStockAmount;
    }

    public Integer getBackordersAllowed() {
        return backordersAllowed;
    }

    public void setBackordersAllowed(Integer backordersAllowed) {
        this.backordersAllowed = backordersAllowed;
    }

    public Integer getSoldIndividually() {
        return soldIndividually;
    }

    public void setSoldIndividually(Integer soldIndividually) {
        this.soldIndividually = soldIndividually;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAllowCustomerReviews() {
        return allowCustomerReviews;
    }

    public void setAllowCustomerReviews(Integer allowCustomerReviews) {
        this.allowCustomerReviews = allowCustomerReviews;
    }

    public String getPurchaseNote() {
        return purchaseNote;
    }

    public void setPurchaseNote(String purchaseNote) {
        this.purchaseNote = purchaseNote;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Integer regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getShippingClass() {
        return shippingClass;
    }

    public void setShippingClass(String shippingClass) {
        this.shippingClass = shippingClass;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDownloadLimit() {
        return downloadLimit;
    }

    public void setDownloadLimit(String downloadLimit) {
        this.downloadLimit = downloadLimit;
    }

    public Integer getDownloadExpiryDays() {
        return downloadExpiryDays;
    }

    public void setDownloadExpiryDays(Integer downloadExpiryDays) {
        this.downloadExpiryDays = downloadExpiryDays;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getGroupedProducts() {
        return groupedProducts;
    }

    public void setGroupedProducts(String groupedProducts) {
        this.groupedProducts = groupedProducts;
    }

    public String getUpsells() {
        return upsells;
    }

    public void setUpsells(String upsells) {
        this.upsells = upsells;
    }

    public String getCrossSells() {
        return crossSells;
    }

    public void setCrossSells(String crossSells) {
        this.crossSells = crossSells;
    }

    public String getExternalURL() {
        return externalURL;
    }

    public void setExternalURL(String externalURL) {
        this.externalURL = externalURL;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getAttribute1Name() {
        return attribute1Name;
    }

    public void setAttribute1Name(String attribute1Name) {
        this.attribute1Name = attribute1Name;
    }

    public String getAttribute1Value() {
        return attribute1Value;
    }

    public void setAttribute1Value(String attribute1Value) {
        this.attribute1Value = attribute1Value;
    }

    public Integer getAttribute1Visible() {
        return attribute1Visible;
    }

    public void setAttribute1Visible(Integer attribute1Visible) {
        this.attribute1Visible = attribute1Visible;
    }

    public Integer getAttribute1Global() {
        return attribute1Global;
    }

    public void setAttribute1Global(Integer attribute1Global) {
        this.attribute1Global = attribute1Global;
    }

    public String getAttribute2Name() {
        return attribute2Name;
    }

    public void setAttribute2Name(String attribute2Name) {
        this.attribute2Name = attribute2Name;
    }

    public String getAttribute2Value() {
        return attribute2Value;
    }

    public void setAttribute2Value(String attribute2Value) {
        this.attribute2Value = attribute2Value;
    }

    public Integer getAttribute2Visible() {
        return attribute2Visible;
    }

    public void setAttribute2Visible(Integer attribute2Visible) {
        this.attribute2Visible = attribute2Visible;
    }

    public Integer getAttribute2Global() {
        return attribute2Global;
    }

    public void setAttribute2Global(Integer attribute2Global) {
        this.attribute2Global = attribute2Global;
    }

    public String getAttribute3Name() {
        return attribute3Name;
    }

    public void setAttribute3Name(String attribute3Name) {
        this.attribute3Name = attribute3Name;
    }

    public String getAttribute3Value() {
        return attribute3Value;
    }

    public void setAttribute3Value(String attribute3Value) {
        this.attribute3Value = attribute3Value;
    }

    public Integer getAttribute3Visible() {
        return attribute3Visible;
    }

    public void setAttribute3Visible(Integer attribute3Visible) {
        this.attribute3Visible = attribute3Visible;
    }

    public Integer getAttribute3Global() {
        return attribute3Global;
    }

    public void setAttribute3Global(Integer attribute3Global) {
        this.attribute3Global = attribute3Global;
    }

    public String getAttribute4Name() {
        return attribute4Name;
    }

    public void setAttribute4Name(String attribute4Name) {
        this.attribute4Name = attribute4Name;
    }

    public String getAttribute4Value() {
        return attribute4Value;
    }

    public void setAttribute4Value(String attribute4Value) {
        this.attribute4Value = attribute4Value;
    }

    public Integer getAttribute4Visible() {
        return attribute4Visible;
    }

    public void setAttribute4Visible(Integer attribute4Visible) {
        this.attribute4Visible = attribute4Visible;
    }

    public Integer getAttribute4Global() {
        return attribute4Global;
    }

    public void setAttribute4Global(Integer attribute4Global) {
        this.attribute4Global = attribute4Global;
    }

    public String getAttribute5Name() {
        return attribute5Name;
    }

    public void setAttribute5Name(String attribute5Name) {
        this.attribute5Name = attribute5Name;
    }

    public String getAttribute5Value() {
        return attribute5Value;
    }

    public void setAttribute5Value(String attribute5Value) {
        this.attribute5Value = attribute5Value;
    }

    public Integer getAttribute5Visible() {
        return attribute5Visible;
    }

    public void setAttribute5Visible(Integer attribute5Visible) {
        this.attribute5Visible = attribute5Visible;
    }

    public Integer getAttribute5Global() {
        return attribute5Global;
    }

    public void setAttribute5Global(Integer attribute5Global) {
        this.attribute5Global = attribute5Global;
    }

    public String getAttribute6Name() {
        return attribute6Name;
    }

    public void setAttribute6Name(String attribute6Name) {
        this.attribute6Name = attribute6Name;
    }

    public String getAttribute6Value() {
        return attribute6Value;
    }

    public void setAttribute6Value(String attribute6Value) {
        this.attribute6Value = attribute6Value;
    }

    public Integer getAttribute6Visible() {
        return attribute6Visible;
    }

    public void setAttribute6Visible(Integer attribute6Visible) {
        this.attribute6Visible = attribute6Visible;
    }

    public Integer getAttribute6Global() {
        return attribute6Global;
    }

    public void setAttribute6Global(Integer attribute6Global) {
        this.attribute6Global = attribute6Global;
    }

    public String getAttribute7Name() {
        return attribute7Name;
    }

    public void setAttribute7Name(String attribute7Name) {
        this.attribute7Name = attribute7Name;
    }

    public String getAttribute7Value() {
        return attribute7Value;
    }

    public void setAttribute7Value(String attribute7Value) {
        this.attribute7Value = attribute7Value;
    }

    public Integer getAttribute7Visible() {
        return attribute7Visible;
    }

    public void setAttribute7Visible(Integer attribute7Visible) {
        this.attribute7Visible = attribute7Visible;
    }

    public Integer getAttribute7Global() {
        return attribute7Global;
    }

    public void setAttribute7Global(Integer attribute7Global) {
        this.attribute7Global = attribute7Global;
    }

    public String getAttribute8Name() {
        return attribute8Name;
    }

    public void setAttribute8Name(String attribute8Name) {
        this.attribute8Name = attribute8Name;
    }

    public String getAttribute8Value() {
        return attribute8Value;
    }

    public void setAttribute8Value(String attribute8Value) {
        this.attribute8Value = attribute8Value;
    }

    public Integer getAttribute8Visible() {
        return attribute8Visible;
    }

    public void setAttribute8Visible(Integer attribute8Visible) {
        this.attribute8Visible = attribute8Visible;
    }

    public Integer getAttribute8Global() {
        return attribute8Global;
    }

    public void setAttribute8Global(Integer attribute8Global) {
        this.attribute8Global = attribute8Global;
    }

    public String getAttribute9Name() {
        return attribute9Name;
    }

    public void setAttribute9Name(String attribute9Name) {
        this.attribute9Name = attribute9Name;
    }

    public String getAttribute9Value() {
        return attribute9Value;
    }

    public void setAttribute9Value(String attribute9Value) {
        this.attribute9Value = attribute9Value;
    }

    public Integer getAttribute9Visible() {
        return attribute9Visible;
    }

    public void setAttribute9Visible(Integer attribute9Visible) {
        this.attribute9Visible = attribute9Visible;
    }

    public Integer getAttribute9Global() {
        return attribute9Global;
    }

    public void setAttribute9Global(Integer attribute9Global) {
        this.attribute9Global = attribute9Global;
    }

    public Set<Long> getProductCategoryIds () {
        return productCategoryIds;
    }

    public void setProductCategoryIds (Set<Long> ids) {
        this.productCategoryIds = ids;
    }
}
