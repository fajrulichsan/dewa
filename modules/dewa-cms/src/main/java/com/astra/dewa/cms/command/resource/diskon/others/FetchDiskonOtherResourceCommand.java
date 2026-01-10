package com.astra.dewa.cms.command.resource.diskon.others;

import com.astra.dewa.cms.constants.CmsKeys;
import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.DiskonOther;
import com.astra.dewa.service.DiskonOtherLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author psmahmad1402
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CmsPortletKeys.DISKON_OTHERS,
                "mvc.command.name=" + CmsKeys.CMS_PATH + CmsKeys.REALISASI_DISKON_PATH + "/others/fetch"
        },
        service = MVCResourceCommand.class
)
public class FetchDiskonOtherResourceCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        int entryId = ParamUtil.getInteger(resourceRequest, "entryId", -1);
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

        try {
            if (entryId > 0) {
                DiskonOther ed = DiskonOtherLocalServiceUtil.getDiskonOther(entryId);
                jsonObject.put("entryId", ed.getDiskonOtherId())
                        .put("dealerId", ed.getDealerId())
                        .put("tahun", ed.getTahun())
                        .put("kuartalId", ed.getKuartalId())
                        .put("diskonOthersFileId", ed.getFileId())
                        .put("diskonOthersFileName", ed.getFileName())
                        .put("diskonOthersFilePath", ed.getFilePath());
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }

        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toString());
    }
}
