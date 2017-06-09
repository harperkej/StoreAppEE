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
        entityManager.persist(userEntity);
        logger.fine("The following entity is stored in db -> " + userEntity);
        return userEntity;
    }


    @Override
    public UserEntity findUserById(Long id) {
        UserEntity foundUser = entityManager.find(UserEntity.class, id);
        logger.fine("With the given id -> " + id + " the following user if found-> " + foundUser);
        return foundUser;
    }


    @Override
    public List<UserEntity> getAllUsers() {
        Query query = entityManager.createQuery("SELECT u from UserEntity u");
        List<UserEntity> result = query.getResultList();
        logger.fine("The following data are fetched from data base -> " + result);
        return result;
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        logger.fine("The user with the following id " + userEntity.getId() + " is updated");
        return entityManager.merge(userEntity);
    }

    @Override
    public List<UserEntity> getUserByUserName(String userName) {
        Query query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.userName = ?1");
        query.setParameter(1,userName);
        List<UserEntity> foundUsers = query.getResultList();
        logger.fine("The following users are found " + foundUsers + " when search by userName " + userName);
        return foundUsers;
    }
}
