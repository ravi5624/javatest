package nst.app.model;

import lombok.Data;
import nst.common.Model;

@Data
//@Entity
//@Table(name = "locale_message")
//@BatchSize(size = 50)
public class Locale implements Model {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String screen;
  private String lang;
  private String key;
  private String value;
}