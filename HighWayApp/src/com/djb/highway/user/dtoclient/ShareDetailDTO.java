package com.djb.highway.user.dtoclient;

import java.util.List;

import com.djb.highway.framework.dto.BaseDTO;

public class ShareDetailDTO extends BaseDTO {
    private static final long serialVersionUID = -378848295830417701L;

    private List<UserReviewClearDTO> userReviewDTOs;

    // 用于显示点赞用户的昵称
    private List<String> nickNames;

    public List<UserReviewClearDTO> getUserReviewDTOs() {
        return userReviewDTOs;
    }

    public void setUserReviewDTOs(List<UserReviewClearDTO> userReviewDTOs) {
        this.userReviewDTOs = userReviewDTOs;
    }

    public List<String> getNickNames() {
        return nickNames;
    }

    public void setNickNames(List<String> nickNames) {
        this.nickNames = nickNames;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}