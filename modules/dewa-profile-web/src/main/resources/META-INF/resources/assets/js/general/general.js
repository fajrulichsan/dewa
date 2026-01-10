function commaSeparateNumber(val) {
    while (/(\d+)(\d{3})/.test(val.toString())) {
        val = val.toString().replace(/(\d+)(\d{3})/, '$1' + ',' + '$2');
    }
    return val;
}

function removeComma(val){
    return val.replace(/\,/g,'')
}

function createSelectize(selectAttr, url, index, selected, maxItem){
    let $selectAddId = $(selectAttr).eq(index).selectize({
        valueField : 'Value',
        labelField : 'Text',
        searchField : 'Text',
        maxItems : maxItem, //Max items selectable in the textbox
        // maxOptions : 5, //Max options to render at once in the dropdown
        preload : true,
        options : [],
        plugins : {
            remove_button:{
                title:'Remove this item',
            }
        },
        load: function(query, callback) {
            $(selectAttr).siblings('.selectize-control').find('.selectize-input').addClass('grey')
            if (!query.length) return callback();
            $.ajax({
                url : url,
                data : {keyword : query},
                type : 'post',
                dataType : "json",
                error : function() {
                    callback();
                },
                success: function(res) {
                    callback(res);
                }
            });
        },
        onChange: function(value) {
            if(value == ''){
                this.$input.siblings('.selectize-control').find('.selectize-input').removeClass('grey').addClass('error-clr')
            }else{
                this.$input.siblings('.selectize-control').find('.selectize-input').removeClass('error-clr').addClass('grey')
            }
        },
        // onInitialize: function(){
        //     $('.selectize-dropdown').append(''+
        //         '<button type="button" class="btn btn-default selall" margin-left="5px"><i class="fa fa-plus"></i> New Master Data</button>'
        //     );
        //     //console.log(this.$dropdown);
        //     self = this;
        //
        //     this.$dropdown.on("click", ".btn.selall", function(event) {
        //         event.preventDefault();
        //         self.setValue(Object.keys(self.options));
        //         self.focus();
        //         return false;
        //     });
        // }
    })
    let element = $selectAddId[0].selectize
    $.each(selected, function(i, v){
        element.addOption({Value:v.Value, Text:v.Text})
        element.addItem(v.Value)
    })


}

function createSelectizeRemoveButton(selectAttr, list, index, selected, maxItem){
    var $selectAddId = $(selectAttr).eq(index).selectize({
        maxItems: maxItem,
        valueField: 'Value',
        labelField: 'Text',
        searchField: 'Text',
        options: list,
        create: false,
        plugins : {
            remove_button:{
                title:'Remove this item',
            }
        },
    });
    let element = $selectAddId[0].selectize
    $.each(selected, function(i, v){
        // element.addOption({Value:v.Value, Text:v.Text})
        element.addItem(v.Value)
    })
}

function createSelectize2(selectAttr, list, index, selected, maxItem){
    let $selectAddId = $(selectAttr).eq(index).selectize({
        valueField : 'Value',
        labelField : 'Text',
        searchField : 'Text',
        maxItems : maxItem,
        // preload : true,
        options : list,
        onChange: function(value) {
            if(value == ''){
                this.$input.siblings('.selectize-control').find('.selectize-input').removeClass('grey').addClass('error-clr')
            }else{
                this.$input.siblings('.selectize-control').find('.selectize-input').removeClass('error-clr').addClass('grey')
            }
        },
    })
    let element = $selectAddId[0].selectize
    $.each(selected, function(i, v){
        element.addItem(v.Value)
    })
}



function createSelectizeCompanyStatus(selectAttr, url, index, selected, maxItem){
    let $selectAddId = $(selectAttr).eq(index).selectize({
        valueField : 'Value',
        labelField : 'Text',
        searchField : 'Text',
        maxItems : maxItem,
        preload : true,
        options : [],
        plugins : {
            remove_button:{
                title:'Remove this item',
            }
        },
        load: function(query, callback) {
            $(selectAttr).siblings('.selectize-control').find('.selectize-input').addClass('grey')
            if (!query.length) return callback();
            $.ajax({
                url : url,
                data : {keyword : query},
                type : 'post',
                dataType : "json",
                error : function() {
                    callback();
                },
                success: function(res) {
                    callback(res.Data);
                }
            });
        },
        onChange: function(value) {
            if(value == ''){
                this.$input.siblings('.selectize-control').find('.selectize-input').removeClass('grey').addClass('error-clr')
            }else{
                this.$input.siblings('.selectize-control').find('.selectize-input').removeClass('error-clr').addClass('grey')
            }
        }
    })

    let element = $selectAddId[0].selectize
    $.each(selected, function(i, v){
        element.addOption({Value:v.Value, Text:v.Text})
        element.addItem(v.Value)
    })

}


function createLoading(){
	var element = ''+
	'<div class="loading-layer">'+
		'<div class="spinner">'+
			'<div class="bounce1"></div>'+
			'<div class="bounce2"></div>'+
			'<div class="bounce3"></div>'+
		'</div>'+
	'</div>';
	$('body').append(element);
}

function destroyLoading(){
    $('.loading-layer').remove();
}

