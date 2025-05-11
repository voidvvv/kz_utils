package com.kz.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KzUserInfoDTO {
    private boolean incomplete;
    private int id;

    private String username; // account name

    private String nickname; // nickname

    private String email; // email

    private String phone; // phone number

    private String avatar; // avatar URL

}
