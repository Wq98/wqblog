package cn.tedu.mapper;

import cn.tedu.pojo.LoginInfo;
import cn.tedu.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int checkUserPhone(@Param("userPhone") String userPhone);

    void saveUser(User user);

    User queryExist(User user);

    void insertLogin(LoginInfo loginInfo);

    int countError(String userPhone);

    boolean updateUserState(String userPhone);

    boolean updateUserState1(String userPhone);

    User queryExistStateNot1(User user);

    User queryUserInfo(String userId);
    User queryUserPhone(String userPhone);
}
