package com.astra.dewa.web.command.action;

import com.astra.dewa.model.DiskonTestCar;
import com.astra.dewa.service.DiskonTestCarLocalServiceUtil;
import com.astra.dewa.service.TipeKendaraanLocalServiceUtil;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.KuartalUtils;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.TipeKendaraanUtils;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.time.LocalDate;
import java.util.Date;
import java.util.Enumeration;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;
import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;

/**
 * @author psmahmad1402
 */
@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.DISKON_TEST_CAR,
      "mvc.command.name=/diskon-test-car-action"
   },
   service = MVCResourceCommand.class
)
public class DiskonTestCarActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DiskonTestCarActionCommand.class);
   private UploadPortletRequest uploadPortletRequest;
   private ThemeDisplay themeDisplay;
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      this.themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      this.user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

      // XSS payload
      boolean isRequestContainsXSS = false;
      Enumeration<String> attributes = resourceRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = resourceRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadPortletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadPortletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

      try {
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else {
            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
            int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");

            DiskonTestCar diskonTestCar = null;
            Date now = new Date();

            switch (crudType) {
               case CREATE:
                  diskonTestCar = DiskonTestCarLocalServiceUtil.createDiskonTestCar(0);
                  setData(diskonTestCar);
                  jsonObject = create(diskonTestCar, now);
                  break;
               case UPDATE:
                  diskonTestCar = DiskonTestCarLocalServiceUtil.getDiskonTestCar(entryId);
                  setData(diskonTestCar);
                  jsonObject = update(diskonTestCar, now);
                  break;
               case DELETE:
                  diskonTestCar = DiskonTestCarLocalServiceUtil.getDiskonTestCar(entryId);
                  jsonObject = delete(diskonTestCar, now);
                  break;
               default:
                  jsonObject = WARNING("Bad request!");
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = WARNING("Unauthorized request!");
         } else if (e instanceof SystemException) {
            jsonObject = WARNING(e.getMessage());
         } else {
            jsonObject = WARNING("Internal Server Error");
         }
         LOG.error(e.getMessage(), e);
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private void setData(DiskonTestCar diskonTestCar) throws SystemException, PortalException {
      int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealer");
      int tahun = ParamUtil.getInteger(uploadPortletRequest, "tahun");
      String kuartalId = ParamUtil.getString(uploadPortletRequest, "kuartalId");
      int tipeKendaraanId = ParamUtil.getInteger(uploadPortletRequest, "tipeKendaraanId");
      long fileId = ParamUtil.getLong(uploadPortletRequest, "diskonTestCarFileId");

      try {
         DealerUtils.getDealerCode(dealerId);
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         throw new SystemException("Dealer tidak terdaftar");
      }

      try {
         TipeKendaraanLocalServiceUtil.getTipeKendaraan(tipeKendaraanId).getName();
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         throw new SystemException("Tipe Kendaraan tidak terdaftar");
      }

      LocalDate now = LocalDate.now();
      int minimumYear = now.getYear() - 1;
      int maximumYear = now.getYear() + 3;

      if (tahun < minimumYear || tahun > maximumYear) {
         throw new SystemException("Maaf tahun yang diinput tidak valid");
      }

      if (Validator.isNull(KuartalUtils.getKuartalName(kuartalId))) {
         throw new SystemException("Maaf kuartal yang diinput tidak valid");
      }

      FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
      String fileName = fileEntry.getFileName().replaceAll("_[^_]+(?=\\.)", "");
      String filePath = themeDisplay.getPortalURL() +
            themeDisplay.getPathContext() +
            "/documents/" +
            themeDisplay.getScopeGroupId() +
            "/" + fileEntry.getFolderId() +
            "/" + fileEntry.getFileName();

      fileName = setNewFileName(dealerId, tahun, kuartalId, tipeKendaraanId, fileName);
      diskonTestCar.setFileName(fileName);

      diskonTestCar.setFileId(fileId);
      diskonTestCar.setDealerId(dealerId);
      diskonTestCar.setFilePath(filePath);
      diskonTestCar.setTahun(tahun);
      diskonTestCar.setKuartalId(kuartalId);
      diskonTestCar.setTipeKendaraanId(tipeKendaraanId);
      diskonTestCar.setKeterangan("-");
      diskonTestCar.setRowStatus(true);
   }

   private JSONObject create(DiskonTestCar diskonTestCar, Date date) {
      try {
         diskonTestCar.setCreatedDate(date);
         diskonTestCar.setCreatedBy(user.getScreenName());
         diskonTestCar.setModifiedDate(date);
         diskonTestCar.setModifiedBy(user.getScreenName());
         DiskonTestCarLocalServiceUtil.updateDiskonTestCar(diskonTestCar);
         return SUCCESS("Data berhasil disimpan!", "");
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         return WARNING("Gagal menyimpan data");
      }
   }

   private JSONObject update(DiskonTestCar diskonTestCar, Date date) {
      try {
         diskonTestCar.setModifiedDate(date);
         diskonTestCar.setModifiedBy(user.getScreenName());
         DiskonTestCarLocalServiceUtil.updateDiskonTestCar(diskonTestCar);
         return SUCCESS("Data berhasil diubah!", "");
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         return WARNING("Gagal mengubah data");
      }
   }

   private JSONObject delete(DiskonTestCar diskonTestCar, Date date) {
      try {
         diskonTestCar.setRowStatus(false);
         diskonTestCar.setModifiedDate(date);
         diskonTestCar.setModifiedBy(user.getScreenName());
         DiskonTestCarLocalServiceUtil.updateDiskonTestCar(diskonTestCar);
         return SUCCESS("Data berhasil dihapus!", "");
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         return WARNING("Gagal menghapus data");
      }
   }

   private String setNewFileName(int dealerId, int tahun, String kuartalId, int tipeKendaraanId, String fileName) {
      String dealerCode = DealerUtils.getDealerCode(dealerId);
      String kuartalName = KuartalUtils.getKuartalName(kuartalId);
      String tipeKendaraanName = TipeKendaraanUtils.getTipeKendaraanName(tipeKendaraanId);
      String newFileName = DewaWebKeys.DISKON_TEST_CAR_MENU + " " + tipeKendaraanName + "-" + kuartalName + "-" + tahun + "-" + dealerCode + "." + FileUtil.getExtensionByStringHandling(fileName).get();
      return newFileName;
   }
}