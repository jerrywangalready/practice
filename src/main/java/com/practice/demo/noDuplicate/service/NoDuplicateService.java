package com.practice.demo.noDuplicate.service;

import com.practice.demo.noDuplicate.model.Query;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2018/9/7.
 */
public interface NoDuplicateService {

    void save(Map<String, String> param);

    List<String> getData();

    List<Map<String, Object>> queryList(Map<String, String> param);

    void deleteItem(int id);
}
