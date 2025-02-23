package com.example.myapp.dto;

import com.example.myapp.model.UserInfoModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

    private Long id;
    private String name;
    private String email;
    private String mobileNum;

    public UserInfoDto (UserInfoModel userInfoModel) {
        this.id = userInfoModel.getId();
        this.name = userInfoModel.getName();
        this.email = userInfoModel.getEmail();
        this.mobileNum = userInfoModel.getMobileNum();
    }
}
