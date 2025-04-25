package com.example.twst.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.twst.domain.model.BuddyGrouping;

@Repository("BuddyGroupingDaoJdbcImpl")
public class BuddyGroupingDaoJdbcImpl implements BuddyGroupingDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @Override
    public List<BuddyGrouping> selectMany() throws DataAccessException {

        // SQL
        String sql = "SELECT * FROM mst_buddy_grouping ORDER BY buddy_grouping";

        // parameter
        SqlParameterSource params = new MapSqlParameterSource();

        // SQL実行
        List<Map<String, Object>> getList = jdbc.queryForList(sql, params);

        // 結果返却用のList
        List<BuddyGrouping> buddyGroupingList = new ArrayList<>();

        // 取得データ分loop
        for (Map<String, Object> map : getList) {

            // インスタンスの生成
            BuddyGrouping buddyGrouping = new BuddyGrouping();

            // 取得したデータをセット
            buddyGrouping.setBuddyGrouping((int) map.get("buddy_grouping"));// バディ区分
            buddyGrouping.setEffect((String) map.get("effect"));// 効果

            // 結果返却用のListに追加
            buddyGroupingList.add(buddyGrouping);
        }
        return buddyGroupingList;
    }
}
