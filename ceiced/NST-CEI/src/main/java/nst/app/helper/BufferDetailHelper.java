package nst.app.helper;

import nst.app.dto.BufferDetailDTO;
import nst.app.dto.newgen.NGBufferDetailDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.BufferDetail;
import nst.app.model.forms.le.LiftInstallation;
import nst.common.Helper;
import nst.util.LoginUserUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BufferDetailHelper implements Helper {

  public BufferDetail toModel(BufferDetailDTO dto) {
        BufferDetail model = new BufferDetail();
        return toModel(model, dto);
    }

    public BufferDetail toModel(BufferDetail model, BufferDetailDTO dto) {
        model.setNumberOfBuffer(dto.getNumberOfBuffer());
        model.setStroke(dto.getStroke());
        model.setType(dto.getType());
        model.setBufferType(dto.getBufferTypeEnum());
        if(!Objects.isNull(dto.getType()) && dto.getType().equalsIgnoreCase("other")){
          model.setOtherType(dto.getOtherType());
        }else{
          model.setOtherType(null);
        }
        return model;
    }

    public BufferDetail blankModel(Object portaluser) {
        BufferDetail ownerDetail = new BufferDetail();
        LoginUserUtil.getLoginUser();
        ownerDetail.getForm().setUser((PortalUser) portaluser);
        return ownerDetail;
    }

    public BufferDetailDTO fromModel(BufferDetail model) {
        if (model == null) {
            return null;
        }
        BufferDetailDTO dto = new BufferDetailDTO();
        dto.setId(model.getId());
        dto.setNumberOfBuffer(model.getNumberOfBuffer());
        dto.setBufferType(model.getBufferType().name());
        dto.setStroke(model.getStroke());
        dto.setType(model.getType());
        dto.setOtherType(model.getOtherType());
        return dto;
    }

    public NGBufferDetailDTO toNGDTO(BufferDetail model) {
        if (model == null) {
            return null;
        }
        NGBufferDetailDTO dto = new NGBufferDetailDTO();
        dto.setId(model.getId());
        dto.setNumberOfBuffer(model.getNumberOfBuffer());
        dto.setBufferType(model.getBufferType().name());
        dto.setStroke(model.getStroke());
        dto.setOtherType(model.getOtherType());
        dto.setType(model.getType());
        return dto;
    }

    public List<BufferDetailDTO> fromModel(Iterable<BufferDetail> ms) {
        if (ms == null) {
            return Collections.emptyList();
        }
        return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
                .collect(Collectors.toList());
    }

    public void manageBufferDetails(LiftInstallation model, BufferDetailDTO dto) {
      if (dto == null || StringUtils.isEmpty(dto.getBufferType())) {
            return;
        }

        BufferDetail bufferDetail = model.getBufferDetailFor((dto.getBufferTypeEnum()));
        if (bufferDetail == null) {
            bufferDetail = new BufferDetail(model.getForm());
        }

        model.addBufferDetails(toModel(bufferDetail, dto));
    }


    /*public List<NGBufferDetailDTO> fromModelNG(Iterable<BufferDetail> ms) {
        if (ms == null) {
            return Collections.emptyList();
        }
        return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
                .collect(Collectors.toList());
    }*/

    /*public NGBufferDetailDTO fromModelNG(BufferDetail ms) {
        return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
                .collect(Collectors.toList());
    }*/



}