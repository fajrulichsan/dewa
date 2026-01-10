<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/calender-event-view" var="calenderEventViewURL"/>
<portlet:resourceURL id="/calender-event-action" var="calenderEventActionURL"/>
<portlet:renderURL var="calenderEventEditURL">
    <portlet:param name="mvcRenderCommandName" value="/calender-event-edit"/>
</portlet:renderURL>

<portlet:resourceURL id="/jenis-acara-calender-event" var="jenisAcaraCalenderEventURL"/>
<portlet:resourceURL id="/status-acara-calender-event" var="statusAcaraCalenderEventURL"/>

<style>
    .calender_event {
        margin: 25px;
    }

    .calender_event > .tabcontent {
        box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
        border-radius: 10px;
        padding: 0.75em;
    }

    #calender_event_table > thead {
        background-color: #014689;
        border: none !important;
        color: white;
        border-radius: 10px 10px 0 0;
    }

    .table > thead:first-child > tr:first-child > th:first-child {
        border-radius: 10px 0 0 0;
    }

    .table > thead:first-child > tr:first-child > th:last-child {
        border-radius: 0 10px 0 0;
    }

    table#calender_event_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#calender_event_table {
        border-radius: 10px 10px 10px 10px;
    }

    #calender_event_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #calender_event_table > thead > tr > th {
        font-weight: normal;
        text-align: center;
    }

    #approval_table_filter > label > input {
        background-image: url(<%=request.getContextPath()%>/assets/img/search.svg);
        background-position: 10px 8px;
        background-repeat: no-repeat;
        padding: 5px 20px 5px 40px;
    }

    .btn_table, .btn_table:hover {
        border: 0px;
        background-color: #014689 !important;
        color: white;
        font-weight: 400;
        opacity: 0.8;
        padding: 8px 16px;
        border-radius: 5px;
    }

    /*Select2*/
    .select2-container--default .select2-selection--single {
        padding: 6px;
        margin-top: 16px;
        /*margin-bottom: 16px;*/
        height: 37px;
        /*width: 148px;*/
        font-size: 1.2em;
        position: relative;
    }
</style>

<ul class="breadcrumb">
    <li><a href="#" style="font-size: 1.5rem;">DEALINK Dealer Information Link</a></li>
    <li class="active"><a href="#" style="font-size: 1.5rem;">Calendar Event</a></li>
</ul>

<!--modal -->
<div class="container">
    <div class="modal fade" id="myModal" role="dialog" data-backdrop="false">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="event-detail-title"></h4>
                    <input type="hidden" type="text" id="calendar_id"></input>
                </div>
                <div class="modal-body">
                    <!-- <p>Some text in the modal.</p> -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="modal-detail-event"></div>
                        </div>
                        <div class="col-sm-6">
                            <div>
                                <i class="fa fa-map-marker" aria-hidden="true"></i>
                                <span id="event-lokasi"></span>
                                <i class="fa-regular fa-clock fa-sm"></i>
                                <span id="event-waktu"></span>
                                <div class="ct-padding-top-md"></div>
                            </div>
                            <p id="event-detail-desc"></p>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
                    <button type="button" id="daftar" class="btn-info btn_table" data-dismiss="modal">Daftar</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--end modal -->

<!--modal daftar -->
<div class="container">
    <div class="alert alert-success alert-dismissible alert-success-pop-up" role="alert">
        <button type="button" id="popup-success"class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>sukses menyimpan data!</strong><a href="#" class="alert-link"></a>.
    </div>
    <!-- Trigger the modal with a button -->
    <!-- <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button> -->
    <!-- Modal -->
    <div class="modal fade" id="myDaftar"  role="dialog" data-backdrop="false">
        <div class="modal-dialog modal-dialog-scrollable">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="event-detail-title"></h4>
                </div>
                <div class="modal-body">
                    <form name="daftarPeserta">
                        <div id="newInputFields"></div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
                <div class="row">
                    <div class="col-sm-1">
                        <button type="submit" class="btn-info btn_table" id="tambah-peserta">Tambah</button>
                    </div>
                    <div class="col-sm-3">
                        <button type="submit" class="btn-info btn_table" onclick="submitPeserta()">Submit</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--end modal -->

<!--calendar-->
<div class="calender-event">
    <div class="row">
        <div class="col-sm-3">
            <h3 style="font-size: 24px; font-weight: 700; margin-bottom: 1em;" class="text-center">Acara Terdekat</h3>
            <div class="calendar-event-card">
                <div class="calendar-detail"></div>
                <h3 id="title-detail"></h3>
                <i class="fa fa-map-marker fa-1x" aria-hidden="true"></i>
                <span id="event-lokasi_nearest"></span>
                <div class="ct-padding-top-md"></div>
                <p id="deskripsi-detail"></p>
            </div>
        </div>
        <div class="col-sm-9">
            <div id='calendar'></div>
        </div>
    </div>
</div>
<!--end calendar-->

<script type="text/javascript">
    let tag='';
    var id;
    var max_x = 100;
    var x = 0;
    var peserta = {
        "email" : null,
        "no_tlp" : null,
        "nama_peserta" : null
    }
    var listPeserta=[];
    var idCabang=0;

    function checkDaftar(listDaftar) {
        let i=0;
        let success=true;
        let found=false;
        let status;

        $.each(listDaftar,function(index,value) {
            if(value.email=='') {
                success=false;
                var peserta = index+1;
                alert("Peserta "+peserta+" email tidak boleh kosong")
            }
            if(value.nama=='') {
                success=false;
                var peserta = index+1;
                alert("Peserta "+peserta+" nama peserta tidak boleh kosong")
            }
            if(value.tlp=='') {
                success=false;
                var peserta = index+1;
                alert("Peserta "+peserta+" tlp/hp tidak boleh kosong")
            }
        });

        if(success==true) {
            var promiseSavePeserta = savePeserta(listDaftar);
            promiseSavePeserta.done(function(data) {
                $("#myDaftar .close").click()
            })
        }
    }

    var promiseCallEvents = callEvents();
    var promiseNearestEvents = callNearestEvents();

    $('#myDaftar .close').on("click",function() {
        $(".modal-body").css("height","100%");
    })

    $("#daftar").on( "click", function() {
        $(".modal-body").css({
            "height" : "60vh"
        })
        $('#myDaftar').modal({ show: true})

        if(x==0) {
            formFields(x);
        }
        pushPeserta(x);
    } );

    $("#tambah-peserta" ).on( "click", function() {
        addMoreFormField();
    } );

    $('#myModal').modal({ show: true})

    $("#myModal .close").click()

    $('#myDaftar').modal({ show: true})

    $("#myDaftar .close").click()

    $('#listCabang-0 select').select2({
        templateSelection: function (data) {
            if (data.id === '') { // adjust for custom placeholder values
                return 'Custom styled placeholder text';
            }

            alert("adasdsa")
            return data.text;
        }
    });

    function selectRefresh() {
        $('.js-example-placeholder-single').select2({
            tags: true,
            placeholder: "Select an Option",
            allowClear: true,
            width: '100%',
            dropdownPosition: 'below'
        });
    }

    function callDealerList(idCabang) {
        return $.ajax({
            type : "GET",
            url: "http://localhost:8080/o/dealer-rest/dealer/list/"+idCabang,
            contentType:'application/json'
        });
    }

    function submitPeserta() {
        var i = 0;
        var participants=[];
        while(i<=listPeserta.length-1) {
            var x=i;
            let email = $("#email-"+x).val();
            let telepon =  $("#tlp-"+x).val();
            let nama =  $("#nama-"+x).val();
            let id_dealer = $("#id_dealer-"+x).attr("value");
            let calendar_id = $("#calendar_id").attr("value");
            participants.push({
                "id" : x,
                "email" : email,
                "tlp" : telepon,
                "nama" : nama,
                "idDealer" : id_dealer,
                "idCalendar" : calendar_id
            });
            i=i+1;
        }
        participants = $.grep(participants, function(item) {
            return item.email !== undefined;
        });
        checkDaftar(participants);
    }

    function closeForm(x) {
        var items = $.grep(listPeserta, function(item) {
            return item.id !== x;
        });
        listPeserta = items;
        $("#peserta-"+x).empty();
    }

    function pushPeserta(i) {
        let email = $("#email-"+i).val();
        let telepon =  $("#tlp-"+i).val();
        let nama =  $("#nama-"+i).val();
        let id_dealer = $("#id_dealer-"+i).attr("value");
        let calendar_id = $("#calendar_id").attr("value");
        peserta.email = email
        peserta.telepon = telepon;
        peserta.nama_peserta = nama;
        peserta.id_dealer = id_dealer;
        listPeserta.push({
            "id" : i,
            "email" : peserta.email,
            "tlp" : peserta.telepon,
            "nama" : peserta.nama_peserta,
            "idDealer" : peserta.id_dealer,
            "idCalendar" : calendar_id
        });
        $(".modal-body").css({
            "height" : "60vh"
        })
        x=i+1;
    }

    function formFields(x)  {

        var wrapper = ("#newInputFields")
        $(wrapper).append("<div id=\"peserta-"+x+"\">\
                        \<h5>Peserta Agenda</h5> \
                        <div class=\"container form-peserta\">\
                            <div class=\"ct-padding-top-sm\"></div>\
                             <!--form-->\
                                <div class=\"input-form \">\
                                    <div id=\"close-form\"><i class=\"fa fa-window-close text-danger\" id=\"form-"+x+"\" aria-hidden=\"true\" value=\"hidden\" onClick=\"closeForm("+x+")\"></i></div>\
                                    <div class=\"ct-padding-top-lg form-group\">\
                                        <label for=\"email\">Email</label>\
                                        <input type=\"email\" class=\"form-control\" id=\"email-"+x+"\" name='email'>\
                                    </div>\
                                    <div class=\"form-group\">\
                                        <label for=\"tlp\">No Hp</label>\
                                        <input type=\"text\" class=\"form-control\" id=\"tlp-"+x+"\" name='telepon'>\
                                    </div>\
                                    <div>\
                                        <label for=\"nama\">Nama Peserta</label>\
                                        <input type=\"text\" class=\"form-control\" id=\"nama-"+x+"\" name='nama_peserta'>\
                                    </div>\
                                    <div>\
                                        <label for=\"nama\" class=\"ct-padding-top-lg\">Nama cabang</label>\
                                        <div id=\"listCabang-"+x+"\">\
                                            <select class=\"js-example-placeholder-single js-states form-control\">\
                                            </select>\
                                        </div> \
                                    </div> \
                                    <div>\
                                        <label for=\"nama\" class=\"ct-padding-top-lg\">Nama Dealer</label>\
                                        <div id=\"listDealer-"+x+"\">\
                                            <select class=\"js-example-placeholder-single js-states form-control\">\
                                            </select>\
                                        </div> \
                                    </div> \
                                </div>\
                            <!--end form-->\
                        </div>\
                    </div>").html();
        selectRefresh();


        var promiseCallCabang = callCabangList();
        promiseCallCabang.done(function(data) {
            var wrapper = ("#listCabang-"+x+" select")
            for(var j=0;j<=data.list.length-1;j++) {
                $(wrapper).append("<option id=id_cabang-"+j+" value="+data.list[j].id+">"+data.list[j].name+"</option>");
            };
        });

        $('#listCabang-'+x+' select').select2({
            templateSelection: function (data) {
                var cabangId = data.id;
                var promiseCallDealers = callDealerList(cabangId);
                promiseCallDealers.done(function(data) {
                    var wrapper = ("#listDealer-"+x+" select")
                    $("#listDealer-"+x+" select").empty();
                    for(var j=0;j<=data.list.length-1;j++) {
                        $(wrapper).append("<option id=id_dealer-"+j+" value="+data.list[j].id+">"+data.list[j].name+"</option>");
                    };
                })
                return data.text;
            }
        });
    }


    function addMoreFormField() {
        var wrapper = ("#newInputFields")
        if(x<max_x) {
            formFields(x);
        }
        pushPeserta(x);


    }

    function callNearestEvents() {
        return $.ajax({
            type : "GET",
            url: "http://localhost:8080/o/dealer-rest/calendar-event/nearest",
            contentType:'application/json'
        });
    }

    function callEvents() {
        return $.ajax({
            type : "GET",
            url: "http://localhost:8080/o/dealer-rest/calendar-event",
            contentType:'application/json'
        });
    }

    function callEventDetail(id) {
        return $.ajax({
            type : "GET",
            url: "http://localhost:8080/o/dealer-rest/calendar-event/detail/"+id,
            contentType:'application/json'
        });
    }

    function callCabangList() {
        return $.ajax({
            type : "GET",
            url: "http://localhost:8080/o/dealer-rest/cabang/list",
            contentType:'application/json'
        });
    }

    function savePeserta(peserta) {
        $('.alert-success-pop-up').css("display","block");
        $(".alert-dismissible").fadeTo(2000, 500).slideUp(500, function(){
            $('.alert-success-pop-up').css("display","none");
        });
        return $.ajax({
            type : "POST",
            url: "http://localhost:8080/o/dealer-rest/calendar-event/add",
            data : JSON.stringify([peserta]),
            contentType:'application/json'
        });
    }

    promiseCallEvents.done(function (data) {
        //add event
        var selected = "unselected";
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl,
            {
                events: data.list,
                header: {
                    left: 'dayGridMonth,timeGridWeek,timeGridDay custom1',
                    center: 'title',
                    right: 'custom2 prevYear,prev,next,nextYear'
                },
                // footer controls
                footer: {
                    left: 'custom1,custom2',
                    center: '',
                    right: 'prev,next'
                },
                // custom toolbar buttons
                customButtons: {
                    custom1: {
                        text: 'custom 1',
                        click: function () {
                            alert('clicked custom button 1!');
                        }
                    },

                    custom2: {
                        text: 'custom 2',
                        click: function () {
                            alert('clicked custom button 2!');
                        }
                    }
                },
                eventClick: function(info) {
                    var eventObj = info.event;
                    if (eventObj.url) {
                        alert(
                            'Clicked ' + eventObj.title + '.\n' +
                            'Will open ' + eventObj.url + ' in a new tab'
                        );
                        window.open(eventObj.url);
                        info.jsEvent.preventDefault(); // prevents browser from following link in current tab.
                    }
                    else if(eventObj) {
                        selected="selected";
                        $(document).on("click",function(e){
                            var xScrollPos = e.scrollLeft || document.documentElement.scrollLeft;
                            var yScrollPos = e.scrollTop || document.documentElement.scrollTop;
                            var newPosX = e.clientX;
                            var newPosY = e.clientY;
                            if(selected=="selected") {
                                var promiseCallEventDetail = callEventDetail(eventObj.id);
                                promiseCallEventDetail.done(function(data) {
                                    if(data.imagePath!='') {
                                        $(".modal-detail-event").css('background-image','url('+data.imagePath+')');
                                    }
                                    $("#event-lokasi").html(data.lokasi);
                                    $("#event-waktu").html(data.waktu);
                                    $("#event-detail-desc").html(data.deskripsi.replace(/<[^>]+>/g, ''));
                                    $("#event-detail-title").html(data.title);
                                    $('#calendar_id').val(eventObj.id);
                                    $('#myModal').modal('show');
                                    selected="unselected";
                                    $("#myModal .close").click(function() {
                                        // document.location.reload(true)
                                    })
                                })
                            }
                        });
                    }
                },
            });
        calendar.render();
    });

    promiseNearestEvents.done(function(data){
        $("#title-detail").html(data.title);
        $("#deskripsi-detail").html(data.deskripsi.replace(/<[^>]+>/g, ''))
        $("#event-lokasi_nearest").html(data.lokasi);
        if(data.imagePath!=null) {
            $(".calendar-detail").css('background-image','url('+data.imagePath+')');
        }
    });
</script>