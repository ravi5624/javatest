package nst.app.manager;

import nst.app.common.CEIBaseManager;
import nst.app.helper.WiremanExaminationHelper;
import nst.app.model.forms.Experience;
import nst.app.model.forms.lb.WiremanExamination;
import nst.app.repo.ExperienceRepository;
import nst.app.repo.WiremanExaminationRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WiremanExaminationManager extends CEIBaseManager<WiremanExamination> {

  @Autowired
  WiremanExaminationRepository repository;

  @Autowired
  ExperienceRepository experienceRepository;

  @Autowired
  WiremanExaminationHelper helper;

  @Override
  public BaseRepository<WiremanExamination> getRepository() {
    return repository;
  }

  @Override
  public WiremanExamination submitForm(WiremanExamination model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public Experience addExperience(Long id) {
    WiremanExamination form = findForm(id);
    Experience experience = new Experience(form.getForm());
    return experienceRepository.save(experience);
  }

  public WiremanExamination get(String identifier1, String identifier2) {
    return get(Long.parseLong(identifier1));
  }
}