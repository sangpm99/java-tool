package com.tool.model;

import jakarta.persistence.*;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "sku")
    private String sku;

    @Column(name = "gtin")
    private String gtin;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "published", nullable = false)
    private Integer published;

    @Column(name = "is_featured", nullable = false)
    private Integer isFeatured;

    @Column(name = "visibility_in_catalog", nullable = false)
    private String visibilityInCatalog;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "description")
    private String description;

    @Column(name = "date_sale_price_starts")
    private String dateSalePriceStarts;

    @Column(name = "date_sale_price_ends")
    private String dateSalePriceEnds;

    @Column(name = "tax_status", nullable = false)
    private String taxStatus;

    @Column(name = "tax_class")
    private String taxClass;

    @Column(name = "in_stock", nullable = false)
    private Integer inStock;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "low_stock_amount")
    private Integer lowStockAmount;

    @Column(name = "back_orders_allowed", nullable = false)
    private Integer backordersAllowed;

    @Column(name = "sold_individually", nullable = false)
    private Integer soldIndividually;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "length")
    private Integer length;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "allow_customer_reviews", nullable = false)
    private Integer allowCustomerReviews;

    @Column(name = "purchase_note")
    private String purchaseNote;

    @Column(name = "sale_price")
    private Integer salePrice;

    @Column(name = "regular_price", nullable = false)
    private Integer regularPrice;

    @Column(name = "categories")
    private String categories;

    @Column(name = "tags")
    private String tags;

    @Column(name = "shipping_class")
    private String shippingClass;

    @Column(name = "images")
    private String images;

    @Column(name = "download_limit")
    private String downloadLimit;

    @Column(name = "download_expiry_days")
    private Integer downloadExpiryDays;

    @Column(name = "parent")
    private String parent;

    @Column(name = "grouped_products")
    private String groupedProducts;

    @Column(name = "upsells")
    private String upsells;

    @Column(name = "cross_sells")
    private String crossSells;

    @Column(name = "external_url")
    private String externalURL;

    @Column(name = "button_text")
    private String buttonText;

    @Column(name = "position")
    private Integer position;

    @Column(name = "brands")
    private String brands;
    
    @Column(name = "attribute_1_name")
    private String attribute1Name;

    @Column(name = "attribute_1_value")
    private String attribute1Value;

    @Column(name = "attribute_1_visible")
    private Integer attribute1Visible;

    @Column(name = "attribute_1_global")
    private Integer attribute1Global;
 
    @Column(name = "attribute_2_name")
    private String attribute2Name;

    @Column(name = "attribute_2_value")
    private String attribute2Value;

    @Column(name = "attribute_2_visible")
    private Integer attribute2Visible;

    @Column(name = "attribute_2_global")
    private Integer attribute2Global;

    @Column(name = "attribute_3_name")
    private String attribute3Name;

    @Column(name = "attribute_3_value")
    private String attribute3Value;

    @Column(name = "attribute_3_visible")
    private Integer attribute3Visible;

    @Column(name = "attribute_3_global")
    private Integer attribute3Global;

    @Column(name = "attribute_4_name")
    private String attribute4Name;

    @Column(name = "attribute_4_value")
    private String attribute4Value;

    @Column(name = "attribute_4_visible")
    private Integer attribute4Visible;

    @Column(name = "attribute_4_global")
    private Integer attribute4Global;

    @Column(name = "attribute_5_name")
    private String attribute5Name;

    @Column(name = "attribute_5_value")
    private String attribute5Value;

    @Column(name = "attribute_5_visible")
    private Integer attribute5Visible;

    @Column(name = "attribute_5_global")
    private Integer attribute5Global;

    @Column(name = "attribute_6_name")
    private String attribute6Name;

    @Column(name = "attribute_6_value")
    private String attribute6Value;

    @Column(name = "attribute_6_visible")
    private Integer attribute6Visible;

    @Column(name = "attribute_6_global")
    private Integer attribute6Global;

    @Column(name = "attribute_7_name")
    private String attribute7Name;

    @Column(name = "attribute_7_value")
    private String attribute7Value;

    @Column(name = "attribute_7_visible")
    private Integer attribute7Visible;

    @Column(name = "attribute_7_global")
    private Integer attribute7Global;

    @Column(name = "attribute_8_name")
    private String attribute8Name;

    @Column(name = "attribute_8_value")
    private String attribute8Value;

    @Column(name = "attribute_8_visible")
    private Integer attribute8Visible;

    @Column(name = "attribute_8_global")
    private Integer attribute8Global;

    @Column(name = "attribute_9_name")
    private String attribute9Name;

    @Column(name = "attribute_9_value")
    private String attribute9Value;

    @Column(name = "attribute_9_visible")
    private Integer attribute9Visible;

    @Column(name = "attribute_9_global")
    private Integer attribute9Global;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "product_product_category", // bảng mới, kết nối giữa product và product category
        joinColumns = @JoinColumn(name = "product_id"), // khóa tham chiếu
        inverseJoinColumns = @JoinColumn(name = "product_category_id") // khóa tham chiếu
    )
    @JsonManagedReference
    private Set<ProductCategory> productCategories;

    // Getter & Setter
    public Product () {}

    public Product(
        String type,
        String sku,
        String gtin,
        String name,
        Integer published,
        Integer isFeatured,
        String visibilityInCatalog,
        String shortDescription,
        String description,
        String dateSalePriceStarts,
        String dateSalePriceEnds,
        String taxStatus,
        String taxClass,
        Integer inStock,
        Integer stock,
        Integer lowStockAmount,
        Integer backordersAllowed,
        Integer soldIndividually,
        Integer weight,
        Integer length,
        Integer width,
        Integer height,
        Integer allowCustomerReviews,
        String purchaseNote,
        Integer salePrice,
        Integer regularPrice,
        String categories,
        String tags,
        String shippingClass,
        String images,
        String downloadLimit,
        Integer downloadExpiryDays,
        String parent,
        String groupedProducts,
        String upsells,
        String crossSells,
        String externalURL,
        String buttonText,
        Integer position,
        String brands,
        String attribute1Name,
        String attribute1Value,
        Integer attribute1Visible,
        Integer attribute1Global,
        String attribute2Name,
        String attribute2Value,
        Integer attribute2Visible,
        Integer attribute2Global,
        String attribute3Name,
        String attribute3Value,
        Integer attribute3Visible,
        Integer attribute3Global,
        String attribute4Name,
        String attribute4Value,
        Integer attribute4Visible,
        Integer attribute4Global,
        String attribute5Name,
        String attribute5Value,
        Integer attribute5Visible,
        Integer attribute5Global,
        String attribute6Name,
        String attribute6Value,
        Integer attribute6Visible,
        Integer attribute6Global,
        String attribute7Name,
        String attribute7Value,
        Integer attribute7Visible,
        Integer attribute7Global,
        String attribute8Name,
        String attribute8Value,
        Integer attribute8Visible,
        Integer attribute8Global,
        String attribute9Name,
        String attribute9Value,
        Integer attribute9Visible,
        Integer attribute9Global
    ) {
        this.type = type;
        this.sku = sku;
        this.gtin = gtin;
        this.name = name;
        this.published = published;
        this.isFeatured = isFeatured;
        this.visibilityInCatalog = visibilityInCatalog;
        this.shortDescription = shortDescription;
        this.description = description;
        this.dateSalePriceStarts = dateSalePriceStarts;
        this.dateSalePriceEnds = dateSalePriceEnds;
        this.taxStatus = taxStatus;
        this.taxClass = taxClass;
        this.inStock = inStock;
        this.stock = stock;
        this.lowStockAmount = lowStockAmount;
        this.backordersAllowed = backordersAllowed;
        this.soldIndividually = soldIndividually;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.allowCustomerReviews = allowCustomerReviews;
        this.purchaseNote = purchaseNote;
        this.salePrice = salePrice;
        this.regularPrice = regularPrice;
        this.categories = categories;
        this.tags = tags;
        this.shippingClass = shippingClass;
        this.images = images;
        this.downloadLimit = downloadLimit;
        this.downloadExpiryDays = downloadExpiryDays;
        this.parent = parent;
        this.groupedProducts = groupedProducts;
        this.upsells = upsells;
        this.crossSells = crossSells;
        this.externalURL = externalURL;
        this.buttonText = buttonText;
        this.position = position;
        this.brands = brands;
        this.attribute1Name = attribute1Name;
        this.attribute1Value = attribute1Value;
        this.attribute1Visible = attribute1Visible;
        this.attribute1Global = attribute1Global;
        this.attribute2Name = attribute2Name;
        this.attribute2Value = attribute2Value;
        this.attribute2Visible = attribute2Visible;
        this.attribute2Global = attribute2Global;
        this.attribute3Name = attribute3Name;
        this.attribute3Value = attribute3Value;
        this.attribute3Visible = attribute3Visible;
        this.attribute3Global = attribute3Global;
        this.attribute4Name = attribute4Name;
        this.attribute4Value = attribute4Value;
        this.attribute4Visible = attribute4Visible;
        this.attribute4Global = attribute4Global;
        this.attribute5Name = attribute5Name;
        this.attribute5Value = attribute5Value;
        this.attribute5Visible = attribute5Visible;
        this.attribute5Global = attribute5Global;
        this.attribute6Name = attribute6Name;
        this.attribute6Value = attribute6Value;
        this.attribute6Visible = attribute6Visible;
        this.attribute6Global = attribute6Global;
        this.attribute7Name = attribute7Name;
        this.attribute7Value = attribute7Value;
        this.attribute7Visible = attribute7Visible;
        this.attribute7Global = attribute7Global;
        this.attribute8Name = attribute8Name;
        this.attribute8Value = attribute8Value;
        this.attribute8Visible = attribute8Visible;
        this.attribute8Global = attribute8Global;
        this.attribute9Name = attribute9Name;
        this.attribute9Value = attribute9Value;
        this.attribute9Visible = attribute9Visible;
        this.attribute9Global = attribute9Global;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(Set<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }
}
