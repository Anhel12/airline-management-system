$(function (){
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
                code: $(this).find('[name$=".code"]').val(),
                name: $(this).find('[name$=".name"]').val(),
                city: $(this).find('[name$=".city"]').val(),
                country: $(this).find('[name$=".country"]').val(),
                timezone: $(this).find('[name$=".timezone"]').val()
            };

            items.push(item);
        })
        console.log(items)
        $.ajax({
            url: '/api/admin/airport',
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
            code: $(this).find('[name="code"]').val(),
            name: $(this).find('[name="name"]').val(),
            city: $(this).find('[name="city"]').val(),
            country: $(this).find('[name="country"]').val(),
            timezone: $(this).find('[name="timezone"]').val()
        }

        $.ajax({
            url: '/api/admin/airport',
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