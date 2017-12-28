package cn.et.shiro.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Select;

import cn.et.shiro.entity.Menu;
import cn.et.shiro.entity.UserInfo;
public interface UserMapper {
	@Select("select user_name as userName,pass_word as password from user_info where user_name=#{0}")
	public UserInfo queryUser(String userName);
	@Select("SELECT r.role_name FROM user_info u "
			+ "INNER JOIN user_role_relation urr ON u.user_id=urr.user_id"
			+ " INNER JOIN role r ON urr.role_id=r.role_id    "
			+ "  WHERE user_name=#{0}")
	public Set<String> querRoleByName(String userName);
	
	@Select("SELECT p.perm_tag FROM user_info u INNER JOIN user_role_relation urr ON u.user_id=urr.user_id "+
                     "    INNER JOIN role r ON urr.role_id=r.role_id  "+
                     "    INNER JOIN role_perms_relation rpr ON r.role_id=rpr.role_id   "+
                     "    INNER JOIN perms p ON rpr.perm_id=p.perm_id "+
					"WHERE user_name=#{0}")
	public Set<String> queryPermsByName(String userName);
	
	@Select("select menu_id as menuid,menu_name as menuname,menu_url as menuurl,menu_filter as menufilter,is_menu as ismenu from menu ")
	public List<Menu> queryMenu();
	@Select("select menu_id as menuid,menu_name as menuname,menu_url as menuurl,menu_filter as menufilter,is_menu as ismenu from menu  where menu_url=#{0}")
	public List<Menu> queryMenuByUrl(String url);
	
	
	@Select("SELECT menu_name as menuname,menu_url as menuurl,menu_filter as menufilter,is_menu as ismenu  FROM menu m INNER JOIN user_menu_relation umr ON m.menu_id=umr.menu_id "+
         "INNER JOIN user_info u ON umr.user_id=u.user_id "+
        "WHERE u.user_name=#{0}")
	public List<Menu> queryMenuByUser(String userName);
	
}
