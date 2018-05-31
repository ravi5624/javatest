package nst;

import java.util.Arrays;
import java.util.List;
import nst.app.dto.ApplicationQueryDTO;
import nst.app.service.SupervisorRenewalService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestQueries extends AbstractTest {

//  ALTER TABLE attachments ADD user_key BIGINT DEFAULT NULL  NULL;
//  UPDATE attachments a SET user_key = q.id from form_queries q WHERE split_part(a.portal_variable_name,'_', 3) = q.pack_id AND a.form_id = q.form_id AND attachment_type = 'QUERY';

  @Autowired
  SupervisorRenewalService service;

  String allForms = "59601\n"
      +"51352\n"
      +"44440\n"
      +"48784\n"
      +"48933\n"
      +"36663\n"
      +"43558\n"
      +"46657\n"
      +"46311\n"
      +"46653\n"
      +"46411\n"
      +"34695\n"
      +"36910\n"
      +"36660\n"
      +"37161\n"
      +"53406\n"
      +"28954\n"
      +"59385\n"
      +"46165\n"
      +"47775\n"
      +"58855\n"
      +"58857\n"
      +"59608\n"
      +"37165\n"
      +"53598\n"
      +"52520\n"
      +"48340\n"
      +"48430\n"
      +"36917\n"
      +"53170\n"
      +"55841\n"
      +"59603\n"
      +"46161\n"
      +"53174\n"
      +"59609\n"
      +"59302\n"
      +"31031\n"
      +"31030\n"
      +"36911\n"
      +"52765\n"
      +"44381\n"
      +"45575\n"
      +"48180\n"
      +"32096\n"
      +"49214\n"
      +"54572\n"
      +"44382\n"
      +"45698\n"
      +"55598\n"
      +"48431\n"
      +"54273\n"
      +"43024\n"
      +"53594\n"
      +"53590\n"
      +"58854\n"
      +"46976\n"
      +"48930\n"
      +"60205\n"
      +"45920\n"
      +"37168\n"
      +"48070\n"
      +"52340\n"
      +"59600\n"
      +"51351\n"
      +"33851\n"
      +"47628\n"
      +"55971\n"
      +"38553\n";
  @Test
  public void getAllQueries() {
    Arrays.stream(allForms.split("\n")).forEach(s -> {
      List<ApplicationQueryDTO> allQueries = service.getAllQueries(59609l);
      System.out.println(s + " => "+ allQueries.size());
      printJSON(allQueries);
      System.out.println("--------------------");
    });
  }
}
