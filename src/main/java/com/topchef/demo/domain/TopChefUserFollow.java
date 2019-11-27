package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "user_follow")
@IdClass(TopChefUserFollow.PublishFollower.class)
@Entity
public class TopChefUserFollow {
    @Id
    @Column(name = "p_id")
    private String publisherId;

    @Id
    @Column(name = "f_id")
    private String followerId;

    public class PublishFollower implements Serializable{
        private String publisherId;
        private String followerId;

        public String getPublisherId() {
            return publisherId;
        }

        public void setPublisherId(String publisherId) {
            this.publisherId = publisherId;
        }

        public String getFollowerId() {
            return followerId;
        }

        public void setFollowerId(String followerId) {
            this.followerId = followerId;
        }
    }
}
