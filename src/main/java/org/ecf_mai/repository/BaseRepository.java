package org.ecf_mai.repository;

import org.ecf_mai.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class BaseRepository<T> {
    protected SessionFactory factory;
    protected Session session;

    public BaseRepository() {
        factory = HibernateUtil.getFactory();
    }

    public List<T> saveMultiple(List<T> listEntity){
        session = factory.openSession();
        session.getTransaction().begin();
        listEntity.forEach(session::save);
        session.getTransaction().commit();
        session.close();
        return listEntity;
    }

    public T create (T entity) {
        session = factory.openSession();
        session.getTransaction().begin();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    public boolean delete(T entity){
        session = factory.openSession();
        session.getTransaction().begin();
        session.delete(entity);
        session.getTransaction().commit();
        boolean isContained = session.contains(entity);
        session.close();
        return !isContained;
    }

    public T update(T entity) {
        session = factory.openSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    public abstract T get(int id);
    public abstract List<T> getAll();
}
