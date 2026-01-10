package com.astra.dewa.web.command.render.upload.bonus;

import com.astra.dewa.model.ApplicationCategory;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.ApplicationHeaderStatus;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.service.ApplicationCategoryLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderStatusLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.utils.ApplicationEnum;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_BONUS_MONITORING,
            "mvc.command.name=upload-bonus-monitoring-list"
      },
      service = MVCResourceCommand.class
)
public class UploadBonusMonitoringListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadBonusMonitoringListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      String currentUrl = httpReq.getParameter("currentUrl");
      boolean isInboxRequest = false;
      boolean isDisplayRequest = false;

//      LOG.info("current Url" + currentUrl);

      if (currentUrl.contains("inbox-request")) {
         isInboxRequest = true;
         isDisplayRequest = false;
      } else if (currentUrl.contains("monitoring-request")) {
         isInboxRequest = false;
         isDisplayRequest = true;
      }

      String column = httpReq.getParameter("column");
      String keyword = httpReq.getParameter("keyword");
      String draw = httpReq.getParameter("draw");
      if (draw.isEmpty()) {
         draw = "0";
      }
      String start = httpReq.getParameter("start");
      if (start.isEmpty()) {
         start = "0";
      }
      String length = httpReq.getParameter("length");
      if (length.isEmpty()) {
         length = "0";
      }
      int first = Integer.parseInt(start);
      int end = first + Integer.parseInt(length);

      String orderColumn = httpReq.getParameter("order[0][column]");
      String orderDir = httpReq.getParameter("order[0][dir]");

      long recordsTotal = 0;
      int count = first;

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      try {
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());

         User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

//         boolean isAdmin = RoleUtils.isAdminDSO(RoleUtils.getUserRole(userId)) || RoleUtils.isDSO(RoleUtils.getUserRole(userId));
//         boolean isHoDealer = RoleUtils.isHODealer(RoleUtils.getUserRole(userId));

         boolean isSuperUser = RoleDealerUtils.isSuperUser(user.getUserId());

         DynamicQuery query = ApplicationHeaderLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("ApplicationId", ApplicationEnum.UPLOAD_BONUS.ordinal() + 1));

         DynamicQuery countQuery = ApplicationHeaderLocalServiceUtil.dynamicQuery();
         countQuery.add(RestrictionsFactoryUtil.eq("ApplicationId", ApplicationEnum.UPLOAD_BONUS.ordinal() + 1));

         List<ApplicationHeader> bonuses = new ArrayList<>();

         // Searching
         if (!column.equals("ALL") && !keyword.equals("ALL")) {
            switch (column) {
               case "REQUEST_CATEGORY":
                  query.add(RestrictionsFactoryUtil.eq("ApplicationCategoryId", Integer.parseInt(keyword)));
                  countQuery.add(RestrictionsFactoryUtil.eq("ApplicationCategoryId", Integer.parseInt(keyword)));
                  break;
               case "DEALER_NAME":
                  query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(keyword)));
                  countQuery.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(keyword)));
                  break;
               case "TICKET_NUMBER":
                  query.add(RestrictionsFactoryUtil.like("TicketNo", "%" + keyword + "%"));
                  countQuery.add(RestrictionsFactoryUtil.like("TicketNo", "%" + keyword + "%"));
                  break;
               case "TICKET_DATE":
                  LocalDate date = LocalDate.now();
                  Integer month = Integer.parseInt(keyword);
                  query.add(RestrictionsFactoryUtil.eq("ReqYear", date.getYear()));
                  countQuery.add(RestrictionsFactoryUtil.eq("ReqYear", date.getYear()));
                  query.add(RestrictionsFactoryUtil.eq("ReqMonth", month));
                  countQuery.add(RestrictionsFactoryUtil.eq("ReqMonth", month));
                  break;
               case "STATUS":
                  query.add(RestrictionsFactoryUtil.eq("ApplicationHeaderStatusId", Integer.parseInt(keyword)));
                  countQuery.add(RestrictionsFactoryUtil.eq("ApplicationHeaderStatusId", Integer.parseInt(keyword)));
                  break;
               case "TAHUN":
                  query.add(RestrictionsFactoryUtil.eq("ReqYear", Integer.parseInt(keyword)));
                  countQuery.add(RestrictionsFactoryUtil.eq("ReqYear", Integer.parseInt(keyword)));
                  break;
               default:
            }
         }
         // Order By
         Integer newOrderColumn = Integer.parseInt(orderColumn);
         if (newOrderColumn.equals(1)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("ApplicationCategoryId"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("ApplicationCategoryId"));
            }
         } else if (newOrderColumn.equals(2)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("ReqScreenName"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("ReqScreenName"));
            }
         } else if (newOrderColumn.equals(3)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("DealerId"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("DealerId"));
            }
         } else if (newOrderColumn.equals(4)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("TicketNo"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("TicketNo"));
            }
         } else if (newOrderColumn.equals(5)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("ReqDate"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("ReqDate"));
            }
         } else if (newOrderColumn.equals(6)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("ApplicationHeaderStatusId"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("ApplicationHeaderStatusId"));
            }
         }

         //is Ho dealer and not admin (inbox request)
         if (!isSuperUser && isInboxRequest) {
            query.add(RestrictionsFactoryUtil.eq("ReqScreenName", user.getScreenName()));
            query.add(RestrictionsFactoryUtil.eq("ApplicationHeaderStatusId", 4));

            countQuery.add(RestrictionsFactoryUtil.eq("ReqScreenName", user.getScreenName()));
            countQuery.add(RestrictionsFactoryUtil.eq("ApplicationHeaderStatusId", 4));
         }

         if (!isSuperUser && isDisplayRequest) {
            query.add(RestrictionsFactoryUtil.eq("ReqScreenName", user.getScreenName()));
            countQuery.add(RestrictionsFactoryUtil.eq("ReqScreenName", user.getScreenName()));
         }

         query.add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .addOrder(OrderFactoryUtil.desc("ModifiedDate"));

         countQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));

         bonuses = ApplicationHeaderLocalServiceUtil.dynamicQuery(query, first, end);

         recordsTotal = ApplicationHeaderLocalServiceUtil.dynamicQueryCount(countQuery);

         for (ApplicationHeader bonus : bonuses) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", bonus.getId());
            dto.put("applicationId", bonus.getApplicationId());

            // GENERAL INFORMATION
            if (bonus.getReqDate() != null) {
               dto.put("reqDate", bonus.getReqDate());
               dto.put("reqHour", DateUtil.localDateTimeToStringHours(bonus.getReqDate()));
            } else {
               dto.put("reqDate", "-");
               dto.put("reqHour", "-");
            }
            dto.put("ticketNo", bonus.getTicketNo());
            dto.put("screenName", bonus.getReqScreenName());
            dto.put("reqName", bonus.getReqName());

            if (bonus.getDealerId() > 0) {
               Dealer dealer = DealerLocalServiceUtil.getDealer(bonus.getDealerId());
               dto.put("dealerId", dealer.getId());
               dto.put("dealerCode", dealer.getCode());
               dto.put("dealerName", dealer.getName());
            } else {
               dto.put("dealerId", "-");
               dto.put("dealerCode", "-");
               dto.put("dealerName", "-");
            }
            dto.put("approverId", "-");
            dto.put("approverName", "-");

            // CONTACT INFORMATION
            dto.put("reqEmail", bonus.getReqEmail());
            dto.put("reqCCEmail", bonus.getReqCCEmail());
            dto.put("reqPhone", bonus.getReqPhone());

            // REQUEST INFORMATION
            ApplicationCategory requestBonus = ApplicationCategoryLocalServiceUtil.getApplicationCategory(bonus.getApplicationCategoryId());
            dto.put("applicationCategoryId", requestBonus.getId());
            dto.put("applicationCategoryName", requestBonus.getName());
            dto.put("nominalPengajuan", bonus.getNominalPengajuan());
            dto.put("reqYearId", bonus.getReqYear());
            dto.put("reqDesc", bonus.getReqDesc());
            dto.put("businessBenefit", bonus.getBusinessBenefit());

            // ATTACHMENT
            dto.put("fileId", bonus.getFileId());
            dto.put("fileName", bonus.getFileName());
            dto.put("fileUrl", bonus.getFileUrl());

            // NOTES
            dto.put("notes", bonus.getNotes());
            dto.put("notesHistory", bonus.getNotesHistory());

            // APPLICATION HEADER STATUS
            ApplicationHeaderStatus statusBonus = ApplicationHeaderStatusLocalServiceUtil.getApplicationHeaderStatus(bonus.getApplicationHeaderStatusId());
            dto.put("applicationHeaderStatusId", statusBonus.getId());
            dto.put("applicationHeaderStatusName", statusBonus.getName());
            jsonData.put(dto);
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpReq));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpReq, "p_auth", "none"), e);
         } else {
            LOG.error(e);
         }
      }
      JSONObject jsonObject = FORMAT(recordsTotal, recordsTotal, Integer.parseInt(draw), jsonData);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}