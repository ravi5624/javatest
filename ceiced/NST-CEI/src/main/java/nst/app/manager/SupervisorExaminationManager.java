package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.SupervisorExaminationHelper;
import nst.app.model.forms.Experience;
import nst.app.model.forms.lb.SupervisorExamination;
import nst.app.repo.ExperienceRepository;
import nst.app.repo.SupervisorExaminationRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupervisorExaminationManager extends CEIBaseManager<SupervisorExamination> {

  @Autowired
  SupervisorExaminationRepository repository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  SupervisorExaminationHelper helper;
  @Autowired
  ExperienceRepository experienceRepository;

  @Override
  public BaseRepository<SupervisorExamination> getRepository() {
    return repository;
  }


  @Override
  public SupervisorExamination submitForm(SupervisorExamination model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public Experience addExperience(Long id) {
    SupervisorExamination form = findForm(id);
    Experience experience = new Experience(form.getForm());
    return experienceRepository.save(experience);
  }
}