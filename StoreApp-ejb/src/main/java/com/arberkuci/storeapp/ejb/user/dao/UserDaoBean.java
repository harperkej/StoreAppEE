package com.arberkuci.storeapp.ejb.user.dao;

import com.arberkuci.storeapp.ejb.user.entity.UserEntity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by a.kuci on 4/6/2017.
 */
@Stateless
@Local(UserDao.class)
public class UserDaoBean implements UserDao {

    @PersistenceContext(unitName = "store-unit-name")
    EntityManager entityManager;


    @Override
    public UserEntity persistUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;
    }


    @Override
    public UserEntity findUserById(Long id) {
        return entityManager.find(UserEntity.class, id);
    }


    @Override
    public List<UserEntity> getAllUsers() {
        Query query = entityManager.createQuery("SELECT u from UserEntity u");
        List<UserEntity> result = query.getResultList();
        return result;
    }
}
