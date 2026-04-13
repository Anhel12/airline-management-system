$(function(){
    
    flatpickr(".date", {
        altInput: true,
        altFormat: "j F, Y",
        minDate: "today",
        dateFormat: "Y-m-d",
        locale: "ru"
    });

    $('.search__form-list div').on('click', function(){

        let $inputFrom = $('.inputFrom');
        let $inputTo = $('.inputTo');

        let temp = $inputFrom.val();
        $inputFrom.val($inputTo.val());
        $inputTo.val(temp);

    })

    $('.tickets__input').on('click', function (){
        const passengerCount = Number($('#passenger').text());
        let totalPrice = 0;

        $('.tickets__input:checked').each(function() {
            const priceText = $(this).closest('.tickets__item').find('.tickets__price-val').text();
            const price = Number(priceText);
            totalPrice += price * passengerCount;
        });

        $('.aside__item-price').text(totalPrice);
    });
})