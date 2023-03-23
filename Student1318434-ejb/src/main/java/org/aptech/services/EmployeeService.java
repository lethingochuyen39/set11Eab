package org.aptech.services;

import org.aptech.entities.Account;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

@Remote
public interface EmployeeService<T extends Serializable> {

    public List<T> getAllEntity();
    public T getEntityById(String id);
    public boolean addEntity(T entity);
    public boolean updateEntity(T entity);
    public boolean deleteEntity(String id);
    public void setType(Class<T> type);

}
