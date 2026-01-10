package com.astra.dewa.utils.email;

import com.astra.dewa.model.Common;
import com.astra.dewa.model.MasterApprovalDetail;
import com.astra.dewa.utils.MailUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.HashMap;
import java.util.Map;

public class EmailUploadUtils {

    private static final Log log = LogFactoryUtil.getLog(EmailUploadUtils.class);

    private EmailUploadUtils() {
    }

    public static void sendEmailSubmitToDso(String groupTicket, String nomorTiket, String description, String benefit, String notes, long userId) {
        try {
            User user = null;
            try {
                user = UserLocalServiceUtil.getUser(userId);
            } catch (PortalException e) {
                log.error(e);
            }
            assert user != null;
            String[] reciepients = new String[]{user.getEmailAddress()};
            String[] ccs = new String[]{};

            Common common = MailTemplateUtils.getMailTemplate("UPLOAD_BONUS_APPROVER_SUBMIT");
            assert common != null;
            String subject = common.getCommonDesc2();
            String body = common.getCommonDesc1();

            HashMap<String, String> subjectNew = new HashMap<>();
            subjectNew.put("[NOMOR_TIKET]", nomorTiket);
            subjectNew.put("[GROUP_TIKET]", groupTicket);

            for (Map.Entry<String, String> set : subjectNew.entrySet()) {
                subject = subject.replace(set.getKey(), set.getValue());
            }

            HashMap<String, String> payload = new HashMap<>();
            payload.put("[NAMA_APPROVER]", user.getFullName());
            payload.put("[GROUP_TIKET]", groupTicket);
            payload.put("[NOMOR_TIKET]", nomorTiket);
            payload.put("[REQUEST_DESCRIPTION]", description);
            payload.put("[BUSINESS_BENEFIT]", benefit);
            payload.put("[NOTES]", notes);
            payload.put("[LINK_DIRECT]", groupTicket.equals(APIConstant.KWITANSI_BONUS) ? common.getCommonDesc3() : common.getCommonDesc4());

            for (Map.Entry<String, String> set : payload.entrySet()) {
                body = body.replace(set.getKey(), set.getValue());
            }

            MailUtil.sendEmail(reciepients, ccs, subject, body, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void sendEmailRejectToDso(String groupTicket, String nomorTiket, String description, String benefit, String notes, MasterApprovalDetail approver) {
        try {
            User user = null;
            try {
                user = UserLocalServiceUtil.getUser(approver.getUserId());
            } catch (PortalException e) {
                log.error(e);
            }
            assert user != null;
            String[] reciepients = new String[]{user.getEmailAddress()};
            String[] ccs = new String[]{};

            Common common = MailTemplateUtils.getMailTemplate("UPLOAD_BONUS_APPROVER_REJECT");
            assert common != null;
            String subject = common.getCommonDesc2();
            String body = common.getCommonDesc1();

            HashMap<String, String> subjectNew = new HashMap<>();
            subjectNew.put("[NOMOR_TIKET]", nomorTiket);
            subjectNew.put("[GROUP_TIKET]", groupTicket);

            for (Map.Entry<String, String> set : subjectNew.entrySet()) {
                subject = subject.replace(set.getKey(), set.getValue());
            }

            HashMap<String, String> payload = new HashMap<>();
            payload.put("[NAMA_APPROVER]", user.getFullName());
            payload.put("[GROUP_TIKET]", groupTicket);
            payload.put("[NOMOR_TIKET]", nomorTiket);
            payload.put("[REQUEST_DESCRIPTION]", description);
            payload.put("[BUSINESS_BENEFIT]", benefit);
            payload.put("[NOTES]", notes);

            for (Map.Entry<String, String> set : payload.entrySet()) {
                body = body.replace(set.getKey(), set.getValue());
            }

            MailUtil.sendEmail(reciepients, ccs, subject, body, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void sendEmailReviseToDso(String groupTicket, String nomorTiket, String description, String benefit, String notes, MasterApprovalDetail approver) {
        try {
            User user = null;
            try {
                user = UserLocalServiceUtil.getUser(approver.getUserId());
            } catch (PortalException e) {
                log.error(e);
            }
            assert user != null;
            String[] reciepients = new String[]{user.getEmailAddress()};
            String[] ccs = new String[]{};

            Common common = MailTemplateUtils.getMailTemplate("UPLOAD_BONUS_APPROVER_REVISE");
            assert common != null;
            String subject = common.getCommonDesc2();
            String body = common.getCommonDesc1();

            HashMap<String, String> subjectNew = new HashMap<>();
            subjectNew.put("[NOMOR_TIKET]", nomorTiket);
            subjectNew.put("[GROUP_TIKET]", groupTicket);

            for (Map.Entry<String, String> set : subjectNew.entrySet()) {
                subject = subject.replace(set.getKey(), set.getValue());
            }

            HashMap<String, String> payload = new HashMap<>();
            payload.put("[NAMA_APPROVER]", user.getFullName());
            payload.put("[GROUP_TIKET]", groupTicket);
            payload.put("[NOMOR_TIKET]", nomorTiket);
            payload.put("[REQUEST_DESCRIPTION]", description);
            payload.put("[BUSINESS_BENEFIT]", benefit);
            payload.put("[NOTES]", notes);

            for (Map.Entry<String, String> set : payload.entrySet()) {
                body = body.replace(set.getKey(), set.getValue());
            }

            MailUtil.sendEmail(reciepients, ccs, subject, body, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void sendEmailSubmitToHoDealer(String groupTicket, String hoDealerEmail, String hoDealerFullName, String nomorTiket, String description, String benefit, String notes) {
        try {
            String[] reciepients = new String[]{hoDealerEmail};
            String[] ccs = new String[]{};

            Common common = MailTemplateUtils.getMailTemplate("UPLOAD_BONUS_REQUESTER_SUBMIT");
            assert common != null;
            String subject = common.getCommonDesc2();
            String body = common.getCommonDesc1();

            HashMap<String, String> subjectNew = new HashMap<>();
            subjectNew.put("[NOMOR_TIKET]", nomorTiket);
            subjectNew.put("[GROUP_TIKET]", groupTicket);

            for (Map.Entry<String, String> set : subjectNew.entrySet()) {
                subject = subject.replace(set.getKey(), set.getValue());
            }

            HashMap<String, String> payload = new HashMap<>();
            payload.put("[NAMA_REQUESTER]", hoDealerFullName);
            payload.put("[GROUP_TIKET]", groupTicket);
            payload.put("[NOMOR_TIKET]", nomorTiket);
            payload.put("[REQUEST_DESCRIPTION]", description);
            payload.put("[BUSINESS_BENEFIT]", benefit);
            payload.put("[NOTES]", notes);

            for (Map.Entry<String, String> set : payload.entrySet()) {
                body = body.replace(set.getKey(), set.getValue());
            }

            MailUtil.sendEmail(reciepients, ccs, subject, body, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void sendEmailApprovedToHoDealer(String groupTicket, String hoDealerEmail, String hoDealerFullName, String nomorTiket, String description) {
        try {
            String[] reciepients = new String[]{hoDealerEmail};
            String[] ccs = new String[]{};

            Common common = MailTemplateUtils.getMailTemplate("UPLOAD_BONUS_REQUESTER_APPROVED");
            assert common != null;
            String subject = common.getCommonDesc2();
            String body = common.getCommonDesc1();

            HashMap<String, String> subjectNew = new HashMap<>();
            subjectNew.put("[NOMOR_TIKET]", nomorTiket);
            subjectNew.put("[GROUP_TIKET]", groupTicket);

            for (Map.Entry<String, String> set : subjectNew.entrySet()) {
                subject = subject.replace(set.getKey(), set.getValue());
            }

            HashMap<String, String> payload = new HashMap<>();
            payload.put("[NAMA_REQUESTER]", hoDealerFullName);
            payload.put("[GROUP_TIKET]", groupTicket);
            payload.put("[NOMOR_TIKET]", nomorTiket);
            payload.put("[REQUEST_DESCRIPTION]", description);

            for (Map.Entry<String, String> set : payload.entrySet()) {
                body = body.replace(set.getKey(), set.getValue());
            }

            MailUtil.sendEmail(reciepients, ccs, subject, body, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void sendEmailRejectToHoDealer(String groupTicket, String hoDealerEmail, String hoDealerFullName, String nomorTiket, String description, String benefit, String notes) {
        try {
            String[] reciepients = new String[]{hoDealerEmail};
            String[] ccs = new String[]{};

            Common common = MailTemplateUtils.getMailTemplate("UPLOAD_BONUS_REQUESTER_REJECTED");
            assert common != null;
            String subject = common.getCommonDesc2();
            String body = common.getCommonDesc1();

            HashMap<String, String> subjectNew = new HashMap<>();
            subjectNew.put("[NOMOR_TIKET]", nomorTiket);
            subjectNew.put("[GROUP_TIKET]", groupTicket);

            for (Map.Entry<String, String> set : subjectNew.entrySet()) {
                subject = subject.replace(set.getKey(), set.getValue());
            }

            HashMap<String, String> payload = new HashMap<>();
            payload.put("[NAMA_REQUESTER]", hoDealerFullName);
            payload.put("[GROUP_TIKET]", groupTicket);
            payload.put("[NOMOR_TIKET]", nomorTiket);
            payload.put("[REQUEST_DESCRIPTION]", description);
            payload.put("[BUSINESS_BENEFIT]", benefit);
            payload.put("[NOTES]", notes);

            for (Map.Entry<String, String> set : payload.entrySet()) {
                body = body.replace(set.getKey(), set.getValue());
            }

            MailUtil.sendEmail(reciepients, ccs, subject, body, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void sendEmailReviseToHoDealer(String groupTicket, String hoDealerEmail, String hoDealerFullName, String nomorTiket, String description, String benefit, String notes) {
        try {
            String[] reciepients = new String[]{hoDealerEmail};
            String[] ccs = new String[]{};

            Common common = MailTemplateUtils.getMailTemplate("UPLOAD_BONUS_REQUESTER_REVISE");
            assert common != null;
            String subject = common.getCommonDesc2();
            String body = common.getCommonDesc1();

            HashMap<String, String> subjectNew = new HashMap<>();
            subjectNew.put("[NOMOR_TIKET]", nomorTiket);
            subjectNew.put("[GROUP_TIKET]", groupTicket);

            for (Map.Entry<String, String> set : subjectNew.entrySet()) {
                subject = subject.replace(set.getKey(), set.getValue());
            }

            HashMap<String, String> payload = new HashMap<>();
            payload.put("[NAMA_REQUESTER]", hoDealerFullName);
            payload.put("[GROUP_TIKET]", groupTicket);
            payload.put("[NOMOR_TIKET]", nomorTiket);
            payload.put("[REQUEST_DESCRIPTION]", description);
            payload.put("[BUSINESS_BENEFIT]", benefit);
            payload.put("[NOTES]", notes);

            for (Map.Entry<String, String> set : payload.entrySet()) {
                body = body.replace(set.getKey(), set.getValue());
            }

            MailUtil.sendEmail(reciepients, ccs, subject, body, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
