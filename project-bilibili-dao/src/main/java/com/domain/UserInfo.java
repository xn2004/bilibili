package com.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;

    private Long userId;

//    @Field(type = FieldType.Text)
    private String nick;

    private String avatar;

    private String sign;

    private String gender;

    private String birth;

//    @Field(type = FieldType.Date)
    private Date createTime;

//    @Field(type = FieldType.Date)
    private Date updateTime;

//    private Boolean followed;
}
