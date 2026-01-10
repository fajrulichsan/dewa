<%@ include file="/META-INF/resources/init.jsp" %>

<style>
    .point-list-detail-materi {
        padding: 6px;
    }

    div#detail-judul-materi{
        border-radius: 8px;
        box-shadow: 0 8px 20px 0 rgba(13, 12, 34, 0.08);
    }

    div#judul-materi {
        padding: 10px 20px;
        border-radius: 8px;
        display: flex;
        justify-content: space-between;
        align-items: baseline;
        margin-top: 5rem;
        box-shadow: 0 8px 20px 0 rgba(13, 12, 34, 0.08);
    }

    div#detail-materi {
        padding: 20px 30px 40px 30px;
    }

    a.file-materi {
        color: black;
        cursor: pointer;
    }

    .btn-back, .btn-back:hover {
        color: white;
        background-color: #00448f;
        height: 40px;
        padding: 7px 20px;
        border-radius: 10px;
    }
</style>

<div id="detail-judul-materi">
    <div id="judul-materi">
        <div>
            <h4>${topikMateri}</h4>
            <p>${jenisMateri}</p>
        </div>
        <button role="button" class="btn btn-back" onclick="backmateriPelatihan(this)">Kembali</button>
    </div>

    <div id="detail-materi">
        <c:choose>
            <c:when test="${hasil.length() gt 0}">
                <c:forEach begin="0" end="${hasil.length() - 1}" varStatus="loop">
                    <h4 id="file-${hasil.getJSONObject(loop.index).getString("id")}">${hasil.getJSONObject(loop.index).getString("name")}</h4>

                    <c:if test='${hasil.getJSONObject(loop.index).getJSONArray("lampiran").length() gt 0}'>
                        <c:forEach begin="0"
                                   end='${hasil.getJSONObject(loop.index).getJSONArray("lampiran").length() - 1}'
                                   varStatus="loop2">
                            <div class="list-detail-materi">
                                <div class="point-list-detail-materi">
                                    <img src="<%=request.getContextPath()%>/assets/img/file-check.png">
                                    <span>
                                        <a class="file-materi"
                                             href='${hasil.getJSONObject(loop.index).getJSONArray("lampiran").getJSONObject(loop2.index).getString("url")}'>
                                            ${hasil.getJSONObject(loop.index).getJSONArray("lampiran").getJSONObject(loop2.index).getString("nama")}
                                        </a>
                                    </span>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <img src="<%=request.getContextPath()%>/assets/img/document-not-found.svg" style="width: 50%;margin-left: 25%;">
            </c:otherwise>
        </c:choose>
    </div>
</div>

<script>
    function backmateriPelatihan (element){
        var url = themeDisplay.getPortalURL() + "/group/dealink/materi-pelatihan";
        window.location.href = url;
    }
</script>