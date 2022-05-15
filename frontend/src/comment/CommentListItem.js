
export function CommentListItem({comment, onEditHandler, onDeleteHandler}) {
    return <div className="list-block-item">
        <div className="list-block-item-content edit-point">
            <span className="link-content link-action" href="" onClick={(event) => onEditHandler({comment: comment}) }>
                <div className="list-block-item-line">
                    <div className="list-block-item-line-value">{comment.text}</div>
                </div>
            </span>
        </div>
        <div className="list-block-item-control-block">
            <span className="link-action delete-point" href="" onClick={(event)=> onDeleteHandler(comment)}>удалить комментарий</span>
        </div>
    </div>
}