package com.example.twst.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.twst.domain.model.MagicGrouping;

@Repository("MagicGroupingDaoJdbcImpl")
public class MagicGroupingDaoJdbcImpl implements MagicGroupingDao {

    private final NamedParameterJdbcTemplate jdbc;

    public MagicGroupingDaoJdbcImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<MagicGrouping> selectMany() throws DataAccessException {

        // SQL
        String sql = "SELECT * FROM mst_magic_grouping ORDER BY magic_grouping";

        // parameter
        SqlParameterSource params = new MapSqlParameterSource();

        // SQL実行
        List<Map<String, Object>> getList = jdbc.queryForList(sql, params);

        // 結果返却用のList
        List<MagicGrouping> magicGroupingList = new ArrayList<>();

        // 取得データ分loop
        for (Map<String, Object> map : getList) {

            // インスタンスの生成
            MagicGrouping magicGrouping = new MagicGrouping();

            // 取得したデータをセット
            magicGrouping.setMagicGrouping((int) map.get("magic_grouping"));// マジック区分
            magicGrouping.setMagicType((String) map.get("magic_type"));// マジック属性
            magicGrouping.setName((String) map.get("name"));// 名称
            magicGrouping.setEffect((String) map.get("effect"));// 効果

            // 結果返却用のListに追加
            magicGroupingList.add(magicGrouping);
        }
        return magicGroupingList;
    }
}
