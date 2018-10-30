package com.practice.demo.noDuplicate.service;

import com.practice.demo.noDuplicate.dao.NoDuplicateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2018/9/7.
 */
@Service
public class NoDuplicateServiceImpl implements NoDuplicateService {

    @Autowired
    private NoDuplicateDao noDuplicateDao;

    @Override
    public void save(Map<String, String> param) {

        noDuplicateDao.save(param);
    }

    @Override
    public List<String> getData() {
        return noDuplicateDao.getData();
    }

    @Override
    public List<Map<String, Object>> queryList(Map<String, String> param) {
        return noDuplicateDao.queryList();
    }

    @Override
    public void deleteItem(int id) {
        noDuplicateDao.deleteItem(id);
    }
}
