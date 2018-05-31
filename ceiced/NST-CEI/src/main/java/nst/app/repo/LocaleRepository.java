package nst.app.repo;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaleRepository {

    List findAll();
}