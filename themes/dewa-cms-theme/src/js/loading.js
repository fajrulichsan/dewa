function buildLoading(){
    var loading ='';
        loading += '<div class="loading-layer">';
            loading += '<div class="loading-box">';
                loading +='<div class="sk-cube-grid">';
                    loading += '<div class="sk-cube sk-cube1"></div>';
                    loading += '<div class="sk-cube sk-cube2"></div>';
                    loading += '<div class="sk-cube sk-cube3"></div>';
                    loading += '<div class="sk-cube sk-cube4"></div>';
                    loading += '<div class="sk-cube sk-cube5"></div>';
                    loading += '<div class="sk-cube sk-cube6"></div>';
                    loading += '<div class="sk-cube sk-cube7"></div>';
                    loading += '<div class="sk-cube sk-cube8"></div>';
                    loading += '<div class="sk-cube sk-cube9"></div>';
                loading += '</div>';
                loading += '<p>Please Wait...</p>';
            loading += '</div>';
        loading += '</div>';
    $('#wrapper').append(loading)
}
/*
function loadingSpinner(){
    var loading ='';
    loading += '<div class="loading-layer">';
        loading += '<div class="loading-box">';
            loading += '<div class="lds-ring"><div></div><div></div><div></div><div></div>';
            loading += '<img class="img-spinners" src="/o/cist-employee-theme/images/cist_tiny.png">';
            loading += '</div>';
        loading += '</div>';
    loading += '</div>';
    $('#wrapper').append(loading)
}*/

function destroyLoading(){
    $('.loading-layer').remove();
}