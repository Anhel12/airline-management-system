document.addEventListener('DOMContentLoaded', () =>{

    const radios = document.querySelectorAll('.ticket-radio-input');
    const passengerCount = Number(
        document.getElementById('passengers').textContent
    );

    const totalPriceEl = document.getElementById('totalPrice');
    const totalPrice2El = document.getElementById('totalPrice2')

    radios.forEach(radio => {
        radio.addEventListener('change', () => {
            const price = Number(radio.dataset.price);
            const total = price * passengerCount;

            totalPriceEl.textContent =
                total.toLocaleString('ru-RU') + ' руб';

            totalPrice2El.textContent =
                total.toLocaleString('ru-RU') + ' руб';

            console.log({
                price,
                passengerCount,
                total
            })
        })
    });
})

