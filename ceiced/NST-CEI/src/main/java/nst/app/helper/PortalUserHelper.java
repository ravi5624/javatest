package nst.app.helper;

import nst.app.dto.PortalUserDTO;
import nst.app.enums.UserType;
import nst.app.model.PortalUser;
import nst.util.AllUtil;
import nst.util.RandomUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PortalUserHelper {

  public static PortalUser toPortalUser(PortalUserDTO user) {
    PortalUser portalUser = new PortalUser();
    portalUser.setUserName(user.getUserName());
    portalUser.setBirthDate(AllUtil.toUIDate(user.getDob()));
    portalUser.setEmail(user.getEmail().toLowerCase());
    portalUser.setContactNo(user.getContactNo());
    portalUser.setFirstName(user.getFirstName());
    portalUser.setLastName(user.getLastName());
    portalUser.setUserType(UserType.getByType(user.getType()));
    portalUser.setExpiryTime(new Date());
    portalUser.setUniqueId(AllUtil.getUUID());
    portalUser.setEmailCode(AllUtil.getUUID());
    portalUser.setCellOtp(RandomUtil.getNumericWord(5));
    portalUser.setOtpValidated(Boolean.FALSE);
    portalUser.setEmailValidated(Boolean.FALSE);
    return portalUser;
  }

  public static List<PortalUserDTO> fromPortalUser(Iterable<PortalUser> users) {
    return StreamSupport.stream(users.spliterator(), false).map(PortalUserHelper::fromPortalUser)
        .collect(Collectors.toList());
  }

  public static PortalUserDTO fromPortalUser(PortalUser user) {
    PortalUserDTO dto = new PortalUserDTO();
    dto.setId(user.getId());
    dto.setUniqueId(user.getUniqueId());
    dto.setDob(AllUtil.formatUIDate(user.getBirthDate()));
    dto.setFirstName(user.getFirstName());
    dto.setLastName(user.getLastName());
    dto.setContactNo(user.getContactNo());
    dto.setUserName(user.getUserName());
    dto.setEmail(user.getEmail());
    dto.setType(user.getUserType().getType());
    dto.setOperations(Arrays.stream(user.getUserType().getOperations()).map(applicationType -> applicationType.toDTO(user.getUserType())).collect(Collectors.toList()));

    return dto;
  }
}
