document.addEventListener('DOMContentLoaded', () => {
    fetchCars();

    const searchBtn = document.getElementById('searchBtn');
    const resetBtn = document.getElementById('resetBtn');
    const plateInput = document.getElementById('plateInput');

    if (searchBtn && plateInput) {
        searchBtn.addEventListener('click', () => {
            searchCars(plateInput.value);
            document.getElementById('filo').scrollIntoView({ behavior: 'smooth' });
        });

        // Add Enter key support
        plateInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                searchCars(plateInput.value);
                document.getElementById('filo').scrollIntoView({ behavior: 'smooth' });
            }
        });
    }

    if (resetBtn) {
        resetBtn.addEventListener('click', () => {
            if (plateInput) plateInput.value = '';
            fetchCars(); // Re-fetch all cars
            document.getElementById('filo').scrollIntoView({ behavior: 'smooth' });
        });
    }
});

async function fetchCars() {
    // ... existing fetchCars logic (using same display function) ...
    loadCars('/api/araclar');
}

async function searchCars(plaka) {
    console.log('Searching for plaka:', plaka);
    let url = '/api/araclar';
    if (plaka && plaka.trim() !== '') {
        url = `/api/araclar/ara?plaka=${encodeURIComponent(plaka.trim())}`;
    }
    console.log('Search URL:', url);
    loadCars(url);
}

async function loadCars(url) {
    console.log('Loading cars from:', url);
    const carList = document.getElementById('car-list');
    carList.innerHTML = '<div class="loading">Araçlar yükleniyor...</div>';

    try {
        const response = await fetch(url);
        console.log('Response status:', response.status);
        if (!response.ok) {
            throw new Error('Veri çekilemedi');
        }
        const cars = await response.json();
        console.log('Cars received:', cars);

        if (cars.length === 0) {
            carList.innerHTML = '<div class="loading">Aradığınız kriterlere uygun araç bulunamadı.</div>';
            return;
        }

        carList.innerHTML = '';
        cars.forEach(car => {
            const card = createCarCard(car);
            carList.appendChild(card);
        });

    } catch (error) {
        console.error('Hata:', error);
        carList.innerHTML = '<div class="loading">Araçlar yüklenirken bir hata oluştu.</div>';
    }
}

function createCarCard(car) {
    const div = document.createElement('div');
    div.className = 'car-card';

    // Placeholder image logic or real image if available
    const imageHtml = `<div class="car-image">
        <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1" stroke-linecap="round" stroke-linejoin="round">
            <rect x="1" y="3" width="15" height="13"></rect>
            <polygon points="16 8 20 8 23 11 23 16 16 16 16 8"></polygon>
            <circle cx="5.5" cy="18.5" r="2.5"></circle>
            <circle cx="18.5" cy="18.5" r="2.5"></circle>
        </svg>
    </div>`;

    div.innerHTML = `
        ${imageHtml}
        <div class="car-details">
            <h3 class="car-title">${car.plaka}</h3> <!-- Model adı join ile gelmeli, şimdilik plaka -->
            <div class="car-info">
                <span>${car.yil} Model</span>
                <span>${car.durum}</span>
            </div>
            <div class="car-footer">
                <div class="car-price">
                    ₺1500 <span>/ gün</span>
                </div>
                <button class="btn btn-primary" style="padding: 8px 20px; font-size: 14px;" onclick="alert('Kiralama işlemi için lütfen bizimle iletişime geçin veya şubemizi ziyaret edin. Online rezervasyon çok yakında!')">Kirala</button>
            </div>
        </div>
    `;
    return div;
}
