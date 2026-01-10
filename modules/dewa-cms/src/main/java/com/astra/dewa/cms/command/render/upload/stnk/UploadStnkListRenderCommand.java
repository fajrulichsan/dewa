package com.astra.dewa.cms.command.render.upload.stnk;

import com.astra.dewa.cms.constants.CmsPortletKeys;

import com.astra.dewa.model.ApplicationCategory;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.ApplicationHeaderStatus;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.service.ApplicationAssignLocalServiceUtil;
import com.astra.dewa.service.ApplicationCategoryLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderStatusLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.utils.ApplicationAssignStatusEnum;
import com.astra.dewa.utils.ApplicationEnum;
import com.astra.dewa.utils.ApplicationHeaderStatusEnum;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.MasterApprovalUtils;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RoleUtils;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(immediate = true, property = {
      "javax.portlet.name=" + CmsPortletKeys.UPLOAD_STNK,
      "mvc.command.name=upload-stnk-list"
}, service = MVCResourceCommand.class)
public class UploadStnkListRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(UploadStnkListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      ServiceContext serviceContext = ServiceContextFactory.getInstance(request);
      User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

      boolean isDisplay = Boolean.parseBoolean(httpReq.getParameter("isDisplay"));

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
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      boolean isApprover = MasterApprovalUtils.isActiveApprover(ApplicationEnum.UPLOAD_STNK.ordinal() + 1,
            user.getUserId());
      boolean isInternalDSO = RoleDealerUtils.isInternalDSO(user.getUserId(),
            MasterApprovalUtils.getMasterApprovalRoleId(ApplicationEnum.UPLOAD_STNK.ordinal() + 1));

      // TICKET LIST
      if (isApprover || isInternalDSO || RoleDealerUtils.isSuperUser(user.getUserId())) {
         try {
            DynamicQuery query = ApplicationHeaderLocalServiceUtil.dynamicQuery();
            DynamicQuery countQuery = ApplicationHeaderLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("ApplicationId", ApplicationEnum.UPLOAD_STNK.ordinal() + 1));
            countQuery.add(RestrictionsFactoryUtil.eq("ApplicationId", ApplicationEnum.UPLOAD_STNK.ordinal() + 1));

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
                     query.add(
                           RestrictionsFactoryUtil.like("TicketNo", "%" + keyword + "%"));
                     countQuery.add(
                           RestrictionsFactoryUtil.like("TicketNo", "%" + keyword + "%"));
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
            if (newOrderColumn.equals(2)) {
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("ApplicationCategoryId"));
               } else {
                  query.addOrder(OrderFactoryUtil.desc("ApplicationCategoryId"));
               }
            } else if (newOrderColumn.equals(3)) {
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("ReqScreenName"));
               } else {
                  query.addOrder(OrderFactoryUtil.desc("ReqScreenName"));
               }
            } else if (newOrderColumn.equals(4)) {
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("DealerId"));
               } else {
                  query.addOrder(OrderFactoryUtil.desc("DealerId"));
               }
            } else if (newOrderColumn.equals(5)) {
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("TicketNo"));
               } else {
                  query.addOrder(OrderFactoryUtil.desc("TicketNo"));
               }
            } else if (newOrderColumn.equals(6)) {
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("ReqDate"));
               } else {
                  query.addOrder(OrderFactoryUtil.desc("ReqDate"));
               }
            } else if (newOrderColumn.equals(7)) {
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("ApplicationHeaderStatusId"));
               } else {
                  query.addOrder(OrderFactoryUtil.desc("ApplicationHeaderStatusId"));
               }
            }
            query
                  .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                  .addOrder(OrderFactoryUtil.desc("ModifiedDate"));
            countQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));

            int count = first;

            List<ApplicationHeader> applicationHeaderList = new ArrayList<>();

            // INBOX REQUEST
            if (!isDisplay) {
               if (isApprover || RoleUtils.isAdminDSO(RoleUtils.getUserRole(user.getUserId()))) {
                  query.add(RestrictionsFactoryUtil.in("ApplicationHeaderStatusId",
                        ApplicationHeaderStatusEnum.waitingStatusIdList()));
                  countQuery.add(RestrictionsFactoryUtil.in("ApplicationHeaderStatusId",
                        ApplicationHeaderStatusEnum.waitingStatusIdList()));

                  String jdbcDriver = PropsUtil.get("jdbc.dewa.driverClassName");
                  jdbcDriver = jdbcDriver.toLowerCase();

                  DynamicQuery applicationAssignQuery = ApplicationAssignLocalServiceUtil.dynamicQuery()
                        .add(RestrictionsFactoryUtil.eq("ProfileId", user.getUserId()))
                        .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                        .add(RestrictionsFactoryUtil.eq("ApplicationAssignStatusId",
                              ApplicationAssignStatusEnum.IN_REVIEW.getId()));

                  // ADD THIS QUERY INTO MYSQL QUERY
                  if (jdbcDriver.contains("mysql")) {
                     applicationAssignQuery.addOrder(OrderFactoryUtil.desc("Id"));
                  }
                  applicationAssignQuery.setProjection(ProjectionFactoryUtil.property("ApplicationHeaderId"));

                  // ADD THIS QUERY INTO MYSQL QUERY
                  if (jdbcDriver.contains("mysql")) {
                     DynamicQuery applicationHeaderSubQuery = ApplicationHeaderLocalServiceUtil.dynamicQuery()
                           .add(PropertyFactoryUtil.forName("Id").in(applicationAssignQuery))
                           .setProjection(ProjectionFactoryUtil.groupProperty("Id"))
                           .addOrder(OrderFactoryUtil.asc("Id"));
                     query.add(PropertyFactoryUtil.forName("Id").in(applicationHeaderSubQuery));
                     countQuery.add(PropertyFactoryUtil.forName("Id").in(applicationHeaderSubQuery));
                     applicationHeaderList = ApplicationHeaderLocalServiceUtil.dynamicQuery(query, first, end);
                  } else {
                     query.add(PropertyFactoryUtil.forName("Id").in(applicationAssignQuery))
                           .setProjection(
                                 ProjectionFactoryUtil.projectionList()
                                       .add(ProjectionFactoryUtil.groupProperty("Id"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("ApplicationId"),
                                             "ApplicationId"))
                                       .add(ProjectionFactoryUtil.alias(
                                             ProjectionFactoryUtil.max("ApplicationHeaderStatusId"),
                                             "ApplicationHeaderStatusId"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("ReqDate"),
                                             "ReqDate"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("TicketNo"),
                                             "TicketNo"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("ReqScreenName"),
                                             "ReqScreenName"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("ReqName"),
                                             "ReqName"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("DealerId"),
                                             "DealerId"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("ReqEmail"),
                                             "ReqEmail"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("ReqCCEmail"),
                                             "ReqCCEmail"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("ReqPhone"),
                                             "ReqPhone"))
                                       .add(ProjectionFactoryUtil.alias(
                                             ProjectionFactoryUtil.max("ApplicationCategoryId"),
                                             "ApplicationCategoryId"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("FileId"), "FileId"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("FileName"),
                                             "FileName"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("FileUrl"),
                                             "FileUrl"))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("Notes"), "Notes"))
                                       .add(ProjectionFactoryUtil.sqlProjection(
                                             "MAX(CAST(NotesHistory AS VARCHAR (MAX))) AS NotesHistory",
                                             new String[] { "NotesHistory" },
                                             new Type[] { Type.STRING }))
                                       .add(ProjectionFactoryUtil.alias(ProjectionFactoryUtil.max("ModifiedDate"),
                                             "ModifiedDate")));

                     countQuery.add(PropertyFactoryUtil.forName("Id").in(applicationAssignQuery))
                           .setProjection(ProjectionFactoryUtil.groupProperty("Id"));

                     List<Object[]> results = ApplicationHeaderLocalServiceUtil.dynamicQuery(query, first, end);
                     List<ApplicationHeader> convertedResult = new ArrayList<>();

                     for (Object[] result : results) {
                        ApplicationHeader applicationHeader = ApplicationHeaderLocalServiceUtil
                              .createApplicationHeader((Integer) result[0]);
                        applicationHeader.setApplicationId((Integer) result[1]);
                        applicationHeader.setApplicationHeaderStatusId((Integer) result[2]);
                        applicationHeader.setReqDate((Date) result[3]);
                        applicationHeader.setTicketNo((String) result[4]);
                        applicationHeader.setReqScreenName((String) result[5]);
                        applicationHeader.setReqName((String) result[6]);
                        applicationHeader.setDealerId((Integer) result[7]);
                        applicationHeader.setReqEmail((String) result[8]);
                        applicationHeader.setReqCCEmail((String) result[9]);
                        applicationHeader.setReqPhone((String) result[10]);
                        applicationHeader.setApplicationCategoryId((Integer) result[11]);
                        applicationHeader.setFileId((Long) result[12]);
                        applicationHeader.setFileName((String) result[13]);
                        applicationHeader.setFileUrl((String) result[14]);
                        applicationHeader.setNotes((String) result[15]);
                        applicationHeader.setNotesHistory((String) result[16]);
                        convertedResult.add(applicationHeader);
                     }
                     applicationHeaderList = convertedResult;
                  }
               }
            } else {
               query.add(RestrictionsFactoryUtil.ne("ApplicationHeaderStatusId",
                     ApplicationHeaderStatusEnum.DRAFT.getId()));
               countQuery.add(RestrictionsFactoryUtil.ne("ApplicationHeaderStatusId",
                     ApplicationHeaderStatusEnum.DRAFT.getId()));
               applicationHeaderList = ApplicationHeaderLocalServiceUtil.dynamicQuery(query, first, end);
            }

            for (ApplicationHeader stnk : applicationHeaderList) {
               count++;
               JSONObject dto = JSONFactoryUtil.createJSONObject();
               dto.put("no", count);
               dto.put("id", stnk.getId());
               dto.put("applicationId", stnk.getApplicationId());

               // GENERAL INFORMATION
               if (stnk.getReqDate() != null) {
                  dto.put("reqDate", stnk.getReqDate());
                  dto.put("reqHour", DateUtil.localDateTimeToStringHours(stnk.getReqDate()));
               } else {
                  dto.put("reqDate", "-");
                  dto.put("reqHour", "-");
               }
               dto.put("ticketNo", stnk.getTicketNo());
               dto.put("screenName", stnk.getReqScreenName());
               dto.put("reqName", stnk.getReqName());
               Dealer dealer = DealerLocalServiceUtil.getDealer(stnk.getDealerId());
               dto.put("dealerId", dealer.getId());
               dto.put("dealerCode", dealer.getCode());
               dto.put("dealerName", dealer.getName());
               dto.put("approverId", "-");
               dto.put("approverName", "-");

               // CONTACT INFORMATION
               dto.put("reqEmail", stnk.getReqEmail());
               dto.put("reqCCEmail", stnk.getReqCCEmail());
               dto.put("reqPhone", stnk.getReqPhone());

               // REQUEST INFORMATION
               ApplicationCategory requestBonus = ApplicationCategoryLocalServiceUtil
                     .getApplicationCategory(stnk.getApplicationCategoryId());
               dto.put("applicationCategoryId", requestBonus.getId());
               dto.put("applicationCategoryName", requestBonus.getName());
               dto.put("nominalPengajuan", stnk.getNominalPengajuan());
               dto.put("reqYearId", stnk.getReqYear());
               dto.put("reqDesc", stnk.getReqDesc());
               dto.put("businessBenefit", stnk.getBusinessBenefit());

               // ATTACHMENT
               dto.put("fileId", stnk.getFileId());
               dto.put("fileName", stnk.getFileName());
               dto.put("fileUrl", stnk.getFileUrl());

               // NOTES
               dto.put("notes", stnk.getNotes());
               dto.put("notesHistory", stnk.getNotesHistory());

               // APPLICATION HEADER STATUS
               ApplicationHeaderStatus statusBonus = ApplicationHeaderStatusLocalServiceUtil
                     .getApplicationHeaderStatus(stnk.getApplicationHeaderStatusId());
               dto.put("applicationHeaderStatusId", statusBonus.getId());
               dto.put("applicationHeaderStatusName", statusBonus.getName());
               jsonData.put(dto);
            }
            recordsTotal = count;
         } catch (Exception e) {
            log.error(e);
         }
      }
      JSONObject jsonObject = FORMAT(recordsTotal, recordsTotal, Integer.parseInt(draw), jsonData);
      jsonObject.put("isApprover", isApprover);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}