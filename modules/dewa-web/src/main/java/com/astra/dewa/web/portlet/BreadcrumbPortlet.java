package com.astra.dewa.web.portlet;

import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.portlet.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Dewa UI",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Breadcrumb Dewa",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/breadcrumb/view.jsp",
                "javax.portlet.name=" + DewaWebPortletKeys.BREADCRUMB_DEWA,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)

public class BreadcrumbPortlet extends MVCPortlet {

    private final Log log = LogFactoryUtil.getLog(BreadcrumbPortlet.class);

    @Override
    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        List<String> listBreadCrumb = new ArrayList<>();

        try{
            Layout layout = LayoutLocalServiceUtil.getLayout(themeDisplay.getPlid());
            log.info(layout.toString());
            log.info(layout.getLayoutId());
            List<String> menuList = new ArrayList<>();
            menuList = printParentLayouts(themeDisplay.getPlid());
            log.info(menuList.toString());


            for (int i = menuList.size()-1; i >= 0; i--) {
                listBreadCrumb.add(menuList.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        request.setAttribute("listBreadCrumb", listBreadCrumb);
        super.render(request, response);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
        super.serveResource(request, response);
    }

    public List<String> printParentLayouts(long layoutId) {
        List<String> menuList = new ArrayList<>();
        try {
            Layout layout = LayoutLocalServiceUtil.getLayout(layoutId);
            long parentLayoutId = layout.getParentLayoutId();
            System.out.println("parentLayoutId : " + parentLayoutId);

            if (parentLayoutId != 0) {
                menuList.add(namePage(layout.getName()));

                // Panggil fungsi rekursif untuk parent layout dan tambahkan hasilnya ke daftar
                menuList.addAll(printParentLayouts(layout.getParentPlid()));
            } else {
                // Ini adalah root layout
                System.out.println("Root Layout ID: " + layout.getLayoutId());
                menuList.add(namePage(layout.getName()));
            }
        } catch (PortalException e) {
            e.printStackTrace();
        }
        log.info(menuList.toString());
        return menuList;
    }

    private String namePage (String xmlData){
        String result = "";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(xmlData.getBytes()));

            // Mendapatkan elemen root
            Element root = document.getDocumentElement();

            // Mendapatkan nilai dari elemen "Name" dengan language-id="en_US"
            NodeList nameNodes = root.getElementsByTagName("Name");
            for (int i = 0; i < nameNodes.getLength(); i++) {
                Node nameNode = nameNodes.item(i);
                String languageId = ((Element) nameNode).getAttribute("language-id");

                if (languageId.equals("en_US")) {
                    result = nameNode.getTextContent();
                    System.out.println("==>>>  " + result);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
