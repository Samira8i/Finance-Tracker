// Проверка авторизации при загрузке страницыAdd commentMore actions
document.addEventListener('DOMContentLoaded', function () {
    const token = document.cookie
        .split('; ')
        .find(row => row.startsWith('USER_JWT='))
        ?.split('=')[1];

    if (!token) {
        window.location.href = 'enter.html';
        return;
    }
});
// Инициализация приложения после загрузки DOM
$(document).ready(function () {
    initCombinedChart();
    loadBalance();
    loadCategoriesMain('income');
    loadCategoriesMain('expense');
    setupEventListeners(); // Настройка обработчиков событий
});

// Конфигурация
const MOCK_MODE = false;
const API_BASE_URL = 'http://localhost:8080/api/v1';
const MOCK_DATA = {
    balance: {
        amount: 150000 // Пример баланса
    },
    categories: {
        income: [
            { id: 1, name: 'Вклад', amount: 100000, color: '#4caf50' },
            { id: 1, name: 'Зарплата', amount: 100000, color: '#ffeda4' },
            { id: 2, name: 'Бизнес', amount: 50000, color: '#2196f3' },
            { id: 1, name: 'Аренда', amount: 100000, color: '#ff6b62' },
            { id: 1, name: 'Крипта', amount: 100000, color: '#f385ff' },
            { id: 2, name: 'Другое', amount: 50000, color: '#21f3db' }
        ],
        expense: [
            { id: 1, name: 'Продукты', amount: 30000, color: '#ffa226' },
            { id: 2, name: 'Транспорт', amount: 15000, color: '#ff938d' },
            { id: 3, name: 'Развлечения', amount: 20000, color: '#90439e' },
            { id: 1, name: 'Дом', amount: 30000, color: '#85bf97' },
            { id: 2, name: 'Здоровье', amount: 15000, color: '#f44336' },
            { id: 3, name: 'Другое', amount: 20000, color: '#fff885' }
        ]
    }
};
// Состояние приложения
const AppState = {
    currentPeriod: 'week',
    charts: {
        combined: null,
        income: null,
        expense: null
    }
};

// Функция для преобразования номеров месяцев в названия
function getMonthNames(monthNumbers) {
    const monthNames = {
        '01': 'Янв', '02': 'Фев', '03': 'Мар', '04': 'Апр',
        '05': 'Май', '06': 'Июн', '07': 'Июл', '08': 'Авг',
        '09': 'Сен', '10': 'Окт', '11': 'Ноя', '12': 'Дек'
    };

    return monthNumbers.map(num => monthNames[num] || num);
}

// Инициализация комбинированного графика (доходы/расходы по месяцам)
function initCombinedChart() {
    if (MOCK_MODE) {
        createCombinedChart(
            ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"],
            [85000, 92000, 105000, 88000, 115000, 125000, 95000, 110000, 105000, 120000, 115000, 130000],
            [45000, 50000, 55000, 48000, 52000, 60000, 65000, 58000, 62000, 70000, 68000, 75000]
        );
    } else {
        // Загружаем данные с бэкенда
        fetchMonthlyStats()
            .then(data => {
                // Преобразуем номера месяцев в названия
                const monthNames = getMonthNames(data.months);
                createCombinedChart(
                    monthNames,
                    data.incomes,
                    data.expenses || Array(data.incomes.length).fill(0)
                );
            })
            .catch(error => {
                console.error('Ошибка загрузки статистики:', error);
                createCombinedChart(
                    ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"],
                    [85000, 92000, 105000, 88000, 115000, 125000, 95000, 110000, 105000, 120000, 115000, 130000],
                    [45000, 50000, 55000, 48000, 52000, 60000, 65000, 58000, 62000, 70000, 68000, 75000]
                );
            });
    }
}

// Создание комбинированного графика
function createCombinedChart(labels, incomeData, expenseData) {
    AppState.charts.combined = new Chart(
        document.getElementById('combinedChart').getContext('2d'),
        {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [
                    {
                        label: 'Доходы',
                        backgroundColor: '#91fabb',
                        borderColor: '#85bf97',
                        borderWidth: 1,
                        borderRadius: 5,
                        data: incomeData
                    },
                    {
                        label: 'Расходы',
                        backgroundColor: '#ff6b62',
                        borderColor: '#c85a50',
                        borderWidth: 1,
                        borderRadius: 5,
                        data: expenseData
                    }
                ]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'top',
                        labels: {
                            font: {
                                family: 'Manrope',
                                size: 14,
                                weight: 500
                            },
                            padding: 20,
                            usePointStyle: true,
                        }
                    },
                    tooltip: {
                        bodyFont: {
                            family: 'Manrope',
                            size: 14,
                            weight: 400
                        },
                        titleFont: {
                            family: 'Manrope',
                            size: 16,
                            weight: 600
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        grid: {
                            color: 'rgba(0, 0, 0, 0.05)'
                        },
                        ticks: {
                            font: {
                                family: 'Manrope',
                                size: 12,
                                weight: 500
                            }
                        }
                    },
                    x: {
                        grid: {
                            display: false
                        },
                        ticks: {
                            font: {
                                family: 'Manrope',
                                size: 14,
                                weight: 500
                            }
                        }
                    }
                }
            }
        }
    );
}

// Загрузка баланса пользователя
function loadBalance() {
    $('#balanceAmount').text('Загрузка...');
    fetchBalance()
        .then(balance => {
            updateBalance(balance);
        })
        .catch(error => {
            console.error('Ошибка загрузки баланса:', error);
            $('#balanceAmount').text('Ошибка загрузки');
            if (MOCK_MODE) updateBalance(MOCK_DATA.balance);
        });
}

// Загрузка категорий (доходов или расходов)
function loadCategoriesMain(type) {
    fetchCategories(type)
        .then(categories => {
            if (type === 'income') {
                updateIncomeCategories(categories);
            } else if (type === 'expense') {
                updateExpenseCategories(categories);
            }
        })
        .catch(error => {
            console.error(`Ошибка загрузки категорий (${type}):`, error);
            if (type === 'income') updateIncomeCategories(MOCK_DATA.categories.income);
            else if (type === 'expense') updateExpenseCategories(MOCK_DATA.categories.expense);
        });
}

// Обновление списка категорий доходов
function updateIncomeCategories(categories) {
    let incomeHtml = '';
    categories.forEach(cat => {
        incomeHtml += `
      <li class="category-item">
        ${cat.name} <span>${cat.amount.toLocaleString('ru-RU')} ₽</span>
      </li>`;
    });
    $('#incomeCategories').html(incomeHtml);
}

// Обновление списка категорий расходов
function updateExpenseCategories(categories) {
    let expenseHtml = '';
    categories.forEach(cat => {
        expenseHtml += `
      <li class="category-item">
        ${cat.name} <span>${cat.amount.toLocaleString('ru-RU')} ₽</span>
      </li>`;
    });
    $('#expenseCategories').html(expenseHtml);
}

// API функции
function fetchBalance() {
    const token = getToken();
    if (!token) {
        console.error("Токен не найден");
        window.location.href = 'enter.html';
        return Promise.reject('Token not found');
    }

    return $.ajax({
        url: `${API_BASE_URL}/transaction/index`,
        type: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    }).then(response => {
        console.log("Получен баланс:", response);
        return {amount: response.balance};
    }).catch(error => {
        console.error('Ошибка при получении баланса:', error);
        throw error;
    });
}

function fetchMonthlyStats() {
    const token = getToken();
    if (!token) {
        console.error("Токен не найден");
        window.location.href = 'enter.html';
        return Promise.reject('Token not found');
    }

    return $.ajax({
        url: `${API_BASE_URL}/transaction/last-year-stats`,
        type: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
}

function fetchCategories(type) {
    const typeParam = type.toUpperCase();
    if (MOCK_MODE) return Promise.resolve(MOCK_DATA.categories[type]);
    return $.get(`${API_BASE_URL}/categories?categoryType=${typeParam}`).then(response => response);
}

function getToken() {
    const token = document.cookie
        .split('; ')
        .find(row => row.startsWith('USER_JWT='))
        ?.split('=')[1];
    return token;
}

// API: Отправка новой операции
function postOperation(operationData) {
    if (MOCK_MODE) {
        console.log('Моковая операция:', operationData);
        return Promise.resolve({status: 'success'});
    }
    const token = getToken();
    if (!token) {
        console.error("Токен не найден");
        window.location.href = 'enter.html';
        return Promise.reject('Token not found');
    }

    console.log("Отправка запроса с токеном:", token);

    return $.ajax({
        url: `${API_BASE_URL}/transaction`,
        type: 'POST',
        contentType: 'application/json',
        headers: {
            'Authorization': `Bearer ${token}`
        },
        data: JSON.stringify(operationData)
    });
}

// Обновление отображения баланса
function updateBalance(balance) {
    if (!balance || balance.amount === undefined) {
        console.error('Некорректные данные баланса:', balance);
        $('#balanceAmount').text('Ошибка данных').removeClass('balance-positive balance-negative');
        return;
    }

    try {
        const formattedAmount = new Intl.NumberFormat('ru-RU', {
            style: 'decimal',
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }).format(balance.amount);

        const $balanceElement = $('#balanceAmount');
        $balanceElement.text(`${formattedAmount} ₽`);

        $balanceElement
            .removeClass('balance-positive balance-negative')
            .addClass(balance.amount >= 0 ? 'balance-positive' : 'balance-negative');

    } catch (e) {
        console.error('Ошибка форматирования баланса:', e);
        $('#balanceAmount')
            .text(`${balance.amount} ₽`)
            .removeClass('balance-positive balance-negative')
            .addClass(balance.amount >= 0 ? 'balance-positive' : 'balance-negative');
    }
}

// Обработчики событий
function setupEventListeners() {
    $('#addOperationBtn').click(function () {
        $('#operationModal').show();
        loadCategories($('.tab.active').data('tab'));
    });
// Закрытие модального окна
    $('.close, #operationModal').click(function (e) {
        if (e.target === this || $(e.target).hasClass('close')) {
            $('#operationModal').hide();
        }
    });
    // Переключение вкладок (доход/расход)
    $('.tab').click(function () {
        $('.tab').removeClass('active');
        $(this).addClass('active');
        loadCategories($(this).data('tab'));
    });
// Отправка формы операции
    $('#submitOperation').click(handleOperationSubmit);
// Переключение периода для круговых диаграмм
    $('.pie-period-btn').click(function () {
        const period = $(this).data('period');
        $('.pie-period-btn').removeClass('active');
        $(this).addClass('active');
        updatePieCharts(period);
    });
}

// Загрузка категорий для модального окна
function loadCategories(type) {
    $('#modalCategories').html('<div class="loading-message">Загрузка категорий...</div>');

    fetchCategories(type).then(function (categories) {
        showCategories(categories);
    }).catch(function (error) {
        console.error('Ошибка загрузки категорий:', error);
        $('#modalCategories').html('<div class="error-message">Не удалось загрузить категории</div>');
        if (MOCK_MODE) {
            showCategories(MOCK_DATA.categories[type]);
        }
    });
}

// Отображение категорий в модальном окне
function showCategories(categories) {
    if (!categories || categories.length === 0) {
        $('#modalCategories').html('<div class="error-message">Категории не найдены</div>');
        return;
    }

    let html = '';
    categories.forEach(cat => {
        html += `
        <div class="modal-category" data-id="${cat.id}">
          <div class="category-circle" style="background-color: ${cat.color || '#ccc'}"></div>
          ${cat.name}
        </div>
      `;
    });
    $('#modalCategories').html(html);

    $('.modal-category').click(function () {
        $('.modal-category').removeClass('selected');
        $(this).addClass('selected');
    });
}

// Обработка отправки формы операции
function handleOperationSubmit() {
    const amount = parseFloat($('#operationAmount').val());
    const type = $('.tab.active').data('tab');
    const categoryId = $('.modal-category.selected').data('id');

    if (!validateOperation(amount, categoryId)) return;

    const operationData = {
        amount: amount,
        transactionType: type.toUpperCase(),
        categoryId: categoryId
    };

    postOperation(operationData).then(function () {
        $('#operationModal').hide();
        if (!MOCK_MODE) {
            loadBalance();
            createCombinedChart();
        }
    }).catch(function () {
        console.error('Ошибка при добавлении операции');
    });
}

// Валидация формы операции
function validateOperation(amount, categoryId) {
    if (isNaN(amount) || amount <= 0) {
        alert('Введите корректную сумму');
        return false;
    }
    if (!categoryId) {
        alert('Выберите категорию');
        return false;
    }
    return true;
}

// Выход из системы
function logout() {
    document.cookie = 'USER_JWT=; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT; Secure; SameSite=Strict';
    window.location.href = 'main.html';
}