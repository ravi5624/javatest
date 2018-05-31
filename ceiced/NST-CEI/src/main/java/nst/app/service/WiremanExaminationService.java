package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.ExperienceDTO;
import nst.app.dto.WiremanExaminationDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.ExperienceHelper;
import nst.app.helper.WiremanExaminationHelper;
import nst.app.manager.WiremanExaminationManager;
import nst.app.model.forms.lb.WiremanExamination;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class WiremanExaminationService extends
    CEIBaseService<WiremanExamination, WiremanExaminationDTO> {

  @Autowired
  WiremanExaminationManager manager;

  @Autowired
  WiremanExaminationHelper helper;

  @Autowired
  ExperienceHelper experieceHelper;

  @Override
  public BaseManager<WiremanExamination> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<WiremanExamination, WiremanExaminationDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.WIREXM;
  }

  public void deleteExperience(Long id, Long expId) {
    WiremanExamination form = manager.findForm(id);
    form.getExperiences().removeIf(experience -> experience.getId().equals(expId));
    save(form);
  }

  public ExperienceDTO addExperience(Long id) {
      return experieceHelper.fromModel(manager.addExperience(id));
    }
}