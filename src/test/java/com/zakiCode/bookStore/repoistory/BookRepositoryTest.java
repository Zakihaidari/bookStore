package com.zakiCode.bookStore.repoistory;

import com.zakiCode.bookStore.model.Book;
import com.zakiCode.bookStore.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.StreamSupport;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Sql(scripts = {"classpath:InsertInitialBookRecordForTest.sql"})
    void ShouldAbltToFetchAllBookInDB(){
        Iterable<Book> all = bookRepository.findAll();
        Long totalBookCount = StreamSupport.stream(all.spliterator() , false).count();
        Assertions.assertEquals(totalBookCount , 1);

    }


    @Test
    @Sql(scripts = {"classpath:InsertInitialBookRecordForTest.sql"})
    void ShouldReturnOneBookByTitle(){
        List<Book> sampleBookTest = bookRepository.findBookByTitle("Sample Book test");
        Assertions.assertEquals(sampleBookTest.size() , 1);

    }


}
