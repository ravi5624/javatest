package nst.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import nst.app.config.CEIConstants;
import nst.app.manager.LookupManager;
import nst.app.model.District;
import nst.app.model.State;
import nst.app.model.Taluka;
import nst.common.AbstractService;
import nst.dto.LookupDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class LookupService extends AbstractService {

    @Autowired
    LookupManager manager;

    public List<LookupDataDTO> getAllState() {
        Iterable<State> allState = manager.getAllState();
        List<LookupDataDTO> list = new ArrayList<>();
        allState.forEach(state -> {
            list.add(LookupDataDTO.create(state.getId(), state.getName(), state.getName()));
        });
        return list;
    }

    public List<LookupDataDTO> getAllStateWithOther() {
        Iterable<State> allState = manager.getAllState();
        List<LookupDataDTO> list = new ArrayList<>();
        allState.forEach(state -> {
            if(state.getName().equalsIgnoreCase(CEIConstants.GUJARAT)){
                list.add(LookupDataDTO.create(state.getId(), state.getName(), state.getName()));
            }else {
                list.add(LookupDataDTO.create(state.getId(),CEIConstants.OTHER, state.getName()));
            }
        });

        return list;
    }

    public List<LookupDataDTO> getAllDistrict() {
        Iterable<District> allDistrict = manager.getAllDistrict();
        List<LookupDataDTO> list = new ArrayList<>();
        allDistrict.forEach(district -> {
            list.add(LookupDataDTO.create(district.getId(), district.getCode(), district.getName()));
        });
        return list;
    }

    public List<LookupDataDTO> getAllDistrictByStateId(Long stateId) {
        Iterable<District> allDistrict = manager.getAllDistrictByStateId(stateId);
        List<LookupDataDTO> list = new ArrayList<>();
        allDistrict.forEach(district -> {
            list.add(LookupDataDTO.create(district.getId(), district.getCode(), district.getName()));
        });
        list.add(LookupDataDTO.create(0L,"other","Other"));
        return list;
    }


    public List<LookupDataDTO> getAllDistrictByStateName(String stateName) {
        Iterable<District> allDistrict = manager.getAllDistrictByStateName(stateName);
        List<LookupDataDTO> list = new ArrayList<>();
        allDistrict.forEach(district -> {
            list.add(LookupDataDTO.create(district.getId(), district.getCode(), district.getName()));
        });
        return list;
    }


    public List<LookupDataDTO> getAllTalukaByDistrictId(Long districtId) {
        Iterable<Taluka> allTaluka = manager.getAllTalukaByDistrictId(districtId);
        List<LookupDataDTO> list = new ArrayList<>();
        allTaluka.forEach(taluka -> {
            list.add(LookupDataDTO.create(taluka.getId(), taluka.getCode(), taluka.getName()));
        });
        list.add(LookupDataDTO.create(0L,"other","Other"));
        return list;
    }


    public List<LookupDataDTO> getAllTalukaByDistrictName(String districtName) {
        Iterable<Taluka> alltaluka = manager.getAllTalukaByDistrictName(districtName);
        List<LookupDataDTO> list = new ArrayList<>();
        alltaluka.forEach(taluka -> {
            list.add(LookupDataDTO.create(taluka.getId(), taluka.getCode(), taluka.getName()));
        });
        return list;
    }
}