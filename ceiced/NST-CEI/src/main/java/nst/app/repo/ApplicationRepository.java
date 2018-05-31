package nst.app.repo;

import nst.app.enums.ApplicationType;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface ApplicationRepository extends BaseRepository<CommonForm> {


        @Query(value = "SELECT * from (SELECT * from form_detail fd WHERE user_id = :userId "
            + " UNION SELECT fd.* from form_detail fd, lift_installation li, agency_detail ad WHERE fd.id = li.form_id AND li.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, escalator_installation ei, agency_detail ad WHERE fd.id = ei.form_id AND ei.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, operating_lift ol, agency_detail ad WHERE fd.id = ol.form_id AND ol.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, operating_escalator oe, agency_detail ad WHERE fd.id = oe.form_id AND oe.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " ) as aps where  applicant_name ILIKE  ('%' || (:applicantName) || '%') AND application_type= :applicationType ORDER BY id OFFSET :os LIMIT :lm ",nativeQuery = true)
    List<CommonForm> forEMAgencyWithLikeNameAndType(@Param("userId") long userId, @Param("fileStatus") String fileStatus,
                                 @Param("applicationType") String applicationType,
                                 @Param("applicantName") String applicantName,
                                 @Param("lm") int lm, @Param("os") int os);

    @Query(value = "SELECT COUNT(*) from (SELECT * from form_detail fd WHERE user_id = :userId "
            + " UNION SELECT fd.* from form_detail fd, lift_installation li, agency_detail ad WHERE fd.id = li.form_id AND li.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, escalator_installation ei, agency_detail ad WHERE fd.id = ei.form_id AND ei.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, operating_lift ol, agency_detail ad WHERE fd.id = ol.form_id AND ol.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, operating_escalator oe, agency_detail ad WHERE fd.id = oe.form_id AND oe.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " ) as aps where  applicant_name ILIKE  ('%' || (:applicantName) || '%') AND application_type= :applicationType ",nativeQuery = true)
    long forEMAgencyWithLikeNameAndTypeCount(@Param("userId") long userId, @Param("fileStatus") String fileStatus,
                                                         @Param("applicationType") String applicationType,
                                                         @Param("applicantName") String applicantName);

    @Query(value = "SELECT * from (SELECT * from form_detail fd WHERE user_id = :userId "
            + " UNION SELECT fd.* from form_detail fd, lift_installation li, agency_detail ad WHERE fd.id = li.form_id AND li.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, escalator_installation ei, agency_detail ad WHERE fd.id = ei.form_id AND ei.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, operating_lift ol, agency_detail ad WHERE fd.id = ol.form_id AND ol.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, operating_escalator oe, agency_detail ad WHERE fd.id = oe.form_id AND oe.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " ) as aps where  applicant_name ILIKE ('%' || (:applicationName) || '%') ORDER BY id OFFSET :os LIMIT :lm ",nativeQuery = true)
    List<CommonForm> forEMAgencyWithOnlyApplicantName(@Param("userId") long userId, @Param("fileStatus") String fileStatus,
                                 @Param("applicationName") String applicationName,
                                 @Param("lm") int lm, @Param("os") int os);

    @Query(value = "SELECT COUNT(*) from (SELECT * from form_detail fd WHERE user_id = :userId "
            + " UNION SELECT fd.* from form_detail fd, lift_installation li, agency_detail ad WHERE fd.id = li.form_id AND li.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, escalator_installation ei, agency_detail ad WHERE fd.id = ei.form_id AND ei.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, operating_lift ol, agency_detail ad WHERE fd.id = ol.form_id AND ol.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, operating_escalator oe, agency_detail ad WHERE fd.id = oe.form_id AND oe.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " ) as aps where applicant_name  ILIKE ('%' || (:applicationName) || '%') ",nativeQuery = true)
    long forEMAgencyWithOnlyApplicantNameCount(@Param("userId") long userId, @Param("fileStatus") String fileStatus,
                                                      @Param("applicationName") String applicationName);

    @Query(value = "SELECT * from (SELECT * from form_detail fd WHERE user_id = :userId "
      + " UNION SELECT fd.* from form_detail fd, lift_installation li, agency_detail ad WHERE fd.id = li.form_id AND li.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
      + " UNION SELECT fd.* from form_detail fd, escalator_installation ei, agency_detail ad WHERE fd.id = ei.form_id AND ei.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
      + " UNION SELECT fd.* from form_detail fd, operating_lift ol, agency_detail ad WHERE fd.id = ol.form_id AND ol.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
      + " UNION SELECT fd.* from form_detail fd, operating_escalator oe, agency_detail ad WHERE fd.id = oe.form_id AND oe.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
      + " ) as aps where aps.application_type  ILIKE ('%' || (:applicationType) || '%') ORDER BY id OFFSET :os LIMIT :lm ",nativeQuery = true)
 List<CommonForm> forEMAgency(@Param("userId") long userId, @Param("fileStatus") String fileStatus,
                              @Param("applicationType") String applicationType,
                              @Param("lm") int lm, @Param("os") int os);


    @Query(value = "SELECT COUNT(*) from (SELECT * from form_detail fd WHERE user_id = :userId "
            + " UNION SELECT fd.* from form_detail fd, lift_installation li, agency_detail ad WHERE fd.id = li.form_id AND li.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, escalator_installation ei, agency_detail ad WHERE fd.id = ei.form_id AND ei.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, operating_lift ol, agency_detail ad WHERE fd.id = ol.form_id AND ol.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " UNION SELECT fd.* from form_detail fd, operating_escalator oe, agency_detail ad WHERE fd.id = oe.form_id AND oe.agency_id = ad.id AND ad.user_id = :userId AND fd.file_status = :fileStatus "
            + " ) as aps where aps.application_type  ILIKE ('%' || (:applicationType) || '%') ",nativeQuery = true)
    long forEMAgencyCount(@Param("userId") long userId, @Param("fileStatus") String fileStatus,
                                 @Param("applicationType") String applicationType);

  @Query(value = "SELECT file_status, count(*) FROM form_detail where user_id = ?1 GROUP BY file_status;", nativeQuery = true)
  List<Object[]> findAppByFileStatus(Long userId);


  /*ToDo: Check Case types for LicenseNumber*/
  CommonForm findByApplicationTypeAndLicenseNumberAndIssueDateAndExpiryDate(ApplicationType applicationType, String licenseNumber, Date issueDate, Date expiryDate);

  List<CommonForm> findAllByUserIdOrderByIdDesc(Long userId, Pageable pageable);

  long countByUserId(Long userId);

    List<CommonForm> findAllByApplicationTypeAndUserIdAndApplicantNameIgnoreCaseContainingOrderByIdDesc(ApplicationType applicationType, Long userId, String applicantName, Pageable pagination);

    List<CommonForm> findAllByUserIdAndApplicantNameIgnoreCaseContainingOrderByIdDesc(Long userId, String applicantName, Pageable pagination);

    List<CommonForm> findByApplicationTypeAndUserIdOrderByIdDesc(ApplicationType applicationType, Long userId, Pageable pagination);

    long countAllByUserIdOrderByIdDesc(Long userId);

    long countByApplicationTypeAndUserIdAndApplicantNameIgnoreCaseContainingOrderByIdDesc(ApplicationType applicationType, Long userId, String applicantName);

    long countAllByUserIdAndApplicantNameIgnoreCaseContainingOrderByIdDesc(Long userId, String applicantName);

    long countByApplicationTypeAndUserIdOrderByIdDesc(ApplicationType applicationType, Long userId);
}