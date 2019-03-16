package com.sale.home.admin.repository;

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

@Repository
public class UserRepositoryImpl implements UserRepository {



    private final String GET_USER_BY_EMAIL = "select * from user u inner join role r on u.id_role = r.id_role where u.email = ?";



    private final String GET_EMAIL_COUNT_SQL = "select count(*) as say from user where email=?";




    @Autowired
    private JdbcTemplate jdbcTemplate;




    @Override
    public User loginUser(String email)  {
      User user = null;
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



    private boolean isEmailValid(String email) {
      boolean result = false;
      int count = jdbcTemplate.queryForObject(GET_EMAIL_COUNT_SQL, new Object[]{email}, Integer.class);
      if(count == 0){
          result = true;
      }


      return result;

    }


    }
