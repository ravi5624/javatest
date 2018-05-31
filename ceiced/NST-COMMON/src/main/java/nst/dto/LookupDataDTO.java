package nst.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import nst.common.base.BaseDTO;
import nst.util.JSONUtil;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LookupDataDTO extends BaseDTO {

  Long id;
  String code;
  String name;

  List<LookupDataDTO> dependent;

  private LookupDataDTO(Long id, String code, String name) {
    this.id = id;
    this.code = code;
    this.name = name;
  }

  public static LookupDataDTO create(Long id, String code, String name) {
    return new LookupDataDTO(id, code, name);
  }

  public void addDependent(LookupDataDTO dto) {
    if (dependent == null) {
      dependent = new ArrayList<>();
    }
    this.dependent.add(dto);
  }

  public static void main(String[] args) {
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

    System.out.println("d2 = " + JSONUtil.toJSON(data));
  }
}
