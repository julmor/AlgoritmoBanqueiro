package org.ufabc.julmor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.ufabc.julmor.model.BankerModel;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
public class Main{
    public static void main(String[] args) {
        SpringApplication.run(BankerModel.class);
        BankerModel banker = new BankerModel();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool ( 1 );
        Runnable r = new Runnable (){
            @Override
            public void run() {
                banker.run();
            }
        };

        try {
            executor.scheduleAtFixedRate ( r , 0L , 5L , TimeUnit.SECONDS ); // ( runnable , initialDelay , period , TimeUnit )
            Thread.sleep ( TimeUnit.MINUTES.toMillis ( 1L ) ); // Let things run a minute to witness the background thread working.
        } catch ( InterruptedException ex ) {
            Logger.getLogger ( BankerModel.class.getName () ).log ( Level.SEVERE , null , ex );
        } finally {
            System.out.println ( "ScheduledExecutorService expiring. Politely asking ScheduledExecutorService to terminate after previously submitted tasks are executed." );
            executor.shutdown ();
        }


        }
}