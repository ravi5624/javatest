package nst.app.controller.secure;

import java.util.ArrayList;
import java.util.List;
import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.WiremanExemptionDTO;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.lb.WiremanExemption;
import nst.app.service.WiremanExemptionService;
import nst.common.base.BaseResponse;
import nst.dto.LookupDataDTO;
import nst.kernal.SystemConstants;
import nst.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/wiremanExemption")
public class WiremanExemptionController extends
    CEIBaseController<WiremanExemption, WiremanExemptionDTO> {

  @Autowired
  WiremanExemptionService service;

  public WiremanExemptionController() {
    super(WiremanExemptionDTO.class);
  }

  public CEIBaseService<WiremanExemption, WiremanExemptionDTO> getService() {
    return service;
  }

  @RequestMapping(value = "/techanicalQualification", method = RequestMethod.GET, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse submitForm() {
    List<LookupDataDTO> data = new ArrayList<>();
    LookupDataDTO guj = LookupDataDTO.create(1l, "GUJARAT", "GUJARAT");
    guj.addDependent(LookupDataDTO.create(1l, "NATIONAL APPRENTICESHIP CERTIFICATE (ELECTRICIAN)", "NATIONAL APPRENTICESHIP CERTIFICATE (ELECTRICIAN)"));
    guj.addDependent(LookupDataDTO.create(2l, "NATIONAL APPRENTICESHIP CERTIFICATE (WIREMAN)", "NATIONAL APPRENTICESHIP CERTIFICATE (WIREMAN)"));
    guj.addDependent(LookupDataDTO.create(3l, "NATIONAL APPRENTICESHIP CERTIFICATE (LINEMAN)", "NATIONAL APPRENTICESHIP CERTIFICATE (LINEMAN)"));
    guj.addDependent(LookupDataDTO.create(4l, "NATIONAL TRADE CERTIFICATE (ELECTRICIAN)", "NATIONAL TRADE CERTIFICATE (ELECTRICIAN)"));
    guj.addDependent(LookupDataDTO.create(5l, "NATIONAL TRADE CERTIFICATE (WIREMAN)", "NATIONAL TRADE CERTIFICATE (WIREMAN)"));
    guj.addDependent(LookupDataDTO.create(6l, "NATIONAL TRADE CERTIFICATE (LINEMAN)", "NATIONAL TRADE CERTIFICATE (LINEMAN)"));
    guj.addDependent(LookupDataDTO.create(7l, "ELECTRICAL SERVICE TECHNICIAN", "ELECTRICAL SERVICE TECHNICIAN"));
    guj.addDependent(LookupDataDTO.create(8l, "ELECTRICAL INSTALLATION,WIRING AND JOINTING FROM TECHNICAL EXAMINATION BOARD", "ELECTRICAL INSTALLATION,WIRING AND JOINTING FROM TECHNICAL EXAMINATION BOARD"));
    data.add(guj);

    LookupDataDTO other = LookupDataDTO.create(2l, "OTHER", "OTHER");
    other.addDependent(LookupDataDTO.create(1l, "NATIONAL TRADE CERTIFICATE (ELECTRICIAN)", "NATIONAL TRADE CERTIFICATE (ELECTRICIAN)"));
    other.addDependent(LookupDataDTO.create(2l, "NATIONAL TRADE CERTIFICATE (WIREMAN)", "NATIONAL TRADE CERTIFICATE (WIREMAN)"));
    other.addDependent(LookupDataDTO.create(3l, "NATIONAL TRADE CERTIFICATE (LINEMAN)", "NATIONAL TRADE CERTIFICATE (LINEMAN)"));
    other.addDependent(LookupDataDTO.create(4l, "NATIONAL APPRENTICESHIP CERTIFICATE (ELECTRICIAN)", "NATIONAL APPRENTICESHIP CERTIFICATE (ELECTRICIAN)"));
    other.addDependent(LookupDataDTO.create(5l, "NATIONAL APPRENTICESHIP CERTIFICATE (WIREMAN)", "NATIONAL APPRENTICESHIP CERTIFICATE (WIREMAN)"));
    other.addDependent(LookupDataDTO.create(6l, "NATIONAL APPRENTICESHIP CERTIFICATE (LINEMAN)", "NATIONAL APPRENTICESHIP CERTIFICATE (LINEMAN)"));
    other.addDependent(LookupDataDTO.create(7l, "ANY OTHER QUALIFICATIONS EQUVILENT NATIONAL TRADE OR APPENTICESHIP CERTIFICATE", "ANY OTHER QUALIFICATIONS EQUVILENT NATIONAL TRADE OR APPENTICESHIP CERTIFICATE"));
    data.add(other);

    return BaseResponse.createSuccess(data);
  }

  @RequestMapping(value = "/techQualificationForWireman", method = RequestMethod.GET)
  public BaseResponse techQualificationForWireman() {
    return BaseResponse.createSuccess(LookupUtil.getTechQualificationForWireman());
  }
  public static void main(String[] args) {
    String body = "{\"data\":[{\"id\":\"20201\",\"candidateType\":\"Wireman\",\"surname\":\"Patel\",\"firstName\":\"Vishal\",\"middleName\":\"A\",\"birthDate\":null,\"age\":null,\"gender\":null,\"mobile\":null,\"altMobileNumber\":null,\"email\":\"\",\"technicalQualification\":null,\"passYear\":null,\"qualificationState\":null,\"iAgree\":false,\"parmanentAddress\":{\"addressType\":\"PERMANENT\",\"houseNo\":\"\",\"buildingName\":\"\",\"talukaName\":\"\",\"district\":null,\"state\":null,\"pincode\":\"\"},\"temporaryAddress\":{\"addressType\":\"TEMPORARY\",\"houseNo\":\"\",\"buildingName\":\"\",\"talukaName\":\"\",\"district\":null,\"state\":null,\"pincode\":\"\"}}]}";
    WiremanExemptionDTO dataDTO = JSONUtil.getDataDTO(body, WiremanExemptionDTO.class);
    System.out.println("dataDTO = " + dataDTO);
  }
}