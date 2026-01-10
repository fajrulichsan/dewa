<%@ include file="/META-INF/resources/init.jsp" %>

<style>
    section#portlet_dewa_web_AgendahomepagePortlet {
        max-width: unset;
    }

    div#column-1 {
        padding: 0px;
    }

    .container-fluid.content_ {
        padding: 1px;
    }

    #content .custom-nav {
        margin-left: 1.4rem !important;
        margin-right: 1.4rem !important;
    }

    .splide__agenda {
        padding: 20px 6rem;
    }

    .splide__slide__agenda img {
        display: block;
        width: 100%;
        border-radius: 8px;
        transform-origin: center center;
        height: 500px;
    }

    .splide__agenda .splide__arrow {
        display: none;
    }

    .image-agenda {
        height: 150px;
        border-radius: 6px 6px 0px 0px;
    }

    .card-agenda {
        border-radius: 6px;
        /*height: 250px;*/
        border: 1px solid #F4F4F4;
        margin-bottom: 10px;
        box-shadow: 2px 5px 9px 0px rgba(0, 0, 0, 0.15);
    }

    .desc-agenda {
        height: 130px;
        padding: 15px;
    }

    #splide-agenda .splide__pagination {
        display: none !important;
    }

    .agenda_detail {
        display: flex;
        gap: 10px;
        align-items: flex-start;
        max-width: 100%;
        margin-bottom: 10px;
    }

    img.icon_agenda {
        width: 20px;
        height: 20px;
    }

    .overflow-agenda {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        max-width: 100%;
    }

    .header_splide_agenda {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    .no-agenda {
        display: flex;
        justify-content: center;
    }

</style>

<c:choose>
    <c:when test="${acaraList.length() gt 0}">
        <div id="splide-agenda" class="splide__agenda splide">
            <div class="header_splide_agenda">
                <h3 style="font-weight: bold;">Agenda</h3>
                <h5><a href="/group/dealink/calendar-event" style="color: #00448f;">Lihat Semua</a></h5>
            </div>

            <div class="splide__track">
                <ul class="splide__list">

                    <c:forEach begin="0" end="${acaraList.length() - 1}" varStatus="loop">
                        <li class="splide__slide">
                            <div class="card-agenda">
                                <div class="image-agenda"
                                     style="background: url(${acaraList.getJSONObject(loop.index).getString("image")}) no-repeat 50% 50% / cover;"></div>
                                <div class="desc-agenda">
                                    <a class="overflow-agenda" style="font-weight: bold; color: black"
                                       href="/group/dealink/calendar-detail?id=${acaraList.getJSONObject(loop.index).getString("id")}">
                                            ${acaraList.getJSONObject(loop.index).getString("judul")}
                                    </a>
                                    <div class="agenda_detail" style="padding-top: 10px;">
                                        <img class="icon_agenda"
                                             src="<%=request.getContextPath()%>/assets/img/calendar_white.svg">
                                        <span class="overflow-agenda">${acaraList.getJSONObject(loop.index).getString("date")}</span>
                                    </div>
                                    <div class="agenda_detail">
                                        <img class="icon_agenda"
                                             src="<%=request.getContextPath()%>/assets/img/location.svg">
                                        <span class="overflow-agenda">${acaraList.getJSONObject(loop.index).getString("location")}</span>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>

                </ul>
            </div>
        </div>
    </c:when>
<%--    <c:otherwise>--%>
<%--        <div class="splide__agenda">--%>
<%--            <div class="header_splide_agenda">--%>
<%--                <h3 style="font-weight: bold;">Agenda</h3>--%>
<%--                <h5><a href="/group/dealink/calendar-event" style="color: #00448f;">Lihat Semua</a></h5>--%>
<%--            </div>--%>
<%--            <div class="no-agenda">--%>
<%--                <h5>Tidak ada agenda terdekat</h5>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </c:otherwise>--%>
</c:choose>

<script>
    $(document).ready(function () {
        var splideAgendaElement = document.getElementById('splide-agenda');

        if (splideAgendaElement) {
            new Splide('#splide-agenda', {
                drag: 'free',
                perPage: 4,
                focus: 'left',
                pagination: false,
                gap: '2%',
                breakpoints: {
                    1024: {
                        perPage: 3,
                    },
                    767: {
                        perPage: 2,
                    },
                    500: {
                        perPage: 1,
                    },
                },
            }).mount();
        }
    });

</script>