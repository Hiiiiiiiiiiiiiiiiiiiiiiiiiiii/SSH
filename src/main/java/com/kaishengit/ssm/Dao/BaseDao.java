package com.kaishengit.ssm.Dao;

import com.kaishengit.ssm.util.RequestQuery;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDao<T,PK extends Serializable> {
    @Autowired
    SessionFactory sessionFactory;
    private Class<T> entityClazz;

    public BaseDao(){
//                                                                              getGenericSuperclass获取泛型父类
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        //  该两列的目的就是为了获取-----> class com.kaishengit.ssm.pojo.Product
        entityClazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void save(T entity){
        getSession().saveOrUpdate(entity);
    }

    /**
     * 查询所有的参数
     * @return
     */
    public List<T> findAll(){
        Criteria criteria = getSession().createCriteria(entityClazz);
        criteria.setFirstResult(0);
        criteria.setMaxResults(100);
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }

    public T findById(PK id){
        return (T) getSession().get(entityClazz,id);
    }

    /**
     * 根据筛选除id外的筛选条件进行筛选
     * @param requestQueryList
     * @return
     */
    public List<T> findBy(List<RequestQuery> requestQueryList){

        Criteria criteria = getSession().createCriteria(entityClazz);
        for(RequestQuery requestQuery : requestQueryList){
            String filterType = requestQuery.getFilterType();
            String parameterName = requestQuery.getParameterName();
            Object value = requestQuery.getValue();
            //根据筛选类型来选择具体的方法进行操作
            criteria.add(createCriterion(filterType,parameterName,value));
            //根据id进行排序
            criteria.addOrder(Order.desc("id"));
        }
        return criteria.list();
    }

    public Criterion createCriterion(String filterType,String parameterName,Object value){
        if("eq".equalsIgnoreCase(filterType)) {
            return Restrictions.eq(parameterName,value);
        } else if("like".equalsIgnoreCase(filterType)) {
            return Restrictions.like(parameterName,value.toString(), MatchMode.ANYWHERE);
        } else if("gt".equalsIgnoreCase(filterType)) {
            return Restrictions.gt(parameterName,value);
        } else if("ge".equalsIgnoreCase(filterType)) {
            return Restrictions.ge(parameterName,value);
        } else if("lt".equalsIgnoreCase(filterType)) {
            return Restrictions.lt(parameterName,value);
        } else if("le".equalsIgnoreCase(filterType)) {
            return Restrictions.le(parameterName,value);
        }
        return null;
    }


}
