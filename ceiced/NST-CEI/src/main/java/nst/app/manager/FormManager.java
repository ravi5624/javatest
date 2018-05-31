package nst.app.manager;

import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.CommonForm;
import nst.app.model.forms.le.Transaction;
import nst.app.repo.FormRepository;
import nst.app.repo.TransactionRepository;
import nst.common.base.BaseManager;
import nst.common.base.BaseRepository;
import nst.common.error.AppException;
import nst.kernal.SystemConstants;
import nst.kernal.SystemConstants.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormManager extends BaseManager<CommonForm> {

  @Autowired
  FormRepository repository;

  @Autowired
  TransactionRepository transactionRepository;

  public CommonForm get(long id) {
    return repository.findOne(id);
  }

  public CommonForm findByUserIdAndApplicationType(Long userId, ApplicationType applicationType) {
    return repository.findByUserIdAndApplicationType(userId, applicationType);
  }

  public CommonForm get(String applicationId) {
    CommonForm commonForm = repository.findByUniqueId(applicationId);
    if (commonForm == null) {
      throw AppException.createWithMessage(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
    }
    return commonForm;
  }

  public Transaction saveTransaction(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  public Transaction getTransaction(String transactionId) {
    Transaction transaction = transactionRepository.findByTransactionId(transactionId);
    if (transaction == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, "INVALID_TRANSACTION");
    }
    return transaction;
  }

  public Transaction getFormTransaction(Long formId) {
    return transactionRepository.findByForm_id(formId);
  }

  /**
   * Retrieves list of entities by applicationType and userId.
   *
   * @param applicationType and userId must not be {@literal null}.
   * @throws AppException if an entityList is not {@literal null} and its size greater then 0.
   */
  public void validateUniqueApplication(PortalUser portalUser, ApplicationType applicationType) {
    long count = repository.countByUserAndApplicationType(portalUser, applicationType);
    if (count > 0) {
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST,
          SystemConstants.Rest.SINGLE_APPLICATION_ALLOWED);
    }
  }

  public void validateUniqueApplication(PortalUser portalUser, ApplicationType applicationType,
      FileStatus[] fileStatuses) {
    long count = repository
        .countByUserAndApplicationTypeAndFileStatusIn(portalUser, applicationType, fileStatuses);
    if (count > 0) {
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST,
          SystemConstants.Rest.SINGLE_APPLICATION_ALLOWED);
    }
  }

  @Override
  public BaseRepository<CommonForm> getRepository() {
    return repository;
  }
}