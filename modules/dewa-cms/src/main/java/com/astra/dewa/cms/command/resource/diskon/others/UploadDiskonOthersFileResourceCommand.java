package com.astra.dewa.cms.command.resource.diskon.others;

import com.astra.dewa.cms.constants.CmsKeys;
import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.DealinkCustomValidationException;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.JSONResponseFormatUtil;
import com.astra.dewa.utils.KuartalUtils;
import com.astra.dewa.utils.TahunUtils;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @author psmahmad1402
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CmsPortletKeys.DISKON_OTHERS,
                "mvc.command.name=" + CmsKeys.CMS_PATH + CmsKeys.REALISASI_DISKON_PATH + "/others/upload_file"
        },
        service = MVCResourceCommand.class
)
public class UploadDiskonOthersFileResourceCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        // responses
        int acknowledge = 1;
        String status = "success";
        String message = "";
        // parameters
        int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId", -1);
        int tahun = ParamUtil.getInteger(uploadPortletRequest, "tahun", -1);
        int kuartalId = ParamUtil.getInteger(uploadPortletRequest, "kuartalId", -1);
        File file = uploadPortletRequest.getFile("file");
        String fileName = ParamUtil.getString(uploadPortletRequest, "fileName", "");
        // helpers
        HashMap<String, String> payload = new HashMap<>();

        try {
            // dealer
            Dealer dealer = DealerUtils.getDealer(dealerId, true);
            if (null == dealer) {
                throw new DealinkCustomValidationException("Dealer tidak valid");
            }
            // kuartal
            String kuartalName = KuartalUtils.getQuarterName(kuartalId);
            if (null == kuartalName) {
                throw new DealinkCustomValidationException("Kuartal tidak valid");
            }
            // tahun
            List<Integer> years = TahunUtils.yearList();
            if (!years.contains(tahun)) {
                throw new DealinkCustomValidationException("Tahun tidak valid");
            }
            //noinspection OptionalGetWithoutIsPresent
            fileName = CmsKeys.DISKON_OTHERS_MENU + "-" + kuartalName + "-" + tahun + "-" + dealer.getCode() + "." + FileUtil.getExtensionByStringHandling(fileName).get();
            payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, CmsKeys.DISKON_OTHERS_MENU);
        } catch (Exception e) {
            status = "error";
            message = "Gagal upload file";
            _log.error(e.getMessage(), e);
        }

        JSONObject jsonObject = JSONResponseFormatUtil.fileUploadResponseFormat(
                acknowledge,
                status,
                message,
                Long.parseLong(payload.get("ID")),
                payload.get("URL"),
                payload.get("fileName")
        );
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }
}
