package com.astra.dealink.rest.api.service;

import com.astra.dewa.model.Token;
import com.astra.dealink.rest.api.util.DateUtil;
import com.astra.dewa.service.TokenLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;
import java.util.List;

public class TokenServiceImpl implements TokenService {
   @Override
   public Token createToken(String tokenNumber, Date date) throws PortalException {
      Token token = TokenLocalServiceUtil.createToken(0);
      token.setTokenNumber(tokenNumber);
      token.setCreatedDate(date);
      token.setExpiredDate(DateUtil.expiredDateToken(date));
      TokenLocalServiceUtil.addToken(token);
      return token;
   }

   @Override
   public Token getToken(String tokenNumber) {
      DynamicQuery dynamicQuery = TokenLocalServiceUtil.dynamicQuery();
      dynamicQuery.add(RestrictionsFactoryUtil.eq("TokenNumber", tokenNumber));
      List<Token> tokenList = TokenLocalServiceUtil.dynamicQuery(dynamicQuery);
      if (tokenList.isEmpty()) {
         return null;
      }
      return tokenList.get(0);
   }
}
