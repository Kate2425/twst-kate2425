package com.example.twst.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.twst.domain.model.Card;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;

public interface CardDao {

    /**
     * テーブルの件数を取得.
     * 
     * @return 件数
     * @throws DataAccessException
     */
    public int countRecord() throws DataAccessException;

    /**
     * テーブルにデータを1件insert.
     * 
     * @param Card
     * @return 件数
     * @throws DataAccessException
     */
    public int insertOne(CardForm cardForm) throws DataAccessException;

    /**
     * テーブルのデータを１件取得
     * 
     * @param name
     * @return Card
     * @throws DataAccessException
     */
    public Card selectOne(String name) throws DataAccessException;

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
     * 全テーブルを全取得.
     * 
     * @param SearchForm
     * @return List<Card>
     * @throws DataAccessException
     */
    List<Card> selectAll(SearchForm form) throws DataAccessException;

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
    public int deleteOne(String id) throws DataAccessException;

    /**
     * SQL取得結果をサーバーにCSVで保存する
     * 
     * @throws DataAccessException
     */
    public void csvOut() throws DataAccessException;
}
