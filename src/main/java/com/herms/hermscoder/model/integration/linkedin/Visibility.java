package com.herms.hermscoder.model.integration.linkedin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Visibility {
    @JsonProperty("com.linkedin.ugc.MemberNetworkVisibility")
    private String memberNetworkVisibility = "CONNECTIONS";

    public Visibility() {
    }

    public String getMemberNetworkVisibility() {
        return memberNetworkVisibility;
    }

    public void setMemberNetworkVisibility(String memberNetworkVisibility) {
        this.memberNetworkVisibility = memberNetworkVisibility;
    }
}
