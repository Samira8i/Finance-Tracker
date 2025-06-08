// Базовый URL API для всех запросовAdd commentMore actions
const API_BASE_URL = 'http://localhost:8080/api/v1';

//Получает JWT токен из куки браузера

function getJwtToken() {
    const cookies = document.cookie.split(';');
    for (let cookie of cookies) {
        const [name, value] = cookie.trim().split('=');
        if (name === 'USER_JWT') {
            return value;
        }
    }
    return null;
}

// Настройка jQuery AJAX для автоматического добавления JWT токена в заголовки запросов
$.ajaxSetup({
    beforeSend: function(xhr) {
        const token = getJwtToken();
        if (token) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + token);
        }
    }
});

//Группирует операции по дате

function groupByDate(operations) {
    return operations.reduce((acc, op) => {
        const date = op.transactionDate.split('T')[0]; // Извлекаем дату без времени
        if (!acc[date]) acc[date] = [];
        acc[date].push(op);
        return acc;
    }, {});
}

//Форматирует дату в читаемый вид (например, "5 мая")
function formatDate(dateString) {
    const date = new Date(dateString);
    const options = { day: 'numeric', month: 'long' };
    return date.toLocaleDateString('ru-RU', options);
}

//Отображает сгруппированные операции в интерфейсе

function displayGroupedOperations(groupedData) {
    const $container = $('#operationsContainer');
    $container.empty();

    // Если нет данных, показываем сообщение
    if (!groupedData || Object.keys(groupedData).length === 0) {
        $container.append('<div class="no-data">Нет операций для отображения</div>');
        return;
    }

    // Сортируем даты по убыванию и создаем элементы для каждой даты
    Object.keys(groupedData)
        .sort((a, b) => new Date(b) - new Date(a))
        .forEach(date => {
            const dateElement = $(`<div class="operations-date">${formatDate(date)}</div>`);
            const dayContainer = $('<div class="operations-day">').append(dateElement);

            // Добавляем каждую операцию для текущей даты
            groupedData[date].forEach(op => {
                const type = op.transactionType.toLowerCase();
                const amountClass = type === 'income' ? 'income' : 'expense';
                const amountSign = type === 'income' ? '+' : '-';

                let categoryName = op.categoryName || op.categoryId || 'Другое';

                dayContainer.append(`
                    <div class="operation-item" data-id="${op.id}">
                        <span class="operation-category">${categoryName}</span>
                        <span class="operation-amount ${amountClass}">${amountSign}${op.amount.toLocaleString('ru-RU')} руб</span>
                    </div>
                `);
            });

            $container.append(dayContainer);
        });
}

//Загружает операции с сервера и отображает их

async function loadOperations() {
    try {
        // Загружаем все операции без фильтров
        const response = await $.get(`${API_BASE_URL}/transaction/history`);
        const grouped = groupByDate(response);
        displayGroupedOperations(grouped);
    } catch (error) {
        console.error('Ошибка загрузки операций:', error);
        if (error.status === 403) {
            // Если токен недействителен, перенаправляем на страницу входа
            alert('Сессия истекла. Пожалуйста, войдите снова.');
            window.location.href = 'enter.html';
        } else {
            $('#operationsContainer').html('<div class="error-message">Ошибка загрузки данных</div>');
        }
    }
}

// При загрузке документа проверяем авторизацию и загружаем операции
$(document).ready(function() {
    if (!getJwtToken()) {
        alert('Пожалуйста, авторизуйтесь');
        window.location.href = 'enter.html';
        return;
    }

    loadOperations();
});