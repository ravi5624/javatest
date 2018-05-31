package nst.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import nst.common.ValidationUtil;
import nst.dto.AttachmentDTO;
import nst.dto.TransactionDTO;
import nst.kernal.SystemConstants;
import nst.kernal.validation.CombiDate;
import nst.kernal.validation.PastDate;
import nst.util.AllUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.ALWAYS)
@Data
public abstract class BaseModelDTO extends BaseDTO {

  private Long id;
  private String uniqueId;
  private Integer version;
  private String type;
  private String status;
  private String applicationName;
  private String fileNumber;
  private String pid;
  private String outwardNo;
  private TransactionDTO transaction;
  private Boolean iagree;
  private boolean multiPart = false;
  private String paymentStatus;
  private String createdBy;
  private Boolean canSubmit = true;

  public BaseModelDTO() {
  }

  public BaseModelDTO(Long id) {
    this.id = id;
  }

  public BaseModelDTO(Long id, Integer version) {
    this.id = id;
    this.version = version;
  }

  @JsonIgnore
  public Class[] getBasicValidations() {
    return new Class[]{Size.class, PastDate.class, CombiDate.class};
  }

  @JsonIgnore
  public Class[] getRequiredValidations() {
    return new Class[]{NotNull.class};
  }

  @JsonIgnore
  public void validateBasic() {
    ValidationUtil.validate(this, getBasicValidations(), otherValidateBasic());
  }
  @JsonIgnore
  public void validateAll() {
      List<ResponseError> errors = otherValidateBasic();
    errors.addAll(otherValidateAll());
    errors.addAll(ValidationUtil.validateFor(this, getBasicValidations()));
    ValidationUtil.validate(this, getRequiredValidations(), errors);
  }

  @JsonIgnore
  protected List<ResponseError> otherValidateBasic() {
    return new ArrayList<>();
  }

  @JsonIgnore
  protected List<ResponseError> otherValidateAll() {
    return new ArrayList<>();
  }

  @JsonIgnore
  public String getApplicantName() {
    return "";
  }

  @JsonIgnore
  public String getAttachment(String fieldIdentifier) {
    if (CollectionUtils.isEmpty(getAttachments())) {
      return "-N/A-";
    }

    if (StringUtils.isEmpty(fieldIdentifier)) {
      return "-N/A-";
    }

    Optional<AttachmentDTO> first = getAttachments().stream()
        .filter(at -> fieldIdentifier.equalsIgnoreCase(at.getFieldIdentifier())).findFirst();
    return first.isPresent() ? first.get().getRealFileName() : "-N/A-";
  }

    @JsonIgnore
    public String getImageInBase64(String fieldIdentifier){
        if (CollectionUtils.isEmpty(getAttachments())) {
            return "-N/A-";
        }

        if (StringUtils.isEmpty(fieldIdentifier)) {
            return "-N/A-";
        }

        Optional<AttachmentDTO> first = getAttachments().stream()
                .filter(at -> fieldIdentifier.equalsIgnoreCase(at.getFieldIdentifier())).findFirst();
        String imageString = "data:image/jpeg;base64,/9j/4QAYRXhpZgAASUkqAAgAAAAAAAAAAAAAAP/sABFEdWNreQABAAQAAAA8AAD/4QMvaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjYtYzA2NyA3OS4xNTc3NDcsIDIwMTUvMDMvMzAtMjM6NDA6NDIgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bXA6Q3JlYXRvclRvb2w9IkFkb2JlIFBob3Rvc2hvcCBDQyAyMDE1IChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo2MjlEMkNGOTM3MTAxMUU4QUZERUZDODcwOUFERDlEMCIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDo2MjlEMkNGQTM3MTAxMUU4QUZERUZDODcwOUFERDlEMCI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjYyOUQyQ0Y3MzcxMDExRThBRkRFRkM4NzA5QUREOUQwIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjYyOUQyQ0Y4MzcxMDExRThBRkRFRkM4NzA5QUREOUQwIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+/+4ADkFkb2JlAGTAAAAAAf/bAIQABgQEBAUEBgUFBgkGBQYJ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";
        if(first.isPresent()){
          //TODO below is for local.
          String rootPath=System.getProperty("user.home")+"/CEICED/DATA";
          //TODO below is for UAT and Testing environment.
          //String rootPath="/opt/ceiced-docs";
           File f=new File(rootPath+first.get().getAbsolutePath());
            try{
                String displayImageBase64Converter = "data:image/gif;base64,";
                imageString = displayImageBase64Converter + Base64.encodeBase64String(Files.readAllBytes(f.toPath()));
            }catch (Exception e){
                return imageString;
            }
        }
    return imageString;
  }

  public static String formatDate(String date) {
    return AllUtil.formatDate(date, SystemConstants.FORMAT_DATE_BACKSLASH);
  }

  @JsonIgnore
  public String getAttachment(String formPart, String fieldIdentifier) {
    if (CollectionUtils.isEmpty(getAttachments())) {
      return "-N/A-";
    }

    if (StringUtils.isEmpty(formPart) || StringUtils.isEmpty(fieldIdentifier)) {
      return "-N/A-";
    }

    Optional<AttachmentDTO> first = getAttachments().stream()
        .filter(at -> formPart.equalsIgnoreCase(at.getFormPart()))
        .filter(at -> fieldIdentifier.equalsIgnoreCase(at.getFieldIdentifier()))
        .findFirst();
    return first.isPresent() ? first.get().getRealFileName() : "-N/A-";
  }

  public List<AttachmentDTO> getAttachments() {
    return Collections.emptyList();
  }
}