package id.co.nds.catalogue.models;

public class RoleModel extends RecordModel {

  
    // @NotNull(message = "Product Id cannot be null", groups = { UpdatingById.class, DeletingById.class })
    // @PositiveOrZero(message = "Product Id must not be less than 0", groups = { GettingAllByCriteria.class,
    //         RequestMethodById.class })
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
