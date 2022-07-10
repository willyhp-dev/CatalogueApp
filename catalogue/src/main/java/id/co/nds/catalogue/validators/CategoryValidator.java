package id.co.nds.catalogue.validators;

import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.globals.GlobalConstant;

public class CategoryValidator {

    public void nullCheckCategoryId(String id) throws ClientException {

        if (id == null) {
            throw new ClientException("Category id is Required");
        }
    }

    public void notnullCheckCategoryId(String id) throws ClientException {

        if (id != null) {
            throw new ClientException(
                    "Category id is auto generate , do not input id");
        }
    }

    public void nullCheckName(String name) throws ClientException {

        if (name == null) {
            throw new ClientException("Category name is Required");
        }
    }

    public void nullCheckObject(Object o) throws ClientException {

        if (o == null) {
            throw new ClientException("Category  is not found");
        }
    }

    public void validateCategoryId(String id) throws ClientException {

        if (id.length() != 6 || !id.startsWith("PC")) {
            throw new ClientException(
                    "Category  id must contains six digits and stats with 'PC' ");
        }
    }

    public void validateName(String name) throws ClientException {
        if (name.trim().equalsIgnoreCase("")) {
            throw new ClientException("Category name  is Required");
        }
    }

    public void validateRecStatus(String id, String recStatus)
            throws ClientException {
        if (recStatus.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException(
                    "Product with id = " + id + "is already been deleted. ");
        }
    }

}
