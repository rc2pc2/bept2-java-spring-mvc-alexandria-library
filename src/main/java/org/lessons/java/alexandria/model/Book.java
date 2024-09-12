package org.lessons.java.alexandria.model;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotEmpty
	@Size(min=2, max=255)
	@Column(name = "title", nullable = false)
	private String title;

	@NotNull
	@NotEmpty
	@Size(min=5, max=250)
	@Column(length = 250)
	private String author;

	@NotNull
	@NotEmpty
	@Size(min=10, max=16)
	@Column(name = "isbn_code", nullable = false, unique = true)
	private String isbn;
	
	@NotNull
	@Max(500)
	private Integer numberOfCopies;


	@UpdateTimestamp
	private Instant updatedAt;
    
    // 1 - il tipo di relazione da qualificare
    // 2 - comportamenti da seguire nel db qualora vengano cambiate informazioni
    // a cascata andranno rimossi tutti i prestiti ad esso conessi
    @OneToMany( mappedBy = "book", cascade = { CascadeType.REMOVE })
    private List<Borrowing> borrowings;
    
    // 1 - chiedo ad hibernate di eseguire una specifica query
    //  numeroDiCopie - il numero di copie prestate 
    	// => count (prestiti) che hanno returnDate = null
        // => che abbiano book.id uguale a questo libro
    @Formula("(select books.number_of_copies - count(b.id) " +
    		 "from books " + 
    		 "left outer join borrowings b " + 
    		 "on books.id = b.book_id " +
    		 "and b.return_date is null " +
    		 "where books.id = id)")
    private Integer availableCopies;

    // TODO aggiungere relazione con categorie
	
    @ManyToMany()
    @JoinTable(
       name = "book_category",
       joinColumns = @JoinColumn(name = "book_id"),
       inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    
    public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Integer getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(Integer numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Borrowing> getBorrowings() {
		return borrowings;
	}

	public void setBorrowings(List<Borrowing> borrowings) {
		this.borrowings = borrowings;
	}

	public Integer getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(Integer availableCopies) {
		this.availableCopies = availableCopies;
	}
}