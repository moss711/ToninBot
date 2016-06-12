/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toninbot;

import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.FilterQuery;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Alex
 */
public class ToninBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AccessToken accessToken = new AccessToken(Credenciales.token, Credenciales.tokenSecret);
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(Credenciales.consumerKey);
        builder.setOAuthConsumerSecret(Credenciales.consumerSecret);
        
        Configuration configuration = builder.build();
        TwitterStreamFactory twStreamFactory = new TwitterStreamFactory(configuration);
        TwitterStream twitterStream=twStreamFactory.getInstance();
        twitterStream.setOAuthAccessToken(accessToken);
        
        ToninStatusListener listener = new ToninStatusListener();
        twitterStream.addListener(listener);
        
        FilterQuery filtre = new FilterQuery();
        //filtre.follow(184742273L,2841338087L);//Allegue y proyectoPSIa1
        filtre.follow(2841338087L);//proyectoPSIa1
        
        
        twitterStream.filter(filtre);
    }
    
}
