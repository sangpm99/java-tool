package com.tool.service;

import com.tool.dto.PagedResponse;
import com.tool.dto.product.CreateProductsRequest;
import com.tool.dto.product.GetProductsRequest;
import com.tool.dto.ProductRequest;
import com.tool.model.Product;
import com.tool.model.ProductCategory;
import com.tool.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import com.tool.repository.ProductCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    // hàm bổ trợ cho get all
    public List<Product> getProducts(String searchValue, List<Long> categoryIds, int limit) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            // Nếu không có category → query khác, bỏ phần IN
            return productRepository.findRandomProductsWithoutCategory(searchValue, limit);
        }
        return productRepository.findRandomProductsByCategories(searchValue, categoryIds, limit);
    }

    // get all
    public PagedResponse<Product> getProducts(GetProductsRequest request) {
        // 1. Lấy các tham số từ request
        String searchValue = request.getSearchValue();
        Integer pageSize = (request.getPageSize() != null && request.getPageSize() > 0) 
                   ? request.getPageSize() 
                   : 20;
        List<Long> productCategoryIds = request.getProductCategoryIds();

        // 2. Lấy 20 sản phẩm random lọc searchValue & category
        List<Product> products = getProducts(searchValue, productCategoryIds, pageSize);

        // 3. Đếm số lượng sản phẩm trong bảng
        long totalCount = productRepository.countSimpleAndVariableProducts();

        // 6. Xử lý variable -> chèn variation ngay sau
        List<Product> finalProducts = new ArrayList<>();
        for (Product p : products) {
            finalProducts.add(p);
            if ("variable".equals(p.getType())) {
                String parentValue = "id:" + p.getId();
                List<Product> variations = productRepository.findAll((root, query, cb) ->
                    cb.and(
                        cb.equal(root.get("type"), "variation"),
                        cb.equal(root.get("parent"), parentValue)
                    )
                );
                finalProducts.addAll(variations);
            }
        }

        PagedResponse<Product> response = new PagedResponse<>(
            pageSize,
            totalCount,
            finalProducts,
            ""
        );

        return response;
    }

    // get one
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // create
    public void createProduct (CreateProductsRequest body) {
        List<ProductRequest> products = body.getProducts();

        Product parentVariable = null;

        for(ProductRequest p: products) {
            Product product = new Product(
                p.getType(),
                p.getSku(),
                p.getGtin(),
                p.getName(),
                p.getPublished(),
                p.getIsFeatured(),
                p.getVisibilityInCatalog(),
                p.getShortDescription(),
                p.getDescription(),
                p.getDateSalePriceStarts(),
                p.getDateSalePriceEnds(),
                p.getTaxStatus(),
                p.getTaxClass(),
                p.getInStock(),
                p.getStock(),
                p.getLowStockAmount(),
                p.getBackordersAllowed(),
                p.getSoldIndividually(),
                p.getWeight(),
                p.getLength(),
                p.getWidth(),
                p.getHeight(),
                p.getAllowCustomerReviews(),
                p.getPurchaseNote(),
                p.getSalePrice(),
                p.getRegularPrice(),
                p.getCategories(),
                p.getTags(),
                p.getShippingClass(),
                p.getImages(),
                p.getDownloadLimit(),
                p.getDownloadExpiryDays(),
                p.getParent(),
                p.getGroupedProducts(),
                p.getUpsells(),
                p.getCrossSells(),
                p.getExternalURL(),
                p.getButtonText(),
                p.getPosition(),
                p.getBrands(),
                p.getAttribute1Name(),
                p.getAttribute1Value(),
                p.getAttribute1Visible(),
                p.getAttribute1Global(),
                p.getAttribute2Name(),
                p.getAttribute2Value(),
                p.getAttribute2Visible(),
                p.getAttribute2Global(),
                p.getAttribute3Name(),
                p.getAttribute3Value(),
                p.getAttribute3Visible(),
                p.getAttribute3Global(),
                p.getAttribute4Name(),
                p.getAttribute4Value(),
                p.getAttribute4Visible(),
                p.getAttribute4Global(),
                p.getAttribute5Name(),
                p.getAttribute5Value(),
                p.getAttribute5Visible(),
                p.getAttribute5Global(),
                p.getAttribute6Name(),
                p.getAttribute6Value(),
                p.getAttribute6Visible(),
                p.getAttribute6Global(),
                p.getAttribute7Name(),
                p.getAttribute7Value(),
                p.getAttribute7Visible(),
                p.getAttribute7Global(),
                p.getAttribute8Name(),
                p.getAttribute8Value(),
                p.getAttribute8Visible(),
                p.getAttribute8Global(),
                p.getAttribute9Name(),
                p.getAttribute9Value(),
                p.getAttribute9Visible(),
                p.getAttribute9Global()
            );

            if("variable".equals(product.getType())) {
                Set<Long> productCategoryIds = p.getProductCategoryIds();
                Set<ProductCategory> productCategories = new HashSet<>();
                for (Long categoryId : productCategoryIds) {
                    Optional<ProductCategory> optionalCategory = productCategoryRepository.findById(categoryId);

                    if (optionalCategory.isPresent()) {
                        ProductCategory category = optionalCategory.get();
                        productCategories.add(category);
                    }
                }

                product.setProductCategories(productCategories);
                parentVariable = productRepository.save(product);
            }

            else if ("variation".equals(product.getType())) {
                if(parentVariable == null) {
                    throw new RuntimeException("Cannot create variation without a variable parent");
                }
                product.setParent("id:" + parentVariable.getId());
                productRepository.save(product);
            }

            // nếu là simple hoặc gì khác save bình thường
            else {
                Set<Long> productCategoryIds = p.getProductCategoryIds();
                Set<ProductCategory> productCategories = new HashSet<>();
                for (Long categoryId : productCategoryIds) {
                    Optional<ProductCategory> optionalCategory = productCategoryRepository.findById(categoryId);

                    if (optionalCategory.isPresent()) {
                        ProductCategory category = optionalCategory.get();
                        productCategories.add(category);
                    }
                }

                product.setProductCategories(productCategories);
                productRepository.save(product);
            }
        }
    }

    // update
    public void updateProduct (Long id, ProductRequest body) {
        Product existing = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        String type = body.getType();
        String sku = body.getSku();
        String gtin = body.getGtin();
        String name = body.getName();
        Integer published = body.getPublished();
        Integer isFeatured = body.getIsFeatured();
        String visibilityInCatalog = body.getVisibilityInCatalog();
        String shortDescription = body.getShortDescription();
        String description = body.getDescription();
        String dateSalePriceStarts = body.getDateSalePriceStarts();
        String dateSalePriceEnds = body.getDateSalePriceEnds();
        String taxStatus = body.getTaxStatus();
        String taxClass = body.getTaxClass();
        Integer inStock = body.getInStock();
        Integer stock = body.getStock();
        Integer lowStockAmount = body.getLowStockAmount();
        Integer backordersAllowed = body.getBackordersAllowed();
        Integer soldIndividually = body.getSoldIndividually();
        Integer weight = body.getWeight();
        Integer length = body.getLength();
        Integer width = body.getWidth();
        Integer height = body.getHeight();
        Integer allowCustomerReviews = body.getAllowCustomerReviews();
        String purchaseNote = body.getPurchaseNote();
        Integer salePrice = body.getSalePrice();
        Integer regularPrice = body.getRegularPrice();
        String categories = body.getCategories();
        String tags = body.getTags();
        String shippingClass = body.getShippingClass();
        String images = body.getImages();
        String downloadLimit = body.getDownloadLimit();
        Integer downloadExpiryDays = body.getDownloadExpiryDays();
        String parent = body.getParent();
        String groupedProducts = body.getGroupedProducts();
        String upsells = body.getUpsells();
        String crossSells = body.getCrossSells();
        String externalURL = body.getExternalURL();
        String buttonText = body.getButtonText();
        Integer position = body.getPosition();
        String brands = body.getBrands();

        String attribute1Name = body.getAttribute1Name();
        String attribute1Value = body.getAttribute1Value();
        Integer attribute1Visible = body.getAttribute1Visible();
        Integer attribute1Global = body.getAttribute1Global();

        String attribute2Name = body.getAttribute2Name();
        String attribute2Value = body.getAttribute2Value();
        Integer attribute2Visible = body.getAttribute2Visible();
        Integer attribute2Global = body.getAttribute2Global();

        String attribute3Name = body.getAttribute3Name();
        String attribute3Value = body.getAttribute3Value();
        Integer attribute3Visible = body.getAttribute3Visible();
        Integer attribute3Global = body.getAttribute3Global();

        String attribute4Name = body.getAttribute4Name();
        String attribute4Value = body.getAttribute4Value();
        Integer attribute4Visible = body.getAttribute4Visible();
        Integer attribute4Global = body.getAttribute4Global();

        String attribute5Name = body.getAttribute5Name();
        String attribute5Value = body.getAttribute5Value();
        Integer attribute5Visible = body.getAttribute5Visible();
        Integer attribute5Global = body.getAttribute5Global();

        String attribute6Name = body.getAttribute6Name();
        String attribute6Value = body.getAttribute6Value();
        Integer attribute6Visible = body.getAttribute6Visible();
        Integer attribute6Global = body.getAttribute6Global();

        String attribute7Name = body.getAttribute7Name();
        String attribute7Value = body.getAttribute7Value();
        Integer attribute7Visible = body.getAttribute7Visible();
        Integer attribute7Global = body.getAttribute7Global();

        String attribute8Name = body.getAttribute8Name();
        String attribute8Value = body.getAttribute8Value();
        Integer attribute8Visible = body.getAttribute8Visible();
        Integer attribute8Global = body.getAttribute8Global();

        String attribute9Name = body.getAttribute9Name();
        String attribute9Value = body.getAttribute9Value();
        Integer attribute9Visible = body.getAttribute9Visible();
        Integer attribute9Global = body.getAttribute9Global();

        existing.setType(type);
        existing.setSku(sku);
        existing.setGtin(gtin);
        existing.setName(name);
        existing.setPublished(published);
        existing.setIsFeatured(isFeatured);
        existing.setVisibilityInCatalog(visibilityInCatalog);
        existing.setShortDescription(shortDescription);
        existing.setDescription(description);
        existing.setDateSalePriceStarts(dateSalePriceStarts);
        existing.setDateSalePriceEnds(dateSalePriceEnds);
        existing.setTaxStatus(taxStatus);
        existing.setTaxClass(taxClass);
        existing.setInStock(inStock);
        existing.setStock(stock);
        existing.setLowStockAmount(lowStockAmount);
        existing.setBackordersAllowed(backordersAllowed);
        existing.setSoldIndividually(soldIndividually);
        existing.setWeight(weight);
        existing.setLength(length);
        existing.setWidth(width);
        existing.setHeight(height);
        existing.setAllowCustomerReviews(allowCustomerReviews);
        existing.setPurchaseNote(purchaseNote);
        existing.setSalePrice(salePrice);
        existing.setRegularPrice(regularPrice);
        existing.setCategories(categories);
        existing.setTags(tags);
        existing.setShippingClass(shippingClass);
        existing.setImages(images);
        existing.setDownloadLimit(downloadLimit);
        existing.setDownloadExpiryDays(downloadExpiryDays);
        existing.setParent(parent);
        existing.setGroupedProducts(groupedProducts);
        existing.setUpsells(upsells);
        existing.setCrossSells(crossSells);
        existing.setExternalURL(externalURL);
        existing.setButtonText(buttonText);
        existing.setPosition(position);
        existing.setBrands(brands);
        existing.setAttribute1Name(attribute1Name);
        existing.setAttribute1Value(attribute1Value);
        existing.setAttribute1Visible(attribute1Visible);
        existing.setAttribute1Global(attribute1Global);
        existing.setAttribute2Name(attribute2Name);
        existing.setAttribute2Value(attribute2Value);
        existing.setAttribute2Visible(attribute2Visible);
        existing.setAttribute2Global(attribute2Global);
        existing.setAttribute3Name(attribute3Name);
        existing.setAttribute3Value(attribute3Value);
        existing.setAttribute3Visible(attribute3Visible);
        existing.setAttribute3Global(attribute3Global);
        existing.setAttribute4Name(attribute4Name);
        existing.setAttribute4Value(attribute4Value);
        existing.setAttribute4Visible(attribute4Visible);
        existing.setAttribute4Global(attribute4Global);
        existing.setAttribute5Name(attribute5Name);
        existing.setAttribute5Value(attribute5Value);
        existing.setAttribute5Visible(attribute5Visible);
        existing.setAttribute5Global(attribute5Global);
        existing.setAttribute6Name(attribute6Name);
        existing.setAttribute6Value(attribute6Value);
        existing.setAttribute6Visible(attribute6Visible);
        existing.setAttribute6Global(attribute6Global);
        existing.setAttribute7Name(attribute7Name);
        existing.setAttribute7Value(attribute7Value);
        existing.setAttribute7Visible(attribute7Visible);
        existing.setAttribute7Global(attribute7Global);
        existing.setAttribute8Name(attribute8Name);
        existing.setAttribute8Value(attribute8Value);
        existing.setAttribute8Visible(attribute8Visible);
        existing.setAttribute8Global(attribute8Global);
        existing.setAttribute9Name(attribute9Name);
        existing.setAttribute9Value(attribute9Value);
        existing.setAttribute9Visible(attribute9Visible);
        existing.setAttribute9Global(attribute9Global);

        Set<Long> productCategoryIds = body.getProductCategoryIds();
        Set<ProductCategory> productCategories = new HashSet<>();
        for (Long categoryId : productCategoryIds) {
            Optional<ProductCategory> optionalCategory = productCategoryRepository.findById(categoryId);

            if (optionalCategory.isPresent()) {
                ProductCategory category = optionalCategory.get();
                productCategories.add(category);
            }
        }

        existing.setProductCategories(productCategories);
        productRepository.save(existing);
    }

    // delete
    public void deleteProduct (Long id) {
        Product existing = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        // Nếu là variable thì xóa luôn các variation có parent = "id:<id>"
        if ("variable".equals(existing.getType())) {
            String parentValue = "id:" + id;
            productRepository.deleteVariationsByParent(parentValue);
        }

        // Xóa product chính
        productRepository.delete(existing);
    }
}
