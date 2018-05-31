package nst;

import nst.app.dto.PortalUserDTO;
import nst.app.enums.UserType;
import nst.app.service.PortalUserService;
import nst.start.CEIApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CEIApplication.class, loader = SpringApplicationContextLoader.class)
public class MyTests {

  @Autowired
  PortalUserService portalUserService;

  @Test
  public void addNew() {
    PortalUserDTO portalUser = new PortalUserDTO();

    portalUser.setFirstName("Kalpesh");
    portalUser.setLastName("Patel");
    portalUser.setUserName("WKalpesh");
    portalUser.setPassword("Password");
    portalUser.setEmail("ceiced@newgen.co.in");
    portalUser.setType(UserType.WIREMAN.getType());
    PortalUserDTO Wireman = portalUserService.register(portalUser);

    portalUser.setFirstName("Kalpesh");
    portalUser.setLastName("Patel");
    portalUser.setUserName("SKalpesh");
    portalUser.setPassword("Password");
    portalUser.setEmail("ceiced@newgen.co.in");
    portalUser.setType(UserType.SUPERVISOR.getType());
    PortalUserDTO supervisor = portalUserService.register(portalUser);

    portalUser.setFirstName("Kalpesh");
    portalUser.setLastName("Patel");
    portalUser.setUserName("CKalpesh");
    portalUser.setPassword("Password");
    portalUser.setEmail("ceiced@newgen.co.in");
    portalUser.setType(UserType.CONTRACTOR.getType());
    PortalUserDTO Contractor = portalUserService.register(portalUser);
  }
}
