package com.example.nativedemo.entity;

import com.example.nativedemo.enmus.MessageType;
import lombok.Data;

@Data
public class MessageDto {
    private Long id;

    private Long userId;

    private String userName;


    private String message;

    private MessageType messageType;

    private String createUser;

    private String updateUser;
}
