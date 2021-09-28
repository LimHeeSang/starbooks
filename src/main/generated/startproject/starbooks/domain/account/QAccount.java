package startproject.starbooks.domain.account;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = -718772273L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccount account = new QAccount("account");

    public final NumberPath<Integer> birthDate = createNumber("birthDate", Integer.class);

    public final ListPath<startproject.starbooks.domain.comment.Comment, startproject.starbooks.domain.comment.QComment> comments = this.<startproject.starbooks.domain.comment.Comment, startproject.starbooks.domain.comment.QComment>createList("comments", startproject.starbooks.domain.comment.Comment.class, startproject.starbooks.domain.comment.QComment.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final ListPath<startproject.starbooks.domain.heart.Heart, startproject.starbooks.domain.heart.QHeart> hearts = this.<startproject.starbooks.domain.heart.Heart, startproject.starbooks.domain.heart.QHeart>createList("hearts", startproject.starbooks.domain.heart.Heart.class, startproject.starbooks.domain.heart.QHeart.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final startproject.starbooks.domain.refresh.QRefreshToken refreshToken;

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final EnumPath<UserRole> userRole = createEnum("userRole", UserRole.class);

    public QAccount(String variable) {
        this(Account.class, forVariable(variable), INITS);
    }

    public QAccount(Path<? extends Account> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccount(PathMetadata metadata, PathInits inits) {
        this(Account.class, metadata, inits);
    }

    public QAccount(Class<? extends Account> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.refreshToken = inits.isInitialized("refreshToken") ? new startproject.starbooks.domain.refresh.QRefreshToken(forProperty("refreshToken"), inits.get("refreshToken")) : null;
    }

}

