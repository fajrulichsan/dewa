<%@ include file="/META-INF/resources/init.jsp" %>

<%--<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/@splidejs/splide@latest/dist/css/splide.min.css'>--%>
<%--<script src='https://cdn.jsdelivr.net/npm/@splidejs/splide@2.4.6/dist/js/splide.min.js'></script>--%>

<style>
    section#portlet_dewa_web_BannerNonCMSPortlet {
        max-width: unset;
    }

    div#column-1 {
        padding: 0px;
    }

    .container-fluid.content_ {
        padding: 0px;
    }

    #content .custom-nav {
        margin-left: 1.4rem !important;
        margin-right: 1.4rem !important;
    }

    section#home-splide{
        position: relative;
        width: 100%;
        background: linear-gradient(to bottom, #00448F 50%, white 50%);
        top: -20px;
        z-index: 0;
        padding-bottom: 5em;
    }

    .root-div.has-control-menu section#home-splide{
        top: -50px;
    }

    .splide {
        padding: 20px 0;
    }

    .splide__slide img {
        display: block;
        width: 100%;
        border-radius: 8px;
        transition: transform 400ms;
        transform: scale(0.9);
        transform-origin: center center;
        height: 600px;
        object-fit: cover;
    }

    .splide__slide.is-active img {
        transform: scale(1);
    }

    .splide .splide__arrow {
        height: 5em;
        width: 5em;
    }

    .splide__pagination {
        display: block !important;
        text-align: center;
        bottom: -2em !important;
    }

    .splide__pagination__page {
        transition: all .2s linear;
        background: #D9D9D9 !important;
    }

    .splide__pagination__page.is-active {
        width: 25px;
        border-radius: 20px;
        transform: scale(1);
        background: #0c3387 !important;
    }

    .no-banner-container {
        background: #fefefe;
        opacity: .95;
        height: 600px;
        aspect-ratio: 2.5/ 1;
        margin-left: auto;
        margin-right: auto;
        border-radius: 8px;
    }

    .no-banner-image {
        display: block;
        margin-left: auto;
        margin-right: auto;
        height: 600px;
    }

    @media only screen and (max-width: 480px) {
        .splide__slide img {
            height: 300px;
        }
        .splide__slide {
            height: 50%;
        }
        .no-banner-image {
            height: 300px;
        }
    }

    @media only screen and (min-width: 481px) and (max-width: 780px){
        .splide__slide img {
            height: 400px;
        }
        .splide__slide {
            height: 60%;
        }
        .no-banner-image {
            height: 400px;
        }
    }

    @media only screen and (min-width: 768px) and (max-width: 991px){
        .splide__slide img {
            height: 500px;
        }
        .splide__slide {
            height: 65%;
        }
        .no-banner-image {
            height: 500px;
        }
    }
</style>

<section id="home-splide">
        <c:choose>
            <c:when test="${not empty bannerList}">
                <div id="splide" class="splide">
                    <div class="splide__track">
                        <ul class="splide__list">

                        <c:forEach var="banner" items="${bannerImages}" varStatus="loop">
                            <li class="splide__slide"><img src="${banner}"></li>
                        </c:forEach>

                        </ul>
                    </div>
                </div>
            </c:when>

            <c:otherwise>
                <div class="no-banner-container">
                    <img class="no-banner-image" src="<%=request.getContextPath()%>/assets/img/document-not-found.svg">
                </div>
            </c:otherwise>
        </c:choose>
</section>


<script>
    var banyakBanner = ${bannerList.size()};

    $(document).ready(function() {
        if (banyakBanner == 1) {
            new Splide('#splide', {
                perPage: 1,
                focus: 'center',
                autoplay: true,
                interval: 4000,
                pagination: true,
                padding: '10%',
                arrow: true,
            }).mount();
        } else if (banyakBanner > 1) {
            new Splide('#splide', {
                type: 'loop',
                perPage: 1,
                focus: 'center',
                autoplay: true,
                interval: 4000,
                pagination: true,
                padding: '10%',
                arrow: true,
            }).mount();
        }
    });
</script>