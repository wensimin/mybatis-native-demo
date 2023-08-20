package com.example.nativedemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.nativedemo.enmus.MessageType;

import java.io.Serializable;

/**
 * @author nieqiurong
 */
public class Messages implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String message;

    private MessageType messageType;

    public Messages() {
    }

    public Messages(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Messages(Long id, String message, MessageType messageType) {
        this.id = id;
        this.message = message;
        this.messageType = messageType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}
