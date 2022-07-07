package id.co.nds.catalogue.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import id.co.nds.catalogue.controllers.ControllerGroup.DeletingById;
import id.co.nds.catalogue.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.catalogue.controllers.ControllerGroup.PostingNew;
import id.co.nds.catalogue.controllers.ControllerGroup.RequestMethodById;
import id.co.nds.catalogue.controllers.ControllerGroup.UpdatingById;

public class ProductModel {

    @Null(message = "Product id must be null", groups = { PostingNew.class })
    @NotNull(message = "Product Id cannot be null", groups = { UpdatingById.class, DeletingById.class })
    @PositiveOrZero(message = "Product Id must not be less than 0", groups = { GettingAllByCriteria.class,
            RequestMethodById.class })
    private Integer id;

    @NotBlank(message = "Product Name cannot be blank", groups = { PostingNew.class })
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$", message = "Product Name pattern must be words only", groups = {
            PostingNew.class, GettingAllByCriteria.class, UpdatingById.class })
    private String name;

    @NotNull(message = "Product Quantity cannot be null", groups = { PostingNew.class })
    @PositiveOrZero(message = "Product stock must not be less than 0", groups = { PostingNew.class,
            GettingAllByCriteria.class,
            UpdatingById.class })
    private Integer quantity;

    @NotNull(message = "Category id cannot be null", groups = { PostingNew.class })
    @Pattern(regexp = "^(PC)[0-9]{4}", message = "Product Id pattern input is invalid", groups = {
            GettingAllByCriteria.class, UpdatingById.class, PostingNew.class })
    private String categoryId;

    @PositiveOrZero(message = "Product Stock must not be less than 0", groups = { PostingNew.class,
            RequestMethodById.class })
    private Integer actorId;

    @Pattern(regexp = "^[A,a,N,n]$", message = "Product recStatus can only be inputted by 'A' or 'N' ", groups = {
            GettingAllByCriteria.class
    })
    private String recStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

}
