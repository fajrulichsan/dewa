package com.astra.dewa.web.command.action;

import com.astra.dewa.model.FakturPajak;
import com.astra.dewa.service.FakturPajakLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.LogUtil.logStatus;
import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;

/**
 * @author psmahmad1402
 */
@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_PAJAK,
      "mvc.command.name=/faktur-pajak-action"
   },
   service = MVCResourceCommand.class
)
public class FakturPajakActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(FakturPajakActionCommand.class);

   private UploadPortletRequest uploadPortletRequest;
   private User user;
   private int entryId;
   private String fileName;
   private int dealerId;
   private String invoiceDateParam;
   private String uploadDateParam;
   private String message;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
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
         // CSRF AUTHENTICATION
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else {
            this.entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
            this.dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
            this.invoiceDateParam = ParamUtil.getString(uploadPortletRequest, "invoiceDate");
            long fileId = ParamUtil.getLong(uploadPortletRequest, "fakturPajakFileId");
            this.fileName = ParamUtil.getString(uploadPortletRequest, "fakturPajakFileName");
            String filePath = ParamUtil.getString(uploadPortletRequest, "fakturPajakFilePath");
            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

            Date invoiceDate = new Date();
            Date uploadDate = new Date();

            if (!crudType.equalsIgnoreCase(DELETE)) {
               invoiceDate = DateUtil.stringToDate(invoiceDateParam, DewaWebKeys.DATE_FORMAT_DOT);
               uploadDateParam = DateUtil.dateToString(uploadDate);
            }

            FakturPajak fakturPajak = null;

            if (crudType.equalsIgnoreCase(CREATE)) {
               fakturPajak = FakturPajakLocalServiceUtil.createFakturPajak(entryId);
               fakturPajak.setFileId(fileId);
            } else {
               fakturPajak = FakturPajakLocalServiceUtil.getFakturPajak(entryId);
            }

            fakturPajak.setDealerId(dealerId);
            fakturPajak.setFilePath(filePath);
            fakturPajak.setInvoiceDate(invoiceDate);
            fakturPajak.setKeterangan("-");
            fakturPajak.setRowStatus(true);

            if (crudType.equalsIgnoreCase(DELETE)) {
               jsonObject = delete();
            } else {
               fileName = setNewFileName();
               fakturPajak.setFileName(fileName);

               if (crudType.equalsIgnoreCase(CREATE)) {
                  jsonObject = create(fakturPajak);
               } else if (crudType.equalsIgnoreCase(UPDATE)) {
                  jsonObject = update(fakturPajak);
               }
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(resourceRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(resourceRequest, "p_auth", "none"), e);
            message = "Unauthorized request!";
         } else {
            LOG.error(e);
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(FakturPajak fakturPajak) {
      try {
         Date now = new Date();
         fakturPajak.setCreatedDate(now);
         fakturPajak.setCreatedBy(user.getScreenName());
         fakturPajak.setModifiedDate(now);
         fakturPajak.setModifiedBy(user.getScreenName());
         fakturPajak.setUploadDate(now);

         FakturPajakLocalServiceUtil.updateFakturPajak(fakturPajak);
         entryId = FakturPajakLocalServiceUtil.getFakturPajaksCount();
         logStatus(1, entryId, CREATE, DewaWebKeys.FAKTUR_PAJAK_MENU, dealerId, fileName, invoiceDateParam, uploadDateParam, "");
         return SUCCESS("Data berhasil disimpan!", String.valueOf(fakturPajak.getId()));
      } catch (Exception e) {
         message = e.getMessage();
         logStatus(0, entryId, CREATE, DewaWebKeys.FAKTUR_PAJAK_MENU, dealerId, fileName, invoiceDateParam, uploadDateParam, message);
         return NOT_SUCCESS(message);
      }
   }

   private JSONObject update(FakturPajak fakturPajak) {
      try {
         Date now = new Date();
         fakturPajak.setModifiedDate(now);
         fakturPajak.setModifiedBy(user.getScreenName());
         fakturPajak.setUploadDate(now);

         FakturPajakLocalServiceUtil.updateFakturPajak(fakturPajak);
         logStatus(1, entryId, UPDATE, DewaWebKeys.FAKTUR_PAJAK_MENU, dealerId, fileName, invoiceDateParam, uploadDateParam, "");
         return SUCCESS("Data berhasil diubah!", String.valueOf(fakturPajak.getId()));
      } catch (Exception e) {
         message = e.getMessage();
         logStatus(0, entryId, UPDATE, DewaWebKeys.FAKTUR_PAJAK_MENU, dealerId, fileName, invoiceDateParam, uploadDateParam, message);
         return NOT_SUCCESS(message);
      }
   }

   private JSONObject delete() {
      try {
         int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
         Date now = new Date();
         FakturPajak fakturPajak = FakturPajakLocalServiceUtil.getFakturPajak(entryId);
         fakturPajak.setModifiedDate(now);
         fakturPajak.setModifiedBy(user.getScreenName());
         fakturPajak.setRowStatus(false);
         FakturPajakLocalServiceUtil.updateFakturPajak(fakturPajak);
         fileName = fakturPajak.getFileName();
         logStatus(1, entryId, DELETE, DewaWebKeys.FAKTUR_PAJAK_MENU, dealerId, fileName, "");
         return SUCCESS("Data berhasil dihapus!", String.valueOf(fakturPajak.getId()));
      } catch (Exception e) {
         message = e.getMessage();
         logStatus(0, entryId, DELETE, DewaWebKeys.FAKTUR_PAJAK_MENU, dealerId, fileName, message);
         return NOT_SUCCESS(message);
      }
   }

   private String setNewFileName() {
      String dealerCode = DealerUtils.getDealerCode(dealerId);
      String newFileName = DewaWebKeys.REKAP_FAKTUR_PAJAK + "-" + dealerCode + "-" + invoiceDateParam + "." + FileUtil.getExtensionByStringHandling(fileName).get();
      return newFileName;
   }

}
