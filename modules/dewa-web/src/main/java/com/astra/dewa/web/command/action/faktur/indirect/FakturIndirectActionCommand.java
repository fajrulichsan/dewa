package com.astra.dewa.web.command.action.faktur.indirect;

import com.astra.dewa.model.FakturIndirect;
import com.astra.dewa.service.FakturIndirectLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
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
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

/**
 * @author psmahmad1402
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_INDIRECT,
            "mvc.command.name=/faktur-indirect/action"
      },
      service = MVCResourceCommand.class
)
public class FakturIndirectActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(this.getClass().getName());
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
         // CSRF AUTHENTICATION
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else {
            int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

            FakturIndirect fakturIndirect = null;
            Date now = new Date();

            switch (crudType) {
               case CREATE:
                  fakturIndirect = FakturIndirectLocalServiceUtil.createFakturIndirect(0);
                  setData(fakturIndirect);
                  jsonObject = create(fakturIndirect, now);
                  break;
               case UPDATE:
                  fakturIndirect = FakturIndirectLocalServiceUtil.getFakturIndirect(entryId);
                  setData(fakturIndirect);
                  jsonObject = update(fakturIndirect, now);
                  break;
               case DELETE:
                  fakturIndirect = FakturIndirectLocalServiceUtil.getFakturIndirect(entryId);
                  jsonObject = delete(fakturIndirect, now);
                  break;
               default:
                  jsonObject = WARNING("Bad request!");
            }

            /*
            Date invoiceDate = null;
            Date uploadDate = new Date();

            if (!crudType.equalsIgnoreCase(DELETE)) {
               invoiceDate = DateUtil.stringToDate(invoiceDateParam, DewaWebKeys.DATE_FORMAT_DOT);
               uploadDateParam = DateUtil.dateToString(uploadDate);
            }

            FakturIndirect fakturIndirect = null;

            if (crudType.equalsIgnoreCase(CREATE)) {
               fakturIndirect = FakturIndirectLocalServiceUtil.createFakturIndirect(entryId);
               fakturIndirect.setFileId(fileId);
            } else {
               fakturIndirect = FakturIndirectLocalServiceUtil.getFakturIndirect(entryId);
            }

            fakturIndirect.setDealerId(dealerId);
            fakturIndirect.setFilePath(filePath);
            fakturIndirect.setInvoiceDate(invoiceDate);
            fakturIndirect.setKeterangan("-");
            fakturIndirect.setModifiedDate(new Date());
            fakturIndirect.setModifiedBy(user.getScreenName());
            fakturIndirect.setRowStatus(true);

            boolean isExist = isExist(invoiceDate, uploadDate);

            if (crudType.equalsIgnoreCase(DELETE)) {
               jsonObject = delete();
            } else {
               fileName = setNewFileName();
               fakturIndirect.setFileName(fileName);

               if (crudType.equalsIgnoreCase(CREATE)) {
                  jsonObject = create(fakturIndirect, isExist);
               } else if (crudType.equalsIgnoreCase(UPDATE)) {
                  jsonObject = update(fakturIndirect, isExist, fileId);
               }
            }
             */
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = WARNING("Unauthorized request!");
         } else if (e instanceof SystemException) {
            jsonObject = WARNING(e.getMessage());
         } else {
            jsonObject = WARNING("Bad request!");
         }
         LOG.error(e.getMessage(), e);
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   @SuppressWarnings("deprecation")
   private void setData(FakturIndirect fakturIndirect) throws SystemException, PortalException {
      int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
      String invoiceDateParam = ParamUtil.getString(uploadPortletRequest, "invoiceDate");
      long fileId = ParamUtil.getLong(uploadPortletRequest, "fakturIndirectFileId");

      try {
         DealerUtils.getDealerCode(dealerId);
      } catch (Exception e) {
         throw new SystemException("Dealer tidak terdaftar");
      }

      Date invoiceDate = DateUtil.stringToDate(invoiceDateParam, DewaWebKeys.DATE_FORMAT_DOT);
      if (Validator.isNull(invoiceDate)) {
         throw new SystemException("Maaf tanggal faktur yang diinput tidak valid");
      }
      int invoiceYear = invoiceDate.getYear() + 1900;

      LocalDate now = LocalDate.now();
      int minimumYear = now.getYear() - 1;
      int maximumYear = now.getYear() + 3;

      if (invoiceYear < minimumYear || invoiceYear > maximumYear) {
         throw new SystemException("Maaf tahun yang diinput tidak valid");
      }

      FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
      String fileName = fileEntry.getFileName().replaceAll("_[^_]+(?=\\.)", "");
      String filePath = themeDisplay.getPortalURL() +
            themeDisplay.getPathContext() +
            "/documents/" +
            themeDisplay.getScopeGroupId() +
            "/" + fileEntry.getFolderId() +
            "/" + fileEntry.getFileName();

      fileName = setNewFileName(dealerId, invoiceDateParam, fileName);
      fakturIndirect.setFileName(fileName);
      fakturIndirect.setFilePath(filePath);

      fakturIndirect.setFileId(fileId);
      fakturIndirect.setDealerId(dealerId);
      fakturIndirect.setInvoiceDate(invoiceDate);
      fakturIndirect.setKeterangan("-");
      fakturIndirect.setRowStatus(true);
   }

   private JSONObject create(FakturIndirect fakturIndirect, Date date) {
      try {
//         if (isExist) {
//            message = "Duplikat file di tanggal faktur dan tanggal upload yang sama!";
//            logStatus(0, fakturIndirect.getId(), CREATE, DewaWebKeys.FAKTUR_INDIRECT_MENU, dealerId, fileName, invoiceDateParam, uploadDateParam, message);
//            return ERROR(409, message);
//         }

         fakturIndirect.setCreatedDate(date);
         fakturIndirect.setCreatedBy(user.getScreenName());
         fakturIndirect.setModifiedDate(date);
         fakturIndirect.setModifiedBy(user.getScreenName());
         fakturIndirect.setUploadDate(date);
         FakturIndirectLocalServiceUtil.updateFakturIndirect(fakturIndirect);

         // FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, CmsKeys.FAKTUR_INDIRECT_MENU);

         return SUCCESS("Data berhasil disimpan!", String.valueOf(fakturIndirect.getId()));
      } catch (Exception e) {
         return NOT_SUCCESS("Gagal menyimpan data");
      }
   }

   private JSONObject update(FakturIndirect fakturIndirect, Date date) {
      try {
//         if (isExist) {
//            message = "Duplikat file di tanggal faktur dan tanggal upload yang sama!";
//            logStatus(0, fakturIndirect.getId(), UPDATE, DewaWebKeys.FAKTUR_INDIRECT_MENU, dealerId, fileName, invoiceDateParam, uploadDateParam, message);
//            return ERROR(409, message);
//         }
         fakturIndirect.setModifiedDate(date);
         fakturIndirect.setModifiedBy(user.getScreenName());
         fakturIndirect.setUploadDate(new Date());
         FakturIndirectLocalServiceUtil.updateFakturIndirect(fakturIndirect);

         // FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, CmsKeys.FAKTUR_INDIRECT_MENU);

         return SUCCESS("Data berhasil diubah!", String.valueOf(fakturIndirect.getId()));
      } catch (Exception e) {
         return NOT_SUCCESS("Gagal mengubah data");
      }
   }

   private JSONObject delete(FakturIndirect fakturIndirect, Date date) {
      try {
         fakturIndirect.setRowStatus(false);
         fakturIndirect.setModifiedDate(date);
         fakturIndirect.setModifiedBy(user.getScreenName());
         FakturIndirectLocalServiceUtil.updateFakturIndirect(fakturIndirect);
         return SUCCESS("Data berhasil dihapus!", String.valueOf(fakturIndirect.getId()));
      } catch (Exception e) {
         return NOT_SUCCESS("Gagal menghapus data");
      }
   }

   /*
   private boolean isExist(int entryId, int dealerId, Date invoiceDate, Date uploadDate) {
      Date uploadDateStart = null;
      Date uploadDateEnd = null;
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(uploadDate);
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      uploadDateStart = calendar.getTime();
      calendar.add(Calendar.DAY_OF_MONTH, 1);
      uploadDateEnd = calendar.getTime();

      DynamicQuery query = FakturIndirectLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
      query.add(RestrictionsFactoryUtil.eq("InvoiceDate", invoiceDate));
      query.add(RestrictionsFactoryUtil.ge("UploadDate", uploadDateStart));
      query.add(RestrictionsFactoryUtil.lt("UploadDate", uploadDateEnd));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<FakturIndirect> fakturIndirects = FakturIndirectLocalServiceUtil.dynamicQuery(query);
      if (!fakturIndirects.isEmpty()) {
         return !(fakturIndirects.get(0).getId() == entryId);
      }
      return false;
   }
   */

   private String setNewFileName(int dealerId, String invoiceDate, String fileName) {
      String dealerCode = DealerUtils.getDealerCode(dealerId);
      String newFileName = DewaWebKeys.REKAP_FAKTUR_KENDARAAN + "-" + dealerCode + "-" + invoiceDate + "." + FileUtil.getExtensionByStringHandling(fileName).get();
      return newFileName;
   }
}
