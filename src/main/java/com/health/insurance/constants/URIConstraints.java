package com.health.insurance.constants;

public interface URIConstraints {

    public static final String BASE_API = "/api/v1";
    
    //Product level
    public static final String PRODUCT = BASE_API + "/product";
    public static final String GET_PRODUCT =  "/getProductDetails";
    public static final String INSERT_PRODUCT =  "/insertProductDetails";
    
    //Party level
    public static final String INSERT_PARTY =  "/party/createParty";
    public static final String GET_ACTIVE_PARTIES = "/active-parties";
    public static final String GET_PARTIES_BY_STAKE = "/party-by-stake"; 
    
    //Policy level
    public static final String POLICY =  BASE_API + "/product";
    public static final String CREATE_QUOTE = "/createQuote"; 
 
}
