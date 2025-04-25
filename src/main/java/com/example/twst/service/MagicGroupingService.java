package com.example.twst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.twst.dao.MagicGroupingDao;
import com.example.twst.domain.model.MagicGrouping;

@Service("MagicGroupingService")
public class MagicGroupingService {

    @Autowired
    @Qualifier("MagicGroupingDaoJdbcImpl")
    MagicGroupingDao dao;

    /**
     * 全件取得用メソッド.
     * 
     * @return List<MagicGrouping>
     */
    public List<MagicGrouping> selectMany() {
        return dao.selectMany();
    }
}
