package com.bovink.androidlearing;

import com.google.gson.Gson;

/**
 * @author Retina975
 * @since 2017/07/14
 */

public class MessageSender {
    private Gson gson = new Gson();
    private String messNote;
    private String messType;
    private String groupId;
    private String receiverUid;

    public String getMessNote() {
        return messNote;
    }

    public void setMessNote(String messNote) {
        this.messNote = messNote;
    }

    public String getMessType() {
        return messType;
    }

    public void setMessType(String messType) {
        this.messType = messType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getReceiverUid() {
        return receiverUid;
    }

    public void setReceiverUid(String receiverUid) {
        this.receiverUid = receiverUid;
    }

    @Override
    public String toString() {
        return gson.toJson(this) + "\n";
    }
}
