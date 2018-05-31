package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.FormIHelper;
import nst.app.model.forms.FormIEmployer;
import nst.app.model.forms.lb.FormI;
import nst.app.repo.FormIEmployerRepository;
import nst.app.repo.FormIRepository;
import nst.common.base.BaseRepository;
import nst.common.security.LoginUser;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormIManager extends CEIBaseManager<FormI> {

  @Autowired
  FormIRepository repository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  FormIHelper helper;

  @Autowired
  FormIEmployerRepository formIEmployerRepository;

  @Override
  public BaseRepository<FormI> getRepository() {
    return repository;
  }

  @Override
  public FormI submitForm(FormI model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public FormI getLastRecord() {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    return repository.getLatest(loginUser.getUserId());
  }

  public FormIEmployer addEmployer(Long id) {
    FormI form = findForm(id);
    FormIEmployer formIEmployer = new FormIEmployer(form.getForm());
    formIEmployer.setIsNewEmployer(Boolean.TRUE);
    return formIEmployerRepository.save(formIEmployer);
  }
}