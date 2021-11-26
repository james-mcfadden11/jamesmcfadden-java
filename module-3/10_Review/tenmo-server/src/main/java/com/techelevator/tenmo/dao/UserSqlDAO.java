package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.user.User;
import com.techelevator.tenmo.model.user.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserSqlDAO implements UserDAO {

    private static final double STARTING_BALANCE = 1000;
    private final JdbcTemplate jdbcTemplate;

    public UserSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        verifyUsername(username);

        String sql = "select user_id from users where username = ?;";

        try {
            Integer userId = jdbcTemplate.queryForObject(sql, Integer.class, username);

            if(userId != null) return userId;

        } catch(EmptyResultDataAccessException ignored) { }

        String message = getMissingUserMessage(username);
        throw new UsernameNotFoundException(message);
    }


    @Override
	public User getUserById(Long userId) throws UserNotFoundException {
		String sql = "select * from users where user_id = ?;";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if(results.next()) {
			return mapRowToUser(results);
		}

        throw new UserNotFoundException();
	}
	
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users order by user_id;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) {
        verifyUsername(username);

        String sql = "select * from users where username = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if(result.next()) {
            return mapRowToUser(result);
        }

        String message = getMissingUserMessage(username);
        throw new UsernameNotFoundException(message);
    }

    @Override
    public boolean create(String username, String password) {
        boolean userCreated;
        boolean accountCreated;

        // create user
        String insertUser = "insert into users (username,password_hash) values(?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2,password_hash);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) Objects.requireNonNull(keyHolder.getKeys()).get(id_column);

        // create account
        String insertAccount = "insert into accounts (user_id,balance) values(?,?)";
        accountCreated = jdbcTemplate.update(insertAccount,newUserId,STARTING_BALANCE) == 1;

        return userCreated && accountCreated;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setActivated(true);
        user.setAuthorities("ROLE_USER");
        return user;
    }

    private void verifyUsername(String username) {
        if(username == null) {
            throw new IllegalArgumentException("username cannot be null.");
        }
    }

    private String getMissingUserMessage(String username) {
        return "User " + username + " was not found.";
    }
}