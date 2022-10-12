package com.hornghub.webappexceldemo.mapper;

import com.hornghub.webappexceldemo.model.DataModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * WebAppMapper - WebAppMapperImpl - interface of the WebAppMapperImpl
 *
 * @author stevenhorng
 * @date 2022/11/10
 */

@Mapper
public interface WebAppMapper {

    @Select("SELECT * FROM data")
    List<DataModel> findAll();

    @Select("SELECT * FROM data WHERE id=#{id}")
    DataModel findById(Long id);

    @Delete("DELETE FROM data WHERE id=#{id}")
    Long deleteById(Long id);

    @Select("INSERT INTO data (id, name, picture, vaccine_card, primary_id, secondary_id) VALUES (#{id}, #{name}, #{picture}, #{vaccineCard} , #{primaryId} , #{secondaryId})")
    DataModel save(DataModel data);

    @Update("UPDATE data SET name=#{name}, picture=#{picture}, vaccine_card=#{vaccineCard}, primary_id=#{primaryId},secondary_id=#{secondaryId}  WHERE id = #{id}")
    Long updateById(DataModel data);
}