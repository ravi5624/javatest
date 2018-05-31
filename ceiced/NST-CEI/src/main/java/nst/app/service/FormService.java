package nst.app.service;

import java.util.Date;
import nst.app.common.HelperUtil;
import nst.app.enums.PaymentStatus;
import nst.app.manager.FormManager;
import nst.app.model.forms.le.CommonForm;
import nst.app.model.forms.le.Transaction;
import nst.common.base.BaseManager;
import nst.common.base.BaseService;
import nst.dto.ApplicationDetailsDTO;
import nst.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class FormService extends BaseService<CommonForm> {

  @Autowired
  FormManager manager;

  @Override
  public BaseManager<CommonForm> getManager() {
    return manager;
  }

  public TransactionDTO postPayment(String transactionId, String status) {
    Transaction transaction = manager.getTransaction(transactionId);
    if ("SUCCESS".equalsIgnoreCase(status)) {
      transaction.setStatus(PaymentStatus.PAID);
      transaction.setPaidOn(new Date());
      transaction.setResponse("Response from PG...");
    } else {
      transaction.setStatus(PaymentStatus.FAILED);
      transaction.setResponse("Response from PG...");
    }
    manager.saveTransaction(transaction);
    return HelperUtil.toTransactionDTO(transaction);
  }
  public ApplicationDetailsDTO postPayment(String transactionId) {
    Transaction transaction = manager.getTransaction(transactionId);
    manager.saveTransaction(transaction);
    ApplicationDetailsDTO applicationDetailsDTO=new ApplicationDetailsDTO();
    applicationDetailsDTO.setApiName(transaction.getForm().getApplicationType().getApi());
    applicationDetailsDTO.setAppId(transaction.getForm().getId());
    applicationDetailsDTO.setUrl(transaction.getUrl());
    return applicationDetailsDTO;
  }

}