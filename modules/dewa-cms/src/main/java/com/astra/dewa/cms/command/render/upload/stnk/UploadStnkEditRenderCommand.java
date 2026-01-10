package com.astra.dewa.cms.command.render.upload.stnk;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.ApplicationCategory;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.ApplicationHeaderStatus;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.ApplicationCategoryLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderStatusLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RolesEnum;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + CmsPortletKeys.UPLOAD_STNK,
      "mvc.command.name=upload-stnk-edit"
   },
   service = {MVCRenderCommand.class}
)
public class UploadStnkEditRenderCommand implements MVCRenderCommand {

   private final Log log = LogFactoryUtil.getLog(UploadStnkEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
      long userId = themeDisplay.getUser().getUserId();
      String screenName = themeDisplay.getUser().getScreenName();
      String fullName = themeDisplay.getUser().getFullName();
      String email = themeDisplay.getUser().getEmailAddress();

      UsersDealers roleDealer = RoleDealerUtils.userId(userId);

      HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
      String id = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("id");

      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      int headerId = Integer.parseInt(id);
      if (headerId > 0){
         action="update";
         try {
            ApplicationHeader header = ApplicationHeaderLocalServiceUtil.getApplicationHeader(headerId);
            dto.put("id", header.getId());
            // General Information
            if(header.getReqDate() != null) {
               dto.put("ticketDate", DateUtil.dateToString(header.getReqDate()));
               dto.put("ticketHour", DateUtil.localDateTimeToStringHours(header.getReqDate()));
            } else {
               dto.put("ticketDate", "-");
               dto.put("ticketHour", "-");
            }
            dto.put("ticketNumber", header.getTicketNo());
            dto.put("userId", header.getReqScreenName());
            dto.put("userName", header.getReqName());
            if(header.getDealerId() > 0) {
               Dealer dealer = DealerLocalServiceUtil.getDealer(header.getDealerId());
               dto.put("dealerId", dealer.getId());
               dto.put("dealerName", dealer.getName());
            } else {
               dto.put("dealerId", "-");
               dto.put("dealerName", "-");
            }
            dto.put("approverId", "-");
            dto.put("approverName", "-");
            // Contact Information
            dto.put("email", header.getReqEmail());
            dto.put("emailCC", header.getReqCCEmail());
            dto.put("phone", header.getReqPhone());
            // Request Information
            ApplicationCategory category = ApplicationCategoryLocalServiceUtil.getApplicationCategory(header.getApplicationCategoryId());
            dto.put("requestBonusId", category.getId());
            dto.put("requestBonusName", category.getName());
            dto.put("nominalPengajuan", header.getNominalPengajuan());
            dto.put("reqYearId", header.getReqYear());
            dto.put("requestDescription", header.getReqDesc());
            dto.put("businessBenefit", header.getBusinessBenefit());
            // Attachment
            dto.put("attachmentId", header.getFileId());
            dto.put("attachmentName", header.getFileName());
            dto.put("attachmentPath", header.getFileUrl());
            // Notes
            dto.put("notes", header.getNotes());
            dto.put("notesHistory", header.getNotesHistory());
            ApplicationHeaderStatus status = ApplicationHeaderStatusLocalServiceUtil.getApplicationHeaderStatus(header.getApplicationHeaderStatusId());
            dto.put("statusBonusId", status.getId());
            dto.put("statusBonusName", status.getName());
         } catch (PortalException e) {
            log.error(e.getMessage(), e);
            action="invalid";
         }
      } else {
         action="create";
         dto.put("userId", screenName);
         dto.put("userName", fullName);
         dto.put("email", email);
         assert roleDealer != null;
         if(roleDealer.getRoleId() == RolesEnum.DSO.ordinal()) {
            dto.put("dealerId", "-");
            dto.put("dealerName", "-");
            dto.put("approverName", "-");
         } else {
            try {
               Dealer dealer = DealerLocalServiceUtil.getDealer(roleDealer.getDealerId());
               dto.put("dealerId", dealer.getCode());
               dto.put("dealerName", dealer.getName());
               // get DSO
               DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery();
               query.add(RestrictionsFactoryUtil.eq("RoleId", RolesEnum.DSO.ordinal()));
               query.add(RestrictionsFactoryUtil.eq("IsDefault", true));
               query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
               query.addOrder(OrderFactoryUtil.asc("Ordinal"));
               List<UsersDealers> userDso = UsersDealersLocalServiceUtil.dynamicQuery(query);
               if(!userDso.isEmpty()) {
                  User user = UserLocalServiceUtil.getUserById(userDso.get(0).getUserId());
                  dto.put("approverName", user.getFullName());
               } else {
                  dto.put("approverName", "-");
               }
            } catch (PortalException e) {
               log.error(e);
            }
         }
      }
      request.setAttribute("data",  dto);
      request.setAttribute("action",  action);
      return "/upload/stnk/form.jsp";
   }

}