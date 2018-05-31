package nst.app.repo;

import nst.app.enums.UserType;
import nst.app.model.PortalUser;
import nst.common.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortalUserRepository extends BaseRepository<PortalUser> {

  PortalUser findByUserName(String email);

  PortalUser findByEmail(String email);

  List<PortalUser> findOneByUserTypeAndUserNameContainingIgnoreCase(UserType userType, String userName);

  PortalUser findByUserTypeAndUserNameAndEmailValidatedIsTrue(UserType userType, String userName);

  PortalUser findByUserTypeAndEmail(UserType userType, String email);

  PortalUser findByUserTypeAndContactNo(UserType userType, String contactNo);

  PortalUser findByEmailCode(String emailCode);

  PortalUser findById(Long id);

  PortalUser findByUniqueId(String uniqueId);

  List<PortalUser> findAll();
}