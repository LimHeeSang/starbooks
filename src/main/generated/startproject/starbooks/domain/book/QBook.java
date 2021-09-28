package startproject.starbooks.domain.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = -537289295L;

    public static final QBook book = new QBook("book");

    public final StringPath author = createString("author");

    public final ListPath<startproject.starbooks.domain.comment.Comment, startproject.starbooks.domain.comment.QComment> comments = this.<startproject.starbooks.domain.comment.Comment, startproject.starbooks.domain.comment.QComment>createList("comments", startproject.starbooks.domain.comment.Comment.class, startproject.starbooks.domain.comment.QComment.class, PathInits.DIRECT2);

    public final StringPath createdAt = createString("createdAt");

    public final StringPath description = createString("description");

    public final ListPath<startproject.starbooks.domain.heart.Heart, startproject.starbooks.domain.heart.QHeart> hearts = this.<startproject.starbooks.domain.heart.Heart, startproject.starbooks.domain.heart.QHeart>createList("hearts", startproject.starbooks.domain.heart.Heart.class, startproject.starbooks.domain.heart.QHeart.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath isbn = createString("isbn");

    public final StringPath modifiedAt = createString("modifiedAt");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath publisher = createString("publisher");

    public final StringPath title = createString("title");

    public QBook(String variable) {
        super(Book.class, forVariable(variable));
    }

    public QBook(Path<? extends Book> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBook(PathMetadata metadata) {
        super(Book.class, metadata);
    }

}

