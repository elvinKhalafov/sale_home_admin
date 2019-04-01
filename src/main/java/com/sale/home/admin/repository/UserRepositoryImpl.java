package com.sale.home.admin.repository;

import com.sale.home.admin.constants.UserConstants;
import com.sale.home.admin.model.Role;
import com.sale.home.admin.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {



    private final String GET_USER_BY_EMAIL = "select * from user u inner join role r on u.id_role = r.id_role where u.email = ?";

    private static final String BLOCK_USER_BY_ID_SQL = "update user set status = ? where id_user = ?";

    private static final String ACTIVATE_USER_BY_ID_SQL = "update user set status = ? where id_user = ?";

    private final String GET_ALL_USERS = "select id_user, email, status, first_name, last_name from user where status=?";




    @Autowired
    private JdbcTemplate jdbcTemplate;




    @Override
    public User loginUser(String email)  {
      User user;
        try {

            user = jdbcTemplate.queryForObject(GET_USER_BY_EMAIL, new Object[]{email}, new RowMapper<User>() {
                @Nullable
                @Override
                public User mapRow(ResultSet rs, int i) throws SQLException {

                    User user = new User();
                    user.setIdUser(rs.getInt("id_user"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setToken(rs.getString("token"));
                    user.setStatus(rs.getInt("status"));
                    Role role = new Role();
                    role.setIdRole(rs.getInt("id_role"));
                    role.setRoleType(rs.getString("role_type"));
                    user.setRole(role);
                    return user;

                }
            });
        } catch (EmptyResultDataAccessException er) {
            return null;
        }


    return user;

    }

    @Override
    public List<User> getAllUsers(int status) {
        List<User> users =jdbcTemplate.query(GET_ALL_USERS, new Object[]{status}, new RowMapper<User>() {
            @Nullable
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setStatus(rs.getInt("status"));
                Role role = new Role();
                role.setIdRole(rs.getInt("id_role"));
                role.setRoleType(rs.getString("role_type"));
                user.setRole(role);
                return user;
            }
        });
        return users;
    }



    @Override
    public void blockUser(int id) {
        int i =jdbcTemplate.update(BLOCK_USER_BY_ID_SQL, id);
        if(i==0){
            throw  new RuntimeException();
        }

    }

    @Override
    public void activateUser(int id) {
        int i =jdbcTemplate.update(ACTIVATE_USER_BY_ID_SQL, id);
        if(i==0){
            throw new RuntimeException();
        }

    }





    }
