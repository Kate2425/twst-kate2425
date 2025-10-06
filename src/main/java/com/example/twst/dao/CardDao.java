package com.example.twst.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.twst.domain.model.Card;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;

public interface CardDao {

    /**
     * テーブルにデータを1件insert.
     * 
     * @param Card
     * @return 件数
     * @throws DataAccessException
     */
    public int insertOne(CardForm cardForm) throws DataAccessException;

    /**
     * テーブルの全データを取得.
     * 
     * @param SearchForm
     * @param tableName
     * @return List<Card>
     * @throws DataAccessException
     */
    public List<Card> selectMany(SearchForm form, String tableName) throws DataAccessException;

    /**
     * テーブルを１件更新.
     * 
     * @param Card
     * @return 件数
     * @throws DataAccessException
     */
    public int updateOne(CardForm cardForm) throws DataAccessException;

    /**
     * テーブルを１件削除.
     * 
     * @param id
     * @return 件数
     * @throws DataAccessException
     */
    public int deleteOne(CardForm cardForm) throws DataAccessException;

    /**
     * SQL取得結果をサーバーにCSVで保存する
     * 
     * @throws DataAccessException
     */
    public void csvOut() throws DataAccessException;
}
