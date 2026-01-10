<%@ include file="../init.jsp" %>

<portlet:resourceURL id="/update_last_read" var="updateReadURL" />
<portlet:resourceURL id="/send_feedback" var="sendFeedbackURL" />

<style>

  <%--  BREADCRUMB  --%>
  div#myBreadcrumbs {
    margin-top: -20px;
    margin-bottom: 20px;
    background-color: #00079D;
    padding: 0.5em 35px;
    color: #95B6DF;
  }

  div#myBreadcrumbs > span > a {
    color: #95B6DF;
    text-decoration: none;
  }

  div#myBreadcrumbs span,
  div#myBreadcrumbs i{
    margin-right: 5px;
  }

  div#myBreadcrumbs span:last-child {
    color: #FFFFFF;
  }

  .main-container-pdf{
    position: relative;
    height: 85vh;
    width: 100%;
    margin: 0 auto;
    background-color: #ddd;
    border: 1px solid #ccc;
    overflow: hidden;
  }

  .toggle-nav-custom{
    position: absolute;
    z-index: 4;
    padding: 10px;
    border-radius: 0 5px 5px 0;
    top: 20px;
    left: 0;
    background-color: rgba(0,0,0,0.3);
    cursor: pointer;
    transition: all 0.3s ease;
  }

  .pdf-toolbar {
    position: absolute;
    bottom: 4%;
    display: flex;
    align-items: center;
    gap: 15px;
    left: 50%;
    transform: translateX(-50%);
    background-color: #fff;
    padding: 7px;
    border-radius: 40px;
    border: 1px solid #eee;
  }

  .button-toolbar{
    width: 40px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    cursor: pointer;
    transition: all 0.2s ease;
    border-radius: 50%;
  }

  .button-toolbar:hover{
    background-color: #eee;
  }

  .toggle-fullscreen > #close-fullscreen{
    display: none;
  }

  .main-container-pdf:fullscreen .toggle-fullscreen > #close-fullscreen{
    display: initial;
  }

  .main-container-pdf:fullscreen .toggle-fullscreen > #open-fullscreen{
    display: none;
  }

  .open-nav .toggle-nav-custom{
    left: 250px;
  }

  .open-nav .toggle-nav-custom > i{
    transform: rotate(180deg);
  }

  .open-nav .sidenav{
    transform: translateX(0);
  }

  .toggle-nav-custom:hover{
    padding-left: 15px;
  }

  .sidenav{
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 250px;
    background-color: #ddd;
    /*box-shadow: inset 0 0 20px rgba(0,0,0,0.3);*/
    text-align: center;
    overflow-x: hidden;
    overflow-y: scroll;
    z-index: 2;
    transform: translateX(-100%);
    transition: transform 0.3s ease;
  }

  .nav-item-custom{
    display: block;
    margin: 25px 0;
    text-decoration: none;
    color: #333 !important;
  }

  .nav-item-custom > img {
    width: 180px;
    height: auto;
    box-shadow: 0 10px 6px -6px #777;
    transition: transform 0.3s ease;
  }

  .nav-item-custom > .paging{
    margin-top: 12px;
    white-space: nowrap;
  }

  .nav-item-custom:hover > img{
    transform: scale(1.02);
  }


  .content{
    height: 100%;
    background-color: #ddd;
    text-align: center;
    overflow-y: scroll;
    transition: all 0.3s ease;
  }

  .content-image{
    margin: 0 auto;
    width: 100%;
  }

  .image-content{
    margin: 10px 0;
  }

  .image-content > img{
    min-height: 150px;
    width: 98%;
    box-shadow:
            0 2.8px 2.2px rgba(0, 0, 0, 0.034),
            0 6.7px 5.3px rgba(0, 0, 0, 0.048),
            0 12.5px 10px rgba(0, 0, 0, 0.06),
            0 22.3px 17.9px rgba(0, 0, 0, 0.072),
            0 41.8px 33.4px rgba(0, 0, 0, 0.086),
            0 100px 80px rgba(0, 0, 0, 0.12);
  }

  .highlight-orange {
    padding: 4px 8px;
    background-color: #FCDACF;
    color: #F15D2D;
    border-radius: 5px;
    font-size: 12px;
  }

  .highlight-blue {
    padding: 4px 8px;
    background-color: #DBEDFF;
    color: #014689;
    border-radius: 5px;
    font-size: 12px;
  }

  .info {
    display: flex;
    align-items: baseline;
    justify-content: space-between;
    gap: 5px;
  }

  .info-button img {
    width: 15px;
  }

  .document-title {
    font-size: 28px;
    font-weight: 600;
    padding: 1rem 0;
  }

  .open-modal-container {
    position: absolute;
    top: 1em;
    right: 2em;
    transform: translateY(-200%);
    transition: all 0.3s ease-in-out;
  }

  .open-modal-container.open {
    transform: translateY(0);
  }

  div#notFoundContainer, div#notFoundContainer img {
    width: 100%;
  }

  div#notFoundContainer {
    padding: 4em 0 0;
    max-width: 600px;
    margin: 0 auto;
  }

  @media (max-width: 600px) {
    div#myBreadcrumbs {
      width: 100%;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      padding: 0.5em 10px;
    }

    .document-title {
      font-size: 18px;
    }
  }


</style>

<section id="documentPreview" style="margin-top: 2em;">
  <c:choose>
    <c:when test="${documentAccess eq true}">

      <%--  MAIN DOCUMENT  --%>
      <div class="main-container-pdf">
        <div class="open-modal-container">
          <button class="btn btn-xs" style="background-color: #014689;color: #fff;" onclick="openModalFeedback()">Send Confirmation & Feedback</button>
        </div>

        <div class="toggle-nav-custom" onclick="toggleSideBar()">
          <i class="fas fa-chevron-right"></i>
        </div>

        <div class="pdf-toolbar">
          <div class="toggle-zoom-out button-toolbar" title="Zoom Out" onclick="zoomOut()">
            <i class="fas fa-search-minus"></i>
          </div>
          <span class="zoom-text">100%</span>
          <div class="toggle-zoom-in button-toolbar" title="Zoom In" onclick="zoomIn()">
            <i class="fas fa-search-plus"></i>
          </div>
          <div class="toggle-reset-zoom button-toolbar" title="Reset Zoom" onclick="resetZoom()">
            <i class="fas fa-sync"></i>
          </div>
          <div class="toggle-fullscreen button-toolbar" title="Fullscreen" onclick="fullScreen()">
            <i id="open-fullscreen" class="fas fa-expand"></i>
            <i id="close-fullscreen" class="fas fa-compress"></i>
          </div>
        </div>

        <div class="sidenav">
          <c:if test="${documentAvailable eq true}">
            <c:forEach var="linkPDF" items="${documentPages}" varStatus="loop">
              <a href="javascript:void(0);" class="nav-item-custom thumbnail-pdf" data-target="#pdfPage_${loop.index}" onclick="scrollToPage(this)">
                <img src="${linkPDF}" alt="Thumbnail ${loop.index + 1}" loading="lazy">
                <p class="paging">Page ${loop.index + 1}</p>
              </a>
            </c:forEach>
          </c:if>
        </div>

        <div class="content">
          <div class="content-image" data-zoom="100">
            <c:if test="${documentAvailable eq true}">
              <c:forEach var="linkPDF" items="${documentPages}" varStatus="loop">
                <div class="image-content" id="pdfPage_${loop.index}">
                  <img src="${linkPDF}"  alt="Document ${loop.index + 1}">
                </div>
              </c:forEach>
            </c:if>
          </div>
        </div>
      </div>
    </c:when>
    <c:otherwise>
      <c:choose>
          <c:when test="${documentAvailable eq true}">
              <div class="document-title">Sorry, You Can't View This Document</div>
              <p>You don't have access to view this document.</p>
              <div class="button-container" style="margin: 2em 0;">
                  <button class="btn btn-outlined" type="button" onclick="window.location.href = '${currentSiteURL}/home'">Next Time
                  </button>
              </div>
          </c:when>
          <c:otherwise>
              <div id="notFoundContainer" style="display: block;">
                  <img src="<%=request.getContextPath()%>/assets/img/document-not-found.svg">
              </div>
        </c:otherwise>
      </c:choose>
    </c:otherwise>
  </c:choose>


</section>

<script>
  var lastReadPage = 0;
  var options = {
    root: document.querySelector('.content'),
    rootMargin: '0px',
    threshold: 0.3
  }

  // Document sidebar toggle function
  function toggleSideBar() {
    $(".main-container-pdf").toggleClass("open-nav");
  }

  function zoomOut() {
    var $zoomArea = $(".content-image");
    var currentZoom = $zoomArea.data("zoom");
    currentZoom -= 10;

    if (currentZoom < 20) return false;

    $zoomArea.data("zoom", currentZoom);
    $zoomArea.css("width", currentZoom + "%");
    $("span.zoom-text").text(currentZoom + "%");
  }

  function zoomIn() {
    var $zoomArea = $(".content-image");
    var currentZoom = $zoomArea.data("zoom");
    currentZoom += 10;

    $zoomArea.data("zoom", currentZoom);
    $zoomArea.css("width", currentZoom + "%");
    $("span.zoom-text").text(currentZoom + "%");
  }

  function resetZoom() {
    var $zoomArea = $(".content-image");

    $zoomArea.data("zoom", 100);
    $zoomArea.css("width", "100%");
    $("span.zoom-text").text("100%");
  }


  // Toggle fullscreen function
  function fullScreen() {
    var divFullscreen = $(".main-container-pdf")[0];

    if (!document.fullscreenElement) {
      divFullscreen.requestFullscreen();
    } else {
      document.exitFullscreen();
    }
  }


  // Scroll to selected page function
  function scrollToPage(element) {
    var dataTarget = $(element).data("target");
    var scrollTop = $(dataTarget).position().top;

    $(".content").animate({
      scrollTop: scrollTop + $(".content").scrollTop()
    }, 1000);
  }

  function openModalFeedback() {
    $("#feedbackModal").fadeIn();
    $("#feedbackModal").addClass("open");
    $("body").addClass("modal-open");
  }
</script>