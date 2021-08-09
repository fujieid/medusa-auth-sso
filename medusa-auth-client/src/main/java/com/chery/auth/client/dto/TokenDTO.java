/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: TokenDTO
 * Author:   Allen
 * Date:     2020/8/17
 * Description: Token返回实体DTO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Token返回实体DTO
 *
 * @author Allen
 * @date 2020/8/17
 */
@Data
public class TokenDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8148656065155811122L;
    /**
     * jwt token
     */
    @JsonProperty(value = "access_token")
    @ApiModelProperty(value = "access_token",
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsibWFyZ2UtYXV0aC1zZXJ2ZXIiLCJtYXJnZS1hZG1pbiJdLCJleHA"
            + "iOjE2MjI1MTIwNTIsInVzZXJfbmFtZSI6ImFkbWluIiwianRpIjoiMDAwNDFhNzEtZjMyMS00MzNmLWFkZGItOTcyYTUxYmUwNzY5Iiw"
            + "iY2xpZW50X2lkIjoidGVzdCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.Y80DpWr3VklU0Kk0nZ_AI6CFmeNO1fUrX7umwlTMQWo",
        required = true, position = 1)
    private String accessToken;

    /**
     * jwt token
     */
    @JsonProperty(value = "refresh_token")
    @ApiModelProperty(value = "refresh_token",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsibWFydmVsLWF1dGgtc2VydmVyIiwibWFydmVsLW1pZGRsZS1"
                + "zZXJ2ZXIiLCJtYXJ2ZWwtb3Blbi1hcGkiXSwidXNlcl9uYW1lIjoic3VwZXJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0"
                + "ZSJdLCJhdGkiOiI0ZTE1ZGZlNS05ZTMwLTRmYTctOTk4Yy01MmEwZjNjNzRlZWUiLCJleHAiOjE2MDA0MjE1NTgsImp0aSI6IjQ1"
                + "MGVmMmZiLTc0ZDctNGYxZC05N2VkLWEyODRjY2Q4ZDFjYSIsImNsaWVudF9pZCI6Im1pZGRsZSJ9.Go9PvQPS2PKax1rW8ocfm4"
                + "-rL4MP8s5pXAmTW6uurm4",
            required = true, position = 2)
    private String refreshToken;
    /**
     * token类型
     */
    @JsonProperty(value = "token_type")
    @ApiModelProperty(value = "token类型", example = "bearer", required = true, position = 3)
    private String tokenType;
    /**
     * 有效期
     */
    @JsonProperty(value = "expires_in")
    @ApiModelProperty(value = "有效期", example = "100000", required = true, position = 4)
    private Long expiresIn;
    /**
     * 作用域
     */
    @JsonProperty(value = "scope")
    @ApiModelProperty(value = "作用域", example = "read write", required = true, position = 5)
    private String scope;
    /**
     * jti
     */
    @ApiModelProperty(value = "jti", example = "a21aa6e4-c57e-4f37-9412-939e0af1ff50", required = true, position = 6)
    private String jti;
}
