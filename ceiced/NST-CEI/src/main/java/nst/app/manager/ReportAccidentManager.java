package nst.app.manager;

import nst.app.common.CEIBaseManager;
import nst.app.helper.ReportAccidentHelper;
import nst.app.model.forms.le.AccidentVictim;
import nst.app.model.forms.le.ReportAccident;
import nst.app.repo.AccidentVictimRepository;
import nst.app.repo.ReportAccidentRepository;
import nst.common.base.BaseRepository;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportAccidentManager extends CEIBaseManager<ReportAccident> {

  @Autowired
  ReportAccidentRepository repository;

  @Autowired
  ReportAccidentHelper helper;

  @Autowired
  AccidentVictimRepository accidentVictimRepository;

  @Override
  public BaseRepository<ReportAccident> getRepository() {
    return repository;
  }

  @Override
  public ReportAccident submitForm(ReportAccident model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public AccidentVictim addAccidentVictim(AccidentVictim model) {
    return accidentVictimRepository.save(model);
  }

  public List<ReportAccident> getMyApps() {
    return repository.findMyApps(LoginUserUtil.getLoginUser().getUserId());
  }

}