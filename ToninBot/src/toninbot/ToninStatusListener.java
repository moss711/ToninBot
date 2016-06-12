/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toninbot;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Alex
 */
public class ToninStatusListener implements StatusListener{
    private Twitter twitter;

    public ToninStatusListener() {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(Credenciales.consumerKey);
        builder.setOAuthConsumerSecret(Credenciales.consumerSecret);
        
        Configuration configuration = builder.build();
        AccessToken accessToken = new AccessToken(Credenciales.token, Credenciales.tokenSecret);
        
        TwitterFactory twFactory = new TwitterFactory(configuration);
        twitter = twFactory.getInstance();
        twitter.setOAuthAccessToken(accessToken);
    }
    
    

    @Override
    public void onStatus(Status arg0) {
        if (arg0.getUser().getId()!=184742273L && arg0.getUser().getId()!=2841338087L){
            return;
        }
        System.out.println(arg0.getUser().getName()+" "+arg0.getText());
        Calendar cal = Calendar.getInstance();
        cal.setTime(arg0.getCreatedAt());
        long hora=cal.get(Calendar.HOUR_OF_DAY);
        System.out.println("Hora: "+cal.get(Calendar.HOUR_OF_DAY));
        
        if (hora>7){
            return;
        }
        
        String texto="Fora de horario!";
        StatusUpdate stat= new StatusUpdate("@"+arg0.getUser().getScreenName()+" "+texto);
        System.out.println("Fora de horario!");

        stat.inReplyToStatusId(arg0.getId());

        try {
            twitter.updateStatus(stat);
            System.out.println("Twitteado: "+stat.toString());
        } catch (TwitterException ex) {
            System.out.println("Error");
        }
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onStallWarning(StallWarning warning) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onException(Exception excptn) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
