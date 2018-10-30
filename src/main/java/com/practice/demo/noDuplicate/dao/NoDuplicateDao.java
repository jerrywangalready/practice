package com.practice.demo.noDuplicate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2018/9/7.
 */
@Component
@Mapper
public interface NoDuplicateDao {

    /**
     * @Description 保存
     * @author JerryWang
     * @date 2018/9/7 22:31
     * @param param
     */
    void save(Map<String, String> param);

    /**
     * @Description 获取数据
     * @author JerryWang
     * @date 2018/9/8 20:43
     * @return
     */
    List<String> getData();

    List<Map<String, Object>> queryList();

    void deleteItem(int id);
}
