package org.example.livretpfinal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import java.io.StringWriter;
import java.util.Properties;


public class HelloController {
    @FXML
    private ListView<Book> bookList;

    @FXML
    private TextField title;

    @FXML
    private TextField isbn;

    @FXML
    private TextField author;

    @FXML
    private TextField year;

    @FXML
    private TextField pages;

    @FXML
    private TextArea description;

    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private Button delete;
    public class EmailNotificationService {

        private static final String HOST_NAME = "mail.gmx.com";
        private static final int SMTP_PORT = 587;
        private static final String USERNAME = "nadjideYnov@gmx.fr";
        private static final String PASSWORD = "Ynov12345678!";
        private static final String FROM_EMAIL = "nadjideYnov@gmx.fr";
        private static final String TO_EMAIL = "nadjide.omar.5@gmail.com";
        private static final String SUBJECT = "Book Notification";

        public void sendEmailNotification(String action, Book book) {
            try {
                // Prepare the email body
                String emailBody = "Cher utilisateur,\n\n" +
                        "Une action de " + action + " a été effectuée sur le livre suivant :\n\n" +
                        "Titre : " + book.getTitle() + "\n" +
                        "Auteur : " + book.getAuthor() + "\n" +
                        "ISBN : " + book.getIsbn() + "\n" +
                        "Année : " + book.getYear() + "\n" +
                        "Pages : " + book.getPages() + "\n" +
                        "Description : " + book.getDescription() + "\n\n" +
                        "Cordialement,\n" +
                        "Votre équipe";

                SimpleEmail email = new SimpleEmail();
                email.setHostName(HOST_NAME);
                email.setSmtpPort(SMTP_PORT);
                email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
                email.setStartTLSEnabled(true);
                email.setFrom(FROM_EMAIL);
                email.setSubject(SUBJECT);
                email.setMsg(emailBody);
                email.addTo(TO_EMAIL);

                email.send();
                System.out.println("Email sent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void addBook() {
        Book book = new Book();
        book.setTitle(title.getText());
        book.setIsbn(isbn.getText());
        book.setAuthor(author.getText());
        book.setYear(year.getText());
        book.setPages(pages.getText());
        book.setDescription(description.getText());

        bookList.getItems().add(book);
        new EmailNotificationService().sendEmailNotification("ajout", book);
    }

    @FXML
    private void editBook() {
        Book selectedBook = bookList.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            selectedBook.setTitle(title.getText());
            selectedBook.setIsbn(isbn.getText());
            selectedBook.setAuthor(author.getText());
            selectedBook.setYear(year.getText());
            selectedBook.setPages(pages.getText());
            selectedBook.setDescription(description.getText());
            bookList.refresh();
            new EmailNotificationService().sendEmailNotification("modification", selectedBook);
        }
    }

    @FXML
    private void deleteBook() {
        Book selectedBook = bookList.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            bookList.getItems().remove(selectedBook);
            new EmailNotificationService().sendEmailNotification("suppression", selectedBook);
        }
    }
}