package com.kaishengit.ssm.service;

import com.kaishengit.ssm.Dao.ProductDao;
import com.kaishengit.ssm.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ProductService {

    @Autowired
    ProductDao productDao;
    public List<Product> findAll(){
        return productDao.findAll();
    }


}
