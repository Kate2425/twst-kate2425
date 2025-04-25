package com.example.twst.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.twst.domain.model.MagicGrouping;

public interface MagicGroupingDao {

    /**
     * テーブルの全データを取得.
     * 
     * @return List<MagicGrouping>
     * @throws DataAccessException
     */
    public List<MagicGrouping> selectMany() throws DataAccessException;
}
