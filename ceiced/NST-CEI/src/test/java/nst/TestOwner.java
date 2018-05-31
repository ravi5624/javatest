package nst;

import nst.app.dto.OwnerDTO;
import nst.app.model.OwnerDetail;
import nst.app.service.OwnerDetailService;
import nst.start.CEIApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CEIApplication.class, loader = SpringApplicationContextLoader.class)
public class TestOwner {

  @Autowired
  OwnerDetailService ownerDetailService;

  @Test
  public void getAll() {
    Iterable<OwnerDetail> all = ownerDetailService.getAll();
  }

  @Test
  public void create() {
    OwnerDTO ownerDTO = ownerDetailService.create();
    System.out.println("ownerDTO = " + ownerDTO.toJSON());
  }
}
