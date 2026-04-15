$(function(){

    $('.login, .overlay, .auth-panel__top svg').on('click', function(e){
        e.preventDefault();

        if($('.auth-panel__login').hasClass('hidden')){
            $('.auth-panel__signup').addClass('hidden');
            $('.auth-panel__login').removeClass('hidden');
        }
        
        $('.auth-panel').toggleClass('auth-panel--show');
        $('.overlay').toggleClass('overlay--show');
    })

    $('.reg').on('click', function(e){
        e.preventDefault();
        $('.auth-panel__login').addClass('hidden');
        $('.auth-panel__signup').removeClass('hidden');
    })
    $('.log').on('click', function(e){
        e.preventDefault();
        $('.auth-panel__signup').addClass('hidden');
        $('.auth-panel__login').removeClass('hidden');
    })

    $('.account').on('click', function(){
        $('.settings-menu').toggleClass('settings-menu--show');
    })

    $('.login-form').on('submit', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/login',
            type: 'POST',
            data: $(this).serialize(),

            success: function (res) {
                location.reload();
            },

            error: function (xhr) {
                console.log(xhr.responseJSON);
                const error = xhr.responseJSON?.error;
                $('.error').text(error || "Неправильный логин или пароль");
            },

            beforeSend: function (xhr) {
                xhr.setRequestHeader("X-CSRF-TOKEN", $('input[name="_csrf"]').val());
            }
        })
    })

    $('.signup-form').on("submit", function (e){
        e.preventDefault();

        const form = $(this);

        const data = {
            email: form.find('[name="email"]').val(),
            password: form.find('[name="password"]').val()
        }

        $.ajax({
            url: '/register',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),

            success: function (res) {
                location.reload();
            },

            error: function (xhr) {
                const errors = xhr.responseJSON;

                const box = $('.error');

                box.html("");
                errors.forEach(err => {
                    box.append(`<p>${err}</p>`);
                })
            },

            beforeSend: function (xhr) {
                xhr.setRequestHeader("X-CSRF-TOKEN", $('input[name="_csrf"]').val());
            }

        })
    })

})