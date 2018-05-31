package nst.app.common;

import nst.app.dto.newgen.NGBaseDTO;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.enums.LiftEscalatorType;
import nst.app.enums.UserType;
import nst.app.model.forms.Form;
import nst.app.model.forms.le.CommonForm;
import nst.app.model.forms.le.Transaction;
import nst.common.EnumType;
import nst.common.base.BaseModelDTO;
import nst.common.security.LoginUser;
import nst.dto.TransactionDTO;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelperUtil {

  public static NGBaseDTO getNGDTO(Form model, NGBaseDTO dto) {
    dto.setId(model.getApplicationId());
    dto.setApplicationId(model.getUniqueId());
    dto.setApplicationName(model.getApplicationType().getNregenName());
    dto.setType(model.getApplicationType().getType());
    dto.setFileNumber(model.getForm().getFileNumber());
    dto.setIagree(model.getForm().getIAgree());
    dto.setStatus("File_in_Processing");
    dto.setCreatedBy(model.getForm().getUser().getUserType().toString());

    return dto;
  }

  public static String toEnumName(EnumType enumType) {
    if (enumType != null) {
      return enumType.getName();
    }
    return null;
  }

  public static String toEnumType(EnumType enumType) {
    if (enumType != null) {
     return enumType.getType();
    }
    return null;
  }

  public static LiftEscalatorType toEnum(LiftEscalatorType enm, String type) {
    if (!StringUtils.isEmpty(type)) {
      return LiftEscalatorType.valueOf(type.toUpperCase());
    }
    return null;
  }

  public static List<String> fromEnum(EnumType[] enumTypes) {
    return Arrays.stream(enumTypes).map(EnumType::getName).collect(Collectors.toList());
  }

  public static void toDTO(BaseModelDTO dto, Form form) {
    dto.setUniqueId(form.getUniqueId());
    dto.setId(form.getApplicationId());
    dto.setType(form.getApplicationType().getType());
    dto.setApplicationName(form.getApplicationType().getName());
    dto.setIagree(form.getForm().getIAgree());
    dto.setFileNumber(form.getForm().getFileNumber());
    dto.setPid(form.getForm().getPid());
    dto.setOutwardNo(form.getForm().getOutwardNo());
    dto.setCreatedBy(form.getForm().getUser().getUserType().toString());
    dto.setPaymentStatus(form.getForm().getTransactionStatus() == null ? "PENDING" : form.getForm().getTransactionStatus().name());

    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    dto.setCanSubmit(FileStatus.DRAFT == form.getFileStatus() ||
        (loginUser.hasAuthority(UserType.EM_AGENCY.getType()) && FileStatus.OWNER_SUBMITTED == form.getFileStatus()));

    if (form.getApplicationType() == ApplicationType.EMAL ||
            form.getApplicationType() == ApplicationType.OMAL ||
            form.getApplicationType() == ApplicationType.ITAL ||
            form.getApplicationType() == ApplicationType.LIL ||
            form.getApplicationType() == ApplicationType.EIL ||
            form.getApplicationType() == ApplicationType.OLIFT ||
            form.getApplicationType() == ApplicationType.OESCL
            ){
      dto.setMultiPart(true);
    }

    dto.setStatus(form.getFileStatus().getType());
    CommonForm commonForm = AllUtil.getValue(form, "Form");
    if (commonForm != null) {
      dto.setTransaction(toTransactionDTO(commonForm.getTransaction()));
    }
  }

  public static void toModel(CommonForm form, BaseModelDTO dto) {
    form.setIAgree(dto.getIagree());
  }

  public static String fromBoolean(Boolean b) {
    return b == null || b == false ? "No" : "Yes";
  }

  public static Boolean toBoolean(String str) {
    return StringUtils.isEmpty(str) || "Yes".equalsIgnoreCase(str) ? Boolean.TRUE : Boolean.FALSE;
  }

  public static String toOnOff(Boolean b) {
    return b == null || b == false ? "off" : "on";
  }

  public static Boolean fromOnOff(String str) {
    return StringUtils.isEmpty(str) ? Boolean.FALSE : "on".equalsIgnoreCase(str);
  }

  public static String fromString(String str) {
    return fromString(str, "");
  }

  public static String fromString(String str, String defaultVal) {
    return StringUtils.isEmpty(str) ? defaultVal : str.trim();
  }

  public static TransactionDTO toTransactionDTO(Transaction transaction) {
    if (transaction == null) {
      return null;
    }

    TransactionDTO dto = new TransactionDTO();
    dto.setPaidOn(AllUtil.formatUIDate(transaction.getPaidOn()));
    dto.setAmount(transaction.getAmount());
    dto.setStatus(transaction.getStatus().name());
    dto.setTransactionId(transaction.getTransactionId());
    dto.setUrl("http://nascentlayers.com/cei3/public/payment/respond");
    return dto;
  }

//  ModelMapper modelMapper = new ModelMapper();
//  modelMapper.getConfiguration().setAmbiguityIgnored(true);
//  LiftInstallationDTO dto = modelMapper.map(model, LiftInstallationDTO.class);
}