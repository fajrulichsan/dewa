<#assign rService = serviceLocator.findService("com.liferay.portal.kernel.service.RoleService")>
<#assign usrRoles  = rService.getUserRoles(user_id) >

<#assign isLiferayAdministrator = false >
<#list usrRoles as uRole>
    <#if uRole.getName() == "Administrator">
		<#assign isLiferayAdministrator = true />
	</#if>
</#list>

<#-- Get Custom Theme Variable -->
<#assign
	appSiteName = getterUtil.getString(theme_settings["app-name"])
	appSiteFriendlyURL = getterUtil.getString(theme_settings["friendly-url"])
>