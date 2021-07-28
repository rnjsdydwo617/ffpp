package com.example.test.mapper.user;

import com.example.test.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int LoginAction(UserVO vo);

    int UserRegister(UserVO vo);

    int IdCheck(UserVO vo);
}

