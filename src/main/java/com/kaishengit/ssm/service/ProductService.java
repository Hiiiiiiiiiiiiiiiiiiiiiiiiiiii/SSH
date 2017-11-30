package com.kaishengit.ssm.service;

import com.kaishengit.ssm.Dao.ProductDao;
import com.kaishengit.ssm.pojo.Product;
import com.kaishengit.ssm.util.RequestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ProductService {

    @Autowired
    ProductDao productDao;
    public List<Product> findAll(){
        return productDao.findAll();
    }

    public List<Product> getList(HttpServletRequest request) {
        List<RequestQuery> requestQueryList = RequestQuery.builderRequestQuery(request);
        if (requestQueryList == null || requestQueryList.isEmpty()) {
            return findAll();
        }
        return productDao.findBy(requestQueryList);
    }

    public void add(Product product) {
        productDao.save(product);
    }

    public Product findById(Integer id) {
        return productDao.findById(id);
    }
}
