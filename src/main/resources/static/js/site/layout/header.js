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

        const form = $(this);

        const data = {
            email: form.find('[name="email"]').val(),
            password: form.find('[name="password"]').val()
        }

        $.ajax({
            url: '/api/sign-in',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',

            success: function (res) {
                location.reload();
            },

            error: function (xhr) {
                console.log(xhr.responseJSON);
                const errors = xhr.responseJSON;
                const box = $('.error');

                showErrors(errors, box)
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
            url: '/api/sign-up',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),

            success: function (res) {
                location.reload();
            },

            error: function (xhr) {
                const errors = xhr.responseJSON;
                const box = $('.error');

                showErrors(errors, box)
            }

        })
    })

    function showErrors(errors, box){
        box.html("");
        if(Array.isArray(errors)){
            errors.forEach(err => {
                box.append(`<p>${err}</p>`)
            });
        } else {
            box.append(`<p>${errors?.errors}</p>`);
        }

    }

})