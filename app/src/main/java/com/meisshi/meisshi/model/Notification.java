package com.meisshi.meisshi.model;

import java.io.Serializable;

/**
 * Created by agustin on 13/11/2017.
 */

public class Notification implements Serializable {
    User attachment;
    String id;

    String type;
    String seen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public User getAttachment() {
        return attachment;
    }

    public void setAttachment(User attachment) {
        this.attachment = attachment;
    }
}
