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
                p.getPublished() != null ? Integer.valueOf(p.getPublished()) : null,
                p.getIsFeatured() != null ? Integer.valueOf(p.getIsFeatured()) : null,
                p.getVisibilityInCatalog(),
                p.getShortDescription(),
                p.getDescription(),
                p.getDateSalePriceStarts(),
                p.getDateSalePriceEnds(),
                p.getTaxStatus(),
                p.getTaxClass(),
                p.getInStock() != null ? Integer.valueOf(p.getInStock()) : null,
                p.getStock() != null ? Integer.valueOf(p.getStock()) : null,
                p.getLowStockAmount() != null ? Integer.valueOf(p.getLowStockAmount()) : null,
                p.getBackordersAllowed() != null ? Integer.valueOf(p.getBackordersAllowed()) : null,
                p.getSoldIndividually() != null ? Integer.valueOf(p.getSoldIndividually()) : null,
                p.getWeight() != null ? Integer.valueOf(p.getWeight()) : null,
                p.getLength() != null ? Integer.valueOf(p.getLength()) : null,
                p.getWidth() != null ? Integer.valueOf(p.getWidth()) : null,
                p.getHeight() != null ? Integer.valueOf(p.getHeight()) : null,
                p.getAllowCustomerReviews() != null ? Integer.valueOf(p.getAllowCustomerReviews()) : null,
                p.getPurchaseNote(),
                p.getSalePrice() != null ? Integer.valueOf(p.getSalePrice()) : null,
                p.getRegularPrice() != null ? Integer.valueOf(p.getRegularPrice()) : null,
                p.getCategories(),
                p.getTags(),
                p.getShippingClass(),
                p.getImages(),
                p.getDownloadLimit(),
                p.getDownloadExpiryDays() != null ? Integer.valueOf(p.getDownloadExpiryDays()) : null,
                p.getParent(),
                p.getGroupedProducts(),
                p.getUpsells(),
                p.getCrossSells(),
                p.getExternalURL(),
                p.getButtonText(),
                p.getPosition() != null ? Integer.valueOf(p.getPosition()) : null,
                p.getBrands(),
                p.getAttribute1Name(),
                p.getAttribute1Value(),
                p.getAttribute1Visible() != null ? Integer.valueOf(p.getAttribute1Visible()) : null,
                p.getAttribute1Global() != null ? Integer.valueOf(p.getAttribute1Global()) : null,
                p.getAttribute2Name(),
                p.getAttribute2Value(),
                p.getAttribute2Visible() != null ? Integer.valueOf(p.getAttribute2Visible()) : null,
                p.getAttribute2Global() != null ? Integer.valueOf(p.getAttribute2Global()) : null,
                p.getAttribute3Name(),
                p.getAttribute3Value(),
                p.getAttribute3Visible() != null ? Integer.valueOf(p.getAttribute3Visible()) : null,
                p.getAttribute3Global() != null ? Integer.valueOf(p.getAttribute3Global()) : null,
                p.getAttribute4Name(),
                p.getAttribute4Value(),
                p.getAttribute4Global() != null ? Integer.valueOf(p.getAttribute4Global()) : null,
                p.getAttribute4Visible() != null ? Integer.valueOf(p.getAttribute4Visible()) : null,
                p.getAttribute5Name(),
                p.getAttribute5Value(),
                p.getAttribute5Visible() != null ? Integer.valueOf(p.getAttribute5Visible()) : null,
                p.getAttribute5Global() != null ? Integer.valueOf(p.getAttribute5Global()) : null,
                p.getAttribute6Name(),
                p.getAttribute6Value(),
                p.getAttribute6Visible() != null ? Integer.valueOf(p.getAttribute6Visible()) : null,
                p.getAttribute6Global() != null ? Integer.valueOf(p.getAttribute6Global()) : null,
                p.getAttribute7Name(),
                p.getAttribute7Value(),
                p.getAttribute7Visible() != null ? Integer.valueOf(p.getAttribute7Visible()) : null,
                p.getAttribute7Global() != null ? Integer.valueOf(p.getAttribute7Global()) : null,
                p.getAttribute8Name(),
                p.getAttribute8Value(),
                p.getAttribute8Visible() != null ? Integer.valueOf(p.getAttribute8Visible()) : null,
                p.getAttribute8Global() != null ? Integer.valueOf(p.getAttribute8Global()) : null,
                p.getAttribute9Name(),
                p.getAttribute9Value(),
                p.getAttribute9Visible() != null ? Integer.valueOf(p.getAttribute9Visible()) : null,
                p.getAttribute9Global() != null ? Integer.valueOf(p.getAttribute9Global()) : null
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
    public void updateProduct (Long id, ProductRequest p) {
        Product existing = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        String type = p.getType();
        String sku = p.getSku();
        String gtin = p.getGtin();
        String name = p.getName();
        Integer published = p.getPublished() != null ? Integer.valueOf(p.getPublished()) : null;
        Integer isFeatured = p.getIsFeatured() != null ? Integer.valueOf(p.getIsFeatured()) : null;
        String visibilityInCatalog = p.getVisibilityInCatalog();
        String shortDescription = p.getShortDescription();
        String description = p.getDescription();
        String dateSalePriceStarts = p.getDateSalePriceStarts();
        String dateSalePriceEnds = p.getDateSalePriceEnds();
        String taxStatus = p.getTaxStatus();
        String taxClass = p.getTaxClass();
        Integer inStock = p.getInStock() != null ? Integer.valueOf(p.getInStock()) : null;
        Integer stock = p.getStock() != null ? Integer.valueOf(p.getStock()) : null;
        Integer lowStockAmount = p.getLowStockAmount() != null ? Integer.valueOf(p.getLowStockAmount()) : null;
        Integer backordersAllowed = p.getBackordersAllowed() != null ? Integer.valueOf(p.getBackordersAllowed()) : null;
        Integer soldIndividually = p.getSoldIndividually() != null ? Integer.valueOf(p.getSoldIndividually()) : null;
        Integer weight = p.getWeight() != null ? Integer.valueOf(p.getWeight()) : null;
        Integer length = p.getLength() != null ? Integer.valueOf(p.getLength()) : null;
        Integer width = p.getWidth() != null ? Integer.valueOf(p.getWidth()) : null;
        Integer height = p.getHeight() != null ? Integer.valueOf(p.getHeight()) : null;
        Integer allowCustomerReviews = p.getAllowCustomerReviews() != null ? Integer.valueOf(p.getAllowCustomerReviews()) : null;
        String purchaseNote = p.getPurchaseNote();
        Integer salePrice = p.getSalePrice() != null ? Integer.valueOf(p.getSalePrice()) : null;
        Integer regularPrice = p.getRegularPrice() != null ? Integer.valueOf(p.getRegularPrice()) : null;
        String categories = p.getCategories();
        String tags = p.getTags();
        String shippingClass = p.getShippingClass();
        String images = p.getImages();
        String downloadLimit = p.getDownloadLimit();
        Integer downloadExpiryDays = p.getDownloadExpiryDays() != null ? Integer.valueOf(p.getDownloadExpiryDays()) : null;
        String parent = p.getParent();
        String groupedProducts = p.getGroupedProducts();
        String upsells = p.getUpsells();
        String crossSells = p.getCrossSells();
        String externalURL = p.getExternalURL();
        String buttonText = p.getButtonText();
        Integer position = p.getPosition() != null ? Integer.valueOf(p.getPosition()) : null;
        String brands = p.getBrands();

        String attribute1Name = p.getAttribute1Name();
        String attribute1Value = p.getAttribute1Value();
        Integer attribute1Visible = p.getAttribute1Visible() != null ? Integer.valueOf(p.getAttribute1Visible()) : null;
        Integer attribute1Global = p.getAttribute1Global() != null ? Integer.valueOf(p.getAttribute1Global()) : null;

        String attribute2Name = p.getAttribute2Name();
        String attribute2Value = p.getAttribute2Value();
        Integer attribute2Visible = p.getAttribute2Visible() != null ? Integer.valueOf(p.getAttribute2Visible()) : null;
        Integer attribute2Global = p.getAttribute2Global() != null ? Integer.valueOf(p.getAttribute2Global()) : null;

        String attribute3Name = p.getAttribute3Name();
        String attribute3Value = p.getAttribute3Value();
        Integer attribute3Visible = p.getAttribute3Visible() != null ? Integer.valueOf(p.getAttribute3Visible()) : null;
        Integer attribute3Global = p.getAttribute3Global() != null ? Integer.valueOf(p.getAttribute3Global()) : null;

        String attribute4Name = p.getAttribute4Name();
        String attribute4Value = p.getAttribute4Value();
        Integer attribute4Visible = p.getAttribute4Visible() != null ? Integer.valueOf(p.getAttribute4Visible()) : null;
        Integer attribute4Global = p.getAttribute4Global() != null ? Integer.valueOf(p.getAttribute4Global()) : null;

        String attribute5Name = p.getAttribute5Name();
        String attribute5Value = p.getAttribute5Value();
        Integer attribute5Visible = p.getAttribute5Visible() != null ? Integer.valueOf(p.getAttribute5Visible()) : null;
        Integer attribute5Global = p.getAttribute5Global() != null ? Integer.valueOf(p.getAttribute5Global()) : null;

        String attribute6Name = p.getAttribute6Name();
        String attribute6Value = p.getAttribute6Value();
        Integer attribute6Visible = p.getAttribute6Visible() != null ? Integer.valueOf(p.getAttribute6Visible()) : null;
        Integer attribute6Global = p.getAttribute6Global() != null ? Integer.valueOf(p.getAttribute6Global()) : null;

        String attribute7Name = p.getAttribute7Name();
        String attribute7Value = p.getAttribute7Value();
        Integer attribute7Visible = p.getAttribute7Visible() != null ? Integer.valueOf(p.getAttribute7Visible()) : null;
        Integer attribute7Global = p.getAttribute7Global() != null ? Integer.valueOf(p.getAttribute7Global()) : null;

        String attribute8Name = p.getAttribute8Name();
        String attribute8Value = p.getAttribute8Value();
        Integer attribute8Visible = p.getAttribute8Visible() != null ? Integer.valueOf(p.getAttribute8Visible()) : null;
        Integer attribute8Global = p.getAttribute8Global() != null ? Integer.valueOf(p.getAttribute8Global()) : null;

        String attribute9Name = p.getAttribute9Name();
        String attribute9Value = p.getAttribute9Value();
        Integer attribute9Visible = p.getAttribute9Visible() != null ? Integer.valueOf(p.getAttribute9Visible()) : null;
        Integer attribute9Global = p.getAttribute9Global() != null ? Integer.valueOf(p.getAttribute9Global()) : null;

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

        Set<Long> productCategoryIds = p.getProductCategoryIds();
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
