package calendar.repositoryTest;

import calendar.domain.Account;
import calendar.repository.AccountRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private PasswordEncoder encoder;

    public AccountRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindByUsername() {
        Account a = new Account();
        a.setUsername("user1");
        String password= encoder.encode("Password1");
        a.setPassword(password);
        a.setRole("USER");

        accountRepository.save(a);

        Account retrieved = accountRepository.findByUsername("user1");
        assertNotNull(retrieved);
        assertEquals(password, retrieved.getPassword());
        assertEquals("USER", retrieved.getRole());
    }

}
