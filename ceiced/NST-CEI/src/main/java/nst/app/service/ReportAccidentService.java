package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.AccidentVictimDTO;
import nst.app.dto.ReportAccidentDTO;
import nst.app.enums.AddressType;
import nst.app.enums.ApplicationType;
import nst.app.helper.AccidentVictimHelper;
import nst.app.helper.AddressHelper;
import nst.app.helper.ReportAccidentHelper;
import nst.app.manager.ReportAccidentManager;
import nst.app.model.forms.Address;
import nst.app.model.forms.le.AccidentVictim;
import nst.app.model.forms.le.ReportAccident;
import nst.app.repo.AddressRepository;
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
public class ReportAccidentService extends
    CEIBaseService<ReportAccident, ReportAccidentDTO> {

  @Autowired
  ReportAccidentManager manager;

  @Autowired
  ReportAccidentHelper helper;

  @Autowired
  AccidentVictimHelper accidentVictimHelper;

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  AddressHelper addressHelper;

  public List<ReportAccidentDTO> getMyApps() {
    return helper.fromModel(manager.getMyApps());
  }
  @Override
  public BaseManager<ReportAccident> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<ReportAccident, ReportAccidentDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.ACCIDENT;
  }

  public AccidentVictimDTO addAccidentVictim(Long id) {
    ReportAccident form = manager.findForm(id);
    if(Objects.isNull(form)) {
      throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, SystemConstants.Model.INVALID_APPLICATION);
    }
    AccidentVictim model = new AccidentVictim(form.getForm());
    AccidentVictim victim = manager.addAccidentVictim(model);
    AccidentVictimDTO victimDTO = accidentVictimHelper.fromModel(victim);

    Address address = new Address(form.getForm());
    address.setAddressType(AddressType.VICTIM);
    address.setUserKey(victimDTO.getId());
    address = addressRepository.save(address);
    victimDTO.setVictimPostalAddress(addressHelper.fromModel(address));

    return victimDTO;
  }

  public void deleteAccidentVictim(Long id, Long victimId) {
    ReportAccident form = manager.findForm(id);
    if(Objects.isNull(form)) {
      throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, SystemConstants.Model.INVALID_APPLICATION);
    }
    form.getVictims().removeIf(victim -> victim.getId().equals(victimId));
    form.getForm().getAddresses().removeIf(address -> address.isFor(AddressType.VICTIM, victimId));
    save(form);
  }
}