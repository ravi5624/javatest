package nst.app.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import nst.common.base.BaseDTO;

/**
 * contains all the fields for ApplicationType
 *
 */


@Data
@AllArgsConstructor
public class ApplicationTypeDTO extends BaseDTO {

  String type;
  String name;
  String api;
  List<PartDTO> parts;

  public ApplicationTypeDTO(String type, String name, String api) {
    this.type = type;
    this.name = name;
    this.api = api;
  }

  public void addPart(PartDTO part) {
    if (this.parts == null) {
      this.parts = new ArrayList<>();
    }
    this.parts.add(part);
  }

  @Data
  public static class PartDTO extends BaseDTO {

    String part;
    boolean view;
    boolean edit;

    private PartDTO(String part, boolean view, boolean edit) {
      this.part = part;
      this.view = view;
      this.edit = edit;
    }

    public static PartDTO create(String part, boolean view, boolean edit) {
      return new PartDTO(part, view, edit);
    }
  }
}
