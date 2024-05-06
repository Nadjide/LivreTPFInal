package org.example.livretpfinal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

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

    private EmailNotificationService emailNotificationService;

    public HelloController() {
        emailNotificationService = new EmailNotificationService();
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
        emailNotificationService.sendEmailNotification("ajout", book);
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
            emailNotificationService.sendEmailNotification("modification", selectedBook);
        }
    }

    @FXML
    private void deleteBook() {
        Book selectedBook = bookList.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            bookList.getItems().remove(selectedBook);
            emailNotificationService.sendEmailNotification("suppression", selectedBook);
        }
    }
}