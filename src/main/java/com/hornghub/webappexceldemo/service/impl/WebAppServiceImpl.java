package com.hornghub.webappexceldemo.service.impl;

import com.hornghub.webappexceldemo.mapper.WebAppMapper;
import com.hornghub.webappexceldemo.model.DataModel;
import com.hornghub.webappexceldemo.service.WebAppService;
import com.hornghub.webappexceldemo.utils.exceptions.CouldNotDeleteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * WebAppServiceImpl - Web App Excel Demo Service Implementation class
 *
 * @author stevenhorng
 * @date 2022/11/10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WebAppServiceImpl implements WebAppService {

    @Autowired
    private WebAppMapper webAppMapper;

    @Override
    public List<DataModel> findAll() {
        log.info("Showing All Data");
        return webAppMapper.findAll();
    }

    @Override
    public DataModel findById(Long id) {
        log.info("Showing Data of Choice");
        return webAppMapper.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete Data of Choice");
        Long numberOfRowAffected = webAppMapper.deleteById(id);
        if (numberOfRowAffected != 1) {
            throw new CouldNotDeleteException(String.format("A data with id no." + id + " does not exist therefore, not deleted", id));
        }
    }

    @Override
    public DataModel save(DataModel data) {
        log.info("Save Data");
        return webAppMapper.save(data);
    }

    @Override
    public Long updateById(DataModel data) {
        log.info("Update Data of Choice");
        return webAppMapper.updateById(data);
    }
}
