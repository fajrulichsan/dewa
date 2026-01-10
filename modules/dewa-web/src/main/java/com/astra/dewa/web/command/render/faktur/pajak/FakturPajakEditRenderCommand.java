package com.astra.dewa.web.command.render.faktur.pajak;

import com.astra.dewa.model.FakturPajak;
import com.astra.dewa.service.FakturPajakLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.text.SimpleDateFormat;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_PAJAK,
      "mvc.command.name=/faktur-pajak-edit"
   },
   service = MVCResourceCommand.class
)
public class FakturPajakEditRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(FakturPajakEditRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest portletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      int id = ParamUtil.getInteger(portletRequest, "entryId");
      JSONObject dto = JSONFactoryUtil.createJSONObject();

      if (id > 0) {
         try {
            // CSRF AUTHENTICATION
            AuthTokenUtil.checkCSRFToken(portletRequest, this.getClass().getName());

            FakturPajak fakturPajak = FakturPajakLocalServiceUtil.getFakturPajak(id);
            SimpleDateFormat sdf = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_DOT);
            String invoiceDate = sdf.format(fakturPajak.getInvoiceDate());

            dto.put("id", fakturPajak.getId());
            dto.put("dealerId", fakturPajak.getDealerId());
            dto.put("fileId", fakturPajak.getFileId());
            dto.put("fileName", fakturPajak.getFileName());
            dto.put("filePath", fakturPajak.getFilePath());
            dto.put("invoiceDate", invoiceDate);
            dto.put("keterangan", fakturPajak.getKeterangan());
         } catch (PortalException e) {
            if (e instanceof PrincipalException) {
               LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(resourceRequest));
               LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(resourceRequest, "p_auth", "none"), e);
            } else {
               LOG.error("Error fetching data: " + e.getMessage());
            }
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), dto.toString());
   }
}