package com.astra.dewa.web.command.render.upload;

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
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_STNK_MONITORING,
      "mvc.command.name=upload-stnk-monitoring-list-card"
   },
   service = MVCResourceCommand.class
)
public class UploadStnkMonitoringListCardRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadStnkMonitoringListCardRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String column = httpReq.getParameter("column");
      String keyword = httpReq.getParameter("keyword");
      String cPage = httpReq.getParameter("currentPage");
      String iPage = httpReq.getParameter("itemPerPage");
      int currentPage = 1;
      if(cPage != null) {
         currentPage = Integer.parseInt(cPage);
      }
      int itemPerPage = 12;
      if(iPage != null) {
         itemPerPage = Integer.parseInt(iPage);
      }
      int start = (currentPage - 1) * itemPerPage;
      int end = currentPage * itemPerPage;
      // log.info("currentPage : " + currentPage + "\titemPerPage : " + itemPerPage + "\tstart " + start + "\tend : " + end)
      String pattern = "dd MMMM yyyy";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

      int count = 0;

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      try {
         // CSRF Authentication
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());

         DynamicQuery query = ApplicationHeaderLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("ApplicationId", ApplicationEnum.UPLOAD_STNK.ordinal() + 1));
         DynamicQuery countQuery = ApplicationHeaderLocalServiceUtil.dynamicQuery();
         countQuery.add(RestrictionsFactoryUtil.eq("ApplicationId", ApplicationEnum.UPLOAD_STNK.ordinal() + 1));
         // Searching
         if(!column.equals("ALL") && !keyword.equals("ALL")) {
            switch (column) {
               case "REQUEST_CATEGORY":
                  query.add(RestrictionsFactoryUtil.eq("ApplicationCategoryId", Integer.parseInt(keyword)));
                  countQuery.add(RestrictionsFactoryUtil.eq("ApplicationCategoryId", Integer.parseInt(keyword)));
                  break;
               case "DEALER_NAME":
                  query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(keyword)));
                  countQuery.add(RestrictionsFactoryUtil.eq("ApplicationCategoryId", Integer.parseInt(keyword)));
                  break;
               case "TICKET_NUMBER":
                  query.add(RestrictionsFactoryUtil.like("TicketNo", "%" + keyword + "%"));
                  countQuery.add(RestrictionsFactoryUtil.eq("ApplicationCategoryId", Integer.parseInt(keyword)));
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

         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
         List<ApplicationHeader> bonuses = ApplicationHeaderLocalServiceUtil.dynamicQuery(query, start, end);
         countQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         // kenapa pada page 2 -> recordsTotal = 0 ya?
         long recordsTotal = ApplicationHeaderLocalServiceUtil.dynamicQueryCount(countQuery);
         for (ApplicationHeader bonus : bonuses) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", bonus.getId());
            // General Information
            if(bonus.getReqDate() != null) {
               // dto.put("reqDate", bonus.getReqDate());
               dto.put("reqDate", simpleDateFormat.format(bonus.getReqDate()));
               dto.put("ticketHour", DateUtil.localDateTimeToStringHours(bonus.getReqDate()));
            } else {
               dto.put("reqDate", "-");
               dto.put("ticketHour", "-");
            }
            dto.put("ticketNo", bonus.getTicketNo());
            dto.put("screenName", bonus.getReqScreenName());
            dto.put("reqName", bonus.getReqName());
            if(bonus.getDealerId() > 0) {
               Dealer dealer = DealerLocalServiceUtil.getDealer(bonus.getDealerId());
               dto.put("dealerId", dealer.getId());
               dto.put("dealerCode", dealer.getCode());
               dto.put("dealerName", dealer.getName());
            } else {
               dto.put("dealerId", "-");
               dto.put("dealerCode", "-");
               dto.put("dealerName", "-");
            }
//            if(uploadBonus.getApproverId() != null) {
//               dto.put("approverId", uploadBonus.getApproverId());
//            } else {
               dto.put("approverId", "-");
               dto.put("approverName", "-");
//            }
            // Contact Information
            dto.put("reqEmail", bonus.getReqEmail());
            dto.put("reqCCEmail", bonus.getReqCCEmail());
            dto.put("reqPhone", bonus.getReqPhone());
            // Request Information
            ApplicationCategory requestBonus = ApplicationCategoryLocalServiceUtil.getApplicationCategory(bonus.getApplicationCategoryId());
            dto.put("applicationCategoryId", requestBonus.getId());
            dto.put("applicationCategoryName", requestBonus.getName());
            dto.put("nominalPengajuan", bonus.getNominalPengajuan());
            dto.put("reqYearId", bonus.getReqYear());
            dto.put("reqDesc", bonus.getReqDesc());
            dto.put("businessBenefit", bonus.getBusinessBenefit());
            // Attachment
            dto.put("fileId", bonus.getFileId());
            if(bonus.getFileName() != null && !bonus.getFileName().isEmpty()) {
               dto.put("fileName", bonus.getFileName());
               dto.put("fileUrl", bonus.getFileUrl());
               dto.put("fileExtension", FileUtil.getExtensionByStringHandling(bonus.getFileName()).get());
            } else {
               dto.put("fileName", "-");
               dto.put("fileUrl", "-");
               dto.put("fileExtension", "-");
            }
            // Notes
            dto.put("notes", bonus.getNotes());
            dto.put("notesHistory", bonus.getNotesHistory());
            if(bonus.getApplicationHeaderStatusId() > 0) {
               ApplicationHeaderStatus statusBonus = ApplicationHeaderStatusLocalServiceUtil.getApplicationHeaderStatus(bonus.getApplicationHeaderStatusId());
               dto.put("applicationHeaderStatusId", statusBonus.getId());
               dto.put("applicationHeaderStatusName", statusBonus.getName());
            } else {
               dto.put("applicationHeaderStatusId", "-");
               dto.put("applicationHeaderStatusName", "-");
            }
            jsonData.put(dto);
         }
         JSONObject jsonObject = FORMAT(recordsTotal, recordsTotal, 1, jsonData);
         ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpReq));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpReq, "p_auth", "none"), e);
         } else {
            LOG.error(e);
         }
      }
   }
}