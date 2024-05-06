package com.example.nativedemo.dao;

import com.example.nativedemo.entity.SysUser;
import com.example.nativedemo.mapper.SysUserMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysUserDao extends MPJBaseServiceImpl<SysUserMapper, SysUser> {
}
