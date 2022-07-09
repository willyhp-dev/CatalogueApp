package id.co.nds.catalogue.validators;

import id.co.nds.catalogue.exceptions.ClientException;

public class RoleValidator {
    public void nullCheckObject(Object o) throws ClientException {

        if (o == null) {
            throw new ClientException("Role  is not found");
        }
    }
}
