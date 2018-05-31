package nst.app.repo;

import nst.app.model.forms.le.Transaction;
import nst.common.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction> {

  Transaction findByForm_id(long id);

  Transaction findByTransactionId(String transactionId);
}