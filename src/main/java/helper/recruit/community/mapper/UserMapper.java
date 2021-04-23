package helper.recruit.community.mapper;

import helper.recruit.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into users (name,account_id,token,gmt_create,gmt_modified,avatar_url) " +
            "values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from users where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from users where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from users where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    // 有下划线或等号右边 是隐射数据库的，而 # 号内部是 mybatis 根据你类、传递的参数自动替换的【对象会根据get方法】，参数就按照参数
    @Update("update users set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl} where id = #{id}")
    void update(User user);
}
