package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.AgencyLicenseModificationDTO;
import nst.app.dto.StaffEmployeeDTO;
import nst.app.dto.le.LESafetyGadgetDTO;
import nst.app.dto.le.LETestingInstrumentDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.AgencyLicenseModificationHelper;
import nst.app.helper.StaffEmployeeHelper;
import nst.app.helper.le.LESafetyGadgetHelper;
import nst.app.helper.le.LETestingInstrumentHelper;
import nst.app.manager.AgencyLicenseModificationManager;
import nst.app.model.forms.le.AgencyLicenseModification;
import nst.app.model.forms.le.EAndMAgencyLicense;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AgencyLicenseModificationService extends
    CEIBaseService<AgencyLicenseModification, AgencyLicenseModificationDTO> {

  @Autowired
  AgencyLicenseModificationManager manager;

  @Autowired
  AgencyLicenseModificationHelper helper;
  @Autowired
  LETestingInstrumentHelper leTestingInstrumentHelper;

  @Autowired
  LESafetyGadgetHelper leSafetyGadgetHelper;


  @Autowired
  StaffEmployeeHelper staffEmployeeHelper;



  @Override
  public BaseManager<AgencyLicenseModification> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<AgencyLicenseModification, AgencyLicenseModificationDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.ALMODI;
  }

  public List<LESafetyGadgetDTO> addLESafetyGadgetDetails(Long id, Boolean multiple) {
    return leSafetyGadgetHelper.fromModel(manager.addSafetyGadgetDetails(id,multiple));
  }


  public List<LETestingInstrumentDTO> addLETestingInstrumentDetails(Long id, Boolean multiple) {
    return leTestingInstrumentHelper.fromModel(manager.addTestingInstrumentDetails(id,multiple));
  }



  public List<StaffEmployeeDTO> addStaffDetails(Long id, Boolean multiple) {
    return staffEmployeeHelper.fromModel(manager.addStaffEmployee(id, multiple));
  }


  public void deleteLESafetyGadgetDetails(Long id, Long safetyGadgetId) {
    AgencyLicenseModification form = manager.findForm(id);
    form.getSafetyGadgets().removeIf(experience -> experience.getId().equals(safetyGadgetId));
    save(form);
  }

  public void deleteLETestingInstrumentDetails(Long id, Long instumentId) {
    AgencyLicenseModification form = manager.findForm(id);
    form.getTestingInstruments().removeIf(experience -> experience.getId().equals(instumentId));
    save(form);
  }

  public void deleteStaffDetails(Long id, Long staffId) {
    AgencyLicenseModification form = manager.findForm(id);
    form.getStaffEmployees().removeIf(experience -> experience.getId().equals(staffId));
    save(form);
  }




}