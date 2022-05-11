export const books = [];

export async function getBooks() {
    const response = await fetch('/book');
    if(response.status !== 200) {
        throw new Error(`Ошибка получения списка книг, статус: ${response.status}`);
    }
    const books = await response.json();
    return books;
}

export async function deleteBook(book) {
    try{
        const response = fetch(`/book/${book.id}`,{
            method: "delete",
            headers: {},
            body: {}
        });
       // books = await response.json();
    } catch(e) {
        console.log(e);
    }

}