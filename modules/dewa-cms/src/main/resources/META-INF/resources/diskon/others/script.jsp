<script type="text/javascript">
  var diskonOtherTable;
  var submitProcess = false;
  var action = 'add';

	(function () {
	  $(document).ready(function () {
	    diskonOtherTable = $('#<portlet:namespace/>diskonOthersCMSTable').DataTable({
	      searching: false,
	      paging: true,
	      info: false,
	      responsive: true,
	      processing: true,
	      serverSide: true,
	      language: dataTableLanguage,
	      dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
	      order: [[4, 'desc']],
	      ajax: {
	        url: '${diskonOtherListURL}',
	        type: 'GET',
	        data: function (d) {
	          d.dealerId = $('#<portlet:namespace/>searchDiskonOthers #<portlet:namespace/>dealerId').val();
	          d.tahun = $('#<portlet:namespace/>searchDiskonOthers #<portlet:namespace/>tahun').val();
	          d.kuartalId = $('#<portlet:namespace/>searchDiskonOthers #<portlet:namespace/>kuartalId').val();
	          d.p_auth = Liferay.authToken;
	        },
	        dataSrc: function (json) {
	          return json.data;
	        }
	      },
	      columns: [
	        { data: "no", "width": "5%", className: "text-center" },
	        { data: "fileName", "width": "30%" },
	        { data: "tahun", "width": "10%", className: "text-center" },
	        { data: "kuartal", "width": "20%", className: "text-center" },
	        {
	          data: "uploadDateString",
	          width: "30%",
	          className: "text-center",
	          render: function (data, type, row, meta) {
	            if (type === "sort") {
	              return row.uploadDate;
	            }
	            return data;
	          }
	        },
	        {
	          data: "id",
	          width: "5%",
	          className: "text-center",
	          render: function (data, type, row, meta) {
	            return renderActionButton(data);
	          },
	          orderable: false
	        }
	      ],
	      columnDefs: [
	        {
	          targets: [2],
	          orderData: [2, 1]
	        },
	        {
	          targets: [3],
	          orderData: [3, 4]
	        }
	      ],
	      initComplete: function () {
	        $('.dataTables_length select').select2({ minimumResultsForSearch: -1 });
	      }
	    });

	    <%-- populate form dropdowns --%>
	    populateSingleSelect2({
	      element: '#formDealerId',
	      url: '${dealerURL}',
	      type: 'POST',
	      placeholderText: 'Pilih Dealer',
	      minimumResultsForSearch: 10,
	      disablePlaceholder: true
	    });

	    populateSingleSelect2({
	      element: '#formTahun',
	      url: '${yearURL}',
	      type: 'POST',
	      placeholderText: 'Pilih Tahun',
	      disablePlaceholder: true
	    });

	    populateSingleSelect2({
	      element: '#formKuartalId',
	      url: '${quarterURL}',
	      type: 'POST',
	      placeholderText: 'Pilih Kuartal',
	      disablePlaceholder: true
	    });

	    <%-- populate table filter dropdowns --%>
	    populateSingleSelect2({
	      element: '#<portlet:namespace/>dealerId, #formDealerId',
	      url: '${dealerURL}',
	      type: 'POST',
	      placeholderText: 'Pilih Dealer',
	      minimumResultsForSearch: 10
	    });

	    populateSingleSelect2({
	      element: '#<portlet:namespace/>tahun',
	      url: '${diskonOtherYearURL}',
	      type: 'POST',
	      placeholderText: 'Pilih Tahun'
	    });

	    populateSingleSelect2({
	      element: '#<portlet:namespace/>kuartalId',
	      url: '${quarterURL}',
	      type: 'POST',
	      placeholderText: 'Pilih Kuartal'
	    });

	    $('#<portlet:namespace/>searchDiskonOthers select').on('select2:select', function () {
	      const value = $(this).val();
	      if (value) {
	        $(this).siblings('.select2-container--default').find('.select2-selection__rendered').addClass('select2-selection__selected');
	      } else {
	        $(this).siblings('.select2-container--default').find('.select2-selection__rendered').removeClass('select2-selection__selected');
	      }
	      diskonOtherTable.ajax.reload();
	    });

	    $("#diskonOthersFile").change(function (e) {
	      const $element = $(this);

	      if ($element.val()) {
	        const dealer = $('#formDealerId').val();
	        const tahun = $('#formTahun').val();
	        const kuartal = $('#formKuartalId').val();

	        if (dealer === null || dealer === undefined || dealer.length < 1 || dealer === "Pilih Dealer") {
	          Swal.fire("Kode Dealer belum terisi.", "", "warning");
	        } else if (tahun === null || tahun === undefined || tahun.length < 1 || tahun === "Pilih Tahun") {
	          Swal.fire("Tahun belum terisi.", "", "warning");
	        } else if (kuartal === null || kuartal === undefined || kuartal.length < 1 || kuartal === "Pilih Kuartal") {
	          Swal.fire("Kuartal belum terisi.", "", "warning");
	        } else {
	          processUpload(e, "diskonOthers", $(this));
	          return true;
	        }

	        $element.val(null);
	      }
	    });

	    $('#backButtonDocFlow').click(function () {
	      Swal.fire({
	        title: "Apakah anda yakin ingin membatalkan?",
	        icon: "question",
	        showCancelButton: true,
	        confirmButtonText: "Yes",
	      }).then((result) => {
	        if (result.isConfirmed) {
	          refreshForm();
	          $('#deleteFileButton a').click();
	          $('#kelola_d_others_navtab').click();
	        }
	      });
	    });

	    $("#formDiskonOthers").submit(function (e) {
	      e.preventDefault();
	      const dealer = $('#formDiskonOthers [name="dealerId"]').val();
	      const tahun = $('#formDiskonOthers [name="tahun"]').val();
	      const kuartal = $('#formDiskonOthers [name="kuartalId"]').val();
	      const diskonOthersFileId = $('#formDiskonOthers [name="diskonOthersFileId"]').val();

	      if (submitProcess === false) {
	        if (dealer === null || dealer === undefined || dealer.length < 1 || dealer === "Pilih Dealer") {
	          Swal.fire("Kode Dealer belum terisi.", "", "warning");
	        } else if (tahun === null || tahun === undefined || tahun.length < 1 || tahun === "Pilih Tahun") {
	          Swal.fire("Tahun belum terisi.", "", "warning");
	        } else if (kuartal === null || kuartal === undefined || kuartal.length < 1 || kuartal === "Pilih Kuartal") {
	          Swal.fire("Kuartal belum terisi.", "", "warning");
	        } else if (diskonOthersFileId === null || diskonOthersFileId === undefined || diskonOthersFileId.length < 1) {
	          Swal.fire("File Report belum terisi.", "", "warning");
	        } else {
	          var formData = new FormData();
	          formData.set('<portlet:namespace/>cmd', action);
	          formData.set('<portlet:namespace/>entryId', $('#formDiskonOthers [name="entryId"]').val());
	          formData.set("<portlet:namespace/>dealerId", dealer);
	          formData.set('<portlet:namespace/>tahun', tahun);
	          formData.set('<portlet:namespace/>kuartalId', kuartal);
	          formData.set('<portlet:namespace/>diskonOthersFileId', diskonOthersFileId);
	          formData.set('<portlet:namespace/>p_auth', Liferay.authToken);

	          Swal.fire({
	            title: 'Apakah data ini sudah benar?',
	            icon: 'question',
	            showCancelButton: true,
	            confirmButtonText: 'Yes',
	          }).then((result) => {
	            if (result.isConfirmed) {
	              createLoading();
	              $.ajax({
	                url: '${diskonOthersActionURL}',
	                type: "POST",
	                data: formData,
	                processData: false,
	                contentType: false,
	                success: function (response) {
	                  submitProcess = true;
	                  if (response) {
	                    var json = JSON.parse(response);
	                    if (json.acknowledge === 1) {
	                      Swal.fire(json.message, '', json.status)
	                        .then(function () {
	                          diskonOtherTable.ajax.reload();
	                          refreshForm();
                            $('#kelola_d_others_navtab').click();
	                        });
	                    } else {
	                      Swal.fire(json.message, '', json.status);
	                    }
	                  } else {
	                    Swal.fire('Internal server error', '', 'error');
	                  }
	                },
	                error: function (data, textStatus, XMLHttpRequest) {
	                  Swal.fire('Internal server error', '', 'error');
	                },
	                complete: function (jqXHR, textStatus) {
	                  submitProcess = false;
	                  destroyLoading();
	                },
	              });
	            }
	          });
	        }
	      }
	    });
	  });
  })();

  function renderActionButton(data) {
	  var response = '';
    response =
      '<span class="action-wrapper">' +
        '<a href="javascript:void(0)" onclick="fetchDiskonOthers(this)" data-id="' + data + '">' +
          '<span><img data-toggle="tooltip" title="Edit" src="<%=request.getContextPath()%>/assets/img/edit.svg"></img></span>' +
        '</a>' +
        '<a href="javascript:void(0)" onclick="deleteDiskonOthers(this)" data-id="' + data + '">' +
          '<span><img data-toggle="tooltip" title="Delete" src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
        '</a>' +
      '</span>';
    return response;
  }

	function fetchDiskonOthers(element) {
    action = 'update';
    $('#actionStatus').html('Edit');
    const entryId = $(element).data('id');

    var formData = new FormData();
    formData.set('<portlet:namespace/>entryId', entryId);
    formData.set('p_auth', Liferay.authToken);

    createLoading();

    $.ajax({
      url: '${fetchDiskonOthersURL}',
      type: 'POST',
      data: formData,
      processData: false,
      contentType: false,
      success: function (response) {
        const data = JSON.parse(response);
        const oneYearAgo = new Date().getFullYear() - 1;
        if (data.tahun < oneYearAgo) {
          Swal.fire({
            icon: "error",
            title: "Dokumen tidak bisa diedit!",
            confirmButtonText: "Back",
            text: "Tidak bisa edit dokumen satu tahun ke belakang.",
            allowOutsideClick: false,
          });
        } else {
          $("#create_d_others_navtab")[0].click();
          for (const key in data) {
            $('#formDiskonOthers [name="' + key + '"]').val(data[key]).change();
          }
        }
      },
      error: function (error) {
          Swal.fire({
              icon: "error",
              title: "Oops...",
              text: "Something went wrong!",
          });
      },
      complete: function() {
          destroyLoading();
      },
    });
  }

  function deleteDiskonOthers(element) {
    const entryId = $(element).data('id');
    Swal.fire({
      title: 'Apakah anda yakin ingin menghapus data ini?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      confirmButtonColor: '#EE1C25',
    }).then((result) => {
      if (result.isConfirmed) {
        var formData = new FormData();
        formData.set('<portlet:namespace/>cmd', 'delete');
        formData.set('<portlet:namespace/>entryId', entryId);
        formData.set('<portlet:namespace/>p_auth', Liferay.authToken);

        $.ajax({
          url: '${diskonOthersActionURL}',
          type: 'POST',
          data: formData,
          processData: false,
          contentType: false,
          success: function (response) {
            if (response) {
              const json = JSON.parse(response);
              if (json.acknowledge === 1) {
                Swal.fire(json.message, '', json.status)
                  .then((response) => {
                    diskonOtherTable.ajax.reload();
                  });
              } else {
                Swal.fire(json.message, '', json.status);
              }
            } else {
              Swal.fire('Internal server error', '', 'error');
            }
          },
          error: function (err) {}
        });
      }
    });
  }

  function uploadDocumentFile(file, fileName, fileID, element, name) {
    let formData = new FormData();
    let format = /[:"\\|,<>\/?^*]/;
    if (format.test(fileName)) {
      swal.fire("", 'File name cannot contains special character ,^*:"|<>\\ /?', "warning");
    } else {
      fileName = fileName.replaceAll(",", "");
      formData.set("<portlet:namespace/>dealerId", $('#formDealerId').val());
      formData.set("<portlet:namespace/>tahun", $('#formTahun').val());
      formData.set("<portlet:namespace/>kuartalId", $('#formKuartalId').val());
      formData.set("<portlet:namespace/>file", file);
      formData.set("<portlet:namespace/>fileName", fileName);
      formData.set("p_auth", Liferay.authToken);

      element.parents(".input-group").siblings(".upload-content").remove();
      element.val("");

      let htmlLoading = $(`
        <div class="upload-content">
          <div class="progress" style="margin-top: 10px; margin-bottom: 0px; transform: scaleY(0.7);">
            <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" style="width: 0%;"></div>
          </div>
          <div id="progress-percentage">0% uploaded</div>
        </div>
      `);

      element.parents(".input-group").after(htmlLoading);

      let elementEdit = element;
      let nameEdit = name;

      $.ajax({
        url: "${uploadDiskonOthersFileURL}",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        xhr: function () {
          var xhr = new XMLHttpRequest();

          xhr.upload.addEventListener("progress", function (e) {
            if (e.lengthComputable) {
              var uploadPercent = e.loaded / e.total;
              uploadPercent = uploadPercent * 100;

              if (parseInt(uploadPercent) === 100) {
                htmlLoading.find("#progress-percentage").text("Silahkan tunggu...");
              } else {
                htmlLoading.find("#progress-percentage").text(parseInt(uploadPercent) + "% uploaded");
              }
              htmlLoading.find(".progress-bar").width(uploadPercent + "%");
            }
          }, false);

          return xhr;
        },
        success: function (response) {
          let data = JSON.parse(response);
          let acknowledge = data["acknowledge"];
          let fileID = data["fileID"];
          fileName = data["fileName"];
          let fileURL = data["fileURL"];

          if (acknowledge === 1) {
            $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileId"]'))[0].value = fileID;
            $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileName"]'))[0].value = fileName;
            $(elementEdit.parent().siblings('[name="' + nameEdit + 'FilePath"]'))[0].value = fileURL;

            let successHTML = `
              <div id="upload-status" style="margin-top: -8px;margin-bottom: 15px;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                <div>
                  <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil </p>
                </div>
                <div>
                  <div id="deleteFileButton">
                    <a href="javascript:void(0);" onclick="deleteUploadedFile(this, ` + fileID + `)" title="Delete File"><i class="fas fa-trash"></i></a>
                  </div>
                  <div id="deleteLoader" style="display: none;">
                    <i class="fas fa-spinner anim-rotate"></i>
                  </div>
                </div>
              </div>`;

            setTimeout(function () {
              htmlLoading.empty();
              htmlLoading.append(successHTML);
            }, 2000);
          } else {
            swal.fire({
              icon: "error",
              title: "Failed to upload file"
            });
          }
        },
        error: function (e) {
          swal.fire({
            icon: "error",
            title: "Failed to upload file"
          });
        },
        complete: function () {
          $('#fileRestrictionText').hide();
        }
      });
    }
  }

  function processUpload(e, name, element) {
    const fileName = e.target.files[0].name;
    const fileSize = e.target.files[0].size;
    if (!(fileName && (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")))) {
      Swal.fire("File harus .xls atau .xlsx.", "", "warning");
    } else if (fileSize > 1048576) {
      Swal.fire("Ukuran file maksimal 1 MB!", "", "warning");
    } else {
      uploadDocumentFile(e.target.files[0], fileName, $('[name="' + name + 'FileId"]')[0].value, element, name);
      return true;
    }
    $("#diskonOthersFile").val(null);
  }

  function deleteUploadedFile(element, fileID) {
    const parentElement1 = $(element).parents(".upload-content");
    parentElement1.find("#deleteFileButton").css("display", "none");
    parentElement1.find("#deleteLoader").css("display", "inherit");

    const parentElement = element.parentElement.parentElement.parentElement;
    const inputElement = $(parentElement1.siblings().find("input.form-control"))[0];

    let formData = new FormData();
    formData.set("<portlet:namespace/>fileId", fileID);
    formData.set("p_auth", Liferay.authToken);

    $.ajax({
      url: "${deleteDiskonOthersFileURL}",
      type: "POST",
      data: formData,
      processData: false,
      contentType: false,
      success: function (response) {
        let data = JSON.parse(response);
        if (data["acknowledge"] === 1) {
          parentElement.remove();
          parentElement1.remove();
          inputElement.value = "";
        } else {
          parentElement1.find("#deleteFileButton").css("display", "inherit");
          parentElement1.find("#deleteLoader").css("display", "none");
          parentElement.find("[name=file-status]").text("delete failed");
        }
      },
      error: function (e) {
        swal({
          icon: "error",
          title: "Failed to delete file"
        });
      },
      complete: function () {
        $('[name="diskonOthersFileId"]')[0].value = null;
        $('#fileRestrictionText').show();
      }
    });
  }

  function refreshForm() {
    action = 'add';
		$('#formDiskonOthers select, #formDiskonOthers input').val(null).change();
		$('#actionStatus').html('Buat');
		$('#fileRestrictionText').show();
		$('.upload-content').remove();
  }
</script>