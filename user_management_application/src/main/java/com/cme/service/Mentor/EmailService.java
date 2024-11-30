package com.cme.service.Mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendMockInterviewDetails(String toEmail, String mentorID, String mentorName, String employeeID, String technology, String mockDate, String mockTime) {
        String subject = "Mock Interview Scheduled";
        String text = String.format("Dear Employee (ID: %s),\n\n"
                        + "You have a scheduled mock interview with Mentor ID: %s (%s).\n"
                        + "Technology: %s\n"
                        + "Date: %s\n"
                        + "Time: %s\n\n"
                        + "Best Regards,\n"
                        + "Your Company",
                employeeID, mentorID, mentorName, technology, mockDate, mockTime);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
}
