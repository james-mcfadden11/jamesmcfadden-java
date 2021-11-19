package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.account.Account;
import com.techelevator.tenmo.model.user.User;
import com.techelevator.tenmo.model.user.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserSqlDaoTests extends BaseDaoTests {
    protected static final User USER_1 = new User(1001L, "user1", "user1", "ROLE_USER");
    protected static final User USER_2 = new User(1002L, "user2", "user2", "ROLE_USER");
    private static final User USER_3 = new User(1003L, "user3", "user3", "ROLE_USER");

    private UserSqlDAO sut;
    private AccountSqlDAO accountSqlDAO;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new UserSqlDAO(jdbcTemplate);
        accountSqlDAO = new AccountSqlDAO(jdbcTemplate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findIdByUsername_given_null_throws_exception() {
        sut.findIdByUsername(null);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void findIdByUsername_given_invalid_username_throws_exception() {
        sut.findIdByUsername("invalid");
    }

    @Test
    public void findIdByUsername_given_valid_user_returns_user_id() {
        int actualUserId = sut.findIdByUsername(USER_1.getUsername());

        Assert.assertEquals(USER_1.getId().intValue(), actualUserId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUsername_given_null_throws_exception() {
        sut.findByUsername(null);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void findByUsername_given_invalid_username_throws_exception() {
        sut.findByUsername("invalid");
    }

    @Test
    public void findByUsername_given_valid_user_returns_user() {
        User actualUser = sut.findByUsername(USER_1.getUsername());

        Assert.assertEquals(USER_1, actualUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserById_given_null_throws_exception() {
        sut.getUserById(null);
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserById_given_invalid_user_id_throws_exception() {
        sut.getUserById(-1L);
    }

    @Test
    public void getUserById_given_valid_user_id_returns_user() {
        User actualUser = sut.getUserById(USER_1.getId());

        Assert.assertEquals(USER_1, actualUser);
    }

    @Test
    public void findAll_returns_all_users() {
        List<User> users = sut.findAll();

        Assert.assertNotNull(users);
        Assert.assertEquals(3, users.size());
        Assert.assertEquals(USER_1, users.get(0));
        Assert.assertEquals(USER_2, users.get(1));
        Assert.assertEquals(USER_3, users.get(2));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void create_user_with_null_username() {
        sut.create(null, USER_3.getPassword());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void create_user_with_existing_username() {
        sut.create(USER_1.getUsername(), USER_3.getPassword());
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_user_with_null_password() {
        sut.create(USER_3.getUsername(), null);
    }

    @Test
    public void create_user_creates_a_user() {
        User newUser = new User(-1L, "new", "user", "ROLE_USER");

        boolean userWasCreated = sut.create(newUser.getUsername(), newUser.getPassword());

        Assert.assertTrue(userWasCreated);

        User actualUser = sut.findByUsername(newUser.getUsername());
        newUser.setId(actualUser.getId());

        Assert.assertEquals(newUser, actualUser);
    }

    @Test
    public void create_user_creates_an_account() {
        // Arrange
        User newUser = new User(-1L, "new", "user", "ROLE_USER");

        sut.create(newUser.getUsername(), newUser.getPassword());
        int userId = sut.findIdByUsername(newUser.getUsername());
        Account expectedUser3Account = new Account(2004L, (long) userId, AccountSqlDaoTests.ONE_THOUSAND_DOLLARS);

        // Act
        Account actualUser3Account = accountSqlDAO.getAccountByUserId((long)userId);

        // Assert
        Assert.assertEquals(expectedUser3Account, actualUser3Account);
    }

}
