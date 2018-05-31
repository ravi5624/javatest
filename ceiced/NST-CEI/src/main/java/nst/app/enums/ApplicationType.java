package nst.app.enums;

import nst.app.CEIUtil;
import nst.app.dto.ApplicationTypeDTO;
import nst.app.dto.ApplicationTypeDTO.PartDTO;
import nst.util.AllUtil;

import java.io.IOException;
import java.util.Objects;

/*
Application For Admission To Examination For Wireman API: <ip>:<port>/examinterstate/webresources/examinterstate
Application For Admission To Examination For Electrical Supervisor API: <ip>:<port>/examinterstate/webresources/examinterstate
Application For Interstate recognition for Electrical Supervisor / Wireman Permit in Gujarat State API: <ip>:<port>/examinterstate/webresources/examinterstate
Application For Admission To Examination For Repeater Candidate Electrical Supervisor Or Wireman API: <ip>:<port>/examinterstate/webresources/examinterstate
Interstate Renewal Wireman API: <ip>:<port>/examinterstate/webresources/examinterstate
Interstate Renewal Supervisor API: <ip>:<port>/examinterstate/webresources/examinterstate
Modification In Wireman Details API: <ip>:<port>/examinterstate/webresources/examinterstate
MODIFICATION IN CONTRACTOR’S DETAILS API: <ip>:<port>/examinterstate/webresources/examinterstate
MODIFICATION IN PROPRIETOR Or PARTNERS NAME API: <ip>:<port>/examinterstate/webresources/examinterstate
Modification In Supervisor Details API: <ip>:<port>/examinterstate/webresources/examinterstate

New Wireman Exemption API: <ip>:<port>/exemptioncontator/webresources/exemptioncontator
New Supervisor Exemption API: <ip>:<port>/exemptioncontator/webresources/exemptioncontator
New Contractor License API: <ip>:<port>/exemptioncontator/webresources/exemptioncontator
Contractor Renewal API: <ip>:<port>/exemptioncontator/webresources/exemptioncontator
Duplicate API: <ip>:<port>/exemptioncontator/webresources/exemptioncontator

For Agency EMAL,ITAL,OMAL
<ip>:<port>/agencyauthorization/webresources/agencyauthorization

For Other Forms
<ip>:<port>/permitlist/webresources/permitlist

*/
public enum ApplicationType {
  EMAL("Agency Authorisation* For Erection & Manteinance of Lift or Escalator", true, "EAndMAgencyLicense",
      "/agencyauthorization/webresources/agencyauthorization"),
  ITAL("Agency Authorisation* For Inspection & Testing of Lift or Escalator", true, "IAndTAgencyLicense",
      "/agencyauthorization/webresources/agencyauthorization"),
  OMAL("Agency Authorisation* For Only Manteinance of Lift or Escalator", true, "OAndMAgencyLicense",
      "/agencyauthorization/webresources/agencyauthorization"),

  ALMODI("Application Form For Modification Request In Agency", true, "agencyLicenseModification",
      "/permitlist/webresources/permitlist"),
  ALDUPL("Application form for Duplicate License of Authorized Agency ", true, "agencyLicenseDuplicate",
      "/permitlist/webresources/permitlist"),
  ALRENE("Application Form For License Renewal Of Authorized Agency", true, "agencyLicenseRenewal",
      "/permitlist/webresources/permitlist"),

  LIL("Application Form For Lift Installation Permission", true, "liftInstallation",
      "/permitlist/webresources/permitlist"),
  EIL("Application Form For Escalator Installation Permission", true, "escalatorInstallation",
      "/permitlist/webresources/permitlist"),
  LEDL("Application for Duplicate License of Lift & Escalator", true, "liftEscalatorDuplicate",
      "/permitlist/webresources/permitlist"),
  LEML("Application Form For Modification Request In Lift/Escalator", true, "liftEscalatorModification",
      "/permitlist/webresources/permitlist"),
  LERL("Application Form For License Renewal Of Lift/Escalator", true, "liftEscalatorRenewal",
      "/permitlist/webresources/permitlist"),

  OESCL("Application Form For Operating Escalator Before Commencement Of The Act", true, "operatingEscalator",
      "/permitlist/webresources/permitlist"),
  OLIFT("Application Form For Operating Lift Before Commencement Of The Act", true, "operatingLift", "/permitlist/webresources/permitlist"),

  ACCIDENT("Application Form For Reporting Accident Of Lift/Escalator", false, "reportAccident",
      "/permitlist/webresources/permitlist"),

  ISPERMIT(
      "Form L* “Application For Interstate recognition for Electrical Supervisor / Wireman Permit in Gujarat State.”  ",
      false, "interStatePermit",
      "/examinterstate/webresources/examinterstate", "/examinterstate/webresources/queryraise",
      "Inter State Permit"),
  DUPPER("Wireman & Supervisor Duplicate Permit Details Form", false, "duplicatePermit",
      "/exemptioncontator/webresources/exemptioncontator",
      "/exemptioncontator/webresources/queryraise",
      "Duplicate Permit"),
  // ISREN("Inter State Renewal", false, "interStateRenewal"),

  CONMODI("MODIFICATION IN CONTRACTOR’S DETAILS", false, "contractorModification",
      "/examinterstate/webresources/examinterstate", "/examinterstate/webresources/queryraise",
      "Contractor Modification"),
  CONREN("CONTRACTOR’S LICENSE RENEWAL", false, "contractorRenewal",
      "/exemptioncontator/webresources/exemptioncontator",
      "/exemptioncontator/webresources/queryraise",
      "Contractor Renewal"),
  CONLIC("Form J* “Application for an Electrical Contractor’s License.”", false,
      "contractorLicense",
      "/exemptioncontator/webresources/exemptioncontator",
      "/exemptioncontator/webresources/queryraise",
      "Contractor License"),
  CONPARTMODI("MODIFICATION IN PROPRIETOR Or PARTNERS NAME", false,
      "contractorPartnersModification",
      "/examinterstate/webresources/examinterstate", "/examinterstate/webresources/queryraise",
      "Contractor Partners Modification"),

  WIREXM("Form A1* “Application For Admission To Examination For Wireman.”", false,
      "wiremanExamination",
      "/examinterstate/webresources/examinterstate", "/examinterstate/webresources/queryraise",
      "Wireman Examination"),
  WIREXMP("Form A4* “Application Form For Exemption From Electrical Wireman Exam.”", false,
      "wiremanExemption",
      "/exemptioncontator/webresources/exemptioncontator",
      "/exemptioncontator/webresources/queryraise",
      "Wireman Exemption"),
  WIRMODI("Modification In Wireman Details", false, "wiremanModification",
      "/examinterstate/webresources/examinterstate", "/examinterstate/webresources/queryraise",
      "Wireman Modification"),
  WIRREN("Interstate Renewal Wireman", false, "wiremanRenewal",
      "/examinterstate/webresources/examinterstate", "/examinterstate/webresources/queryraise",
      "Wireman Renewal"),

  SUPEXM("Form A1* “Application For Admission To Examination For Electrical Supervisor.”", false,
      "supervisorExamination",
      "/examinterstate/webresources/examinterstate", "/examinterstate/webresources/queryraise",
      "Supervisor Examination"),
  SUPEXMP("Form A3* “Application Form For Exemption From Electrical SupervisorExam.”", false,
      "supervisorExemption",
      "/exemptioncontator/webresources/exemptioncontator",
      "/exemptioncontator/webresources/queryraise",
      "Supervisor Exemption"),
  SUPREN("Interstate Renewal Supervisor", false, "supervisorRenewal",
      "/examinterstate/webresources/examinterstate", "/examinterstate/webresources/queryraise",
      "Supervisor Renewal"),//Interstate Supervisor renewal
  SUPMODI("Modification In Supervisor Details", false, "supervisorModification",
      "/examinterstate/webresources/examinterstate", "/examinterstate/webresources/queryraise",
      "Supervisor Modification"),

  FORMI("Form “I”", false, "formI", "/exemptioncontator/webresources/exemptioncontator",
          "/exemptioncontator/webresources/queryraise", "Form “I”"),
  REPEATER(
      "Form A2* “Application For Admission To Examination For Repeater Candidate Electrical Supervisor Or Wireman.”",
      false, "repeater",
      "/examinterstate/webresources/examinterstate", "/examinterstatewebresources/queryraise",
      "Repeater"),;

  private final String name;
  private boolean payable;
  private final String api;
  private final String nregenUrl;
  private final String queryUrl;
  private final String nregenName;

  ApplicationType(String name, boolean payable, String api, String nregenUrl) {
    this.name = name;
    this.payable = payable;
    this.api = api;
    this.nregenUrl = nregenUrl;
    this.queryUrl = "";
    this.nregenName = name;
  }

  ApplicationType(String name, boolean payable, String api, String nregenUrl,
      String queryUrl, String nregenName) {
    this.name = name;
    this.payable = payable;
    this.api = api;
    this.nregenUrl = nregenUrl;
    this.queryUrl = queryUrl;
    this.nregenName = nregenName;
  }

  public String getName() {
    return name;
  }

  public String getName(UserType userType) {
    if (UserType.WIREMAN == userType || UserType.SUPERVISOR == userType) {
      String regex = CEIUtil.WIREMAN_SUPERVISOR_REPLACE;
      String data = AllUtil.extractData(getName(), regex, 0);
      return name.trim().replaceAll(regex, data != null && Character.isLowerCase(data.charAt(0)) ? userType.getName().toLowerCase() : userType.getName());
    }
    return name;
  }

  public String getType() {
    return name();
  }

  public String getOperation() {
    return name();
  }

  public String getApi() {
    return api;
  }

  public String getNregenUrl() {
    return nregenUrl;
  }

  public String getQueryUrl() {
    return queryUrl;
  }

  public ApplicationTypeDTO toDTO() {
    return new ApplicationTypeDTO(getType(), getName(), getApi());
  }

  public ApplicationTypeDTO toDTO(UserType userType) {
    ApplicationTypeDTO typeDTO = new ApplicationTypeDTO(getType(), getName(userType), getApi());
    if ((this == ApplicationType.LIL || this == ApplicationType.EIL || this == ApplicationType.OESCL
        || this == ApplicationType.OLIFT)
        && UserType.OWNER == userType) {
      typeDTO.addPart(PartDTO.create("PART-1", true, true));
      typeDTO.addPart(PartDTO.create("PART-2", true, false));
      typeDTO.addPart(PartDTO.create("PART-3", true, false));
    } else if ((this == ApplicationType.LIL || this == ApplicationType.EIL || this == ApplicationType.OESCL
         || this == ApplicationType.OLIFT)
         && UserType.EM_AGENCY == userType) {
      typeDTO.addPart(PartDTO.create("PART-1", true, false));
      typeDTO.addPart(PartDTO.create("PART-2", true, true));
      typeDTO.addPart(PartDTO.create("PART-3", true, true));
    }
    return typeDTO;
  }

  public String getNregenName() {
    return nregenName;
  }

  public boolean isPayable() {
    return payable;
  }

  public void setPayable(boolean payable) {
    this.payable = payable;
  }

  public static void main(String[] args) throws IOException {
    /*for (ApplicationType type : ApplicationType.values()) {
      System.out.println(
          "type = " + type.getType() + " =>Submit:" + type.getNregenUrl() + " =>Query:" + type
              .getQueryUrl());
    }*/

    /*String s="Form A2* Application For Admission To Examination For Repeater Candidate Electrical Supervisor Or Wireman";
    System.out.println("str::"+s.replaceAll("(.*)(Wireman|Supervisor)(\\s)(?i)(&|/|-|OR)(?-i)(\\s)(Wireman|Supervisor)(.*)", s));;*/

/*
    String file = "<html>\n"
        + "<head><title>Application : $data.applicationName</title></head>\n"
        + "<body bgcolor=\"#faf7f1\">\n"
        + "<h1>Customer number $data.mobile</h1>\n"
        + "<b>Name:</b> $data.surname $data.firstName $data.middleName<br>\n"
        + "<b>Gender:</b> $data.gender<br>\n"
        + "<b>Gender:</b> $data.parmanentAddress.houseNo<br>\n"
        + "<br>Attachments<br>\n"
        + "  #foreach ($atc in $data.attachments)\n"
        + "    $atc.realFileName <br>\n"
        + "  #end\n"
        + "</body>\n"
        + "</html>";

    for (ApplicationType type : ApplicationType.values()) {
      Files.write(Paths.get(
          "/home/vishal/DATA/Projects/Nascent/Projects/NST-CEICED/NST-CEI/src/main/resources/printView/"
              + type.getType() + ".vm"),
          file.getBytes());
    }
*/
  }
}
