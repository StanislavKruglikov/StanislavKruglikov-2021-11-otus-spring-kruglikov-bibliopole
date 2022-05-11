export function Book({book, onEditHandler, onDeleteHandler, onCommentsHandler}) {
    return <div className="list-block-item">
        <div className="list-block-item-content edit-point">
            <span className="link-content link-action" href="" onClick={(event) => onEditHandler({book: book}) }>
                <div className="list-block-item-line">
                    <div className="list-block-item-line-title">Название:</div>
                    <div className="list-block-item-line-value">{book.title}</div>
                </div>
                <div className="list-block-item-line">
                    <div className="list-block-item-line-title">Автор:</div>
                    <div>{book.authorName}</div>
                </div>
                <div className="list-block-item-line">
                    <div className="list-block-item-line-title">Жанр:</div>
                    <div className="list-block-item-line-value">{book.genreName}</div>
                </div>
            </span>
        </div>
        <div className="list-block-item-control-block">
            <span className="list-block-item-control-block-first link-action detail-point" href=""
                onClick={(event)=> onCommentsHandler(book)}>комментарии</span>
            <span className="list-block-item-control-second link-action delete-point" href=""
                onClick={(event)=> onDeleteHandler(book)}>удалить книгу</span>
        </div>
    </div>
}