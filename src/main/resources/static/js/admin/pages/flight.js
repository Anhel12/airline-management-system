$(function (){
    flatpickr(".date", {
        altInput: true,
        altFormat: "d.m.Y H:i",
        dateFormat: "Y-m-d H:i",
        enableTime: "true",
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
                flightNumber: $(this).find('[name$=".flightNumber"]').val(),
                departureDateTime: $(this).find('[name$=".departureDateTime"]').val(),
                arrivalDateTime: $(this).find('[name$=".arrivalDateTime"]').val(),
                departureAirportId: $(this).find('[name$=".departureAirportId"]').val(),
                arrivalAirportId: $(this).find('[name$=".arrivalAirportId"]').val()
            };

            items.push(item);
        })
        console.log(items)

        $.ajax({
            url: '/api/admin/flight',
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
            flightNumber: $(this).find('[name="flightNumber"]').val(),
            departureDateTime: $(this).find('[name="departureDateTime"]').val(),
            arrivalDateTime: $(this).find('[name="arrivalDateTime"]').val(),
            departureAirportId: $(this).find('[name="departureAirportId"]').val(),
            arrivalAirportId: $(this).find('[name="arrivalAirportId"]').val()
        }

        $.ajax({
            url: '/api/admin/flight',
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