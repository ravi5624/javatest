package nst.app.helper;

import nst.app.dto.AccidentVictimDTO;
import nst.app.dto.newgen.NGAccidentVictimDTO;
import nst.app.enums.AddressType;
import nst.app.model.forms.le.AccidentVictim;
import nst.common.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Component
public class AccidentVictimHelper implements Helper {

    @Autowired
    AddressHelper addressHelper;

    public AccidentVictim toModel(AccidentVictimDTO dto) {
        AccidentVictim model = new AccidentVictim();
        return toModel(model, dto);
    }

    public AccidentVictim toModel(AccidentVictim model, AccidentVictimDTO dto) {

        model.setName(dto.getName());
        model.setFatherName(dto.getFatherName());
        model.setGender(dto.getGender());
        model.setAge(dto.getAge());
        model.setEmail(dto.getEmail());
        model.setContactNo(dto.getContactNo());
        addressHelper.manageAddress(model.getForm(), dto.getVictimPostalAddress(), model.getId());
        return model;
    }


    public List<AccidentVictimDTO> fromModel(Iterable<AccidentVictim> ms) {
        if (ms == null) {
            return Collections.emptyList();
        }
        return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
                .collect(Collectors.toList());
    }


    public List<NGAccidentVictimDTO> fromModelNG(Iterable<AccidentVictim> ms) {
        if (ms == null) {
            return Collections.emptyList();
        }
        return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
                .collect(Collectors.toList());
    }


    public AccidentVictimDTO fromModel(AccidentVictim model) {
        if (model == null) {
            return null;
        }
        AccidentVictimDTO dto = new AccidentVictimDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setFatherName(model.getFatherName());
        dto.setGender(model.getGender());
        dto.setAge(model.getAge());

        dto.setEmail(model.getEmail());
        dto.setContactNo(model.getContactNo());
        dto.setVictimPostalAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.VICTIM, model.getId())));
        return dto;
    }

    public NGAccidentVictimDTO toNGDTO(AccidentVictim model) {
        NGAccidentVictimDTO dto = new NGAccidentVictimDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setFatherName(model.getFatherName());
        dto.setGender(model.getGender());
        dto.setAge(model.getAge());
        dto.setEmail(model.getEmail());
        dto.setContactNo(model.getContactNo());
        dto.setVictimPostalAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.VICTIM, model.getId())));
        return dto;
    }

}
