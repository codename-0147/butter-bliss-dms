/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collections;
import java.util.Properties;

/**
 *
 * @author barth
 */
public class GmailOAuthService {
    private static final String APPLICATION_NAME = "Your POS Application";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    // Use the Client ID and Secret from your Google Cloud Console
    private static final String CLIENT_ID = "214226903778-2i2ahkc22cjuhp8alqoophpsdlf56b6e.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    
    public Gmail getGmailService() throws Exception {
        // Load credentials and set up the Gmail API service
        Credential credential = getCredentials();  // Updated from GoogleCredential to Credential
        return new Gmail.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
//    private Credential getCredentials() throws Exception {
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                GoogleNetHttpTransport.newTrustedTransport(),
//                JSON_FACTORY,
//                CLIENT_ID, CLIENT_SECRET,
//                Collections.singleton(GmailScopes.GMAIL_SEND))
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//                .setAccessType("offline")
//                .build();
//
//        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver.Builder().setPort(8888).build()).authorize("user");
//    }
    
    private Credential getCredentials() throws Exception {
        File tokenFolder = new java.io.File(TOKENS_DIRECTORY_PATH);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                CLIENT_ID, CLIENT_SECRET,
                Collections.singleton(GmailScopes.GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(tokenFolder))
                .setAccessType("offline")
                .setApprovalPrompt("force")
                .build();

        try {
            // Try normal authorization first
            //System.out.println("No refresh token found. User authorization (browser login) may be needed.");
            return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver.Builder().setPort(8888).build())
                    .authorize("user");
        } catch (com.google.api.client.auth.oauth2.TokenResponseException e) {
            if (e.getDetails() != null && "invalid_grant".equals(e.getDetails().getError())) {
                System.out.println("Detected invalid token. Deleting old tokens and retrying...");

                // Delete tokens folder
                deleteDirectory(tokenFolder);

                // Retry authorization (fresh)
                GoogleAuthorizationCodeFlow freshFlow = new GoogleAuthorizationCodeFlow.Builder(
                        GoogleNetHttpTransport.newTrustedTransport(),
                        JSON_FACTORY,
                        CLIENT_ID, CLIENT_SECRET,
                        Collections.singleton(GmailScopes.GMAIL_SEND))
                        .setDataStoreFactory(new FileDataStoreFactory(tokenFolder))
                        .setAccessType("offline")
                        .setApprovalPrompt("force")
                        .build();

                //System.out.println("Using saved refresh token. No browser login needed.");
                return new AuthorizationCodeInstalledApp(freshFlow, new LocalServerReceiver.Builder().setPort(8888).build())
                        .authorize("user");
            } else {
                throw e; // If different error, rethrow it
            }
        }
    }

    // Helper method to delete the tokens folder
    private void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        deleteDirectory(f);
                    } else {
                        f.delete();
                    }
                }
            }
            directory.delete();
        }
    }

    public void sendEmailWithAttachment(String to, String subject, String bodyText, String pdfFileName) throws Exception {
        // Initialize the Gmail API service
        Gmail service = getGmailService();

        // Dynamically locate the PDF file
        String appDirectory = new File(".").getCanonicalPath(); // Get application installation directory
        String attachmentFilePath = Paths.get(appDirectory, "ExportedReports", "Return Invoice Reports", pdfFileName).toString();

        // Check if the file exists
        if (!Files.exists(Paths.get(attachmentFilePath))) {
            throw new FileNotFoundException("Attachment file not found: " + attachmentFilePath);
        }

        // Create the email content with an attachment
        MimeMessage email = createEmailWithAttachment(to, "me", subject, bodyText, attachmentFilePath);
        Message message = createMessageWithEmail(email);

        // Send the email
        service.users().messages().send("me", message).execute();
        System.out.println("Email with attachment sent successfully!");
    }

    private MimeMessage createEmailWithAttachment(String to, String from, String subject, String bodyText, String attachmentFilePath) throws MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);

        // Create the email body
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText(bodyText);

        // Create the attachment part
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachmentFilePath);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(new File(attachmentFilePath).getName());

        // Combine the body and attachment
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textBodyPart);
        multipart.addBodyPart(attachmentBodyPart);

        email.setContent(multipart);
        return email;
    }

    private Message createMessageWithEmail(MimeMessage email) throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.getUrlEncoder().encodeToString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

//    public static void main(String[] args) {
//        try {
//            GmailOAuthService service = new GmailOAuthService();
//            String recipient = "gamergangster866@gmail.com";
//            String subject = "Report Attached";
//            String bodyText = "Please find the attached PDF report.";
//            String pdfFileName = "Daily_Sales_report_2024-October-21.pdf"; // Provide the PDF file name to attach
//
//            service.sendEmailWithAttachment(recipient, subject, bodyText, pdfFileName);
//        } catch (FileNotFoundException e) {
//            System.err.println("Error: " + e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
