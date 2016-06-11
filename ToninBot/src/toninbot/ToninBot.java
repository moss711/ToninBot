/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toninbot;

import twitter4j.FilterQuery;
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
        TwitterStreamFactory factory = new TwitterStreamFactory(configuration);
        TwitterStream twitterStream=factory.getInstance();
        twitterStream.setOAuthAccessToken(accessToken);
        
        ToninStatusListener listener = new ToninStatusListener();
        twitterStream.addListener(listener);
        
        FilterQuery filtre = new FilterQuery();
        filtre.follow(2841336351L,57322631L);
        
        
        twitterStream.filter(filtre);
    }
    
}
