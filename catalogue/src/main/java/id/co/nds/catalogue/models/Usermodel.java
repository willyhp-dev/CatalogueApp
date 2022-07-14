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

public class Usermodel {

    @Null(message = "User id must be null", groups = { PostingNew.class })
    @NotNull(message = "User id cannot be null", groups = { UpdatingById.class,
            DeletingById.class })
    @PositiveOrZero(message = "User id must be not be less than 0", groups = {
            GettingAllByCriteria.class })
    private Integer id;
    @NotBlank(message = "User fullname cannot be blank", groups = {
            PostingNew.class })
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$", message = "User Fullname pattern must be words only", groups = {
            PostingNew.class, GettingAllByCriteria.class, UpdatingById.class })
    private String fullname;

    @NotNull(message = "User role id cannot be null", groups = {
            PostingNew.class })
    @Pattern(regexp = "^(R)[0-9]{4}$", message = "User Role id pattern input is invalid", groups = {
            PostingNew.class, GettingAllByCriteria.class, UpdatingById.class })
    private String roleId;

    @Pattern(regexp = "^[0,+62][0-9]{9,12}$", message = "User Call Number pattern input is invalid ,Must be numeric, and Start with 0 , +62", groups = {
            PostingNew.class, GettingAllByCriteria.class, UpdatingById.class })
    private String callnumber;

    @PositiveOrZero(message = "User must not be less than 0", groups = {
            PostingNew.class, RequestMethodById.class })
    private Integer actorId;

    @Pattern(regexp = "^[A,a,N,n]$", message = "Product recStatus can only be inputted by 'A' or 'N' ", groups = {
            GettingAllByCriteria.class })
    private String recStatus;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getFullname() { return fullname; }

    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getRoleId() { return roleId; }

    public void setRoleId(String roleId) { this.roleId = roleId; }

    public String getCallnumber() { return callnumber; }

    public void setCallnumber(String callnumber) {
        this.callnumber = callnumber;
    }

    public Integer getActorId() { return actorId; }

    public void setActorId(Integer actorId) { this.actorId = actorId; }

    public String getRecStatus() { return recStatus; }

    public void setRecStatus(String recStatus) { this.recStatus = recStatus; }

}
