
export async function getAuthors() {
    const response = await fetch('/author');
    if(response.status !== 200) {
        throw new Error(`Ошибка получения списка авторов, статус: ${response.status}`);
    }
    const authors = await response.json();
    return authors;
}