$(function (){

    flatpickr(".date", {
        altInput: true,
        altFormat: "d.m.Y",
        dateFormat: "Y-m-d",
        locale: "ru"
    });


    $('.add, .overlay').on('click', function(e){
        e.preventDefault();

        $('.main__add-entity').toggleClass('main__add-entity--show');
        $('.overlay').toggleClass('overlay--show');
    })

    $('.table__main-form').on('submit', function (e){
        e.preventDefault();

        let items = [];

        $('.table__main-list').each(function () {
            let item = {
                id: $(this).find('[name$=".id"]').val(),
                phoneNumber: $(this).find('[name$=".phoneNumber"]').val(),
                email: $(this).find('[name$=".email"]').val(),
                passportNumber: $(this).find('[name$=".passportNumber"]').val(),
                birthDate: $(this).find('[name$=".birthDate"]').val(),
                firstName: $(this).find('[name$=".firstName"]').val(),
                middleName: $(this).find('[name$=".middleName"]').val(),
                lastName: $(this).find('[name$=".lastName"]').val(),
                sex: $(this).find('[name$=".sex"]').val(),
                role: $(this).find('[name$=".role"]').val(),
                password: $(this).find('[name$=".password"]').val()
            };

            items.push(item);
        })

        $.ajax({
            url: '/api/admin/passenger',
            type: 'PATCH',
            contentType: 'application/json',
            data: JSON.stringify(items),
            headers: {
                "X-XSRF-TOKEN": $.cookie('XSRF-TOKEN')
            },
            xhrFields: {
                withCredentials: true
            },

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

    $('.add-entity__form').on('submit', function (e){
        e.preventDefault();

        const data = {
            phoneNumber: $(this).find('[name="phoneNumber"]').val(),
            email: $(this).find('[name="email"]').val(),
            passportNumber: $(this).find('[name="passportNumber"]').val(),
            birthDate: $(this).find('[name="birthDate"]').val(),
            firstName: $(this).find('[name="firstName"]').val(),
            middleName: $(this).find('[name="middleName"]').val(),
            lastName: $(this).find('[name="lastName"]').val(),
            sex: $(this).find('[name="sex"]').val(),
            role: $(this).find('[name="role"]').val(),
            password: $(this).find('[name="password"]').val()
        }

        $.ajax({
            url: '/api/admin/passenger',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            headers: {
                "X-XSRF-TOKEN": $.cookie('XSRF-TOKEN')
            },

            xhrFields: {
                withCredentials: true
            },

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