$(function(){
    
    flatpickr(".date", {
        altInput: true,
        altFormat: "j F, Y",
        minDate: "today",
        dateFormat: "Y-m-d",
        locale: "ru"
    }); 
    
})