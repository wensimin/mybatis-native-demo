package com.example.nativedemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysUser {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;
}
