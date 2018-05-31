package nst;

import nst.app.dto.AddressDTO;
import nst.app.dto.BufferDetailDTO;
import nst.app.dto.LEAddressDTO;
import nst.app.dto.LiftInstallationDTO;
import nst.app.enums.AddressType;
import nst.app.enums.BufferType;
import nst.app.model.forms.le.LiftInstallation;
import nst.app.service.LiftInstallationService;
import nst.dto.AttachmentDTO;
import nst.start.CEIApplication;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CEIApplication.class, loader = SpringApplicationContextLoader.class)
public class TestLiftInstallation extends AbstractTest {

  @Autowired
  LiftInstallationService liftInstallationService;

  @Test
  public void getAll() {
    Iterable<LiftInstallation> all = liftInstallationService.getAll();
  }

  @Test
  public void get() {
    liftInstallationService.get(20l);
  }

  @Test
  public void addLifInst() {
    LiftInstallation form = new LiftInstallation();
//    form.setName("Name");
    /*form.getForm().setApplicationType(ApplicationType.LIL);
    form.getForm().setFileNumber("FFFNO");
    form.getForm().setUniqueId(AllUtil.getUUID());
    form.getForm().setFileStatus(FileStatus.DRAFT);

    form.getForm().addQuery(new ApplicationQuery(form.getForm()));
    form.getForm().addQuery(new ApplicationQuery(form.getForm()));
    form.getForm().addQuery(new ApplicationQuery(form.getForm()));

    form.getForm().addAttachments(new ApplicationAttachment(form.getForm()));
    form.getForm().addAttachments(new ApplicationAttachment(form.getForm()));
    form.getForm().addAttachments(new ApplicationAttachment(form.TestgetForm()));

    liftInstallationService.save(form);*/
  }



  @Test
  public void test() throws Throwable {

    LoginUserUtil.USER = LoginUserUtil.OWNER_USER;
    LiftInstallationDTO liftInstallationDTO = liftInstallationService.create();

    File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
    MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
            "image/png", new FileInputStream(fileItem));
    AttachmentDTO add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.1.1", "local_proof", "Rajachitti development", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.1.2", "ownership_proof", "Approval of civilplan from authority", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.1.3", "signature_proof", "Key plan", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.1.4", "lift_drawing", "elevation", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.1.5", "lift_drawing", "floor plan bottom , top and other (indivual)", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.2.1", "lift_drawing", "Partnership Deed / Memorandum of undertaking / articles of association", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.2.2", "lift_drawing", "Sale Deed (if any)", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.2.3", "lift_drawing", "Development aggreement", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.2.4", "lift_drawing", "Assignment deed (if any)", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.2.5", "lift_drawing", "Society registration certificate (if any)", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.2.6", "lift_drawing", "Transfer deed (if any)", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.2.7", "lift_drawing", "Index-2 , 7/12 Index, Template 8(A) , Template 6 & Other", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.3", "lift_drawing", " Proof of Sign", "1",
        null);

    add = attachmentService.add(liftInstallationDTO.getId(), multipartFile, "78.4", "lift_drawing", "Drawing of Lift", "1",
        null);

    LiftInstallation form = new LiftInstallation();
    liftInstallationDTO.setLiftApplication("Replacement");
    liftInstallationDTO.setLiftLicenseNumber("L311");
    liftInstallationDTO.setLeApplicantName("sagar");
    liftInstallationDTO.setAgencyId(30l);
    liftInstallationDTO.setApplicantEmail("sagar@gmail.com");
    liftInstallationDTO.setApplicantMobile("9999999999");
    liftInstallationDTO.setIsLocalAgent("Yes");
    liftInstallationDTO.setLocalAgentName("Local Agent");
    liftInstallationDTO.setLocalAgentEmail("agent@gmail.com");
    liftInstallationDTO.setLocalAgentContactNo("9999999999");
    liftInstallationDTO.setPersonName("person name");
    liftInstallationDTO.setPersonEmail("person@gmail.com");
    liftInstallationDTO.setPersonMobile("9999999999");
    liftInstallationDTO.setLiftType("Passanger");
    liftInstallationDTO.setSubCategoryLift("Traction without Machine room");
    liftInstallationDTO.setMakeOfLift("Sagar");
    liftInstallationDTO.setNumberOfLift(2);
    liftInstallationDTO.setNumberOfPassengers(20);
    liftInstallationDTO.setRatedLoad(200);
    liftInstallationDTO.setRatedSpeed(60);
    liftInstallationDTO.setTravelMeters(50);
    liftInstallationDTO.setTravelTime(20);
    liftInstallationDTO.setBasementFloors(2);
    liftInstallationDTO.setGroundFloors(2);
    liftInstallationDTO.setMezzaineFloors(1);
    liftInstallationDTO.setServedFloors(4);
    liftInstallationDTO.setNumberOfEntrances(6);
    liftInstallationDTO.setClosedFloors("2");
    liftInstallationDTO.setDummyFloors("2");
    liftInstallationDTO.setControlMethodForLift("control method");
    liftInstallationDTO.setLiftDoorOperationType("door operation");
    liftInstallationDTO.setCarLength(12.2);
    liftInstallationDTO.setCarBreadth(22.1);
    liftInstallationDTO.setPlatformDetails("plateform details");
    liftInstallationDTO.setCarFrameDetails("car frame details");
    liftInstallationDTO.setCarDoorDetails("car door details");
    liftInstallationDTO.setLandingDoorDetails("LandingDoorDetails");
    liftInstallationDTO.setLockingArrangementDetails("LockingArrangementDetails");
    liftInstallationDTO.setCounterweightPosition("CounterweightPosition");
    liftInstallationDTO.setCounterweightApproxKg("Approx kg");
    liftInstallationDTO.setLiftPitLength(12.21);
    liftInstallationDTO.setLiftPitWidth(12.12);
    liftInstallationDTO.setLiftPitBreadth(12.12);
    liftInstallationDTO.setLiftWellLength(21.12);
    liftInstallationDTO.setLiftWellBreadth(21.21);
    liftInstallationDTO.setLiftWellHeight(12.12);
    liftInstallationDTO.setLiftWellAndBeamDetails("LiftWellDetails");
    liftInstallationDTO.setHeadRoomDistance(12);
    liftInstallationDTO.setBottomCarRunBy(21);
    liftInstallationDTO.setBottomCounterWeightRunBy(21);
    liftInstallationDTO.setBottomCounterWeightClearance(12);
    liftInstallationDTO.setTopCounterWeightClearance(12);
    liftInstallationDTO.setBottomCarClearance(21);
    liftInstallationDTO.setTopCarClearance(12);
    liftInstallationDTO.setLiftWellDimLength(12.12);
    liftInstallationDTO.setLiftWellDimBreadth(12.12);
    liftInstallationDTO.setLiftWellDimHeight(12.12);
    liftInstallationDTO.setCarCounterDetail("CarCounterDetail");
    liftInstallationDTO.setCarBodyWork("CarBodyWork");
    liftInstallationDTO.setFrontDistance("FrontDistance");
    liftInstallationDTO.setRearDistance("setRearDistance");
    liftInstallationDTO.setLeftDistance("getLeftDistance");
    liftInstallationDTO.setRightDistance("RightDistance");
    liftInstallationDTO.setMachineRoomDetails("MachineRoomDetails");
    liftInstallationDTO.setMachineRoomLength(12.1);
    liftInstallationDTO.setMachineRoomBreadth(12.1);
    liftInstallationDTO.setMachineRoomDimHeight(12.21);
    liftInstallationDTO.setMachineGearDetail("setMachineGearDetail");
    liftInstallationDTO.setSheavePulleyDetail("setSheavePulleyDetail");
    liftInstallationDTO.setSheavePulleyDiameter(12);
    liftInstallationDTO.setDiverterPulleyDetail("setDiverterPulleyDetail");
    liftInstallationDTO.setRopingSize(12);
    liftInstallationDTO.setNoOfRope(12);
    liftInstallationDTO.setSupportCableDesc("setSupportCableDesc");
    liftInstallationDTO.setOverSpeedGovernor("setOverSpeedGovernor");
    liftInstallationDTO.setSafetyGearDetail("setSafetyGearDetail");
    liftInstallationDTO.setIsRetiringCam("Yes");
    liftInstallationDTO.setRetiringCamDetail("setRetiringCamDetail");
    liftInstallationDTO.setConstructionDetail("setConstructionDetail");
    liftInstallationDTO.setCallIndicatorDetail("setCallIndicatorDetail");
    liftInstallationDTO.setEmergencyStopSwitchDetail("setEmergencyStopSwitchDetail");
    liftInstallationDTO.setFloorLevelDetail("setFloorLevelDetail");
    liftInstallationDTO.setFloorSelectorDetail("setFloorSelectorDetail");
    liftInstallationDTO.setSlackRopeDetail("setSlackRopeDetail");
    liftInstallationDTO.setTerminalSlowDetail("setTerminalSlowDetail");
    liftInstallationDTO.setTerminalStopNormalDetail("setTerminalStopNormalDetail");
    liftInstallationDTO.setTerminalStopFinalDetail("setTerminalStopFinalDetail");
    liftInstallationDTO.setIsFiremanSwitch("Yes");
    liftInstallationDTO.setFiremanSwitchDetail("setFiremanSwitchDetail");
    liftInstallationDTO.setMainSwitchDetails("setMainSwitchDetails");
    liftInstallationDTO.setWiringMachineDetail("setWiringMachineDetail");
    liftInstallationDTO.setWiringLiftPitDetail("setWiringLiftPitDetail");
    liftInstallationDTO.setControlSwitchDetail("setControlSwitchDetail");
    liftInstallationDTO.setOverCurrentProtection("setOverCurrentProtection");
    liftInstallationDTO.setAlarmSystemDetail("setAlarmSystemDetail");
    liftInstallationDTO.setEarthingDetail("setEarthingDetail");
    liftInstallationDTO.setEmergencySignalDetail("setEmergencySignalDetail");
    liftInstallationDTO.setPowerDetail("setPowerDetail");
    liftInstallationDTO.setMainCablesDetail("setMainCablesDetail");
    liftInstallationDTO.setCommencementWork("2010-09-02T02:50:12");
    liftInstallationDTO.setCompletionWork("2010-09-02T02:50:12");

    AddressDTO address = new AddressDTO();
    address.setAddressType(AddressType.APPLICANT.getType());
    address.setHouseNo("35");
    address.setBuildingName("Safal Complex");
    address.setState("GUJARAT");
    address.setDistrict("Ahmedabad");
    address.setTalukaName("Ahmedabad");
    address.setPincode("390019");
    address.setAddrLine1("Line1");
    address.setAddrLine2("Line2");
    address.setVillage("village");

    liftInstallationDTO.setApplicantAddress(address);

    address = new AddressDTO();
    address.setAddressType(AddressType.AGENT.getType());
    address.setHouseNo("35");
    address.setBuildingName("Safal Complex");
    address.setState("GUJARAT");
    address.setDistrict("Ahmedabad");
    address.setTalukaName("Ahmedabad");
    address.setPincode("390019");
    address.setAddrLine1("Line1");
    address.setAddrLine2("Line2");
    address.setVillage("village");


    liftInstallationDTO.setLocalAgentAddress(address);

    address = new AddressDTO();
    address.setAddressType(AddressType.PERSON.getType());
    address.setHouseNo("35");
    address.setBuildingName("Safal Complex");
    address.setState("GUJARAT");
    address.setDistrict("Ahmedabad");
    address.setTalukaName("Ahmedabad");
    address.setPincode("390019");
    address.setAddrLine1("Line1");
    address.setAddrLine2("Line2");
    address.setVillage("village");


    liftInstallationDTO.setPersonAddress(address);

    LEAddressDTO leAddressDTO = new LEAddressDTO();
    leAddressDTO.setAddressType(AddressType.LIFTSITE.getType());
    leAddressDTO.setSchemeNo("1002");
    leAddressDTO.setSiteName("siteName");
    leAddressDTO.setFpNo("FPNO");
    leAddressDTO.setRsNo("RSNO");
    leAddressDTO.setSubPlotNo("21");
    leAddressDTO.setHouseNo("1221");
    leAddressDTO.setTenamentNo("12");
    leAddressDTO.setCitySurveyNo("City1002");
    leAddressDTO.setBuildingName("Safal");
    leAddressDTO.setState("Gujarat");
    leAddressDTO.setDistrict("Ahm");
    leAddressDTO.setTalukaName("Ahm");
    leAddressDTO.setPincode("3800015");

    liftInstallationDTO.setLiftSiteAddress(leAddressDTO);
    List<BufferDetailDTO> bufferDetailDTOList = new ArrayList<>();

    BufferDetailDTO bufferDetail = new BufferDetailDTO();
    bufferDetail.setBufferType(BufferType.CAR_BUFFER.getType());
    bufferDetail.setNumberOfBuffer(1);
    bufferDetail.setStroke(1);
    bufferDetail.setType("Spring");
    bufferDetail.setOtherType("ssjkdj");
    liftInstallationDTO.setCarBufferDetail(bufferDetail);

    bufferDetail = new BufferDetailDTO();
    bufferDetail.setBufferType(BufferType.COUNTER_WEIGHT_BUFFER.getType());
    bufferDetail.setNumberOfBuffer(2);
    bufferDetail.setStroke(2);
    bufferDetail.setType("Spring2");
    bufferDetail.setOtherType("ssjkdj");
    liftInstallationDTO.setCounterWeightBufferDetail(bufferDetail);


    System.out.println("liftEscalatorDuplicateDTO = " + liftInstallationDTO.toJSON());

    LiftInstallationDTO save = liftInstallationService.saveForm(liftInstallationDTO);
    System.out.println("save = " + save.toJSON());

    //save.validateAll();
    save = liftInstallationService.get(liftInstallationDTO.getId());
    System.out.println("GET =>" + save.toJSON());
    System.out.println("JSON =>" + liftInstallationService.getJson(liftInstallationDTO.getId()).toJSON());
    //TODO: Needs to set appropriate value in velocity template fields: 31.3,35
    System.out.println("PrintView =>" + liftInstallationService.getPrintView(liftInstallationDTO.getId()));
    HtmlToPDF.toPDF(liftInstallationService.getPrintView(liftInstallationDTO.getId()));


  }

}
