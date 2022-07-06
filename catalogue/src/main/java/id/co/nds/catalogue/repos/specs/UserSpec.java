package id.co.nds.catalogue.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.catalogue.globals.GlobalConstant;
import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.models.Usermodel;

public class UserSpec implements Specification<UserEntity> {

    private Usermodel usermodel;

    public UserSpec(Usermodel usermodel) {
        super();
        this.usermodel = usermodel;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {
        // TODO Auto-generated method stub
        Predicate p = cb.and();

        if (usermodel.getId() != null && usermodel.getId() != 0) {
            p.getExpressions().add(cb.equal(root.get("id"), usermodel.getId()));
        }

        if (usermodel.getFullname() != null && usermodel.getFullname().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("fullname")), "%" + usermodel.getFullname().toLowerCase() + "%"));
        }

        if (usermodel.getRecStatus() != null
                && (usermodel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_ACTIVE)
                        || usermodel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE))) {
            p.getExpressions()
                    .add(cb.equal(cb.upper(root.get("recStatus")), usermodel.getRecStatus().toUpperCase()));
        }

        cq.orderBy(cb.asc(root.get("id")));
        return p;
    }

}
