package nst;

import nst.app.dto.*;
import nst.app.enums.AddressType;
import nst.app.enums.UserType;
import nst.app.manager.SystemManager;
import nst.app.service.AgencyLicenseDuplicateService;
import nst.app.service.AgencyLicenseModificationService;
import nst.app.service.AgencyLicenseRenewalService;
import nst.app.service.ApplicationAttachmentService;
import nst.app.service.ApplicationQueryService;
import nst.app.service.ContractorLicenseService;
import nst.app.service.ContractorModificationService;
import nst.app.service.ContractorPartnersModificationService;
import nst.app.service.ContractorRenewalService;
import nst.app.service.DuplicatePermitService;
import nst.app.service.EAndMAgencyLicenseService;
import nst.app.service.EscalatorInstallationService;
import nst.app.service.FormIService;
import nst.app.service.FormService;
import nst.app.service.IAndTAgencyLicenseService;
import nst.app.service.InterStatePermitService;
import nst.app.service.InterStateRenewalService;
import nst.app.service.LiftEscalatorDuplicateService;
import nst.app.service.LiftEscalatorModificationService;
import nst.app.service.LiftEscalatorRenewalService;
import nst.app.service.LiftInstallationService;
import nst.app.service.LocaleService;
import nst.app.service.LookupService;
import nst.app.service.NewgenService;
import nst.app.service.OAndMAgencyLicenseService;
import nst.app.service.OperatingEscalatorService;
import nst.app.service.OperatingLiftService;
import nst.app.service.PortalUserService;
import nst.app.service.RepeaterService;
import nst.app.service.ReportAccidentService;
import nst.app.service.SupervisorExaminationService;
import nst.app.service.SupervisorExemptionService;
import nst.app.service.SupervisorModificationService;
import nst.app.service.SupervisorRenewalService;
import nst.app.service.WiremanExaminationService;
import nst.app.service.WiremanExemptionService;
import nst.app.service.WiremanModificationService;
import nst.app.service.WiremanRenewalService;
import nst.common.security.LoginUser;
import nst.start.CEIApplication;
import nst.util.JSONUtil;
import nst.util.LoginUserUtil;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CEIApplication.class, loader = SpringApplicationContextLoader.class)
public class AbstractTest {

  @Autowired
  SystemManager systemManager;

  @Autowired
  LookupService lookupService;
  @Autowired
  LocaleService localeService;
  @Autowired
  LiftEscalatorDuplicateService liftEscalatorDuplicateService;
  @Autowired
  LiftEscalatorRenewalService liftEscalatorRenewalService;
  @Autowired
  LiftEscalatorModificationService liftEscalatorModificationService;

  @Autowired
  EscalatorInstallationService escalatorInstallationService;
  @Autowired
  LiftInstallationService liftInstallationService;

  @Autowired
  EAndMAgencyLicenseService eAndMAgencyLicenseService;
  @Autowired
  IAndTAgencyLicenseService iAndTAgencyLicenseService;
  @Autowired
  OAndMAgencyLicenseService oAndMAgencyLicenseService;

  @Autowired
  AgencyLicenseDuplicateService agencyLicenseDuplicateService;
  @Autowired
  AgencyLicenseModificationService agencyLicenseModificationService;
  @Autowired
  AgencyLicenseRenewalService agencyLicenseRenewalService;

  @Autowired
  OperatingEscalatorService operatingEscalatorService;
  @Autowired
  OperatingLiftService operatingLiftService;
  @Autowired
  ReportAccidentService reportAccidentService;

  @Autowired
  FormService formService;
  @Autowired
  ApplicationAttachmentService attachmentService;
  @Autowired
  ApplicationQueryService queryService;

  @Autowired
  PortalUserService portalUserService;

  @Autowired
  InterStatePermitService interStatePermitService;
  @Autowired
  ContractorModificationService contractorModificationService;
  @Autowired
  ContractorRenewalService contractorRenewalService;
  @Autowired
  ContractorLicenseService contractorLicenseService;
  @Autowired
  WiremanExaminationService wiremanExaminationService;
  @Autowired
  //SuperwiserRenewalService superwiserRenewalService;
  SupervisorRenewalService supervisorRenewalService;

  @Autowired
  ContractorPartnersModificationService contractorPartnersModificationService;
  @Autowired
  WiremanRenewalService wiremanRenewalService;
  @Autowired
  FormIService formIService;
  @Autowired
  SupervisorExaminationService supervisorExaminationService;

  @Autowired
  DuplicatePermitService duplicatePermitService;
  @Autowired
  WiremanExemptionService wiremanExemptionService;
  @Autowired
  RepeaterService repeaterService;
  @Autowired
  SupervisorExemptionService supervisorExemptionService;
  @Autowired
  WiremanModificationService wiremanModificationService;
  @Autowired
  InterStateRenewalService interStateRenewalService;
  @Autowired
  SupervisorModificationService supervisorModificationService;

  @Autowired
  PortalUserService service;

  @Autowired
  NewgenService newgenService;

  static {
    LoginUserUtil.IS_TEST = Boolean.TRUE;
  }

  @Before
  public void loadUsers(){
    PortalUserDTO contractor = portalUserService.getDetail("Wireman");
    LoginUserUtil.WIREMAN = new LoginUser(contractor.getId(), "Wireman", "Vishal", LoginUserUtil.getGrantedAuthorities(UserType.WIREMAN.getType()));

  }

  public void printJSON(Object o){
    System.out.println(JSONUtil.toJSON(o));
  }

    /*All address will set reuse this*/
    public AddressDTO getAddressDTOByAddressType(AddressType addressType){
        AddressDTO address = new AddressDTO();
        address.setAddressType(addressType.getType());
        address.setAddrLine1("AddrLine1");
        address.setAddrLine2("AddrLine2");
        address.setAddressLine3("AddressLine3");
        address.setBuildingName("Safal Complex");
        address.setVillage("sarkhej");
        address.setTalukaName("Ahmedabad");
        address.setState("GUJARAT");
        address.setPincode("390019");
        address.setHouseNo("35");
        address.setDistrict("Ahmedabad");
        return  address;
    }

    /*Business address will set reuse this*/
    public BusinessAddressDTO getBMAddressDTOByAddressType(AddressType addressType){
        BusinessAddressDTO address = new BusinessAddressDTO();
        address.setAddressType(addressType.getType());
        address.setAddrLine1("AddrLine1");
        address.setAddrLine2("AddrLine2");
        address.setAddressLine3("AddressLine3");
        address.setBuildingName("Safal Complex");
        address.setVillage("sarkhej");
        address.setTalukaName("Ahmedabad");
        address.setState("GUJARAT");
        address.setPincode("390019");
        address.setHouseNo("35");
        address.setDistrict("Ahmedabad");
        address.setBusinessEmail("ra@.fd.d");
        address.setBusinessWebsite("wwww.dfs.fds");
        address.setBusinessContactNo("78977979797");
        return  address;
    }

    /*Branch address will set reuse this*/
    public BranchAddressDTO getBranchAddressDTOByAddressType(AddressType addressType){
        BranchAddressDTO address = new BranchAddressDTO();
        address.setAddressType(addressType.getType());
        address.setAddrLine1("AddrLine1");
        address.setAddrLine2("AddrLine2");
        address.setAddressLine3("AddressLine3");
        address.setBuildingName("Safal Complex");
        address.setVillage("sarkhej");
        address.setTalukaName("Ahmedabad");
        address.setState("GUJARAT");
        address.setPincode("390019");
        address.setHouseNo("35");
        address.setDistrict("Ahmedabad");
        address.setBranchWebsite("www.google.com");
        address.setBranchEmail("test@gmail.com");
        address.setBranchContactNo("7879879797");
        return  address;
    }

    /*All Test LEAddress will set reuse this*/
    public LEAddressDTO getLEAddressDTOByAddressType(AddressType addressType){
        LEAddressDTO leAddress = new LEAddressDTO();
        leAddress.setAddressType(addressType.getType());
        leAddress.setBuildingName("Safal Complex");
        leAddress.setVillage("sarkhej");
        leAddress.setTalukaName("Ahmedabad");
        leAddress.setState("GUJARAT");
        leAddress.setPincode("390019");
        leAddress.setHouseNo("35");
        leAddress.setDistrict("Ahmedabad");
        leAddress.setPremisesName("premisesName");
        leAddress.setSchemeNo("SchemeNo");
        leAddress.setSiteName("siteName");
        leAddress.setFpNo("fpNo");
        leAddress.setRsNo("rsNo");
        leAddress.setSubPlotNo("subPlotNo");
        leAddress.setCitySurveyNo("citySurveyNo");
        leAddress.setCountry("citySurveyNo");
        return  leAddress;
    }


}
