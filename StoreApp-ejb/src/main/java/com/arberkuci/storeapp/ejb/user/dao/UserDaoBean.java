package com.arberkuci.storeapp.ejb.user.dao;

import com.arberkuci.storeapp.ejb.user.entity.UserEntity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;


@Stateless
@Local(UserDao.class)
public class UserDaoBean implements UserDao {

    @PersistenceContext(unitName = "store-unit-name")
    EntityManager entityManager;


    private final String className = this.getClass().getName();

    private Logger logger = Logger.getLogger(className);

    @Override
    public UserEntity persistUser(UserEntity userEntity) {
        final String MN = "persistUser";
        logger.entering(className, MN);
        entityManager.persist(userEntity);
        logger.fine("The following entity is stored in db -> " + userEntity);
        logger.exiting(className, MN);
        return userEntity;
    }


    @Override
    public UserEntity findUserById(Long id) {
        final String MN = "findUserById";
        logger.entering(className, MN);
        UserEntity foundUser = entityManager.find(UserEntity.class, id);
        logger.fine("Id -> " + id + " the use associated with id is -> " + foundUser);
        logger.exiting(className, MN);
        return foundUser;
    }


    @Override
    public List<UserEntity> getAllUsers() {
        final String MN = "getAllUsers";
        logger.entering(className, MN);
        Query query = entityManager.createQuery("SELECT u from UserEntity u");
        List<UserEntity> result = query.getResultList();
        logger.fine("The following data are fetched from data base -> " + result);
        logger.exiting(className, MN);
        return result;
    }
}
