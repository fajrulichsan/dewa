package com.astra.dewa.web.command.action;

import com.astra.dewa.model.DiskonFleet;
import com.astra.dewa.service.DiskonFleetLocalServiceUtil;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.KuartalUtils;
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

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FLEET,
      "mvc.command.name=/diskon-fleet-action"
   },
   service = MVCResourceCommand.class
)
public class DiskonFleetActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DiskonFleetActionCommand.class);
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

            DiskonFleet diskonFleet = null;
            Date now = new Date();

            switch (crudType) {
               case CREATE:
                  diskonFleet = DiskonFleetLocalServiceUtil.createDiskonFleet(0);
                  setData(diskonFleet);
                  jsonObject = create(diskonFleet, now);
                  break;
               case UPDATE:
                  diskonFleet = DiskonFleetLocalServiceUtil.getDiskonFleet(entryId);
                  setData(diskonFleet);
                  jsonObject = update(diskonFleet, now);
                  break;
               case DELETE:
                  diskonFleet = DiskonFleetLocalServiceUtil.getDiskonFleet(entryId);
                  jsonObject = delete(diskonFleet, now);
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

   private void setData(DiskonFleet diskonFleet) throws SystemException, PortalException {
      int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
      int tahun = ParamUtil.getInteger(uploadPortletRequest, "tahun");
      String kuartal = ParamUtil.getString(uploadPortletRequest, "kuartal");
      long fileId = ParamUtil.getLong(uploadPortletRequest, "diskonFleetFileId");

      try {
         LOG.debug("dealerId: " + dealerId);
         DealerUtils.getDealerCode(dealerId);
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         throw new SystemException("Dealer tidak terdaftar");
      }

      LocalDate now = LocalDate.now();
      int minimumYear = now.getYear() - 1;
      int maximumYear = now.getYear() + 3;

      if (tahun < minimumYear || tahun > maximumYear) {
         throw new SystemException("Maaf tahun yang diinput tidak valid");
      }

      if (Validator.isNull(KuartalUtils.getKuartalName(kuartal))) {
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

      fileName = setNewFileName(dealerId, tahun, kuartal, fileName);
      diskonFleet.setFileName(fileName);

      diskonFleet.setFileId(fileId);
      diskonFleet.setDealerId(dealerId);
      diskonFleet.setFilePath(filePath);
      diskonFleet.setTahun(tahun);
      diskonFleet.setKuartal(kuartal);
      diskonFleet.setKeterangan("-");
      diskonFleet.setRowStatus(true);
   }

   private JSONObject create(DiskonFleet diskonFleet, Date date) {
      try {
         diskonFleet.setCreatedDate(date);
         diskonFleet.setCreatedBy(user.getScreenName());
         diskonFleet.setModifiedDate(date);
         diskonFleet.setModifiedBy(user.getScreenName());
         DiskonFleetLocalServiceUtil.updateDiskonFleet(diskonFleet);
         return SUCCESS("Data berhasil disimpan!", String.valueOf(diskonFleet.getId()));
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         return WARNING("Gagal menyimpan data");
      }
   }

   private JSONObject update(DiskonFleet diskonFleet, Date date) {
      try {
         diskonFleet.setModifiedDate(date);
         diskonFleet.setModifiedBy(user.getScreenName());
         DiskonFleetLocalServiceUtil.updateDiskonFleet(diskonFleet);
         return SUCCESS("Data berhasil diubah!", String.valueOf(diskonFleet.getId()));
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         return WARNING("Gagal mengubah data");
      }
   }

   private JSONObject delete(DiskonFleet diskonFleet, Date date) {
      try {
         diskonFleet.setRowStatus(false);
         diskonFleet.setModifiedDate(date);
         diskonFleet.setModifiedBy(user.getScreenName());
         DiskonFleetLocalServiceUtil.updateDiskonFleet(diskonFleet);
         return SUCCESS("Data berhasil dihapus!", String.valueOf(diskonFleet.getId()));
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         return WARNING("Gagal menghapus data!");
      }
   }

   private String setNewFileName(int dealerId, int tahun, String kuartalId, String fileName) {
      String dealerCode = DealerUtils.getDealerCode(dealerId);
      String kuartalName = KuartalUtils.getKuartalName(kuartalId);
      String newFileName = DewaWebKeys.DISKON_FLEET_MENU + "-" + kuartalName + "-" + tahun + "-" + dealerCode + "." + FileUtil.getExtensionByStringHandling(fileName).get();
      return newFileName;
   }
}