package id.co.nds.catalogue.models;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import id.co.nds.catalogue.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.catalogue.controllers.ControllerGroup.PostingNew;
import id.co.nds.catalogue.controllers.ControllerGroup.RequestMethodById;

public class RecordModel {
    @PositiveOrZero(message = "Product Stock must not be less than 0", 
    groups = { PostingNew.class,
            RequestMethodById.class })
    private Integer actorId;

    @Pattern(regexp = "^[A,a,N,n]$", message = "Product recStatus can only be inputted by 'A' or 'N' ", groups = {
            GettingAllByCriteria.class
    })
    private String recStatus;

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
