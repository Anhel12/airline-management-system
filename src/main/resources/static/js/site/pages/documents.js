$(function(){
    
    flatpickr(".date", {
        altInput: true,
        altFormat: "j F, Y",
        dateFormat: "Y-m-d",
        locale: "ru",
        maxDate: "today"
    });

    $('.document-form__input').on('input', function (){
        let value = $(this).val();
        value = value.replace(/[^А-Яа-яЁё]/g, '');
        $(this).val(value);
    })


    $('.document-form').on("submit", function (e){
        e.preventDefault();

        const form = $(this);

        const data = {
            sex: form.find('[name="sex"]:checked').val(),
            lastName: form.find('[name="lastName"]').val(),
            firstName: form.find('[name="firstName"]').val(),
            middleName: form.find('[name="middleName"]').val(),
            birthDate: form.find('[name="birthDate"]').val(),
            passportNumber: form.find('[name="passportNumber"]').val()
        }

        $.ajax({
            url: '/api/profile/documents',
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