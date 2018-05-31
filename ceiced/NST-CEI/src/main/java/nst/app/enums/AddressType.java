package nst.app.enums;

import nst.common.EnumType;

public enum AddressType implements EnumType<Integer> {
  PERMANENT(1, "Permanent"),
  TEMPORARY(2, "Temporary"),
  OFFICE(3, "Office"),
  BRANCHOFFICE(4, "Branch Office"),
  MAINOFFICE(5, "Main Office"),
  WORKSHOP(6, "Workshop"),
  LIFTINSTALLER(7, "LiftInstaller"),
  PREMISES(8, "Premises"),
  APPLICANT(9, "Applicant"),
  MAINTAINER(10, "Maintainer"), /*escalator MaintainerName*/
  MAKER(11, "Maker"),/*escalator MaintainerName*/
  AGENT(12, "Agent"),
  ACCIDENT(13, "Accident"), /* accident Address*/
  BUSINESS(1, "Business"),
  AGENCY(14, "Agency"),/* Agency Address*/
  LIFTSITE(15,"LiftSite"), /*Address were Lift/Esc Install */
  PERSON(16,"Person"), /*Name of the Person who install  Lift*/
  OWNER(17,"Owner"),
  VICTIM(18,"Victim");


  private final String name;
  private final int id;

  AddressType(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getType() {
    return name();
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  @Override
  public Integer getDBId() {
    return id;
  }
}
