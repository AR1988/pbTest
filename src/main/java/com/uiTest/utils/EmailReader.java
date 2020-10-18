package com.uiTest.utils;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

public class EmailReader {
    private static final String HOST = "pop.gmail.com";
    private static final String USERNAME = "phone.boock.test@gmail.com";
    private static final String PASSWORD = "TelRan2020";


    public static void check() {
        try {
            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", HOST);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(HOST, USERNAME, PASSWORD);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            Folder folders = store.getDefaultFolder();
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);

            Message message = messages[messages.length - 1];

            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + message.getFrom()[0]);
            System.out.println("Text: " + message.getContent().toString());

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
