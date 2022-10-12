package com.hornghub.webappexceldemo.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DataModel - Fields of database, requestable fields, and fields that will be displayed
 *
 * @author stevenhorng
 * @date 2022/11/10
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DataModel {
    private Long id;
    private String name;
    private byte[] picture;
    private byte[] vaccineCard;
    private byte[] primaryId;
    private byte[] secondaryId;


}
