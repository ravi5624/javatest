package nst.util;

import nst.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestUtil extends AbstractTest {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Test
  public void password() {
    System.out.println(passwordEncoder.encode("Vishal"));
  }
}
