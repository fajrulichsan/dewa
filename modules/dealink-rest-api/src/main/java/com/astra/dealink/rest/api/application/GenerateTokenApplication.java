package com.astra.dealink.rest.api.application;

import com.astra.dealink.rest.api.constants.DewaRestApiKeys;
import com.astra.dealink.rest.api.service.TokenServiceImpl;
import com.astra.dealink.rest.api.util.JSONUtil;
import com.astra.dealink.rest.api.util.TokenUtil;
import com.astra.dewa.model.Token;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

/**
 * @author psmmutia0113
 */
@ApplicationPath("/token")
@Component(
        immediate = true,
        service = Application.class
)
public class GenerateTokenApplication extends Application {
    private final TokenServiceImpl tokenService = new TokenServiceImpl();
    private final Log _log = LogFactoryUtil.getLog(GenerateTokenApplication.class);

    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }

    @GET
    @Produces(ContentTypes.APPLICATION_JSON)
    public Response postToken(@Context HttpServletRequest request) throws PortalException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        try {
            if (Validator.isNull(userName) || Validator.isNull(password) ||
                    !userName.equalsIgnoreCase(DewaRestApiKeys.USER_NAME) ||
                    !password.equalsIgnoreCase(DewaRestApiKeys.PASSWORD)) {

                _log.info(JSONUtil.createResponseJson(0, "", "Invalid User Name or Password").toJSONString());

                return Response
                        .status(Response.Status.FORBIDDEN)
                        .entity(JSONUtil.createResponseJson(0, "", "Invalid User Name or Password").toJSONString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            String tokenNumber = TokenUtil.generateToken(256, DewaRestApiKeys.CHARACTERS);
            Token token = tokenService.createToken(tokenNumber, new Date());
            JSONObject response = JSONFactoryUtil.createJSONObject();
            response.put("access_token", token.getTokenNumber());
            response.put("token_type", "Bearer");
            response.put("expires_in", 3600);

            _log.info(response.toJSONString());

            return Response
                    .ok(response.toJSONString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (SystemException e) {
            _log.error(e.getMessage(), e);
            return Response
                    .ok(JSONUtil.createResponseJson(0, "", e.getMessage()).toJSONString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Activate
    protected void activate() {
        _log.info(">>> REST Application /token has been activated.");
    }

    @Deactivate
    protected void deactivate() {
        _log.info(">>> REST Application /token has been deactivated.");
    }
}
