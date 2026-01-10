package com.astra.dewa.web.command.render.faktur.indirect.cms;

import com.astra.dewa.model.FakturIndirect;
import com.astra.dewa.service.FakturIndirectLocalServiceUtil;
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
      "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_INDIRECT,
      "mvc.command.name=/faktur-indirect/edit"
   },
   service = {MVCResourceCommand.class}
)
public class FakturIndirectEditRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(FakturIndirectEditRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest portletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      int id = ParamUtil.getInteger(portletRequest, "entryId");
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (id > 0) {
         try {
            AuthTokenUtil.checkCSRFToken(portletRequest, this.getClass().getName());

            FakturIndirect fakturIndirect = FakturIndirectLocalServiceUtil.getFakturIndirect(id);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_DOT);
            String invoiceDate = simpleDateFormat.format(fakturIndirect.getInvoiceDate());
            dto.put("id", fakturIndirect.getId());
            dto.put("dealerId", fakturIndirect.getDealerId());
            dto.put("fileId", fakturIndirect.getFileId());
            dto.put("fileName", fakturIndirect.getFileName());
            dto.put("filePath", fakturIndirect.getFilePath());
            dto.put("invoiceDate", invoiceDate);
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