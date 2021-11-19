package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.AuthorizationException;
import com.techelevator.tenmo.model.account.Account;
import com.techelevator.tenmo.model.transfer.Transfer;
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

@RunWith(SpringRunner.class)
public class AccountControllerTests extends BaseDaoTests {
    private static final User USER_SELF = new User(1001L, "user1", "password", "ROLE_USER");
    private static final User USER_SPY = new User(1004L, "spy", "password", "ROLE_USER");
    private static final Principal PRINCIPAL_SELF = (UserPrincipal) USER_SELF::getUsername;
    private static final Principal PRINCIPAL_SPY = (UserPrincipal) USER_SPY::getUsername;

    protected static final BigDecimal ONE_THOUSAND_DOLLARS = BigDecimal.valueOf(100000, 2);

    private AccountController controller;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        AccountDAO accountDAO = new AccountSqlDAO(jdbcTemplate);
        UserDAO userDAO = new UserSqlDAO(jdbcTemplate);

        controller = new AccountController(accountDAO, userDAO);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void getBalance_as_invalid_user_throws_exception() {
        controller.getBalance(PRINCIPAL_SPY);
    }

    @Test
    public void getBalance_as_valid_user_returns_balance() {
        BigDecimal balance = controller.getBalance(PRINCIPAL_SELF);

        Assert.assertNotNull(balance);
        Assert.assertEquals(ONE_THOUSAND_DOLLARS, balance);
    }
}
