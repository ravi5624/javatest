package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.FormIDTO;
import nst.app.dto.FormIEmployerDTO;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.helper.FormIEmployerHelper;
import nst.app.helper.FormIHelper;
import nst.app.manager.FormIManager;
import nst.app.model.forms.lb.FormI;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import nst.common.error.AppException;
import nst.config.MyLogger;
import nst.kernal.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class FormIService extends
    CEIBaseService<FormI, FormIDTO> {

  @Autowired
  FormIManager manager;

  @Autowired
  FormIHelper helper;

  @Autowired
  FormIEmployerHelper formIEmployerHelper;


  @Override
  public BaseManager<FormI> getManager() {
    return manager;
  }

  @Override
  public FormIDTO create() {
    FormI lastRecord = getLastRecord();
    if (lastRecord == null) {
      return super.create();
    }
    MyLogger.logService("FormIService", "create", "Form_Id: " + lastRecord.getForm().getId() + " Application Status: "+ lastRecord.getFileStatus());
    if (lastRecord.getFileStatus() != FileStatus.APPROVED) {
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, SystemConstants.Rest.APPLICATION_NOT_APPROVED);
    }
    FormIDTO dto = super.create();
    dto = helper.cloneDTO(lastRecord, dto);
    return saveForm(dto);
  }

  @Override
  public BaseHelper<FormI, FormIDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.FORMI;
  }

  public FormI getLastRecord() {
    return manager.getLastRecord();
  }

  public FormIEmployerDTO addEmployer(Long id) {
    return formIEmployerHelper.fromModel(manager.addEmployer(id));
  }
  public void deleteEmployer(Long id,Long empId) {
    FormI form = manager.findForm(id);
    form.getEmployer().removeIf(experience -> experience.getId().equals(empId));
    save(form);
  }
}