package com.astra.dewa.cms.command.render.upload.bonus;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.utils.ApplicationEnum;
import com.astra.dewa.utils.ApplicationHeaderStatusEnum;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.FileUtil;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
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
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.UPLOAD_BONUS,
            "mvc.command.name=upload-bonus-document-list-cms"
      },
      service = MVCResourceCommand.class
)
public class UploadBonusDocumentListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadBonusDocumentListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      String column = httpReq.getParameter("column");
      String keyword = httpReq.getParameter("keyword");
      String cPage = httpReq.getParameter("currentPage");
      String iPage = httpReq.getParameter("itemPerPage");
      int currentPage = 1;
      if (cPage != null) {
         currentPage = Integer.parseInt(cPage);
      }
      int itemPerPage = 12;
      if (iPage != null) {
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
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());

         DynamicQuery query = ApplicationHeaderLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("ApplicationId", ApplicationEnum.UPLOAD_BONUS.ordinal() + 1));

         // COUNTER
         DynamicQuery countQuery = ApplicationHeaderLocalServiceUtil.dynamicQuery();
         countQuery.add(RestrictionsFactoryUtil.eq("ApplicationId", ApplicationEnum.UPLOAD_BONUS.ordinal() + 1));

         query.add(RestrictionsFactoryUtil.ne("ApplicationHeaderStatusId", ApplicationHeaderStatusEnum.DRAFT.getId()));
         countQuery.add(RestrictionsFactoryUtil.ne("ApplicationHeaderStatusId", ApplicationHeaderStatusEnum.DRAFT.getId()));

         List<ApplicationHeader> bonuses = new ArrayList<>();

         // FILTERS
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

         query.add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .addOrder(OrderFactoryUtil.desc("ModifiedDate"));
         countQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));

         long recordsTotal = ApplicationHeaderLocalServiceUtil.dynamicQueryCount(countQuery);

         bonuses = ApplicationHeaderLocalServiceUtil.dynamicQuery(query, start, end);

         for (ApplicationHeader bonus : bonuses) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);

            // GENERAL INFORMATION
            if (bonus.getReqDate() != null) {
               dto.put("reqDate", simpleDateFormat.format(bonus.getReqDate()));
               dto.put("ticketHour", DateUtil.localDateTimeToStringHours(bonus.getReqDate()));
            } else {
               dto.put("reqDate", "-");
               dto.put("ticketHour", "-");
            }

            // ATTACHMENT
            dto.put("fileId", bonus.getFileId());
            if (bonus.getFileName() != null && !bonus.getFileName().isEmpty()) {
               dto.put("fileName", bonus.getFileName());
               dto.put("fileExtension", FileUtil.getExtensionByStringHandling(bonus.getFileName()).get());
            } else {
               dto.put("fileName", "-");
               dto.put("fileExtension", "-");
            }

            // FILE ENTRY
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(bonus.getFileId());
            String fileUrl = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/"
                  + themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileEntry.getFileName();
            dto.put("fileUrl", fileUrl);

            jsonData.put(dto);
         }
         JSONObject jsonObject = FORMAT(recordsTotal, recordsTotal, 1, jsonData);
         ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpReq));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpReq, "p_auth", "none"), e);
         } else {
            LOG.error(e.getMessage(), e);
         }
      }
   }
}