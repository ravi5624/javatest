package nst.app.manager;

import nst.app.enums.AgencyType;
import nst.app.model.*;
import nst.app.repo.AgencyDetailRepository;
import nst.app.repo.EAndMAgencyRepository;
import nst.app.repo.IAndTAgencyRepository;
import nst.app.repo.OAndMAgencyRepository;
import nst.common.Manager;
import nst.common.error.AppException;
import nst.kernal.SystemConstants;
import nst.util.AllUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AgencyManager implements Manager {

  @Autowired
  EAndMAgencyRepository eAndMAgencyRepository;
  @Autowired
  OAndMAgencyRepository oAndMAgencyRepository;
  @Autowired
  IAndTAgencyRepository iAndTAgencyRepository;
  @Autowired
  AgencyDetailRepository agencyDetailRepository;

  public EAndMAgency save(EAndMAgency agency) {
    return eAndMAgencyRepository.save(agency);
  }

  public OAndMAgency save(OAndMAgency agency) {
    return oAndMAgencyRepository.save(agency);
  }

  public IAndTAgency save(IAndTAgency agency) {
    return iAndTAgencyRepository.save(agency);
  }

  public Iterable<EAndMAgency> getAllEM() {
    return eAndMAgencyRepository.findAll();
  }

  public List<AgencyDetail> searchByLicense(AgencyType agencyType, String agencyAuthNo) {
    return agencyDetailRepository.findByAgencyTypeAndAgencyAuthNoIgnoreCaseLike(agencyType, String.format("%s%%", agencyAuthNo));
  }

  public List<AgencyDetail> search(String key, Pageable pagination, AgencyType agencyType) {
    return agencyDetailRepository.findByAgencyTypeAndExpiryDateGreaterThanAndNameIgnoreCaseLike(agencyType, AllUtil.getDayStart(), String.format("%s%%", key), pagination);
  }

  public List<AgencyDetail> search(String key, Pageable pagination) {
    return agencyDetailRepository.findByExpiryDateGreaterThanAndNameIgnoreCaseLike(new Date(), String.format("%s%%", key), pagination);
  }

  public Iterable<OAndMAgency> getAllOM() {
    return oAndMAgencyRepository.findAll();
  }

  public AgencyDetail findById(Long id) {
    AgencyDetail agencyDetail = agencyDetailRepository.findOne(id);
    if(agencyDetail == null){
      throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, SystemConstants.Model.INVALID_AGENCY);
    }
    return agencyDetail;
  }

  public AgencyDetail findByUser(PortalUser portalUser) {
    AgencyDetail agencyDetail = agencyDetailRepository.getByUser(portalUser);
    if(agencyDetail == null){
      throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, SystemConstants.Model.INVALID_AGENCY);
    }
    return agencyDetail;
  }

  public Iterable<IAndTAgency> getAllIT() {
    return iAndTAgencyRepository.findAll();
  }

  public EAndMAgency getEM(Long id) {
    return eAndMAgencyRepository.findOne(id);
  }

  public IAndTAgency getIT(Long id) {
    return iAndTAgencyRepository.findOne(id);
  }

  public OAndMAgency getOM(Long id) {
    return oAndMAgencyRepository.findOne(id);
  }

  public EAndMAgency getEMForUser(Long userId) {
    return eAndMAgencyRepository.getAgency(userId);
  }

  public IAndTAgency getITForUser(Long userId) {
    return iAndTAgencyRepository.getAgency(userId);
  }

  public OAndMAgency getOMForUser(Long userId) {
    return oAndMAgencyRepository.getAgency(userId);
  }
}