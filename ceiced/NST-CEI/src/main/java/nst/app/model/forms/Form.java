package nst.app.model.forms;

import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.CommonForm;

public interface Form {

  ApplicationType getApplicationType();

  PortalUser getOwner();

  FileStatus getFileStatus();

  Long getApplicationId();

  String getUniqueId();

  CommonForm getForm();

  default PortalUser getAssignedTo(){
    return null;
  }
}
