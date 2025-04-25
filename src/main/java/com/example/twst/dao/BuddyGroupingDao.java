package com.example.twst.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.twst.domain.model.BuddyGrouping;

public interface BuddyGroupingDao {
    /**
     * テーブルの全データを取得.
     * 
     * @return List<BuddyGrouping>
     * @throws DataAccessException
     */
    public List<BuddyGrouping> selectMany() throws DataAccessException;

}
