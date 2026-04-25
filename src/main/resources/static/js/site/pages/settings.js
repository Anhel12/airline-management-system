$(function () {

    $('.settings__self-info-form').on("submit", function (e){
        e.preventDefault();

        const form = $(this);

        const data = {
            phoneNumber: form.find('[name="phoneNumber"]').val(),
            email: form.find('[name="email"]').val(),
        }

        $.ajax({
            url: '/api/profile/settings',
            type: 'PATCH',
            contentType: 'application/json',
            data: JSON.stringify(data),
            headers: {
                "X-XSRF-TOKEN": $.cookie('XSRF-TOKEN')
            },

            withCredentials: true,

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

    $('.logout__form').on("submit", function (e){
        e.preventDefault();

        $.ajax({
            url: '/logout',
            type: 'POST',
            contentType: 'application/json',
            headers: {
                "X-XSRF-TOKEN": $.cookie('XSRF-TOKEN')
            },

            xhrFields: {
                withCredentials: true
            },

            success: function (){
                window.location.href = "/home";
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