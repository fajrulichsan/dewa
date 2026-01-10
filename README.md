# Liferay Upgrade Documentation
___

## Deprecated Module or Missing Module
___
The following are modules or functions that are deprecated or have been removed in liferay 7.4

| Deprecated                                                                                                                  | Changed                                                                                                                                                                 | Example Code |
|-----------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------|
| ```PortletURL portletURL = new PortletURLImpl(actionRequest, portletName, layout.getPlid(),PortletRequest.RENDER_PHASE);``` | ```PortletURL portletURL= PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(actionRequest), portletName, layout.getPlid(), PortletRequest.RENDER_PHASE);``` |              |
| ```import com.liferay.portal.kernel.util.StringPool;```                                                                     | ```import com.liferay.petra.string.StringPool```                                                                                                                        |              |
| ```import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;```                                                   | ```import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;```                                                                            | #1           |

### Example Code #1
```java
// not found
List<AssetEntry> ae = AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(ac.getCategoryId());
// fix
List<AssetEntryAssetCategoryRel> ae = AssetEntryAssetCategoryRelLocalServiceUtil
                                           .getAssetEntryAssetCategoryRelsByAssetCategoryId(ac.getCategoryId());
```

## Setting `build.gradle`
___
From `Liferay 7.0`
```
dependencies {
    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
    compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib"
    compileOnly group: "javax.portlet", name: "portlet-api"
    compileOnly group: "javax.servlet", name: "javax.servlet-api"
    compileOnly group: "jstl", name: "jstl"
    compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations"
}
```
To `Liferay 7.4`
```
dependencies {
    compileOnly group: "com.liferay.portal", name: "release.dxp.api"	
}
```

## Ignoring Module
___
There is an issue when you include the Apache HTTP Client Module in your project. To fix this, you can exclude `org.apache.tools.ant` from your module project.

You just need to add this to the `bnd.bnd` file.
```bnd
Import-Package: \
    !org.apache.tools.ant,\
    *;
```

## Creating New Theme
___
### Setup
___
1. Install **Node.js** and **npm** on your local computer.
2. Install **Dart SASS** using `npm install -g sass`.
3. If you have already done, next you need to install the Liferay Theme Generator with this command:
```
npm install -g generator-liferay-theme@10.x.x
```
4. Install the Yeoman and gulp dependencies with this command:
```
npm install -g yo gulp
```
### Running the Liferay Theme Generator
___
1. Run the Liferay Theme Generator using Yeoman:
```
yo liferay-theme
```
2. Type a name for your theme at the prompt. Press Enter to use the default, “My Liferay Theme”.
```
? What would you like to call your theme? (My Liferay Theme)
```
3. Type an ID for your theme at the prompt. When the theme is generated, the ID determines the name of the folder where your theme is built. You can also press Enter to use a default ID based on the name.
```
? What id would you like to give to your theme? (my-liferay-theme)
```
4. Use the arrow keys to select the version of Liferay DXP to build the theme for at the prompt.
```
? Which version of Liferay is this theme for? (Use arrow keys)
❯ 7.4
  7.3
  7.2
```
5. Answer whether you would like to add Font Awesome as an available font for your theme at the prompt.
6. After the theme is generated, complete the process by using the arrow keys to select the appropriate deployment type for your theme: deploy with a local app server, Docker container, or other URL.
```
? Select your deployment strategy (Use arrow keys)
❯ Local App Server
  Docker Container
  Other
```
7. Provide the location of the app server at the prompt, depending on which deployment type you are using. You may provide the app server directory, the Docker container name, or the host URL to locate the app server.

The theme is then generated and placed inside of a folder named after the ID you have chosen. You can now build and deploy it to your DXP instance by running `gulp deploy` from the theme’s base folder.

Visit this link to see full documentation: [Setting Up an Environment and Creating a Theme](https://learn.liferay.com/w/dxp/site-building/site-appearance/themes/theme-development/getting-started/setting-up-an-environment-and-creating-a-theme).