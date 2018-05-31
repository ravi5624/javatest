package nst.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.springframework.util.StringUtils;

public class CodeGenerator {

  public static void main(String[] args) throws IOException {
    File file = new File("/home/vishal/DATA/Projects/Nascent/Projects/NST-CEICED/NST-CEI/src/main/java/nst/app/model/forms/lb");
    Arrays.stream(file.list()).forEach(System.out::println);
//    generate();
  }

  public static void generate() throws IOException {
    String basePath = "/home/vishal/DATA/Projects/Nascent/Projects/NST-CEICED/NST-CEI/src/main/java/";

    String[] models = {
//        "InterStatePermit",
//        "ContractorModification",
//        "ContractorRenewal",
//        "ContractorLicense",

        "WiremanExamination",
        "SupervisorRenewal",
        "ContractorPartnersModification",
        "WiremanRenewal",
        "FormI",
        "SupervisorExamination",
        "DuplicatePermit",
        "WiremanExemption",
        "Repeater",
        "WiremanModification",
        "InterStateRenewal",
        "SupervisorModification",
    };

    if (models == null || models.length == 0) {
      System.out.println("No Model specified......");
      System.exit(0);
    }

    Arrays.stream(models).forEach(model -> {
      try {

        if (StringUtils.isEmpty(model)) {
          System.out.println("No Model specified......");
          return;
        }

        String dto = model + "DTO";

        Files.write(Paths.get(basePath + "nst/app/controller/secure/" + model + "Controller.java"),
            CONTROLLER.replace("#DTO#", dto).replace("#MODEL#", model).getBytes());

//        Files.write(Paths.get(basePath + "nst/app/model/forms/" + model + ".java"),
//            MODEL.replace("#DTO#", dto).replace("#MODEL#", model).getBytes());

        Files.write(Paths.get(basePath + "nst/app/dto/" + dto + ".java"),
            DTO.replace("#DTO#", dto).replace("#MODEL#", model).getBytes());

        Files.write(Paths.get(basePath + "nst/app/dto/newgen/NG" + model + "DTO.java"),
            NG_DTO.replace("#DTO#", dto).replace("#MODEL#", model).getBytes());

        Files.write(Paths.get(basePath + "nst/app/helper/" + model + "Helper.java"),
            HELPER.replace("#DTO#", dto).replace("#MODEL#", model).getBytes());

        Files.write(Paths.get(basePath + "nst/app/service/" + model + "Service.java"),
            SERVICE.replace("#DTO#", dto).replace("#MODEL#", model).getBytes());

        Files.write(Paths.get(basePath + "nst/app/repo/" + model + "Repository.java"),
            REPO.replace("#DTO#", dto).replace("#MODEL#", model).getBytes());

        Files.write(Paths.get(basePath + "nst/app/manager/" + model + "Manager.java"),
            MANAGER.replace("#DTO#", dto).replace("#MODEL#", model).getBytes());

      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  private static String CONTROLLER = "package nst.app.controller.secure;\n"
      + "\n"
      + "import nst.app.common.CEIBaseController;\n"
      + "import nst.app.common.CEIBaseService;\n"
      + "import nst.app.dto.#DTO#;\n"
      + "import nst.app.model.forms.lb.#MODEL#;\n"
      + "import nst.app.service.#MODEL#Service;\n"
      + "import org.springframework.beans.factory.annotation.Autowired;\n"
      + "import org.springframework.web.bind.annotation.RequestMapping;\n"
      + "import org.springframework.web.bind.annotation.RestController;\n"
      + "\n"
      + "@RestController\n"
      + "@RequestMapping(\"api/#MODEL#\")\n"
      + "public class #MODEL#Controller extends\n"
      + "    CEIBaseController<#MODEL#, #DTO#> {\n"
      + "\n"
      + "  @Autowired\n"
      + "  #MODEL#Service service;\n"
      + "\n"
      + "  public CEIBaseService<#MODEL#, #DTO#> getService() {\n"
      + "    return service;\n"
      + "  }\n"
      + "}";

  private static String MODEL = "package nst.app.model.forms;\n"
      + "\n"
      + "import javax.persistence.CascadeType;\n"
      + "import javax.persistence.Column;\n"
      + "import javax.persistence.Entity;\n"
      + "import javax.persistence.JoinColumn;\n"
      + "import javax.persistence.OneToOne;\n"
      + "import javax.persistence.Table;\n"
      + "import lombok.Data;\n"
      + "import nst.app.enums.ApplicationType;\n"
      + "import nst.app.model.PortalUser;\n"
      + "import nst.common.base.BaseModel;\n"
      + "import org.hibernate.annotations.BatchSize;\n"
      + "\n"
      + "@Data\n"
      + "@Entity\n"
      + "@Table(name = \"#MODEL#\")\n"
      + "@BatchSize(size = 50)\n"
      + "public class #MODEL# extends BaseModel implements Form {\n"
      + "\n"
      + "  @JoinColumn(name = \"form_id\")\n"
      + "  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)\n"
      + "  CommonForm form = new CommonForm();\n"
      + "\n"
      + "  @Column(name = \"agency_name\")\n"
      + "  private static String name;\n"
      + "\n"
      + "  @Override\n"
      + "  public ApplicationType getApplicationType() {\n"
      + "    return getForm().getApplicationType();\n"
      + "  }\n"
      + "\n"
      + "  @Override\n"
      + "  public PortalUser getOwner() {\n"
      + "    return getForm().getUser();\n"
      + "  }\n"
      + "}";

  private static String DTO = "package nst.app.dto;\n"
      + "\n"
      + "import javax.validation.constraints.NotNull;\n"
      + "import javax.validation.constraints.Size;\n"
      + "import lombok.Data;\n"
      + "import lombok.NoArgsConstructor;\n"
      + "import nst.common.base.BaseModelDTO;\n"
      + "\n"
      + "@Data\n"
      + "@NoArgsConstructor\n"
      + "public class #DTO# extends BaseModelDTO {\n"
      + "\n"
      + "  @NotNull\n"
      + "  @Size(max = 30)\n"
      + "  private static String ownerName;\n"
      + "\n"
      + "  private Long userId;\n"
      + "}";

  private static String NG_DTO = "package nst.app.dto.newgen;\n"
      + "\n"
      + "import lombok.Data;\n"
      + "\n"
      + "@Data\n"
      + "public class NG#MODEL#DTO extends NGBaseDTO {\n"
      + "\n"
      + "}";

  private static String HELPER = "package nst.app.helper;\n"
      + "\n"
      + "import nst.app.dto.#DTO#;\n"
      + "import nst.app.model.PortalUser;\n"
      + "import nst.app.model.forms.lb.#MODEL#;\n"
      + "import nst.common.base.BaseHelper;\n"
      + "import nst.util.LoginUserUtil;\n"
      + "import nst.app.dto.newgen.NG#MODEL#DTO;\n"
      + "import org.springframework.stereotype.Component;\n"
      + "\n"
      + "@Component\n"
      + "public class #MODEL#Helper extends\n"
      + "    BaseHelper<#MODEL#, #DTO#> {\n"
      + "\n"
      + "  public #MODEL# toModel(#DTO# #DTO#) {\n"
      + "    #MODEL# portalUser = new #MODEL#();\n"
      + "    return toModel(portalUser, #DTO#);\n"
      + "  }\n"
      + "\n"
      + "  @Override\n"
      + "  public #MODEL# toModel(#MODEL# model,\n"
      + "      #DTO# dto) {\n"
      + "    return model;\n"
      + "  }\n"
      + "\n"
      + "  public #MODEL# blankModel(Object portalUser) {\n"
      + "    #MODEL# ownerDetail = new #MODEL#();\n"
      + "    LoginUserUtil.getLoginUser();\n"
      + "    ownerDetail.getForm().setUser((PortalUser) portalUser);\n"
      + "    return ownerDetail;\n"
      + "  }\n"
      + "\n"
      + "  public #DTO# fromModel(#MODEL# model) {\n"
      + "    #DTO# dto = new #DTO#();\n"
      + "    dto.setId(model.getApplicationId());\n"
      + "        dto.setType(model.getApplicationType().getType());\n"
      + "        dto.setApplicationName(model.getApplicationType().getName());\n"
      + "    return dto;\n"
      + "  }\n"
      + "\n"
      + "  public NG#MODEL#DTO toNGDTO(#MODEL# model) {\n"
      + "    NG#MODEL#DTO dto = new NG#MODEL#DTO();\n"
      + "    dto.setApplicationId(model.getForm().getUniqueId());\n"
      + "    dto.setApplicationName(model.getApplicationType().getName());\n"
      + "    dto.setType(model.getApplicationType().getType());\n"
      + "    dto.setStatus(model.getForm().getFileStatus().name());\n"
      + "    return dto;\n"
      + "  }\n"
      + "}";

  private static String MANAGER = "package nst.app.manager;\n"
      + "\n"
      + "import java.util.List;\n"
      + "import nst.app.common.CEIBaseManager;\n"
      + "import nst.app.enums.FileStatus;\n"
      + "import nst.app.model.forms.lb.#MODEL#;\n"
      + "import nst.app.repo.#MODEL#Repository;\n"
      + "import nst.common.base.BaseRepository;\n"
      + "import nst.util.LoginUserUtil;\n"
      + "import org.springframework.beans.factory.annotation.Autowired;\n"
      + "import org.springframework.stereotype.Component;\n"
      + "\n"
      + "@Component\n"
      + "public class #MODEL#Manager extends CEIBaseManager<#MODEL#> {\n"
      + "\n"
      + "  @Autowired\n"
      + "  #MODEL#Repository repository;\n"
      + "\n"
      + "  @Override\n"
      + "  public BaseRepository<#MODEL#> getRepository() {\n"
      + "    return repository;\n"
      + "  }\n"
      + "\n"
      + "  @Override\n"
      + "  public #MODEL# submitForm(#MODEL# t) {\n"
      + "    t.getForm().setFileStatus(FileStatus.SUBMITTED);\n"
      + "    return repository.save(t);\n"
      + "  }\n"
      + "\n"
      + "}";

  private static String REPO = "package nst.app.repo;\n"
      + "\n"
      + "import java.util.List;\n"
      + "import nst.app.common.CEIBaseRepository;\n"
      + "import nst.app.model.forms.lb.#MODEL#;\n"
      + "import org.springframework.data.jpa.repository.Query;\n"
      + "import org.springframework.stereotype.Repository;\n"
      + "\n"
      + "@Repository\n"
      + "public interface #MODEL#Repository extends\n"
      + "    CEIBaseRepository<#MODEL#> {\n"
      + "}";

  private static String SERVICE = "package nst.app.service;\n"
      + "\n"
      + "import java.util.List;\n"
      + "import nst.app.common.CEIBaseService;\n"
      + "import nst.app.dto.#DTO#;\n"
      + "import nst.app.helper.#MODEL#Helper;\n"
      + "import nst.app.manager.#MODEL#Manager;\n"
      + "import nst.app.model.forms.lb.#MODEL#;\n"
      + "import nst.common.base.BaseHelper;\n"
      + "import nst.common.base.BaseManager;\n"
      + "import org.springframework.beans.factory.annotation.Autowired;\n"
      + "import org.springframework.stereotype.Component;\n"
      + "import org.springframework.transaction.annotation.Transactional;\n"
      + "\n"
      + "@Component\n"
      + "@Transactional\n"
      + "public class #MODEL#Service extends\n"
      + "    CEIBaseService<#MODEL#, #DTO#> {\n"
      + "\n"
      + "  @Autowired\n"
      + "  #MODEL#Manager manager;\n"
      + "\n"
      + "  @Autowired\n"
      + "  #MODEL#Helper helper;\n"
      + "\n"
      + "  @Override\n"
      + "  public BaseManager<#MODEL#> getManager() {\n"
      + "    return manager;\n"
      + "  }\n"
      + "\n"
      + "  @Override\n"
      + "  public BaseHelper<#MODEL#, #DTO#> getHelper() {\n"
      + "    return helper;\n"
      + "  }\n"
      + "}";

}
