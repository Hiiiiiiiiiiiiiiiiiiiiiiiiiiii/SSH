package com.kaishengit.ssm.Dao;

import com.kaishengit.ssm.pojo.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductDao {
    @Autowired
    SessionFactory sessionFactory;


    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Product> findAll(){
        Session session = getSession();
        String hql = "from Product";
        Query query =session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(100);
        List<Product> lists = query.list();
        return lists;
    }

}
