package com.astra.dealink.rest.api.service;

import com.astra.dewa.model.Token;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

public interface TokenService {

    Token createToken(String tokenNumber, Date date) throws PortalException;
    Token getToken(String tokenNumber) throws PortalException;
}
