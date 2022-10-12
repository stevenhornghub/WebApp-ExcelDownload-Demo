package com.hornghub.webappexceldemo.mapper.impl;

import com.hornghub.webappexceldemo.mapper.WebAppMapper;
import com.hornghub.webappexceldemo.model.DataModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WebAppMapperImpl - Web App Excel Demo Mapper Implementation class
 *
 * @author stevenhorng
 * @date 2022/11/10
 */

@Repository
public class WebAppMapperImpl implements WebAppMapper {
    @Override
    public List<DataModel> findAll() {
        return findAll();
    }

    @Override
    public DataModel findById(Long id) {
        return findById(id);
    }

    @Override
    public Long deleteById(Long id) {

        return deleteById(id);
    }

    @Override
    public DataModel save(DataModel data) {
        return save(data);
    }

    @Override
    public Long updateById(DataModel data) {
        return updateById(data);
    }

}
