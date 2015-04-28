package com.permoveo.apps.jumboshrimp.oauth;

import android.util.Log;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.HmacSha1MessageSigner;
import oauth.signpost.signature.QueryStringSigningStrategy;

/**
 * Created by byfieldj on 4/27/15.
 */
public class OAuthUrlSigner {

    private String mUrl;
    private static String CONSUMER_KEY;
    private static String CONSUMER_SECRET;

    public OAuthUrlSigner(String url){
        mUrl = url;
    }

    public OAuthUrlSigner(String url, String consumerKey, String consumerSecret){
       mUrl = url;
       CONSUMER_KEY = consumerKey;
       CONSUMER_SECRET = consumerSecret;
    }

    public String sign() throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {

        Log.d("UrlSigner", "Consumer Key -> " + CONSUMER_KEY);
        Log.d("UrlSigner", "Consumer Secret -> " + CONSUMER_SECRET);
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setMessageSigner(new HmacSha1MessageSigner());
        consumer.setSigningStrategy(new QueryStringSigningStrategy());
        //consumer.setTokenWithSecret(prefs.getString(OAUTH_ACCESS_TOKEN_KEY, ""), prefs.getString(OAUTH_ACCESS_SECRET_KEY, ""));
        return consumer.sign(mUrl);
    }


}
