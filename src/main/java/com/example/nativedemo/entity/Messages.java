package com.example.nativedemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.nativedemo.enmus.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nieqiurong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messages {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String message;

    private MessageType messageType;

    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;
}
