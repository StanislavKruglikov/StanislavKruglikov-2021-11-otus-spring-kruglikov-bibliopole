export const books = [];

export async function getBooks() {
    const response = await fetch('/book');
    if(response.status !== 200) {
        throw new Error(`Ошибка получения списка книг, статус: ${response.status}`);
    }
    const books = await response.json();
    return books;
}

export async function saveBook(bookToSave) {
    let book;
    try{
        const response = fetch(`/book`,{
            method: bookToSave.id ? "put" : "post",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(bookToSave)
        });
        if(response.status !== 200) {
                throw new Error(`Ошибка сохранения ниг, статус: ${response.status}`);
        }
        book = await response.json();
    } catch(e) {
        console.log(e);
    }
    return book;
}

export async function deleteBook(book) {
    try{
        const response = fetch(`/book/${book.id}`,{
            method: "delete",
            body: {}
        });
    } catch(e) {
        console.log(e);
    }

}