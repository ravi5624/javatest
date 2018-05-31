package nst.app.manager;

import nst.app.model.District;
import nst.app.model.State;
import nst.app.model.Taluka;
import nst.app.repo.DistrictRepository;
import nst.app.repo.StateRepository;
import nst.app.repo.TalukaRepository;
import nst.common.Manager;
import org.hibernate.jpa.internal.schemagen.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LookupManager implements Manager {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    DistrictRepository districtRepository;


    @Autowired
    TalukaRepository talukaRepository;

    public Iterable<State> getAllState() {
        return stateRepository.findAllByOrderByName();
    }

    public Iterable<District> getAllDistrict() {
        return districtRepository.findAllByOrderByName();
    }

    public Iterable<District> getAllDistrictByStateId(Long stateId) {
        return districtRepository.findAllByStateIdOrderByNameAsc(stateId);
    }

    public Iterable<District> getAllDistrictByStateName(String stateName) {
        return districtRepository.findAllByStateName(stateName);
    }


    public Iterable<Taluka> getAllTalukaByDistrictId(Long districtId) {
        return talukaRepository.findAllByDistrictIdOrderByNameAsc(districtId);
    }

    public Iterable<Taluka> getAllTalukaByDistrictName(String districtName) {
        return talukaRepository.findAllByDistrictName(districtName);
    }
}
