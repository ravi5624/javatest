package nst.manager;

import nst.common.base.BaseManager;
import nst.common.base.BaseRepository;
import nst.common.security.LoginUser;
import nst.dto.APIRequestDTO;
import nst.dto.CellDTO;
import nst.dto.EmailDTO;
import nst.helper.UserNotificationHelper;
import nst.kernal.EmailSender;
import nst.kernal.SMSSender;
import nst.kernal.SystemConstants;
import nst.kernal.UserNotification;
import nst.repo.UserNotificationRepository;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserNotificationManager extends BaseManager<UserNotification> {

  @Autowired
  UserNotificationRepository repository;

  @Autowired
  UserNotificationHelper helper;

  @Autowired
  EmailSender emailSender;

  @Autowired
  SMSSender smsSender;


  @Override
  public BaseRepository<UserNotification> getRepository() {
    return repository;
  }

  public List<UserNotification> getMyNotifications(Pageable pagination) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    return repository.findByUserIdOrderByIdDesc(loginUser.getUserId(), pagination);
  }

  public long getMyNotificationCount() {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    return repository.countByUserId(loginUser.getUserId());
  }

  public void addNotification(EmailDTO dto, Boolean flag) {
    UserNotification notification = helper.toUserNotification(dto);
    repository.save(notification);
    if(flag)
      emailSender.send(dto);
  }

  public void sendSMS(CellDTO cellDTO) {
    APIRequestDTO apiRequestDTO = APIRequestDTO.create(cellDTO.getServer(), SystemConstants.Rest.METHOD_GET);
    Map<String, String> params = new HashMap<>();
    params.put("mobile",cellDTO.getUserName());
    params.put("pass",cellDTO.getPassword());
    params.put("senderid",cellDTO.getSenderId());
    params.put("to",cellDTO.getTo());
    params.put("msg",cellDTO.getBody());
    apiRequestDTO.setParams(params);
    smsSender.send(apiRequestDTO);
  }

  public void addNotification(EmailDTO dto, CellDTO cellDTO) {
    UserNotification notification = helper.toUserNotification(dto);
    repository.save(notification);
    emailSender.send(dto);
//    sendSMS(cellDTO);
  }

  public long myNotificationCount() {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    return repository.countByUserIdAndRead(loginUser.getUserId(), Boolean.FALSE);
  }
}