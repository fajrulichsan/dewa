package com.astra.dewa.web.command.action;

import com.astra.dewa.model.DiskonFakpol;
import com.astra.dewa.service.DiskonFakpolLocalServiceUtil;
import com.astra.dewa.utils.BulanUtils;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
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
      "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FAKPOL,
      "mvc.command.name=/diskon-fakpol-action"
   },
   service = MVCResourceCommand.class
)
public class DiskonFakpolActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DiskonFakpolActionCommand.class);
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

            DiskonFakpol diskonFakpol = null;
            Date now = new Date();

            switch (crudType) {
               case CREATE:
                  diskonFakpol = DiskonFakpolLocalServiceUtil.createDiskonFakpol(entryId);
                  setData(diskonFakpol);
                  jsonObject = create(diskonFakpol, now);
                  break;
               case UPDATE:
                  diskonFakpol = DiskonFakpolLocalServiceUtil.getDiskonFakpol(entryId);
                  setData(diskonFakpol);
                  jsonObject = update(diskonFakpol, now);
                  break;
               case DELETE:
                  diskonFakpol = DiskonFakpolLocalServiceUtil.getDiskonFakpol(entryId);
                  jsonObject = delete(diskonFakpol, now);
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

   private void setData(DiskonFakpol diskonFakpol) throws SystemException, PortalException {
      int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealer");
      int tahun = ParamUtil.getInteger(uploadPortletRequest, "tahun");
      String periode = ParamUtil.getString(uploadPortletRequest, "periode");
      long fileId = ParamUtil.getLong(uploadPortletRequest, "diskonFakpolFileId");

      try {
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

      if (!BulanUtils.isMonthExist(periode)) {
         throw new SystemException("Maaf periode yang diinput tidak valid");
      }

      FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
      String fileName = fileEntry.getFileName().replaceAll("_[^_]+(?=\\.)", "");
      String filePath = themeDisplay.getPortalURL() +
            themeDisplay.getPathContext() +
            "/documents/" +
            themeDisplay.getScopeGroupId() +
            "/" + fileEntry.getFolderId() +
            "/" + fileEntry.getFileName();

      fileName = setNewFileName(dealerId, tahun, periode, fileName);
      diskonFakpol.setFileName(fileName);
      diskonFakpol.setFilePath(filePath);

      diskonFakpol.setFileId(fileId);
      diskonFakpol.setDealerId(dealerId);
      diskonFakpol.setTahun(tahun);
      diskonFakpol.setPeriode(periode);
      diskonFakpol.setKeterangan("-");
      diskonFakpol.setRowStatus(true);
   }

   private JSONObject create(DiskonFakpol diskonFakpol, Date date) throws SystemException {
      try {
         diskonFakpol.setCreatedDate(date);
         diskonFakpol.setCreatedBy(user.getScreenName());
         diskonFakpol.setModifiedDate(date);
         diskonFakpol.setModifiedBy(user.getScreenName());
         DiskonFakpolLocalServiceUtil.updateDiskonFakpol(diskonFakpol);
         return SUCCESS("Data berhasil disimpan!", "");
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         throw new SystemException("Gagal menyimpan data!");
      }
   }

   private JSONObject update(DiskonFakpol diskonFakpol, Date date) throws SystemException {
      try {
         diskonFakpol.setModifiedDate(date);
         diskonFakpol.setModifiedBy(user.getScreenName());
         DiskonFakpolLocalServiceUtil.updateDiskonFakpol(diskonFakpol);
         return SUCCESS("Data berhasil diubah!", String.valueOf(diskonFakpol.getId()));
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         throw new SystemException("Gagal menyimpan data!");
      }
   }

   private JSONObject delete(DiskonFakpol diskonFakpol, Date date) {
      try {
         diskonFakpol.setRowStatus(false);
         diskonFakpol.setModifiedDate(date);
         diskonFakpol.setModifiedBy(user.getScreenName());
         DiskonFakpolLocalServiceUtil.updateDiskonFakpol(diskonFakpol);
         return SUCCESS("Data berhasil dihapus!", String.valueOf(diskonFakpol.getId()));
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         throw new SystemException("Gagal menghapus data!");
      }
   }

   private String setNewFileName(int dealerId, int tahun, String periode, String fileName) {
      String dealerCode = DealerUtils.getDealerCode(dealerId);
      String newFileName = DewaWebKeys.DISKON_FAKPOL_MENU + "-" + periode + "-" + tahun + "-" + dealerCode + "." + FileUtil.getExtensionByStringHandling(fileName).get();
      return newFileName;
   }
}