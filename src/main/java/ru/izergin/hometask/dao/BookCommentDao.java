package ru.izergin.hometask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.izergin.hometask.domain.BookComment;

public interface BookCommentDao extends JpaRepository<BookComment, Long> {

}
