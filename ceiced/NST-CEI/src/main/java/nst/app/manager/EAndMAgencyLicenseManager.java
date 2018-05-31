package nst.app.manager;

import nst.app.common.CEIBaseManager;
import nst.app.dto.AddressDTO;
import nst.app.enums.AddressType;
import nst.app.enums.FileStatus;
import nst.app.helper.AddressHelper;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.Address;
import nst.app.model.forms.le.*;
import nst.app.repo.*;
import nst.common.base.BaseRepository;
import nst.dto.LookupDataDTO;
import nst.kernal.ActivityType;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class EAndMAgencyLicenseManager extends CEIBaseManager<EAndMAgencyLicense> {

  @Autowired
  EAndMAgencyLicenseRepository repository;

  @Autowired
  AgencyLegalStatusDetailsRepository agencyLegalStatusDetailsRepository;

  @Autowired
  LESafetyGadgetRepository leSafetyGadgetRepository;


  @Autowired
  StaffEmployeeRepository staffEmployeeRepository;


  @Autowired
  LETestingInstrumentRepository leTestingInstrumentRepository;

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  AddressHelper addressHelper;

  @Override
  public BaseRepository<EAndMAgencyLicense> getRepository() {
    return repository;
  }

  @Override
  public EAndMAgencyLicense submitForm(EAndMAgencyLicense model) {
    validatePayment(model.getApplicationId(), model.getApplicationType());
    model.getForm().setFileStatus(FileStatus.SUBMITTED);
    printLog(ActivityType.SUBMITTED, model.getId(), "1","2");

    return repository.save(model);
  }

  public List<EAndMAgencyLicense> getMyApps() {
    return repository.findMyApps(LoginUserUtil.getLoginUser().getUserId());
  }

  public AgencyLegalStatusDetails addAgencyLegalStatusDetails(Long id) {
    EAndMAgencyLicense form = findForm(id);
    AgencyLegalStatusDetails agencyLegalStatusDetails = new AgencyLegalStatusDetails(form.getForm());
    return agencyLegalStatusDetailsRepository.save(agencyLegalStatusDetails);
  }
    public AddressDTO addAddress(Long id) {
        EAndMAgencyLicense form = findForm(id);
        Address address = new Address(form.getForm());
        address.setAddressType(AddressType.BRANCHOFFICE);
        address = addressRepository.save(address);
        address.setUserKey(address.getId());
        address = addressRepository.save(address);
        return addressHelper.fromModel(address);
    }
 public List<LETestingInstrument> addTestingInstrumentDetails(Long id,Boolean multiple) {
    EAndMAgencyLicense form = findForm(id);
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

    public List<LESafetyGadget> addSafetyGadgetDetails(Long id,Boolean multiple) {
        EAndMAgencyLicense form = findForm(id);
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


  public List<StaffEmployee> addStaffEmployee(Long id,Boolean multiple) {
    EAndMAgencyLicense form = findForm(id);
    List<StaffEmployee> staffEmployeeList=new ArrayList<>();
      if(multiple) {
          List<LookupDataDTO> items = LookupUtil.getStaffDetails();

          /*Integer i =0;
          items.forEach(item1 -> {

              StaffEmployee staffEmployee = new StaffEmployee(form.getForm());
              staffEmployee = staffEmployeeRepository.save(staffEmployee);
              staffEmployee.setName(item1.getName());
              staffEmployee.setIsEditable(false);
              staffEmployee.setSerialNumber(i);
              staffEmployeeList.add(staffEmployee);

          });*/

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
          //staffEmployee.setSerialNumber(item1.getId());
          staffEmployeeList.add(staffEmployee);
      }
    return staffEmployeeList;
  }


}