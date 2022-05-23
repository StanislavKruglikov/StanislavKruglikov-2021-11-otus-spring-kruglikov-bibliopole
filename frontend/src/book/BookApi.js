
export async function getBooks() {
    const response = await fetch('/api/book');
    if(response.status !== 200) {
        throw new Error(`Ошибка получения списка книг, статус: ${response.status}`);
    }
    const books = await response.json();
    return books;
}

export async function saveBook(bookToSave) {
    let book;
    try{
        const response = fetch('/api/book',{
            method: bookToSave.id ? "put" : "post",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(bookToSave)
        });
        if(response.status !== 200) {
                throw new Error(`Ошибка сохранения книг, статус: ${response.status}`);
        }
        book = await response.json();
    } catch(e) {
        console.log(e);
    }
    return book;
}

export async function deleteBook(book) {
    try{
        const response = await fetch(`/api/book/${book.id}`,{
            method: "delete",
            body: {}
        });
        if(response.status !== 200) {
                throw new Error(`Ошибка удаления книги, статус: ${response.status}`);
        }
    } catch(e) {
        console.log(e);
    }

}