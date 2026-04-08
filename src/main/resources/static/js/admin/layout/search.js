$(function (){
    let timeout = null;
    $(".search__input").on("input", function(){
        clearTimeout(timeout);

        timeout = setTimeout(() => {
            this.form.submit();
        }, 1000);
    })

    $(window).on("load", function() {
        const input = document.getElementsByName("search")[0];
        if(input){
            input.focus();

            const value = input.value;
            input.value = "";
            input.value = value;
        }
    })
})