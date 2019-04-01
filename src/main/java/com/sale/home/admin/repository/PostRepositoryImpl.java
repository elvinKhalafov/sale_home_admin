package com.sale.home.admin.repository;



import com.sale.home.admin.constants.PostConstants;
import com.sale.home.admin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

   private static final String GET_ALL_POST_SQL = "select p.id_post, p.address, p.title, p.desc, p.post_type, p.home_type, p.status, p.room_count, p.area, p.price, p.adding_time, \n" +
           "c.id_city, c.city_name, u.id_user, \n" +
           "u.email, u.first_name, u.last_name, u.status,\n" +
           "r.id_role, r.role_type from post p inner join city c on p.id_city = c.id_city\n" +
           "inner join user u on p.id_user = u.id_user\n" +
           "inner join role r on u.id_role= r.id_role where p.status = ?";

   private static final String DELETE_POST = "delete from post where id_post = ?";

    private final String SELECT_POST_BY_ID = "select * from post p inner join user u on p.id_user=u.id_user inner join city c on c.id_city=p.id_city where p.id_post = ?";

    private final static String ACTIVATE_POST_BY_ID = "update post set status = ? where id_post = ?";




    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Post> getAllPosts(String status) {
        List<Post>postList =jdbcTemplate.query(GET_ALL_POST_SQL, new Object[]{status}, new RowMapper<Post>() {
            @Nullable
            @Override
            public Post mapRow(ResultSet resultSet, int i) throws SQLException {
                Post post = new Post();
                post.setIdPost(resultSet.getInt("id_post"));
                post.setAddress(resultSet.getString("address"));
                post.setArea(resultSet.getDouble("area"));

                City city = new City();
                city.setCityName(resultSet.getString("city_name"));
                city.setIdCity(resultSet.getInt("id_city"));
                post.setCity(city);
                post.setDesc(resultSet.getString("desc"));
                post.setHomeType(resultSet.getString("home_type"));
                post.setPrice(resultSet.getDouble("price"));
                post.setRoomCount(resultSet.getInt("room_count"));
                post.setPostType(resultSet.getString("home_type"));
                post.setStatus(resultSet.getString("status"));
                post.setShareDate(resultSet.getTimestamp("adding_time").toLocalDateTime());

                User user = new User();
                user.setIdUser(resultSet.getInt("id_user"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));


                Role role = new Role();
                role.setIdRole(resultSet.getInt("id_role"));
                role.setRoleType(resultSet.getString("role_type"));
                user.setRole(role);

                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                post.setUser(user);




                return post;
            }
        });


        return postList;
    }


    @Override
    public void deletePost(int id) {
        int i =jdbcTemplate.update(DELETE_POST, id);
        if(i==0){
            throw new RuntimeException();
        }

    }

    @Override
    public Post getPostById(int id) {
        return jdbcTemplate.query(SELECT_POST_BY_ID, new Object[]{id}, new ResultSetExtractor<Post>() {
            @Override
            public Post extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Post post = new Post();
                while (resultSet.next()) {
                    if (post.getIdPost() == 0) {
                        post.setIdPost(resultSet.getInt("id_post"));
                        post.setAddress(resultSet.getString("address"));
                        post.setTitle(resultSet.getString("title"));
                        post.setArea(resultSet.getDouble("area"));
                        City city = new City();
                        city.setCityName(resultSet.getString("city_name"));
                        city.setIdCity(resultSet.getInt("id_city"));
                        post.setCity(city);
                        post.setDesc(resultSet.getString("desc"));
                        post.setEmailAllowed(resultSet.getBoolean("email_allowed"));
                        post.setHomeType(resultSet.getString("home_type"));
                        post.setPrice(resultSet.getDouble("price"));
                        post.setRoomCount(resultSet.getInt("room_count"));
                        post.setPostType(resultSet.getString("home_type"));
                        post.setStatus(resultSet.getString("status"));
                        post.setShareDate(resultSet.getTimestamp("adding_time").toLocalDateTime());
                        User user = new User();
                        user.setIdUser(resultSet.getInt("id_user"));
                        user.setEmail(resultSet.getString("email"));
                        user.setFirstName(resultSet.getString("first_name"));
                        user.setLastName(resultSet.getString("last_name"));
                        post.setUser(user);
                    }
                }
                return post;
            }
        });
    }

    @Override
    public void activatePost(int id) {
        int i = jdbcTemplate.update(ACTIVATE_POST_BY_ID, new Object[]{PostConstants.POST_STATUS_ACTIVE, id});
        if(i == 0){
            throw new RuntimeException();
        }
    }

}
