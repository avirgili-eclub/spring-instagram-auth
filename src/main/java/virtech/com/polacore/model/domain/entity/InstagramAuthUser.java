package virtech.com.polacore.model.domain.entity;

import virtech.com.polacore.model.domain.BaseEntity;

public class InstagramAuthUser extends BaseEntity {

    private String instagramCode;
    private String userId;
    public String getInstagramCode() {
        return instagramCode;
    }

    public void setInstagramCode(String instagramCode) {
        this.instagramCode = instagramCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
