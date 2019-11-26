package com.lz.redis.luaandtransaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sy
 * @date 2019年11月13日
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestWapperDto {
    private String name;
    private Integer age;
}
