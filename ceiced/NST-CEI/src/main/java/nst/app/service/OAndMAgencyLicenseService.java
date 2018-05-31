package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.AddressDTO;
import nst.app.dto.OAndMAgencyLicenseDTO;
import nst.app.dto.StaffEmployeeDTO;
import nst.app.dto.le.AgencyLegalStatusDetailsDTO;
import nst.app.dto.le.LESafetyGadgetDTO;
import nst.app.dto.le.LETestingInstrumentDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.OAndMAgencyLicenseHelper;
import nst.app.helper.StaffEmployeeHelper;
import nst.app.helper.le.AgencyLegalStatusDetailsHelper;
import nst.app.helper.le.LESafetyGadgetHelper;
import nst.app.helper.le.LETestingInstrumentHelper;
import nst.app.manager.OAndMAgencyLicenseManager;
import nst.app.model.forms.le.CommonForm;
import nst.app.model.forms.le.OAndMAgencyLicense;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import nst.common.error.AppException;
import nst.kernal.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class OAndMAgencyLicenseService extends
    CEIBaseService<OAndMAgencyLicense, OAndMAgencyLicenseDTO> {

  @Autowired
  OAndMAgencyLicenseManager manager;

  @Autowired
  OAndMAgencyLicenseHelper helper;

  @Autowired
  AgencyLegalStatusDetailsHelper agencyLegalStatusDetailsHelper;

  @Autowired
  LETestingInstrumentHelper leTestingInstrumentHelper;

  @Autowired
  LESafetyGadgetHelper leSafetyGadgetHelper;

  @Autowired
  StaffEmployeeHelper staffEmployeeHelper;

  public List<OAndMAgencyLicenseDTO> getMyApps() {
    return helper.fromModel(manager.getMyApps());
  }
  @Override
  public BaseManager<OAndMAgencyLicense> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<OAndMAgencyLicense, OAndMAgencyLicenseDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.OMAL;
  }

  @Override
  public OAndMAgencyLicenseDTO create() {
    CommonForm form = getCommonForm(applicationType());
    if (Objects.isNull(form)) {
      return super.create();
    }
    throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, SystemConstants.Rest.SINGLE_APPLICATION_ALLOWED);
  }

  public AgencyLegalStatusDetailsDTO addAgencyLegalStatusDetails(Long id) {
    return agencyLegalStatusDetailsHelper.fromModel(manager.addAgencyLegalStatusDetails(id));
  }

  public List<LETestingInstrumentDTO> addLETestingInstrumentDetails(Long id, Boolean multiple) {
    return leTestingInstrumentHelper.fromModel(manager.addTestingInstrumentDetails(id,multiple));
  }


  public List<LESafetyGadgetDTO> addLESafetyGadgetDetails(Long id, Boolean multiple) {
    return leSafetyGadgetHelper.fromModel(manager.addSafetyGadgetDetails(id,multiple));
  }


    public List<StaffEmployeeDTO> addStaffDetails(Long id,Boolean multiple) {
        return staffEmployeeHelper.fromModel(manager.addStaffEmployee(id,multiple));
    }

  public void deleteAgencyLegalStatusDetails(Long id, Long legalStatusId) {
    OAndMAgencyLicense form = manager.findForm(id);
    form.getAgencyLegalStatusDetails().removeIf(experience -> experience.getId().equals(legalStatusId));
    save(form);
  }

  public void deleteLESafetyGadgetDetails(Long id, Long safetyGadgetId) {
    OAndMAgencyLicense form = manager.findForm(id);
    form.getLESafetyGadgets().removeIf(experience -> experience.getId().equals(safetyGadgetId));
    save(form);
  }

  public void deleteLETestingInstrumentDetails(Long id, Long instumentId) {
    OAndMAgencyLicense form = manager.findForm(id);
    form.getLETestingInstruments().removeIf(experience -> experience.getId().equals(instumentId));
    save(form);
  }

    public AddressDTO addAddress(Long id) {
        return manager.addAddress(id);
    }

    public void deleteAddress(Long id, Long addId) {
        OAndMAgencyLicense form = manager.findForm(id);
        if(Objects.isNull(form)) {
            throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, SystemConstants.Model.INVALID_APPLICATION);
        }
        form.getForm().getAddresses().removeIf(add -> add.getId().equals(addId));
        save(form);
    }

    public void deleteStaffDetails(Long id, Long staffId) {
        OAndMAgencyLicense form = manager.findForm(id);
        form.getStaffEmployees().removeIf(experience -> experience.getId().equals(staffId));
        save(form);
    }
}