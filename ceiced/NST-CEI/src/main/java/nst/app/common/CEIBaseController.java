package nst.app.common;

import nst.app.dto.ApplicationQueryDTO;
import nst.app.dto.LicenseSearchDTO;
import nst.common.AbstractController;
import nst.common.base.BaseModel;
import nst.common.base.BaseModelDTO;
import nst.common.base.BaseResponse;
import nst.kernal.SystemConstants;
import nst.util.JSONUtil;
import org.springframework.web.bind.annotation.*;

public abstract class CEIBaseController<M extends BaseModel, P extends BaseModelDTO> extends
    AbstractController {

  private Class<P> dtoType;

  public CEIBaseController() {
  }

  public CEIBaseController(Class<P> dtoType) {
    this.dtoType = dtoType;
  }

  public abstract CEIBaseService<M, P> getService();

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public BaseResponse create() {
    return BaseResponse.createSuccess(performCreate());
  }

  @RequestMapping(value = "/duplicate", method = RequestMethod.GET)
  public BaseResponse duplicate(@RequestParam(value = "identifier1", defaultValue = "") String identifier1,
                             @RequestParam(value = "identifier2", defaultValue = "") String identifier2) {
    return BaseResponse.createSuccess(performDuplicate(identifier1,identifier2));
  }

  @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
  public BaseResponse get(@PathVariable("id") Long id) {
    return performGet(id);
  }

  @RequestMapping(value = "/getQuery/{id}", method = RequestMethod.GET)
  public BaseResponse getQuery(@PathVariable("id") Long id) {
    return performGetQuery(id);
  }

  @RequestMapping(value = "/getComments/{id}", method = RequestMethod.GET)
  public BaseResponse getComments(@PathVariable("id") Long id) {
    return performGetComments(id);
  }

  @RequestMapping(value = "/getAllQueries/{id}", method = RequestMethod.GET)
  public BaseResponse getAllQueries(@PathVariable("id") Long id) {
    return performGetAllQueries(id);
  }

  @RequestMapping(value = "/getRepliedQueries/{id}", method = RequestMethod.GET)
  public BaseResponse getRepliedQueries(@PathVariable("id") Long id) {
    return performGetRepliedQueries(id);
  }

  @RequestMapping(value = "/saveQuery", method = RequestMethod.POST, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse saveQuery(@RequestBody String body) {
    return performSaveQuery(JSONUtil.getDataDTO(body, ApplicationQueryDTO.class));
  }

  @RequestMapping(value = "/submitQuery/{applicationId}/{queryId}", method = RequestMethod.POST)
  public BaseResponse submitQuery(@PathVariable("applicationId") Long applicationId, @PathVariable("queryId") Long queryId) {
    return performSubmitQuery(applicationId, queryId);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public BaseResponse delete(@PathVariable("id") Long id) {
    return performDelete(id);
  }

  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public BaseResponse getAll() {
    return performGetMyForm();
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse save(@RequestBody String body) {
    return performSave(JSONUtil.getDataDTO(body, dtoType));
  }

  @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse search(@RequestBody String body) {
    return performSearch(JSONUtil.getDataDTO(body, LicenseSearchDTO.class));
  }

  @RequestMapping(value = "/submit/{id}", method = RequestMethod.POST, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse submitForm(@PathVariable("id") Long id) {
    return performSubmitForm(id);
  }

  @RequestMapping(value = "/processPayment/{id}", method = RequestMethod.POST, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse processPayment(@PathVariable("id") Long id) {
    return performProcessPayment(id);
  }

  @RequestMapping(value = "/submit", method = RequestMethod.POST, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse submitForm(@RequestBody String body) {
    return performSubmitForm(JSONUtil.getDataDTO(body, dtoType));
  }

  @RequestMapping(value = "/json/{id}", method = RequestMethod.GET)
  public BaseResponse json(@PathVariable("id") Long id) {
    return performJsonGet(id);
  }

  @RequestMapping(value = "/printView/{id}", method = RequestMethod.GET, produces = "text/html")
  public String printView(@PathVariable("id") Long id) {
    return performPrintView(id);
  }

  private BaseResponse performJsonGet(Long id) {
    return BaseResponse.createSuccess(getService().getJson(id));
  }

  private String performPrintView(Long id) {
    return getService().getPrintView(id);
  }

  private P performCreate() {
    return getService().create();
  }

  private P performDuplicate(String identifier1,String identifier2) {
    return getService().duplicate(identifier1, identifier2);
  }

  private BaseResponse performSearch(LicenseSearchDTO p) {
    return BaseResponse.createSuccess(getService().search(p));
  }

  private BaseResponse performProcessPayment(Long id) {
    return BaseResponse.createSuccess(getService().processPayment(id));
  }

  private BaseResponse performSubmitForm(Long id) {
    return BaseResponse.createSuccess(getService().submitForm(id));
  }

  private BaseResponse performSubmitForm(P p) {
    p.validateAll();
    return BaseResponse.createSuccess(getService().submitForm(p));
  }

  protected BaseResponse performGet(Long id) {
    return BaseResponse.createSuccess(getService().get(id));
  }

  protected BaseResponse performGetComments(Long id) {
    return BaseResponse.createSuccess(getService().getComments(id));
  }

  private BaseResponse performSubmitQuery(Long applicationId, Long queryId) {
    getService().submitQuery(applicationId, queryId);
    return BaseResponse.createSuccess();
  }

  protected BaseResponse performGetQuery(Long id) {
    return BaseResponse.createSuccess(getService().getLatestQuery(id));
  }

  protected BaseResponse performGetAllQueries(Long id) {
    return BaseResponse.createSuccess(getService().getAllQueries(id));
  }

  protected BaseResponse performGetRepliedQueries(Long id) {
    return BaseResponse.createSuccess(getService().getRepliedQueries(id));
  }

  protected BaseResponse performSaveQuery(ApplicationQueryDTO dto) {
    return BaseResponse.createSuccess(getService().saveQuery(dto));
  }

  protected BaseResponse performDelete(Long id) {
    getService().delete(id);
    return BaseResponse.createSuccess();
  }

  protected BaseResponse performGetMyForm() {
    return BaseResponse.createSuccess(getService().getMyForms());
  }

  protected BaseResponse performSave(P p) {
    p.validateBasic();
    return BaseResponse.createSuccess(getService().saveForm(p));
  }
}