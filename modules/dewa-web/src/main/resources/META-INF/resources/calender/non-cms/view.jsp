<%@ include file="/META-INF/resources/init.jsp" %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/calendar/dist/tui-calendar.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/calendar/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/calendar/css/icons.css">
<script src="<%=request.getContextPath()%>/assets/js/tui/tui-code-snippet.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/tui/tui-time-picker.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/tui/tui-date-picker.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/moment-js/moment.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/moment-js/chance.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/calendar/dist/tui-calendar.js"></script>
<link href="<%=request.getContextPath()%>/assets/css/dewaCalendar.css?<%=System.currentTimeMillis()%>" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/css/custom.css?<%=System.currentTimeMillis()%>" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/css/tab.css?<%=System.currentTimeMillis()%>" rel="stylesheet" type="text/css"/>

<style>
    #calendar1 .tui-full-calendar-layout {
        background: rgba(255, 255, 255, 0.4) !important;
    }

    #listAcara {
        background: white;
        color: black;
        padding: 10px;
        border-radius: 20px;
        height: 500px;
        overflow-y: auto;
    }
</style>

<section class="page-section mb-0" id="forum-head">
   <div class="container-fluid" id="home">
      <div class="row forum-body">
         <div class="">
            <div class="col-md-12">
               <div class="col-md-5 text-white">
                  <h3 class="text-right">Acara Terdekat</h3>
                  <ul class="list-none" id="listAcara" style="background:white; color: black; padding: 10px; border-radius: 20px; height:500px; overflow-y:auto"></ul>
               </div>
               <div class="col-md-7">
                  <div id="menu">
                     <span id="menu-navi">
                         <button type="button" class="btn btn-default btn-sm move-today" data-action="move-today">Today</button>
                         <button type="button" class="btn btn-default btn-sm move-day" data-action="move-prev">
                             <i class="calendar-icon ic-arrow-line-left" data-action="move-prev"></i>
                         </button>
                         <button type="button" class="btn btn-default btn-sm move-day" data-action="move-next">
                             <i class="calendar-icon ic-arrow-line-right" data-action="move-next"></i>
                         </button>
                     </span>
                     <span id="renderRange" class="render-range"></span>
                  </div>
                  <div id="calendar1"></div>
               </div>
            </div>
         </div>
      </div>
   </div>
</section>

<portlet:resourceURL var="resourceURL"/>

<script type="text/javascript">
   $(document).ready(function () {
      //reloadStatus
      $.ajax({
         url: '${resourceURL.toString()}',
         type: 'POST',
         data: {
            <portlet:namespace/>state: "reloadstatus",
            <portlet:namespace/>p_auth: Liferay.authToken
         },
         success: function (data) {
            // console.log(data);
         },
         error: function (e) {
            console.log(e);
         }
      });
   });

   var calendar = new tui.Calendar(document.getElementById('calendar1'), {
      defaultView: 'month',
      taskView: true,    // Can be also ['milestone', 'task']
      scheduleView: true,  // Can be also ['allday', 'time']
      useDetailPopup: true,
      useCreationPopup: false,
      // useDetailPopup: false,
      isReadOnly: true,
      template: {
         milestone: function (schedule) {
            return '<span style="color:red;"><i class="fa fa-flag"></i> ' + schedule.title + '</span>';
         },
         milestoneTitle: function () {
            return 'Milestone';
         },
         task: function (schedule) {
            return '&nbsp;&nbsp;#' + schedule.title;
         },
         taskTitle: function () {
            return '<label><input type="checkbox" />Task</label>';
         },
         allday: function (schedule) {
            return schedule.title + ' <i class="fa fa-refresh"></i>';
         },
         alldayTitle: function () {
            return 'All Day';
         },
         time: function (schedule) {
            return schedule.title + ' <i class="fa fa-refresh"></i>';
         },
         popupDetailBody: function (model) {
            return model.body;
         }
      },
   });

   calendar.on({
      'clickSchedule': function (e) {
         // console.log('clickSchedule', e);
      },
      'beforeCreateSchedule': function (e) {
         // console.log('beforeCreateSchedule', e);
         // open a creation popup

         // If you dont' want to show any popup, just use `e.guide.clearGuideElement()`

         // then close guide element(blue box from dragging or clicking days)
         // e.guide.clearGuideElement();
      },
      'beforeUpdateSchedule': function (e) {
         // console.log('beforeUpdateSchedule', e);
         e.schedule.start = e.start;
         e.schedule.end = e.end;
         cal.updateSchedule(e.schedule.id, e.schedule.calendarId, e.schedule);
      },
      'beforeDeleteSchedule': function (e) {
         // console.log('beforeDeleteSchedule', e);
         cal.deleteSchedule(e.schedule.id, e.schedule.calendarId);
      }
   });


   var resizeThrottled;
   setEventListener();

   function setEventListener() {
      $('#menu-navi').on('click', onClickNavi);

      window.addEventListener('resize', resizeThrottled);
   }

   resizeThrottled = tui.util.throttle(function () {
      calendar.render();
   }, 50);

   function onClickNavi(e) {
      var action = getDataAction(e.target);

      switch (action) {
         case 'move-prev':
            calendar.prev();
            break;
         case 'move-next':
            calendar.next();
            break;
         case 'move-today':
            calendar.today();
            break;
         default:
            return;
      }

      setRenderRangeText();
   }

   setRenderRangeText();

   function setRenderRangeText() {
      var renderRange = document.getElementById('renderRange');
      var options = calendar.getOptions();
      var viewName = calendar.getViewName();

      var html = [];
      if (viewName === 'day') {
         html.push(currentCalendarDate('YYYY.MM.DD'));
      } else if (viewName === 'month' &&
         (!options.month.visibleWeeksCount || options.month.visibleWeeksCount > 4)) {
         html.push(currentCalendarDate('MMMM YYYY'));
      } else {
         html.push(moment(cal.getDateRangeStart().getTime()).format('YYYY.MM.DD'));
         html.push(' ~ ');
         html.push(moment(cal.getDateRangeEnd().getTime()).format(' MM.DD'));
      }
      renderRange.innerHTML = html.join('');
   }

   function getDataAction(target) {
      return target.dataset ? target.dataset.action : target.getAttribute('data-action');
   }

   function currentCalendarDate(format) {
      var currentDate = moment([calendar.getDate().getFullYear(), calendar.getDate().getMonth(), calendar.getDate().getDate()]);

      return currentDate.format(format);
   }

   getData();

   function getData() {
      $.ajax({
         url: '${resourceURL.toString()}',
         type: 'POST',
         data: {
            <portlet:namespace/>state: "calendar",
            <portlet:namespace/>p_auth: Liferay.authToken
         },
         success: function (data) {
            console.log(data);
            var result = $.parseJSON(data);
            var row = result.data;

            calendar.createSchedules(row);
         },
         error: function (e) {
            console.log(e);
         }
      });
   }

   getAcara();

   function getAcara() {
      $.ajax({
         url: '${resourceURL.toString()}',
         type: 'POST',
         data: {
            <portlet:namespace/>state: "acara",
            <portlet:namespace/>p_auth: Liferay.authToken
         },
         success: function (data) {
            // console.log(data);
            var result = $.parseJSON(data);
            var row = result.data;
            var html = "";

            <%--
            for (var i = 0; i < row.length; i++) {
               html +=
                  '<li class="list-line">' +
                     '<div class="font14 text-black text-right">' +
                        row[i].date + ' - ' + row[i].date2 + '<br/>' + row[i].title + ' </br>' +
                        '<a href=' + row[i].url + '>Lihat Detail</a>' +
                     '</div>' +
                     '<div class="list-icon">' +
                        '<span class="icon-r" style="background-color: ' + row[i].color + '"></span>' +
                     '</div>' +
                  '</li>';
            }
            --%>

            for (var i = 0; i < row.length; i++) {
               html +=
                  '<li class="list-line">' +
                     '<div class="font14 text-black text-right">' +
                        row[i].date + ' - ' + row[i].date2 + '<br/>' + row[i].title + ' </br>' +
                        '<a data-senna-off="true" href="javascript:void(0);" data-id=' + row[i].id + ' onclick="getDetail(this);">Lihat Detail</a>' +
                     '</div>' +
                     '<div class="list-icon">' +
                        '<span class="icon-r" style="background-color: ' + row[i].color + '"></span>' +
                     '</div>' +
                  '</li>';
            }
            $('#listAcara').empty().append(html);
         },
         error: function (e) {
            console.log(e);
         }
      });
   }

   function getDetail(element) {
      var entryId = $(element).data('id');
      var url = '/group/dealink/calendar-detail?id=' + entryId;
      window.location.href = url;
   }
</script>