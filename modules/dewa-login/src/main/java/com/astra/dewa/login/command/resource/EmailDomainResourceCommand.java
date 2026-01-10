package com.astra.dewa.login.command.resource;

import com.astra.dewa.login.constants.DewaLoginPortletKeys;
import com.astra.dewa.model.EmailDomain;
import com.astra.dewa.service.EmailDomainLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

/**
 * @author psmahmad1402
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaLoginPortletKeys.REGISTER,
            "mvc.command.name=/email-domains"
      },
      service = MVCResourceCommand.class
)
public class EmailDomainResourceCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(this.getClass().getName());

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      int acknowledge = 0;
      long count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;
      try {
         DynamicQuery query = EmailDomainLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.ne("DomainName", "ai.astra.co.id"))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<EmailDomain> result = EmailDomainLocalServiceUtil.dynamicQuery(query);
         result.forEach(data -> {
            JSONObject dto = JSONFactoryUtil.createJSONObject()
                  .put("id", data.getId())
                  .put("text", data.getDomainName());
            jsonData.put(dto);
         });
         acknowledge = 1;
         count = EmailDomainLocalServiceUtil.dynamicQueryCount(query);
         jsonMessage = SUCCESS(200, "OK");
      } catch (SystemException se) {
         LOG.error(se);
         jsonMessage = ERROR(500, se.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }
}
