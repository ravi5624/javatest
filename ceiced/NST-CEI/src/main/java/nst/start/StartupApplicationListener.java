package nst.start;

import nst.app.dto.*;
import nst.app.enums.ApplicationType;
import nst.app.enums.UserType;
import nst.app.model.ApplicationDate;
import nst.app.model.ApplicationFees;
import nst.app.repo.ApplicationDateRepository;
import nst.app.repo.ApplicationFeesRepository;
import nst.app.service.AgencyService;
import nst.app.service.OwnerDetailService;
import nst.app.service.PortalUserService;
import nst.common.security.LoginUser;
import nst.config.DataSourceConfig;
import nst.config.MyLogger;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

@Component
public class StartupApplicationListener implements
    ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  PortalUserService portalUserService;

  @Autowired
  OwnerDetailService ownerDetailService;

  @Autowired
  AgencyService agencyService;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private ApplicationFeesRepository feesRepository;

  @Autowired
  private ApplicationDateRepository dateRepository;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

      if (true) {
     return;
      }

    try {
      DatabaseMetaData md = dataSource.getConnection().getMetaData();
      ResultSet rs = md.getTables(null, null, "oauth_%_token", null);
      boolean oauth_access_token = false;
      boolean oauth_refresh_token = false;

      while (rs.next()) {
        oauth_access_token =
            oauth_access_token | "oauth_access_token".equalsIgnoreCase(rs.getString(3));
        oauth_refresh_token =
            oauth_refresh_token | "oauth_refresh_token".equalsIgnoreCase(rs.getString(3));
      }

      if (!oauth_access_token || !oauth_refresh_token) {
        System.out.println("Please create oauth_access_token & oauth_refresh_token tables.");
        System.exit(0);
      }
    } catch (Throwable throwable) {
      MyLogger.log("StartupApplicationListener:onApplicationEvent",
          "oauth_access_token & oauth_refresh_token check....");
      MyLogger.logError(throwable);
    }

    if (DataSourceConfig.HIBERNATE_HBM2DDL_AUTO.equalsIgnoreCase("create") == false) {
      MyLogger.log("StartupApplicationListener:onApplicationEvent", "For Update....");
      return;
    }
    MyLogger.log("StartupApplicationListener", "Increment counter");

    PortalUserDTO portalUser = new PortalUserDTO();

    portalUser.setFirstName("Vishal");
    portalUser.setLastName("Patel");
    portalUser.setUserName("EM_AGENCY");
    portalUser.setPassword("Vishal");
    portalUser.setEmail("vishal.patel@nascentinfo.com");
    portalUser.setType(UserType.EM_AGENCY.getType());
    PortalUserDTO agencyUser = portalUserService.register(portalUser);

    LoginUserUtil.EM_AGENCY = new LoginUser(agencyUser.getId(), portalUser.getUserName(),
        portalUser.getPassword(), LoginUserUtil
        .getGrantedAuthorities(UserType.EM_AGENCY.getType()));

    EAndMAgencyDTO eAndMAgencyDTO = new EAndMAgencyDTO();
    eAndMAgencyDTO.setAgencyName("EAndMAgency");
    eAndMAgencyDTO.setUserId(agencyUser.getId());
    agencyService.save(eAndMAgencyDTO);

    portalUser.setFirstName("Vishal");
    portalUser.setLastName("Patel");
    portalUser.setUserName("IT_AGENCY");
    portalUser.setPassword("Vishal");
    portalUser.setEmail("vishal.patel@nascentinfo.com");
    portalUser.setType(UserType.IT_AGENCY.getType());
    agencyUser = portalUserService.register(portalUser);

    LoginUserUtil.IT_AGENCY = new LoginUser(agencyUser.getId(), portalUser.getUserName(),
        portalUser.getPassword(), LoginUserUtil
        .getGrantedAuthorities(UserType.IT_AGENCY.getType()));

    IAndTAgencyDTO iAndTAgencyDTO = new IAndTAgencyDTO();
    iAndTAgencyDTO.setAgencyName("IAndTAgency");
    iAndTAgencyDTO.setUserId(agencyUser.getId());
    agencyService.save(iAndTAgencyDTO);

    portalUser.setFirstName("Vishal");
    portalUser.setLastName("Patel");
    portalUser.setUserName("OM_AGENCY");
    portalUser.setPassword("Vishal");
    portalUser.setEmail("vishal.patel@nascentinfo.com");
    portalUser.setType(UserType.OM_AGENCY.getType());
    agencyUser = portalUserService.register(portalUser);

    LoginUserUtil.OM_AGENCY = new LoginUser(agencyUser.getId(), portalUser.getUserName(),
        portalUser.getPassword(), LoginUserUtil
        .getGrantedAuthorities(UserType.OM_AGENCY.getType()));

    OAndMAgencyDTO oAndMAgencyDTO = new OAndMAgencyDTO();
    oAndMAgencyDTO.setAgencyName("OAndMAgency");
    oAndMAgencyDTO.setUserId(agencyUser.getId());
    agencyService.save(oAndMAgencyDTO);

    portalUser.setFirstName("Vishal");
    portalUser.setLastName("Patel");
    portalUser.setUserName("Guest");
    portalUser.setPassword("Vishal");
    portalUser.setEmail("vishal.patel@nascentinfo.com");
    portalUser.setType(UserType.GUEST.getType());
    PortalUserDTO guest = portalUserService.register(portalUser);

    LoginUserUtil.GUEST = new LoginUser(guest.getId(), portalUser.getUserName(),
        portalUser.getPassword(), LoginUserUtil
        .getGrantedAuthorities(UserType.GUEST.getType()));

    portalUser.setFirstName("Vishal");
    portalUser.setLastName("Patel");
    portalUser.setUserName("Wireman");
    portalUser.setPassword("Vishal");
    portalUser.setEmail("vishal.patel@nascentinfo.com");
    portalUser.setType(UserType.WIREMAN.getType());
    PortalUserDTO Wireman = portalUserService.register(portalUser);

    LoginUserUtil.WIREMAN = new LoginUser(Wireman.getId(), portalUser.getUserName(),
        portalUser.getPassword(), LoginUserUtil
        .getGrantedAuthorities(UserType.WIREMAN.getType()));

    portalUser.setFirstName("Vishal");
    portalUser.setLastName("Patel");
    portalUser.setUserName("Supervisor");
    portalUser.setPassword("Vishal");
    portalUser.setEmail("vishal.patel@nascentinfo.com");
    portalUser.setType(UserType.SUPERVISOR.getType());
    PortalUserDTO supervisor = portalUserService.register(portalUser);

    LoginUserUtil.SUPERVISOR = new LoginUser(supervisor.getId(), portalUser.getUserName(),
        portalUser.getPassword(), LoginUserUtil
        .getGrantedAuthorities(UserType.SUPERVISOR.getType()));

    portalUser.setFirstName("Vishal");
    portalUser.setLastName("Patel");
    portalUser.setUserName("Contractor");
    portalUser.setPassword("Vishal");
    portalUser.setEmail("vishal.patel@nascentinfo.com");
    portalUser.setType(UserType.CONTRACTOR.getType());
    PortalUserDTO Contractor = portalUserService.register(portalUser);

    LoginUserUtil.CONTRACTOR = new LoginUser(Contractor.getId(), portalUser.getUserName(),
        portalUser.getPassword(), LoginUserUtil
        .getGrantedAuthorities(UserType.CONTRACTOR.getType()));

    portalUser.setFirstName("Owner-FN");
    portalUser.setLastName("Owner-LN");
    portalUser.setUserName("Owner");
    portalUser.setPassword("Vishal");
    portalUser.setEmail("vishal.patel@nascentinfo.com");
    portalUser.setType(UserType.OWNER.getType());
    PortalUserDTO ownerUser = portalUserService.register(portalUser);

    LoginUserUtil.OWNER_USER = new LoginUser(ownerUser.getId(), portalUser.getUserName(),
        portalUser.getPassword(), LoginUserUtil
        .getGrantedAuthorities(UserType.OWNER.getType()));

    OwnerDTO ownerDTO = new OwnerDTO();
    ownerDTO.setOwnerName("Owner-1");
    ownerDTO.setUserId(ownerUser.getId());
    ownerDetailService.saveForm(ownerDTO);
    //feesRepository.save(new ApplicationFees(ApplicationType.ISREN, 120d));

    feesRepository.save(new ApplicationFees(ApplicationType.EMAL, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.ITAL, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.OMAL, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.ALMODI, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.ALDUPL, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.ALRENE, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.LIL, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.EIL, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.LEDL, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.LEML, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.LERL, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.OESCL, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.OLIFT, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.ACCIDENT, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.ISPERMIT, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.DUPPER, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.CONMODI, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.CONREN, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.CONLIC, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.CONPARTMODI, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.WIREXM, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.WIREXMP, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.WIRMODI, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.WIRREN, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.SUPEXM, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.SUPEXMP, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.SUPREN, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.SUPMODI, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.FORMI, 120d));
    feesRepository.save(new ApplicationFees(ApplicationType.REPEATER, 120d));

    dateRepository
        .save(new ApplicationDate(ApplicationType.EMAL, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.ITAL, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.OMAL, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.ALMODI, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.ALDUPL, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.ALRENE, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.LIL, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.EIL, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.LEDL, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.LEML, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.LERL, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.OESCL, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.OLIFT, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.ACCIDENT, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.ISPERMIT, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.DUPPER, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.CONMODI, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.CONREN, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.CONLIC, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.CONPARTMODI, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.WIREXM, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.WIREXMP, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.WIRMODI, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.WIRREN, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.SUPEXM, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.SUPEXMP, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.SUPREN, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.SUPMODI, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.FORMI, AllUtil.toDate("01/01/2018"), null));
    dateRepository
        .save(new ApplicationDate(ApplicationType.REPEATER, AllUtil.toDate("01/01/2018"), null));
  }
}