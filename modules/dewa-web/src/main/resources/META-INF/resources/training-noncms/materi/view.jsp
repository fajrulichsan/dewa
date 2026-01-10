<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/jenis-materi-noncms-list" var="jenisMateriNonCMSListURL"/>

<style>

    #jenis_materi_nonCMS_table_length > label {
        visibility: hidden;
    }

    #jenis_materi_nonCMS_table_length > label > select {
        visibility: visible !important;
    }

    .card-jenis-materi {
        cursor: pointer;
        border-radius: 8px;
        min-height: 130px;
        padding-left: 0px;
        display: flex;
        align-items: center;
    }

    .image-materi {
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        border-radius: 12px 0px 0px 12px;
        height: 130px;
    }

    #jenis_materi_nonCMS_table thead {
        display: none;
    }

    .detail-card {
        display: flex;
        flex-direction: column;
        height: 130px;
        align-items: flex-start;
        justify-content: space-between;
        padding: 10px;
        width: 75%;
    }

    .judul-card {
        font-weight: bold;
        font-size: x-large;
        white-space: normal;
        overflow: hidden;
        word-break: break-all;
        color: black;
    }

    .table > thead > tr > th, .table > thead > tr > td,
    .table > tbody > tr > th, .table > tbody > tr > td,
    .table > tfoot > tr > th, .table > tfoot > tr > td {
        border-top: transparent !important;
    }

    .table-hover > tbody > tr:hover {
        background-color: transparent;
    }

    .searchIcon {
        background-image: url(<%=request.getContextPath()%>/assets/img/search.svg);
        background-repeat: no-repeat;
        background-size: 20px;
        background-position: calc(5px) center;
        padding-left: 27px !important;
    }

    .btn_table {
        background-color: #014689;
        color: white;
        border: 1px solid #014689;
        opacity: 10;
        height: 35px;
        margin: 2px;
    }

    .dataTables_filter > label > input {
        background-image: url(<%=request.getContextPath()%>/assets/img/search.svg);
        background-position: 10px 8px;
        background-repeat: no-repeat;
        padding: 5px 20px 5px 40px;
    }

    div.dataTables_wrapper div.dataTables_filter {
        text-align: right;
        display: flex;
    }

    .image-card {
        padding-left: 0;
        text-align: center;
        width: 25%;
    }

    .detail-card-topik {
        padding: 15px 0px;
        border-bottom: 1px #ddd solid;
    }

    .detail-card-topik:last-child {
        border-bottom: none;
    }

    .panel-group .panel {
        box-shadow: 0 8px 20px 0 rgba(13, 12, 34, 0.08);
        border-radius: 12px;
    }

    @media only screen and (max-width: 780px) {
        .card-jenis-materi {
            flex-direction: column;
        }

        .image-card {
            width: 100%;
            height: 130px;
        }

        .detail-card {
            width: 100%;
            height: auto;
        }

        .judul-card {
            padding-left: 0 !important;
        }

        .image-materi {
            border-radius: 12px;
        }

        div.dataTables_wrapper div.dataTables_filter {
            flex-wrap: wrap;
            justify-content: center;
            gap: 10px;
        }
    }

</style>

<div class="jenis_materi_nonCMS">
   <h3 style="font-size: 24px; font-weight: 600; margin-bottom: 1em;" class="text-center">Materi Pelatihan</h3>
   <table id="jenis_materi_nonCMS_table" class="table table-hover nowrap" style="width:100%">
      <thead>
      <tr>
         <th></th>
      </tr>
      </thead>
      <tbody></tbody>
   </table>
</div>

<script>
   var publishDate = "";

   var dataTableLanguageOptionsTable = {
      "emptyTable": "There are no new comers",
      "info": "",
      "lengthMenu": "_MENU_",
      "search": "",
      searchPlaceholder: "Search"
   }

   var selectDate = {"<portlet:namespace/>date": publishDate}

   var jenisMateriDataTable = $('#jenis_materi_nonCMS_table').DataTable({
      searching: true,
      paging: true,
      responsive: true,
      info: false,
      ajax: {
         url: '${jenisMateriNonCMSListURL}',
         type: 'GET',
         data: selectDate,
         dataSrc: function (json) {
            return json.Data;
         }
      },
      language: dataTableLanguageOptionsTable,
      columns: [
         {
            data: "file",
            "width": "100%",
            orderable: false,
            render: function (data, type, row, meta) {
               return cardDetail(row);
            }
         }
      ],
      order: [],
      "sDom": '<"row view-filter"<"col-sm-12"<"pull-left"l><"pull-right"f><"clearfix">>>t<"row view-pager"<"col-sm-12"<"text-center"ip>>>',
      //  dom: '<"row view-filter"<"col-sm-12"<"col-md-6 pull-left"<"pull-left"l>><"col-md-6"<BfB>><"clearfix">>>t<"row view-pager"<"col-sm-12"<"text-center"ip>>>',

      initComplete: function () {
         var input = $('.dataTables_filter input').unbind(),
            self = this.api(),
            $searchButton = $('<button>')
               .text('Search')
               .addClass("btn")
               .addClass("btn-info")
               .addClass("btn_table")
               .css("margin-left", "10px")
               .click(function () {
                  self.search(input.val()).draw();
               });

         input.on("keyup", function (e) {
            if (e.key === 'Enter' || e.keyCode === 13 || !input.val()) {
               self.search(input.val()).draw();
            }
         });

         $('.dataTables_filter').prepend(`
                        <div>
                            <input type="text" autocomplete="off" onkeydown="return false" class="form-control dateIcon form-required margin-right-10" name="publish-date-1" autocomplete="off" placeholder="Pilih Tanggal">
                        </div>
                `)

         $('.dataTables_filter').append($searchButton);

         setPublishDateDatepicker();
      }
   });

   var startDate = new Date(1900, 0, 0);

   function setPublishDateDatepicker() {
      Date.prototype.ddmmyyyy = function () {
         var mm = this.getMonth() + 1; // getMonth() is zero-based
         var dd = this.getDate();

         return [
            (dd > 9 ? '' : '0') + dd,
            '/',
            (mm > 9 ? '' : '0') + mm,
            '/',
            this.getFullYear()
         ].join('');
      };

      $('[name=publish-date-1]').datepicker({
         format: 'dd/mm/yyyy',
         endDate: '+180d',
         forceParse: false,
         autoclose: true,
         todayHighlight: true,
         daysOfWeekHighlighted: [0, 6],
         weekStart: 1,
         language: "ID",
         changeYear: true,
         changeMonth: true,
         clearBtn: true,
      }).on('changeDate', function (ev) {
         let newStartDate = $('[name=publish-date-1]').datepicker('getDate');
         publishDate = newStartDate.ddmmyyyy();
         filteringDate();

      }).on('clearDate', function (ev) {
         publishDate = ""; // Mengosongkan tanggal
         filteringDate();
      });
   }

   function filteringDate() {
      if (publishDate == "") {
         jenisMateriDataTable.ajax.reload();
      }
      var url = new URL("${jenisMateriNonCMSListURL}");
      url.searchParams.set("<portlet:namespace/>date", publishDate);
      jenisMateriDataTable.ajax.url(url.toString());
      jenisMateriDataTable.ajax.reload();
   }


   function cardDetail(row) {
      var cardHTML =
         `
            <div class="panel-group" id="accordionMateri_` + row.id + `" role="tablist" aria-multiselectable="true" data-id="` + row.id + `">
                <div class="panel">
                    <div class="panel-heading card-jenis-materi" role="tab" id="headingMateri_` + row.id + `">
                        <div class="image-card">
                            <div class="image-materi" style="background-image: url(` + row.imageUrl + `)"></div>
                        </div>
                        <div class="detail-card">
                            <h4 class="panel-title">
                                <a role="button" class="judul-card collapsed" data-toggle="collapse" data-parent="#accordionMateri_` + row.id + `" href="#collapseMateri_` + row.id + `"
                                   aria-expanded="true" aria-controls="collapseMateri_` + row.id + `">
                                    ` + row.name + `
                                </a>
                            </h4>
                            <div>` + row.date + `</div>
                        </div>
                    </div>
                    <div id="collapseMateri_` + row.id + `" class="panel-collapse collapse card-materi" role="tabpanel" aria-labelledby="headingMateri_` + row.id + `">
                        <div class="panel-body">
             `;

      var topikList = row.topikList;

      if (topikList.length !== 0) {
         topikList.forEach(function (topik) {
            cardHTML +=
               `
                            <div class="detail-card-topik">
                                <a role="button" class='judul-card' onclick="detailMateri(this)" data-id="` + topik.topikId + `" data-jenis="` + row.id + `" > ` + topik.topikName + ` </a>
                                <div> ` + topik.topikDate + ` </div>
                            </div>
                        `;
         });

      } else {
         cardHTML +=
            `
                            <div class=" detail-card-topik" style="display: flex;justify-content: center;">
                                <p> Tidak ada materi terkait topik </p>
                            </div>
                        `;
      }

      cardHTML +=
         `
                        </div>
                    </div>
                </div>
            </div>
            `;

      return cardHTML;
   }

   function detailMateri(element) {
      var entryId = $(element).data("id");
      var jenisMateriId = $(element).data("jenis");
      var url = themeDisplay.getPortalURL() + "/group/dealink/detail-topik-materi-pelatihan?id=" + entryId + "&jenisMateriId=" + jenisMateriId;
      window.location.href = url;
   }
</script>