package tn.esprit.spring.services;

import com.twilio.Twilio;
import lombok.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.http.HttpMethod;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Service
public class ITwillioService implements TwillioService{
    @Override
    public void sendSms(String to, String from, String body) {
        try {
            Twilio.init("ACa8d2f5dea94a611f73836afa510fc2cc", "ffafd22623bfe8b26ba1d7b3e336d326");
            Message message = Message.creator( new PhoneNumber(to), new PhoneNumber(from),body) // to:to which no  you want to send sms                               // body : text message
                    .create();

            System.out.println(message);
            System.out.println(message.getSid());

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeCall(String from, String to) {

    }
}
