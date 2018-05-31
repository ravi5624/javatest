package nst.app.controller.common;

import java.io.FileInputStream;
import javax.servlet.http.HttpServletResponse;
import nst.app.config.CEIConstants;
import nst.app.model.forms.le.ApplicationAttachment;
import nst.app.service.ApplicationAttachmentService;
import nst.common.AbstractController;
import nst.common.base.BaseResponse;
import nst.common.error.AppException;
import nst.config.MyLogger;
import nst.kernal.SystemConstants;
import nst.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/attachment")
public class AttachmentController extends AbstractController {

  @Autowired
  ApplicationAttachmentService attachmentService;

  @RequestMapping(value = "/upload", headers = SystemConstants.Rest.APPLICATION_MULTIPART, method = RequestMethod.POST)
  public BaseResponse update(
      @RequestParam(value = "file") MultipartFile inputFile,
      @RequestParam(value = "applicationId") Long applicationId,
      @RequestParam(value = "fieldIdentifier") String fieldIdentifier,
      @RequestParam(value = "labelName") String labelName,
      @RequestParam(value = "formPart") String formPart,
      @RequestParam(value = "queryId", required = false) Long queryId,
      @RequestParam(value = "variableName") String variableName
  ) {
    if (inputFile == null || inputFile.isEmpty()) {
      throw AppException.create("NO_FILE", "file", CEIConstants.NO_FILE_PROVIDED);
    }
    if (StringUtils.isEmpty(fieldIdentifier)) {
      throw AppException.create("FIELD_ERROR", "fieldIdentifier", CEIConstants.REQUIRED);
    }
    if (StringUtils.isEmpty(formPart)) {
      throw AppException.create("FIELD_ERROR", "formPart", CEIConstants.REQUIRED);
    }
    if (StringUtils.isEmpty(labelName)) {
      throw AppException.create("FIELD_ERROR", "labelName", CEIConstants.REQUIRED);
    }
    if (StringUtils.isEmpty(variableName)) {
      throw AppException.create("FIELD_ERROR", "variableName", CEIConstants.REQUIRED);
    }
    return BaseResponse.createSuccess(attachmentService.add(applicationId, inputFile, fieldIdentifier, variableName, labelName, formPart,
        queryId));
  }

  @RequestMapping(value = "/{applicationId}/getFile/{fileId}", method = RequestMethod.GET)
  public void getFile(@PathVariable("applicationId") Long applicationId,
      @PathVariable("fileId") Long fileId, HttpServletResponse response) {

    ApplicationAttachment attachment = attachmentService.getFile(applicationId, fileId, null);
    response.setContentType(attachment.getMimeType());
    response
        .addHeader("Content-Disposition", "attachment; filename=" + attachment.getRealFileName());
    try {
      IOUtil.copy(new FileInputStream(attachment.getFile()), response.getOutputStream());
      response.getOutputStream().flush();
    } catch (Throwable throwable) {
      MyLogger.logError(throwable);
    }
  }

  @RequestMapping(value = "/{applicationId}/getFile/{fileId}/delete", method = RequestMethod.DELETE)
  public BaseResponse delete(@PathVariable("applicationId") Long applicationId,
      @PathVariable("fileId") Long fileId) {
    attachmentService.delete(applicationId, fileId);
    return BaseResponse.createSuccess();
  }
}