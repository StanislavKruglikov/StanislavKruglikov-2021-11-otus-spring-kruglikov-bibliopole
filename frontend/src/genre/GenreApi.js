export const genres = [];

export async function getGenres() {
    const response = await fetch('/api/genre');
    if(response.status !== 200) {
        throw new Error(`Ошибка получения списка жанров, статус: ${response.status}`);
    }
    const genres = await response.json();
    return genres;
}