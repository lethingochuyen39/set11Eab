package org.aptech.services;

import org.aptech.entities.Account;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Stateless(name = "EmployeeBeanEJB")
@LocalBean
public class EmployeeBean<T extends Serializable> implements EmployeeService<T>{

  private Class<T> type;

  private final EntityManager entityManager;

    public EmployeeBean() {

      EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Student1318434PersistenceUnit");
      entityManager = entityManagerFactory.createEntityManager();
    }

  @Override
  public List<T> getAllEntity() {
    CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cq = builder.createQuery(type);
    Root<T> root = cq.from(type);
    cq.select(root);
    return entityManager.createQuery(cq).getResultList();
  }

  @Override
  public T getEntityById(String id) {
    return entityManager.find(type, id);
  }


  @Override
  public boolean addEntity(T entity) {
    try {

      entityManager.getTransaction().begin();
      entityManager.persist(entity);
      entityManager.getTransaction().commit();

      return true;

    } catch (Exception ex) {

      ex.printStackTrace();
      entityManager.getTransaction().rollback();
      return false;
    }
  }

  @Override
  public boolean updateEntity(T entity) {
    try {

      entityManager.getTransaction().begin();
      entityManager.merge(entity);
      entityManager.getTransaction().commit();

      return true;

    } catch (Exception ex) {

      ex.printStackTrace();
      entityManager.getTransaction().rollback();
      return false;
    }

  }

  @Override
  public boolean deleteEntity(String id) {
    try{

      entityManager.getTransaction().begin();
      entityManager.remove(getEntityById(id));
      entityManager.getTransaction().commit();

      return true;

    }catch (Exception ex){

      ex.printStackTrace();
      entityManager.getTransaction().rollback();
      return false;
    }
  }
  @Override
  public void setType(Class<T> t){
    type = t;
  }

}