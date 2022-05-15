export const comments = [];

export async function getComments(bookId) {
    const response = await fetch(`/book/${bookId}/comment`);
    if(response.status !== 200) {
        throw new Error(`Ошибка получения списка комментариев, статус: ${response.status}`);
    }
    const comments = await response.json();
    return comments;
}

export async function getComment(commentId) {
    const response = await fetch(`/comment/${commentId}`);
    if(response.status !== 200) {
        throw new Error(`Ошибка получения комментария, статус: ${response.status}`);
    }
    const comments = await response.json();
    return comments;
}

export async function saveComment(commentToSave) {
    const response = await fetch('/comment',{
        method: commentToSave.id ? "put" : "post",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(commentToSave)
    });
    if(response.status !== 200) {
        throw new Error(`Ошибка сохранения комментария, статус: ${response.status}`);
    }
    const comment = await response.json();
    return comment;
}

export async function deleteComment(comment) {
    try{
        const response = fetch(`/comment/${comment.id}`,{
            method: "delete",
            headers: {},
            body: {}
        });
    } catch(e) {
        console.log(e);
    }

}