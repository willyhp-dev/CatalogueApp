package id.co.nds.catalogue.validators;

import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;

public class UserValidator {

    public void nullCheckUserId(Integer id) throws ClientException {
        if (id == null) {
            throw new ClientException("User id is Required");
        }
    }

    public void notNullCheckUserId(Integer id) throws ClientException {

        if (id != null) {
            throw new ClientException(
                    "User id is Auto genereated, do not input id");
        }
    }

    public void nullCheckUserFullname(String fullname) throws ClientException {
        if (fullname == null) {
            throw new ClientException("User fullname is required");
        }
    }

    public void nullCheckUserRoleId(String roleId) throws ClientException {

        if (roleId == null) {
            throw new ClientException("User roleId is required");
        }
    }

    public void nullCheckUserCallNumber(String callNumber)
            throws ClientException {

        if (callNumber == null) {
            throw new ClientException("User Number is required");
        }
    }

    public void nullCheckObject(Object o) throws NotFoundException {
        if (o == null) {
            throw new NotFoundException("User is Not required");
        }
    }

    public void validateUserId(Integer id) throws ClientException {
        if (id == 0) {
            throw new ClientException("User id input is invalid");
        }
    }

    public void validateFullname(String fullname) throws ClientException {

        if (fullname.trim().equalsIgnoreCase("")) {
            throw new ClientException("User fullname is Required");
        }
    }

    public void validateroleId(String roleId) throws ClientException {

        if (roleId.length() < 5 && !roleId.startsWith("R")) {
            throw new ClientException(
                    "User role id constains 5 digits and starts with 'R' ");

        } else {
            String number = roleId.substring(1);

            try {
                Long.parseLong(number);
            } catch (NumberFormatException e) {
                // TODO: handle exception
                throw new ClientException("User call Number must be numeric");
            }
        }
    }

    public void validateCallNumber(String callNumber) throws ClientException {

        if (callNumber.startsWith("0") || callNumber.startsWith("+62")) {
            if (callNumber.startsWith("0")) {
                String number = callNumber.substring(1);

                try {
                    Long.parseLong(number);
                } catch (NumberFormatException e) {
                    // TODO: handle exception
                    throw new ClientException(
                            "User call Number must be numeric");
                }

                if (number.length() < 9 || number.length() > 12) {
                    throw new ClientException(
                            "User call Number  must Contains  9 - 12 Digits  ");
                }

            } else if (callNumber.startsWith("+62")) {

                String number = callNumber.substring(3);
                String hasil = "0" + number;
                try {
                    Long.parseLong(hasil);
                } catch (NumberFormatException e) {
                    // TODO: handle exception
                    throw new ClientException(
                            "User call Number must be numeric");
                }

                if (number.length() < 9 || number.length() > 12) {
                    throw new ClientException(
                            "User call Number  must Contains  9 - 12 Digits  ");
                }
            }
        } else {
            throw new ClientException(
                    "User call Number  must start with '0' or '+62' ");
        }

    }

    public void validateRecStatus(String id, String recStatus)
            throws ClientException {
        throw new ClientException(
                "User with id =" + id + "is already been deleted");
    }

}
