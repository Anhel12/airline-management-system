$(function(){
    
    flatpickr(".date", {
        altInput: true,
        altFormat: "j F, Y",
        minDate: "today",
        dateFormat: "Y-m-d",
        locale: "ru"
    });

    $('.search__form-list a').on('click', function(){

        let $inputFrom = $('.inputFrom');
        let $inputTo = $('.inputTo');

        let temp = $inputFrom.val();
        $inputFrom.val($inputTo.val());
        $inputTo.val(temp);

    })
        
    
})