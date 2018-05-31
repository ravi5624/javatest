package nst;

import nst.app.common.CEIBaseService;
import nst.app.dto.LicenseSearchDTO;
import nst.app.dto.LiftInstallationDTO;
import nst.app.dto.PortalUserDTO;
import nst.app.dto.newgen.NGQueryRequestDTO;
import nst.app.dto.newgen.NGQueryRequestDTO.NGQueryDetailDTO;
import nst.app.dto.newgen.NGRequestDTO;
import nst.app.enums.ApplicationType;
import nst.app.enums.UserType;
import nst.common.base.BaseDTO;
import nst.common.base.BaseModelDTO;
import nst.common.error.AppException;
import nst.config.DataSourceConfig;
import nst.dto.AttachmentDTO;
import nst.dto.TransactionDTO;
import nst.util.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestAll extends AbstractTest {

  @Autowired
  DataSourceConfig dataSourceConfig;

  @Test
  public void testAttachment() throws Exception{
    LoginUserUtil.USER = LoginUserUtil.EM_AGENCY;
    LiftInstallationDTO liftInstallationDTO = liftInstallationService.create();
    File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
    MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
        "image/png", new FileInputStream(fileItem));
    AttachmentDTO add = attachmentService.add(202, multipartFile, "78.1.1", "local_proof", "Rajachitti development", "1",
        null);
  }


  @Test
  public void testSystemManager() {
    systemManager.isEnabled(ApplicationType.REPEATER);
    System.out.println(systemManager.findFees(ApplicationType.REPEATER));
  }

  @Test
  public void testSequence() {
    SequenceUtil sequenceUtil = new SequenceUtil();
    Long states_seq = sequenceUtil.getNextSequence("states_seq");
    System.out.println("1: " + states_seq);
    states_seq = sequenceUtil.getNextSequence("states_seq");
    System.out.println("2: " + states_seq);

    sequenceUtil.resetSequence("states_seq", states_seq - 1);

    states_seq = sequenceUtil.getNextSequence("states_seq");
    System.out.println("2: " + states_seq);
  }

  public void testValidateUserName(String userName) {
    portalUserService.validateUser(UserType.OWNER, userName);
  }

  public void testValidateEmail(UserType userType, String email) {
    portalUserService.validateEmail(userType, email);
  }

  @Test
  public void testRegisterUser() {
    PortalUserDTO portalUser = new PortalUserDTO();
    portalUser.setFirstName("Sagar");
    portalUser.setLastName("Makvana");
    portalUser.setUserName("shmakvana");
    portalUser.setPassword("Sagar");
    portalUser.setEmail("sagar.makvana@nascentinfo.com");
    portalUser.setType(UserType.OWNER.getType());

    testValidateUserName(portalUser.getUserName());
    testValidateEmail(UserType.valueOf(portalUser.getType()), portalUser.getEmail());

    PortalUserDTO portalUserDTO = portalUserService.register(portalUser);
    System.out.println(portalUserDTO);
  }

  @Test
  public void verifyEmail() {
    portalUserService
        .verifyEmail("0495c89876e74557931283ca4996e001", "dce3db28307e49eb98b134f45018d409");
    System.out.println("success");
  }

  @Test
  public void testLocal() throws Throwable {
    Map<String, Map<String, Map<String, String>>> all = localeService.getAll(null);
    System.out.println(JSONUtil.toJSON(all));
  }

  @Test
  public void testState() throws Throwable {
    System.out.println(lookupService.getAllState());
    System.out.println(lookupService.getAllDistrict());
  }

  @Test
  public void testSearch() throws Throwable {
    LicenseSearchDTO dto = new LicenseSearchDTO();
    dto.setType(ApplicationType.OLIFT.getType());
    dto.setLicenseNumber("L-1");
    dto.setIssueDate(AllUtil.formatDate(new Date()));
    dto.setExpiryDate(AllUtil.formatDate(new Date()));
    BaseDTO baseDTO = service.searchApplication(dto);
    System.out.println("baseDTO = " + baseDTO.toJSON());
  }

  @Test
  public void get() throws Throwable {
    LoginUserUtil.USER = LoginUserUtil.WIREMAN;
    System.out
        .println("wiremanExaminationService = " + wiremanExaminationService.getJson(133l).toJSON());
    System.out
        .println("wiremanExemptionService = " + wiremanExemptionService.getJson(134l).toJSON());
    System.out.println(
        "wiremanModificationService = " + wiremanModificationService.getJson(132l).toJSON());
    System.out.println("wiremanRenewalService = " + wiremanRenewalService.getJson(131l).toJSON());
  }

  @Test
  public void testAddAll() throws Throwable {

    LoginUserUtil.USER = LoginUserUtil.OWNER_USER;

    perform(liftInstallationService);
    perform(escalatorInstallationService);
    perform(reportAccidentService);
    perform(liftEscalatorRenewalService);
    perform(liftEscalatorModificationService);
    perform(liftEscalatorDuplicateService);
    perform(operatingLiftService);
    perform(operatingEscalatorService);

    System.out.println("===============================================");

    LoginUserUtil.USER = LoginUserUtil.EM_AGENCY;
    perform(eAndMAgencyLicenseService);
    perform(agencyLicenseRenewalService);
    perform(agencyLicenseModificationService);
    perform(agencyLicenseDuplicateService);

    LoginUserUtil.USER = LoginUserUtil.IT_AGENCY;
    perform(iAndTAgencyLicenseService);
    perform(agencyLicenseRenewalService);
    perform(agencyLicenseModificationService);
    perform(agencyLicenseDuplicateService);

    LoginUserUtil.USER = LoginUserUtil.OM_AGENCY;
    perform(oAndMAgencyLicenseService);
    perform(agencyLicenseRenewalService);
    perform(agencyLicenseModificationService);
    perform(agencyLicenseDuplicateService);

    /*For LIncence Board*/
    LoginUserUtil.USER = LoginUserUtil.CONTRACTOR;
    perform(contractorModificationService);
    perform(contractorRenewalService);
    perform(contractorLicenseService);
    perform(contractorPartnersModificationService);

    perform(interStatePermitService);
    perform(interStateRenewalService);
    perform(duplicatePermitService);

//    perform(formIService);
    perform(repeaterService);

    LoginUserUtil.USER = LoginUserUtil.WIREMAN;
    perform(wiremanExaminationService);
    perform(wiremanRenewalService);
    perform(wiremanExemptionService);
    perform(wiremanModificationService);

    LoginUserUtil.USER = LoginUserUtil.SUPERVISOR;
    perform(supervisorRenewalService);
    perform(supervisorExaminationService);
    perform(supervisorExemptionService);
    perform(supervisorModificationService);

    System.out
        .println("MyApps => " + portalUserService.getMyApps(null,"",RepoUtil.pagination(1, 50)));
    System.out.println(
        "MyNotifications => " + portalUserService.getMyNotifications(RepoUtil.pagination(1, 50)));
  }

  private void perform(CEIBaseService service) throws Throwable {
    try {
      System.out.println("======================================================");
      BaseModelDTO dto = service.create();
      service.saveForm(dto);
      System.out.println("dto.getId() = " + dto.getId());

      File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
      MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
          "image/png", new FileInputStream(fileItem));
      AttachmentDTO add = attachmentService
          .add(dto.getId(), multipartFile, "Original License", "original_License");
      System.out.println("add = " + add);
      add = attachmentService.add(dto.getId(), multipartFile, "Other documents", "other_documents");
      System.out.println("add = " + add);

      attachmentService.delete(dto.getId(), add.getId());

      BaseModelDTO baseModelDTO = service.get(dto.getId());
      System.out.println("GET = " + baseModelDTO.toJSON());

      List myForms = service.getMyForms();
      System.out.println("myForms = " + myForms.size());

      TransactionDTO processPayment = service.processPayment(dto.getId());
      try {
        service.submitForm(dto.getId());
      } catch (AppException throwable) {
        System.out.println("throwable = " + throwable);
      }

      formService.postPayment(processPayment.getTransactionId(), "SUCCESS");
      service.submitForm(dto.getId());
//      System.out.println("baseModelDTO = " + service.get(dto.getId()));

      NGQueryRequestDTO queryDTO = new NGQueryRequestDTO();
      queryDTO.getData().setApplicationId(dto.getUniqueId());
      queryDTO.getData().setPackId("fdsfsd");

      List<NGQueryDetailDTO> qrydtoList = new ArrayList<NGQueryDetailDTO>();
      NGQueryDetailDTO q = new NGQueryDetailDTO();
      q.setRaiseOn("2018-01-01 12:12:22");
      q.setQId("1");
      q.setRaiseBy("Vishal");
      q.setQRaise("Query - 1");
      qrydtoList.add(q);

      q = new NGQueryDetailDTO();
      q.setRaiseOn("2018-01-01 12:12:22");
      q.setQId("2");
      q.setRaiseBy("Vishal - 2");
      q.setQRaise("Query - 2");
      qrydtoList.add(q);
      queryDTO.getData().setQueries(qrydtoList);

      newgenService.queryRequested(queryDTO);
      NGRequestDTO ngRequestDTO = new NGRequestDTO();
      ngRequestDTO.setFileStatus("Completed");
      ngRequestDTO.setRemarks("File Completed");
      ngRequestDTO.setOutwardNo("OutwardNo");
      ngRequestDTO.setFileNo("FILE-No");
      ngRequestDTO.setApplicationId("" + dto.getId());
      newgenService.request(ngRequestDTO);

      //service.delete(dto.getId());

    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }
}