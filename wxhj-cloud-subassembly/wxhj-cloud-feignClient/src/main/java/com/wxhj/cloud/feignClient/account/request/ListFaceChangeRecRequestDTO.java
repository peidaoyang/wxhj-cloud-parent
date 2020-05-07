package com.wxhj.cloud.feignClient.account.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxpjf
 * @date 2020/5/7 10:51
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListFaceChangeRecRequestDTO {
    private String sceneId;
    private Long maxCurrent;
    private Long minCurrent;
}
