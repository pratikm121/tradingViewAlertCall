package nl.trading.view.alert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.*;

@Service
public class GmailService {

    private static final String HOST = "pop.gmail.com";
    private static final String USERNAME = "pratikm121@gmail.com";
    private static final String PASSWORD = "fepnqjzvdknhxxnf";
    private final TwilioService twilioService ;

    @Autowired
    GmailService(TwilioService twilioService){
        this.twilioService = twilioService;
    }

    // https://javahowtos.com/guides/127-libraries/366-how-to-read-gmail-using-java-and-javax-mail.html
    @Scheduled(fixedDelay = 10000)
    public void check() {
        try {

            //create properties field
            Properties properties = new Properties();
            properties.put("mail.imap.host", HOST);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.starttls.enable", "true");
            properties.put("mail.imap.ssl.trust", HOST);
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");

            store.connect(HOST, USERNAME, PASSWORD);

            //Find the folder object and open it
            Folder emailFolder = store.getFolder("TradingView");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            List<Message> messages = Arrays.stream(emailFolder.getMessages()).filter(m -> {
                try {
                    return dateFilter(m.getSentDate());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return false;
            }).collect(Collectors.toList());

            Message message = messages.get(messages.size()-1);
            String subject = message.getSubject();
            Address from = message.getFrom()[0];
            System.out.println("Subject =" + subject + " From "+from);
            if(subject.equalsIgnoreCase("Alert: Alert".toLowerCase())){
                System.out.println("calling");
                this.twilioService.makeCall("time to buy");
            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean dateFilter(Date dateToConvert) {
        LocalDate date = dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return date.isAfter(LocalDate.now().minusDays(3));
    }

}
