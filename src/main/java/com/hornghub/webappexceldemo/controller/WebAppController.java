package com.hornghub.webappexceldemo.controller;


import com.hornghub.webappexceldemo.model.DataModel;
import com.hornghub.webappexceldemo.service.WebAppService;
import com.hornghub.webappexceldemo.utils.ExcelUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * WebAppController - holds the endpoints of the web app excel demo
 *
 * @author stevenhorng
 * @date 2022/11/10
 */


@Slf4j
@Controller
@RequestMapping(path = "/web-app-excel")
@RequiredArgsConstructor
public class WebAppController {

    final WebAppService webAppService;


    @PostMapping(path = "/add") // Map ONLY POST Requests
    public ResponseEntity<String> addData(@Validated @RequestParam String name, @RequestParam MultipartFile picture,
                                          @RequestParam MultipartFile vaccineCard,
                                          @RequestParam MultipartFile primaryId, @RequestParam MultipartFile secondaryId) throws IOException {
        byte[] picBA = picture.getBytes();
        byte[] vaccineCardBA = vaccineCard.getBytes();
        byte[] primaryIdBA = primaryId.getBytes();
        byte[] secondaryIdBA = secondaryId.getBytes();
        DataModel data = new DataModel(null, name, picBA, vaccineCardBA, primaryIdBA, secondaryIdBA);
        webAppService.save(data);
        return new ResponseEntity<>("Data with the name: " + name + " Inserted Successfully", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<DataModel> getAllData() {
        // This returns a JSON or XML with the users
        log.info("showing all data");
        return webAppService.findAll();
    }

    //Has an Issue
    @GetMapping(path = "/{id}")
    public @ResponseBody
    DataModel getData(@PathVariable Long id) {
        log.info("showing data by selection");
        return webAppService.findById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long id) {
        webAppService.deleteById(id);
        return new ResponseEntity<>("Data with the id no." + id + " Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity updateById(@PathVariable("id") Long id, @RequestParam String name,
                                     @RequestParam MultipartFile picture, @RequestParam MultipartFile vaccineCard,
                                     @RequestParam MultipartFile primaryId, @RequestParam MultipartFile secondaryId) throws IOException {
        byte[] picBA = picture.getBytes();
        byte[] vaccineCardBA = vaccineCard.getBytes();
        byte[] primaryIdBA = primaryId.getBytes();
        byte[] secondaryIdBA = secondaryId.getBytes();
        DataModel data = new DataModel(id, name, picBA, vaccineCardBA, primaryIdBA, secondaryIdBA);
        webAppService.updateById(data);
        return new ResponseEntity<>("Data Updated the id no." + id + " Successfully", HttpStatus.OK);
    }

    @GetMapping("/download/export.xlsx")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        log.info("download all data on excel");
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=ExcelExport.xlsx");
        ExcelUtils.exportExcel((List<DataModel>) getAllData(), response.getOutputStream());
    }

    @GetMapping("/download/single/{id}")
    public void singleDownloadCsv(@PathVariable(value = "id") Long id, HttpServletResponse response) throws IOException {
        log.info("download data by selection on excel");
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=SingleExcelExport.xlsx");
        ExcelUtils.exportExcel(Collections.singletonList(getData(id)), response.getOutputStream());
    }
}