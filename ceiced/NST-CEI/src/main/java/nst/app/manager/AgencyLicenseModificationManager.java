package nst.app.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import nst.app.common.CEIBaseManager;
import nst.app.helper.AgencyLicenseModificationHelper;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.le.*;
import nst.app.repo.AgencyLicenseModificationRepository;
import nst.app.repo.LESafetyGadgetRepository;
import nst.app.repo.LETestingInstrumentRepository;
import nst.app.repo.StaffEmployeeRepository;
import nst.common.base.BaseRepository;
import nst.dto.LookupDataDTO;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgencyLicenseModificationManager extends CEIBaseManager<AgencyLicenseModification> {

  @Autowired
  AgencyLicenseModificationRepository repository;

  @Autowired
  AgencyLicenseModificationHelper helper;

  @Autowired
  LESafetyGadgetRepository leSafetyGadgetRepository;


  @Autowired
  StaffEmployeeRepository staffEmployeeRepository;


  @Autowired
  LETestingInstrumentRepository leTestingInstrumentRepository;

  @Override
  public BaseRepository<AgencyLicenseModification> getRepository() {
    return repository;
  }

  @Override
  public AgencyLicenseModification submitForm(AgencyLicenseModification model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public List<AgencyLicenseModification> getMyApps() {
    return repository.findMyApps(LoginUserUtil.getLoginUser().getUserId());
  }

  public List<LETestingInstrument> addTestingInstrumentDetails(Long id, Boolean multiple) {
    AgencyLicenseModification form = findForm(id);
    List<LETestingInstrument> leTestingInstrument=new ArrayList<>();
    //if multiple true then multiple recode will retain
    if(multiple) {
      List<LookupDataDTO> items = LookupUtil.getMachineries();

      items.forEach(item1 -> {
        LETestingInstrument testingInstrument = new LETestingInstrument(form.getForm());
        testingInstrument = leTestingInstrumentRepository.save(testingInstrument);
        testingInstrument.setMachineName(item1.getName());
        testingInstrument.setIsNew(false);
        leTestingInstrument.add(testingInstrument);
      });
    }
    else
    {
      LETestingInstrument testingInstrument = new LETestingInstrument(form.getForm());
      testingInstrument = leTestingInstrumentRepository.save(testingInstrument);
      testingInstrument.setIsNew(true);
      leTestingInstrument.add(testingInstrument);
    }

    return leTestingInstrument;
  }

  public List<LESafetyGadget> addSafetyGadgetDetails(Long id, Boolean multiple) {
    AgencyLicenseModification form = findForm(id);
    List<LESafetyGadget> emSafetyGadgetList=new ArrayList<>();
    //if multiple true then multiple recode will retain
    if(multiple) {
      List<LookupDataDTO> items = LookupUtil.getSafetyGadgets();

      items.forEach(item1 -> {
        LESafetyGadget emSafetyGadget = new LESafetyGadget(form.getForm());
        emSafetyGadget = leSafetyGadgetRepository.save(emSafetyGadget);
        emSafetyGadget.setName(item1.getName());
        emSafetyGadget.setIsNew(false);
        emSafetyGadgetList.add(emSafetyGadget);
      });
    }
    else
    {
      LESafetyGadget emSafetyGadget = new LESafetyGadget(form.getForm());
      emSafetyGadget = leSafetyGadgetRepository.save(emSafetyGadget);
      emSafetyGadget.setIsNew(true);
      emSafetyGadgetList.add(emSafetyGadget);
    }

    return emSafetyGadgetList;
  }


  /*public StaffEmployee addStaffEmployee(Long id, Boolean multiple) {
    StaffEmployee staffEmployee = new StaffEmployee(form.getForm());
    return staffEmployeeRepository.save(staffEmployee);
  }*/


  public List<StaffEmployee> addStaffEmployee(Long id,Boolean multiple) {
    AgencyLicenseModification form = findForm(id);
    List<StaffEmployee> staffEmployeeList=new ArrayList<>();
    if(multiple) {
      List<LookupDataDTO> items = LookupUtil.getStaffDetails();

      IntStream.range(0, items.size()).forEach(idx ->{
        StaffEmployee staffEmployee = new StaffEmployee(form.getForm());
        staffEmployee = staffEmployeeRepository.save(staffEmployee);
        staffEmployee.setEmployee(items.get(idx).getName());
        staffEmployee.setIsNew(false);
        staffEmployee.setSerialNumber(idx);
        staffEmployeeList.add(staffEmployee);
      });

    }
    else{
      StaffEmployee staffEmployee = new StaffEmployee(form.getForm());
      staffEmployee = staffEmployeeRepository.save(staffEmployee);
      staffEmployee.setIsNew(true);
      staffEmployeeList.add(staffEmployee);
    }
    return staffEmployeeList;
  }

}