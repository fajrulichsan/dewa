//package com.astra.dewa.cms.command.render.upload;
//
//import com.astra.dewa.cms.command.render.upload.util.ApplicationHeaderServiceImpl;
//import com.liferay.portal.kernel.exception.PortalException;
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;
//import com.liferay.portal.kernel.messaging.DestinationNames;
//import com.liferay.portal.kernel.messaging.Message;
//import com.liferay.portal.kernel.messaging.MessageListener;
//import com.liferay.portal.kernel.messaging.MessageListenerException;
//import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
//import com.liferay.portal.kernel.scheduler.SchedulerEntry;
//import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
//import com.liferay.portal.kernel.scheduler.SchedulerException;
//import com.liferay.portal.kernel.scheduler.StorageType;
//import com.liferay.portal.kernel.scheduler.StorageTypeAware;
//import com.liferay.portal.kernel.scheduler.Trigger;
//import com.liferay.portal.kernel.scheduler.TriggerFactory;
//import com.liferay.portal.kernel.util.Portal;
//import org.osgi.service.component.annotations.Activate;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Deactivate;
//import org.osgi.service.component.annotations.Modified;
//import org.osgi.service.component.annotations.Reference;
//
//import java.util.Date;
//
//@Component(
//      immediate = true,
//      property = {
//            "cron.expression=0 0/1 * * * ? *"
//      },
//      service = MessageListener.class
//)
//public class UploadRequestScheduler implements MessageListener {
//   @Reference
//   private Portal _portal;
//
//   private static final Log LOG = LogFactoryUtil.getLog(UploadRequestScheduler.class);
//
//   // In production, scheduler is active for every 8 A.M.
//   // private static final String DEFAULT_CRON_EXPRESSION = "0 0 8 ? * * *";
//
//   // Launch the scheduler for every 5 minutes
//   private static final String DEFAULT_CRON_EXPRESSION = "0 0/5 * 1/1 * ? *";
//
//   private final ApplicationHeaderServiceImpl _applicationHeaderService = new ApplicationHeaderServiceImpl();
//   private volatile boolean _initialized;
//   private TriggerFactory _triggerFactory;
//   private SchedulerEntry _schedulerEntry;
//   private SchedulerEngineHelper _schedulerEngineHelper;
//
//   @Override
//   public void receive(Message message) throws MessageListenerException {
//      try {
//         _applicationHeaderService.sendApprovalReminder();
//      } catch (PortalException pe) {
//         LOG.error(pe.getMessage());
//      }
//   }
//
//   @Reference(unbind = "-")
//   protected void setTriggerFactory(TriggerFactory triggerFactory) {
//      _triggerFactory = triggerFactory;
//   }
//
//   @Reference(unbind = "-")
//   public void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
//      _schedulerEngineHelper = schedulerEngineHelper;
//   }
//
//   protected StorageType getStorageType() {
//      if (_schedulerEntry instanceof StorageTypeAware) {
//         return (((StorageTypeAware) _schedulerEntry).getStorageType());
//      }
//      return StorageType.MEMORY_CLUSTERED;
//   }
//
//   @Activate
//   @Modified
//   protected void activate() throws SchedulerException {
//      String _className = getClass().getName();
//      Trigger trigger = _triggerFactory.createTrigger(_className, _className, new Date(), null, DEFAULT_CRON_EXPRESSION);
//
//      _schedulerEntry = new SchedulerEntryImpl(_className, trigger);
//
//      LOG.info("Dealer Web Gateway Incoming Request Scheduler is running");
//      if (_initialized) {
//         deactivate();
//      }
//
//      _schedulerEngineHelper.register(this, _schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
//      _initialized = true;
//   }
//
//   @Deactivate
//   protected void deactivate() {
//      if (_initialized) {
//         LOG.info("Deactivating Dealer Web Gateway Incoming Request Scheduler");
//         try {
//            _schedulerEngineHelper.unschedule(_schedulerEntry, getStorageType());
//         } catch (SchedulerException se) {
//            if (LOG.isWarnEnabled()) {
//               LOG.warn("Unable to unschedule trigger!");
//            }
//         }
//         _schedulerEngineHelper.unregister(this);
//      }
//   }
//}
