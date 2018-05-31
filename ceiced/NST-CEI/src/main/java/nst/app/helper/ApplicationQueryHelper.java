package nst.app.helper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import nst.app.dto.ApplicationQueryDTO;
import nst.app.dto.QueryDTO;
import nst.app.dto.newgen.NGQueryResponseDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.ApplicationQuery;
import nst.app.model.forms.le.Query;
import nst.common.Helper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ApplicationQueryHelper implements Helper {

  @Autowired
  AttachmentHelper attachmentHelper;

  public ApplicationQuery toModel(ApplicationQueryDTO dto) {
    ApplicationQuery model = new ApplicationQuery();
    return toModel(model, dto);
  }

  public ApplicationQuery toModel(ApplicationQuery model, ApplicationQueryDTO dto) {
    model.setPackId(dto.getPackId());

    if (CollectionUtils.isEmpty(dto.getQueryList())) {
      model.getQueryList().clear();
    } else {
      dto.getQueryList().forEach(queryDTO -> {
        toModel(model.myQuery(queryDTO.getId()), queryDTO);
      });
    }

    return model;
  }

  public List<QueryDTO> fromQuery(Iterable<Query> queries) {
    if (queries == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(queries.spliterator(), false).map(this::fromModel)
        .collect(Collectors.toList());
  }

  public Query toModel(Query model, QueryDTO dto) {
    model.setReply(dto.getReply());

    /*Removed : As we do not required.*/
    //model.setQueryId(dto.getQueryId());
    //model.setRaise(dto.getRaise());
    //model.setRaiseBy(dto.getRaiseBy());
    //model.setRaiseOn(AllUtil.toUIDate(dto.getRaiseOn()));

    return model;
  }

  public List<ApplicationQueryDTO> fromModel(Iterable<ApplicationQuery> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
        .collect(Collectors.toList());
  }

  public QueryDTO fromModel(Query model) {
    QueryDTO dto = new QueryDTO();
    dto.setQueryId(model.getQueryId());
    dto.setId(model.getId());
    dto.setRaise(model.getRaise());
    dto.setRaiseBy(model.getRaiseBy());
    dto.setRaiseOn(AllUtil.formatUIDate(model.getRaiseOn()));
    dto.setReply(model.getReply());
    return dto;
  }

  public ApplicationQuery blankModel(Object portaluser) {
    ApplicationQuery ownerDetail = new ApplicationQuery();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portaluser);
    return ownerDetail;
  }

  public ApplicationQueryDTO fromModel(ApplicationQuery model) {
    ApplicationQueryDTO dto = new ApplicationQueryDTO();
    dto.setId(model.getId());
    dto.setApplicationId(model.getForm().getId());
    dto.setPackId(model.getPackId());

    dto.setQueriedOn(AllUtil.formatUIDate(model.getCreatedDate()));
    dto.setRepliedOn(AllUtil.formatUIDate(model.getRepliedOn()));

    dto.setQueryStatus(model.getQueryStatus());
    dto.setQueryList(fromQuery(model.getQueryList()));

    dto.setAttachments(attachmentHelper.fromModel(model.getAttachments()));
    return dto;
  }

  public NGQueryResponseDTO toNGDTO(ApplicationQuery model) {
    NGQueryResponseDTO dto = new NGQueryResponseDTO();
    dto.setQueries(model.getQueryList().stream().map(model1 -> toNGDTO(model1, model))
        .collect(Collectors.toList()));
    return dto;
  }

  public NGQueryResponseDTO.NGQueryResponseDetailDTO toNGDTO(Query model,
      ApplicationQuery applicationQuery) {
    NGQueryResponseDTO.NGQueryResponseDetailDTO dto = new NGQueryResponseDTO.NGQueryResponseDetailDTO();
    dto.setFileNo(applicationQuery.getForm().getFileNumber());
    dto.setPackid(applicationQuery.getPackId());
    dto.setPid(applicationQuery.getForm().getPid());
    dto.setQuerySubmitDate(AllUtil.formatNGDate(applicationQuery.getRepliedOn()));
    dto.setQ_id(String.valueOf(model.getQueryId()));
    dto.setQ_raise(model.getRaise());
    dto.setRaiseby(model.getRaiseBy());
    dto.setRaiseon(AllUtil.formatNGDate(model.getRaiseOn()));
    dto.setQcomment(model.getReply());
    dto.setDocument(attachmentHelper.fromModelNG(applicationQuery.getAttachmentFor(
        String.format("QUE.%s.%s", applicationQuery.getPackId(), model.getQueryId()))));
    return dto;
  }
}