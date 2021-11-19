package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.account.Account;
import com.techelevator.tenmo.model.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;

@RunWith(SpringRunner.class)
public class UserControllerTests extends BaseDaoTests {
    private static final User USER_SELF = new User(1001L, "user1", "password", "ROLE_USER");
    private static final User USER_TWO = new User(1002L, "user2", "password", "ROLE_USER");
    private static final User USER_THREE = new User(1003L, "user3", "password", "ROLE_USER");
    private static final User USER_SPY = new User(1004L, "spy", "password", "ROLE_USER");
    private static final Principal PRINCIPAL_SELF = (UserPrincipal) USER_SELF::getUsername;
    private static final Principal PRINCIPAL_SPY = (UserPrincipal) USER_SPY::getUsername;

    private UserController controller;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        UserDAO userDAO = new UserSqlDAO(jdbcTemplate);

        controller = new UserController(userDAO);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void get_invalid_user_throws_exception() {
        controller.get(PRINCIPAL_SPY);
    }

    @Test
    public void get_self() {
        User user = controller.get(PRINCIPAL_SELF);

        Assert.assertEquals(USER_SELF, user);
    }

    @Test
    public void list_gets_all_users() {
        List<User> users = controller.list();

        Assert.assertNotNull(users);
        Assert.assertEquals(3, users.size());
        Assert.assertEquals(USER_SELF, users.get(0));
        Assert.assertEquals(USER_TWO, users.get(1));
        Assert.assertEquals(USER_THREE, users.get(2));
    }
}
