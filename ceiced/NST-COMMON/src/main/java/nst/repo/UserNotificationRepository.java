package nst.repo;

import java.util.List;
import nst.common.base.BaseRepository;
import nst.kernal.NotificationStatus;
import nst.kernal.UserNotification;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationRepository extends BaseRepository<UserNotification> {

  List<UserNotification> findByUserIdOrderByIdDesc(long userId, Pageable pagination);

  long countByUserId(long userId);

  long countByUserIdAndRead(long userId, boolean read);

  List<UserNotification> findByUserIdAndStatus(long userId, NotificationStatus status,
      Pageable pagination);
}