package cn.tedu.mapper;

import cn.tedu.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int checkUserPhone(@Param("userPhone") String userPhone);

    void saveUser(User user);

    User queryExist(User user);

}
