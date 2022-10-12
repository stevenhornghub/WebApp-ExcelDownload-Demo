package com.hornghub.webappexceldemo.service;

import com.hornghub.webappexceldemo.model.DataModel;

import java.util.List;

/**
 * WebAppService - WebAppServiceImpl - interface of the WebAppServiceImpl
 *
 * @author stevenhorng
 * @date 2022/11/10
 */
public interface WebAppService {

    List<DataModel> findAll();

    DataModel findById(Long id);

    void deleteById(Long id);

    DataModel save(DataModel data);

    Long updateById(DataModel data);
}
