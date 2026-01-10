
var analystBox = $('#df-analyst')

if(JSON.stringify(data) !== '{}'){
    switch (action){
        case "addNew":
            $('#judul-doc-flow').text("Add New Data Flow")
            renderDocAddNew()
            break;
        case "edit":
            $('#judul-doc-flow').text("Edit Data Flow")
            renderDoc()
            break;
        default :
            $('#judul-doc-flow').text("Data Flow")
            renderDoc()
            break;
    }
}else {
    swal.fire("", "Data not found", "warning").then(function() {
        window.location = homeUrl;
    });
}

function renderDocAddNew(){
    renderSelectPicker();
    $('[name="df-description"]')[0].value="";

}

function renderSelectPicker(){
    $('select[name="df-company"]').selectpicker({
        title: "Choose Company User Requester"
    });
    $('select[name="df-year"]').selectpicker({
        title: "Choose Year"
    });
    $('select[name="df-title"]').selectpicker({
        title: "Choose document title"
    });
}

function renderDoc(){
    renderSelectPicker();
    $('[name="document-flow-id"]')[0].value=data.id
    $('[name="fileID"]')[0].value=data.docTemplateId
    $('[name="fileURL"]')[0].value=data.docTemplate
    $('[name="df-doc-name"]')[0].value=data.docName

    $('[name="df-title"]')[0].value=data.docTitle
    $('[name="df-title"]').selectpicker('refresh')

    $('[name="df-company"]')[0].value=data.companyType
    $('[name="df-company"]').selectpicker('refresh')

    $('[name="df-year"]')[0].value=data.year
    $('[name="df-year"]').selectpicker('refresh')

    if(data.publish==1){
        $('[name="df-publish"]').prop("checked",true)
    }else{
        $('[name="df-publish"]').prop("checked",false)
    }

    $('[name="df-description"]')[0].value=data.description

}


function renderAnalyst(analyst){
    analystBox.empty();
    $.each(analyst, function(i, v){
        analystBox.append(createAnalystBox(v))
    })
    analystBox.append(createAddAnalyst())
}

function createAnalystBox(analyst){
    let element = ''+
        '<button onclick="clearAnalyst(this);" data-id="'+analyst.id+'" name="df-analyst-user" type="button" class="btn btn-secondary btn-sm"> '+
        '    '+analyst.name+' <span style="margin-left: 5px;">✕</span> '+
        '</button> '
    ;

    return element
}

function createAddAnalyst(){
    let element = ''+
        '<button onclick="renderAnalyst(data.analystAll);"   type="button" class="btn-plus"> '+
        '     <span ><i class="fas fa-plus" style="margin-right: 5px"></i></span> '+
        '</button> '
    ;

    return element
}

function clearAnalyst(element){
    element.remove()
}

$('[name="df-file-name"]').change(function(e) {
    if (e.target.files[0].type != "application/vnd.openxmlformats-officedocument.wordprocessingml.document"){
        swal.fire("", "Jenis file harus docx ", "warning");
    } else if(e.target.files[0].size > 5242880){
        swal.fire("", "Besar file maksimum 5 MB", "warning");
    }else{
        let fileName = e.target.files[0].name;
        uploadDocumentFile(e.target.files[0], fileName, $('[name="fileID"]')[0].value, $(this));
    }
});

function uploadDocumentFile(file, fileName, fileId, element) {
    let formData = new FormData();
    let format = /[:"\\|,<>\/?^*]/;
    if (format.test(fileName)) {
        swal.fire('', 'File name cannot contains special character ,^*:"|<>\\ /?', 'warning')
    } else {
        fileName = fileName.replaceAll(",", "")
        formData.append("authToken", Liferay.authToken);
        formData.append("file-upload", file);
        formData.append("file-name", fileName);
        formData.append("file-id", fileId);

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

        $.ajax({
            url: uploadFileCommandURL,
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            xhr: function () {
                var xhr = new XMLHttpRequest();

                xhr.upload.addEventListener('progress', function (e) {
                    if (e.lengthComputable) {
                        var uploadPercent = e.loaded / e.total;
                        uploadPercent = (uploadPercent * 100);

                        if (parseInt(uploadPercent) === 100) {
                            htmlLoading.find("#progress-percentage").text('Please wait...');
                        } else {
                            htmlLoading.find("#progress-percentage").text(parseInt(uploadPercent) + '% uploaded');
                        }
                        htmlLoading.find(".progress-bar").width(uploadPercent + '%');
                    }
                }, false);

                return xhr;
            },
            success: function (response) {
                let data = JSON.parse(response);
                let acknowledge = data["acknowledge"];
                let fileURL = data["fileURL"];
                let fileID = data["fileID"];
                fileName = data["fileName"];
                console.log(data);

                if (acknowledge == 1) {
                    $('#fileID')[0].value = fileID;
                    $('#fileURL')[0].value = fileURL;
                    $('[name="df-doc-name"]')[0].value = fileName;

                    let successHTML = `
					<div style="margin-top: -5px;margin-bottom: 15px;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
						<div>
							<p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px;">File uploaded successfully</p>
						</div>
						<div>
							<div id="delete-file-button">
								<a href="javascript:void(0);" onclick="deleteUploadedFile(this, '${fileID}')" title="Delete File"><i class="fas fa-trash"></i></a>
							</div>

							<div id="delete-loader" style="display: none;">
								<i class="fas fa-spinner anim-rotate"></i>
							</div>
						</div>
					</div>
					`;

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
                console.log(e);
                swal.fire({
                    icon: "error",
                    title: "Failed to upload file"
                });
            },
            complete: function () {
                console.log("DONE");
            }
        });
    }
}

function deleteUploadedFile(element, fileID) {
    let parentElement1 = $(element).parents(".upload-content");
    parentElement1.find("#delete-file-button").css("display", "none");
    parentElement1.find("#delete-loader").css("display", "inherit");

    let formData = new FormData();
    formData.append("authToken", Liferay.authToken);
    formData.append("file-id", fileID);
    let parentElement  = element.parentElement.parentElement.parentElement

    let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

    $.ajax({
        url : deleteFileCommandURL,
        type : 'POST',
        data : formData,
        processData: false,
        contentType: false,
        success: function(response){
            let data = JSON.parse(response);
            if(data["acknowledge"] == 1) {
                parentElement.remove()

                parentElement1.remove();
                inputElement.value='';
            }else {
                parentElement1.find("#delete-file-button").css("display", "inherit");
                parentElement1.find("#delete-loader").css("display", "none");

                parentElement.find("[name=file-status]").text("delete failed");
            }
        },
        error: function(e){
            console.log(e);
            swal({
                icon: "error",
                title: "Failed to delete file"
            });
        },
        complete: function() {
            console.log("DONE");
        }
    });
}

    var submitProcess = false;
    $("#document-flow").submit(function(e) {
        e.preventDefault();
        if(submitProcess == false) {
            var $form = $(this);
            if (!formValidation($form)) {
                Swal.fire(
                    "Please complete all fields before save data!",
                    "",
                    "warning"
                );

                return false;
            } else {

                submitProcess = true
                var formData = new FormData(this);
                formData.append("df-company",$('[name="df-company"]').val());
                if ($('[name="df-publish"]').prop("checked")) {
                    formData.append("publish", 1);
                } else {
                    formData.append("publish", 0);
                }


                formData.append("crud-type", "save")

                createLoading();
                $.ajax({
                    url: crudDocumentFlowCommandURL,
                    type: 'post',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        if (JSON.parse(response).status == 'success') {
                            swal.fire("Success", "Your request has been saved", "success")
                                .then(function () {
                                        window.location = homeUrl;
                                    }
                                );
                        } else if (JSON.parse(response).status == 'warning') {
                            swal.fire('Sorry', JSON.parse(response).message, 'warning');
                        } else {
                            swal.fire('Sorry', 'There is an internal error', 'error');
                        }
                    },
                    error: function (data, textStatus, XMLHttpRequest) {
                        if (data.status == 500) {
                            var msg = data.responseJSON.Message
                            swal('Sorry', msg, 'error');
                        } else if (data.status == 408) {
                            swal('Sorry', 'Request Time Out, Please Try Again', 'error');
                        } else {
                            swal('Sorry', textStatus + 'error submit', 'error');
                        }
                    },
                    complete: function (jqXHR, textStatus) {
                        submitProcess = false;
                        destroyLoading();
                    }
                })
            }
        }
    })


function formValidation($form) {
    var isValidate = true;
    $form.find("input.required").each(function () {
        if ($(this).val() === "") {
            isValidate = false;
            return false;
        }
    });
    $form.find("select.required").each(function () {
        if ($(this).val() === "" || $(this).val() === null) {
            isValidate = false;
            return false;
        }
    });
    $form.find("textarea.required").each(function () {
        if ($(this).val() === "" || $(this).val() === null) {
            isValidate = false;
            return false;
        }
    });
    return isValidate;
}

var isValidationPublish = false ;
$('[name="df-publish"]').change(function () {
    console.log("Publish Change")
    if (!isValidationPublish){
        isValidationPublish = true ;
        var formData = new FormData();
        if ($('[name="df-publish"]').prop("checked")) {
            formData.append("publish", 1);
            formData.append("title", $('[name=df-title]').val());
            formData.append("companyType", $('[name=df-company]').val());
            formData.append("year", $('[name=df-year]').val());
        } else {
            formData.append("publish", 0);
            formData.append("title", $('[name=df-title]').val());
            formData.append("companyType", $('[name=df-company]').val());
            formData.append("year", $('[name=df-year]').val());
        }

        createLoading();
        $.ajax({
            url: checkDocumentExistCommandURL,
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                let responJson = JSON.parse(response)
                if (responJson.acknowledge == 0) {
                    if ($('[name="df-publish"]').prop("checked")) {
                        $('[name="df-publish"]').prop( "checked", false );
                        swal.fire('Failed to Publish', responJson.message, '');
                    }else{
                        $('[name="df-publish"]').prop( "checked", true );
                        swal.fire('Failed to Unpublish', responJson.message, '');
                    }
                }
            },
            error: function (data, textStatus, XMLHttpRequest) {
                if (data.status == 500) {
                    var msg = data.responseJSON.Message
                    swal('Sorry', msg, '');
                } else if (data.status == 408) {
                    swal('Sorry', 'Request Time Out, Please Try Again', '');
                } else {
                    swal('Sorry', textStatus + 'error submit', '');
                }
            },
            complete: function (jqXHR, textStatus) {
                isValidationPublish = false ;
                destroyLoading();
            }
        })
    }
})

$('[name="df-title"]').change(function(){
    debugger
    console.log($('[name="df-title"]').val())
    if ($('[name="df-title"]').val() == 'Surat Kuasa Pendaftaran Merek'){
        $('[name="df-company"]').val(2);
    }else{
        $('[name="df-company"]').val(1)
    }
    $('[name="df-company"]').selectpicker('refresh');
})