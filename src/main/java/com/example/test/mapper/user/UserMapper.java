package com.example.test.mapper.user;

import com.example.test.mvc.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserVO LoginAction(UserVO vo);

    int UserRegister(UserVO vo);

    int IdCheck(UserVO vo);
}

