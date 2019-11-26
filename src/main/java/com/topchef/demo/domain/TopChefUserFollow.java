package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table
@IdClass(TopChefUserFollow.PublishFollower.class)
@Entity
public class TopChefUserFollow {
    @Id
    @Column
    private String publisherId;

    @Id
    @Column
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
