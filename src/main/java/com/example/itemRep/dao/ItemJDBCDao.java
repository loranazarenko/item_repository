package com.example.itemRep.dao;

import com.example.itemRep.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
public class ItemJDBCDao implements ItemDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Long> save(List<ItemEntity> items) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String INSERT_ITEM = "insert INTO [dbo].[Item]\n" +
                "           ([code]\n" +
                "           ,[description]\n" +
                "           ,[isDeleted])\n" +
                "values(?,?,?)";
        List<Long> ids = new ArrayList<>();
        for (ItemEntity entity : items) {
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(INSERT_ITEM, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, entity.getCode());
                ps.setString(2, entity.getDescription());
                ps.setInt(3, entity.getIsDeleted());
                return ps;
            }, keyHolder);
            ids.add(keyHolder.getKey().longValue());
            System.out.println(keyHolder.getKey().longValue());
        }

        return ids;
    }

    @Override
    public List<Long> saveMerge(List<ItemEntity> items) {
        jdbcTemplate.execute("CREATE TABLE #Item_tmp(\n" +
                "\t[id] [bigint] NULL ," +
                "\t[code] [nvarchar](1500) NULL,\n" +
                "\t[description] [nvarchar](50) NULL,\n" +
                "\t[isDeleted] [bit] NULL)");

        jdbcTemplate.execute("CREATE UNIQUE INDEX IX_T ON [#Item_tmp] ([id]) WHERE [id] IS NOT NULL");


        final SimpleJdbcInsert statement = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("#Item_tmp")
                .usingColumns("id", "code", "description", "isDeleted");

        int[] results = statement.executeBatch(SqlParameterSourceUtils.createBatch(items));
        System.out.println(Arrays.toString(results));


        String sql =
                "MERGE Item AS TARGET\n" +
                        "USING #Item_tmp AS SOURCE \n" +
                        "ON (TARGET.id = SOURCE.id) \n" +
                        "WHEN MATCHED AND TARGET.code <> SOURCE.code OR TARGET.description <> SOURCE.description OR  TARGET.isDeleted <> SOURCE.isDeleted\n" +
                        "THEN UPDATE SET TARGET.code = SOURCE.code, TARGET.description = SOURCE.description, TARGET.isDeleted = SOURCE.isDeleted \n" +
                        "WHEN NOT MATCHED BY TARGET \n" +
                        "THEN INSERT (code, description, isDeleted) VALUES (SOURCE.code, SOURCE.description, SOURCE.isDeleted)\n" +
                        "OUTPUT $action, \n" +
                        "INSERTED.id AS NewId, " +
                        "INSERTED.code AS SourceCode, \n" +
                        "INSERTED.description AS Sourcedescription, \n" +
                        "INSERTED.isDeleted AS SourceisDeleted; \n" +
                        " \n";


        List<Long> new_ids = jdbcTemplate.query(sql, (ResultSet rs) -> {
            List<Long> Ids = new ArrayList<>();
            String action = "";
            while (rs.next()) {
                action = rs.getString("$action");
                if (action.equals("INSERT")) {
                    Ids.add(rs.getLong("NewId"));
                }
            }
            return Ids;
        });
        System.out.println(new_ids.toString());
        jdbcTemplate.execute("DROP TABLE #Item_tmp");
        return new_ids;
    }
}

