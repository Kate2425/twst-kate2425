package com.example.twst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.twst.dao.BuddyGroupingDao;
import com.example.twst.domain.model.BuddyGrouping;

@Service("BuddyGroupingService")
public class BuddyGroupingService {

    @Autowired
    @Qualifier("BuddyGroupingDaoJdbcImpl")
    BuddyGroupingDao dao;

    /**
     * 全件取得用メソッド.
     * 
     * @return List<BuddyGrouping>
     */
    public List<BuddyGrouping> selectMany() {
        return dao.selectMany();
    }
}
