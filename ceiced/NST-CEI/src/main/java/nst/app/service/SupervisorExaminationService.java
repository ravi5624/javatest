package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.ExperienceDTO;
import nst.app.dto.SupervisorExaminationDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.ExperienceHelper;
import nst.app.helper.SupervisorExaminationHelper;
import nst.app.manager.SupervisorExaminationManager;
import nst.app.model.forms.lb.SupervisorExamination;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SupervisorExaminationService extends
    CEIBaseService<SupervisorExamination, SupervisorExaminationDTO> {

  @Autowired
  SupervisorExaminationManager manager;

  @Autowired
  SupervisorExaminationHelper helper;

  @Autowired
  ExperienceHelper experieceHelper;

  @Override
  public BaseManager<SupervisorExamination> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<SupervisorExamination, SupervisorExaminationDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.SUPEXM;
  }

  public void deleteExperience(Long id, Long expId) {
    SupervisorExamination form = manager.findForm(id);
    form.getExperiences().removeIf(experience -> experience.getId().equals(expId));
    save(form);
  }

  public ExperienceDTO addExperience(Long id) {
    return experieceHelper.fromModel(manager.addExperience(id));
  }
}