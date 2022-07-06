package id.co.nds.catalogue.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.catalogue.entities.ProductEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstant;
import id.co.nds.catalogue.models.ProductModel;
import id.co.nds.catalogue.repos.ProductRepo;
import id.co.nds.catalogue.repos.specs.ProductSpec;
import id.co.nds.catalogue.validators.ProductValidator;

@Service
public class ProductServices {
    @Autowired
    private ProductRepo productRepo;

    ProductValidator productValidator = new ProductValidator();

    public ProductEntity add(ProductModel productModel) throws ClientException {

        productValidator.notNullCheckProductId(productModel.getId());
        productValidator.nullCheckName(productModel.getName());
        productValidator.validateName(productModel.getName());
        productValidator.nullCheckQuantity(productModel.getQuantity());
        productValidator.validateQuantity(productModel.getQuantity());
        productValidator.nullCheckCategoryId(productModel.getCategoryId());
        productValidator.validateCategoryId(productModel.getCategoryId());

        long count = productRepo.countByName(productModel.getName());
        if (count > 0) {
            throw new ClientException("Product name is already Exited");
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productModel.getName());
        productEntity.setQuantity(productModel.getQuantity());
        productEntity.setCategoryId(productModel.getCategoryId());
        productEntity.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        productEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        productEntity.setCreatorId(productModel.getActorId() == null ? 0 : productModel.getActorId());

        return productRepo.save(productEntity);
    }

    public List<ProductEntity> findAll() {
        List<ProductEntity> products = new ArrayList<>();
        productRepo.findAll().forEach(products::add);

        return products;
    }

    public List<ProductEntity> findAllByCriteria(ProductModel productModel) {
        List<ProductEntity> products = new ArrayList<>();
        ProductSpec specs = new ProductSpec(productModel);
        productRepo.findAll(specs).forEach(products::add);

        return products;
    }

    public ProductEntity findById(Integer id) throws ClientException, NotFoundException {

        productValidator.nullCheckProductId(id);
        productValidator.validateProductId(id);

        ProductEntity product = productRepo.findById(id).orElse(null);
        productValidator.nullCheckObject(product);

        return product;
    }

    public ProductEntity edit(ProductModel productModel) throws ClientException, NotFoundException {

        productValidator.nullCheckProductId(productModel.getId());
        productValidator.validateProductId(productModel.getId());

        if (!productRepo.existsById(productModel.getId())) {
            throw new NotFoundException("Cannot find product with id: " + productModel.getId());
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity = findById(productModel.getId());

        if (productModel.getName() != null) {
            productValidator.validateName(productModel.getName());

            Long count = productRepo.countByName(productModel.getName());
            if (count > 0) {
                throw new ClientException("Product name is already existed");
            }

            productEntity.setName(productModel.getName());
        }

        if (productModel.getQuantity() != null) {
            productValidator.validateQuantity(productModel.getQuantity());

            productEntity.setQuantity(productModel.getQuantity());
        }

        if (productModel.getCategoryId() != null) {
            productValidator.validateCategoryId(productModel.getCategoryId());

            productEntity.setCategoryId(productModel.getCategoryId());
        }

        productEntity.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        productEntity.setUpdaterId(
                productModel.getActorId() == null ? 0 : productModel.getActorId());

        return productRepo.save(productEntity);

    }

    public ProductEntity delete(ProductModel productModel) throws ClientException, NotFoundException {
        
        productValidator.nullCheckProductId(productModel.getId());
        productValidator.validateProductId(productModel.getId());

        if (!productRepo.existsById(productModel.getId())) {
            throw new NotFoundException("Cannot find product with id: " + productModel.getId());
        }

        ProductEntity product = new ProductEntity();
        product = findById(productModel.getId());

        if (product.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Product id(" + productModel.getId() + ") is already been deleted.");
        }

        product.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        product.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        product.setDeleterId(
            productModel.getActorId() == null ? 0 : productModel.getActorId()
        );
      
        productRepo.doDelete(product.getId(), product.getDeleterId());

        return product;

        // return productRepo.save(product);

    }
}

