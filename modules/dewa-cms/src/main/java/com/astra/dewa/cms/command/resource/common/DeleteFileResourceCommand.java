package com.astra.dewa.cms.command.resource.common;

import com.astra.dewa.cms.constants.CmsKeys;
import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.JSONResponseFormatUtil;
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
        property = {
                "javax.portlet.name=" + CmsPortletKeys.DISKON_OTHERS,
                "mvc.command.name=" + CmsKeys.COMMON_PATH + "/resource/delete_file"
        },
        service = MVCResourceCommand.class
)
public class DeleteFileResourceCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        // response
        int acknowledge = 0;
        String status = "success";
        String message = "";
        // param
        long fileId = ParamUtil.getLong(resourceRequest, "fileId", -1);

        try {
            FileUtil.deleteFile(fileId);
            acknowledge = 1;
        } catch (Exception e) {
            status = "error";
            message = "Gagal menghapus data";
            _log.error(e.getMessage(), e);
        }
        JSONObject jsonObject = JSONResponseFormatUtil.getCommonFormat(acknowledge, status, message);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }
}
